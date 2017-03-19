package com.zblog.controller;

import com.zblog.common.page.Pagination;
import com.zblog.common.page.SimplePage;
import com.zblog.model.Message;
import com.zblog.model.User;
import com.zblog.service.MessageService;
import com.zblog.util.Constants;
import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @RequestMapping(value = "/addMessage.do",method = RequestMethod.POST)
    public String addMessage(Message message, HttpServletRequest request, HttpServletResponse response, Model model){
        User nowuser  = UserUtil.getUser(request);
        if(nowuser!=null) {
            message.setFromuserid(nowuser.getUserid());
            if(message.getTouserid()==null){
                message.setTouserid(0);
            }
            message.setId(1);
            message.setDatetime(new Date());
            int i= messageService.insert(message);
            return "redirect:/welcome/message.do";
        }else {
            message.setFromuserid(0);
            message.setTouserid(0);
            message.setDatetime(new Date());
            int i= messageService.insert(message);
            return "redirect:/welcome/message.do";
        }
    }
    @RequestMapping( "/myMessage.do")
    public String myMessage(Message message, HttpServletRequest request, HttpServletResponse response, Model model,Integer pageNo){
        User nowuser  = UserUtil.getUser(request);
        if(nowuser!=null) {
            Pagination pagination = this.messageService.getPage(
                    SimplePage.cpn(pageNo), Constants.PAGE_SIZE, nowuser.getUserid());
            model.addAttribute("pagination", pagination);
            model.addAttribute("pagination", pagination);
            return "message/message";
        }else {
            return "redirect:/welcome/login.do";
        }
    }

}
