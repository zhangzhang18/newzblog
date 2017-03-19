package com.zblog.controller;

import com.zblog.model.Article;
import com.zblog.model.Discuss;
import com.zblog.model.User;
import com.zblog.service.DiscussService;
import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
@Controller
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    DiscussService discussService;

    @RequestMapping("/addDiscuss.do")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("discuss/adddiscuss");
        //  mav.addObject("articles",articles);
        return mav;
    }

    @RequestMapping(value = "/addDiscuss.do", method = RequestMethod.POST)
    public String adddiscuss(Discuss discuss, HttpServletRequest request, HttpServletResponse response) {
        User nowuser = UserUtil.getUser(request);
        if (nowuser != null) {
            discuss.setVistor(nowuser.getUserid());
            discuss.setDiscussDatetime(new Date());
            discuss.setReply(-1);
            discuss.setIsshow(1);
            discuss.setWcount(0);
            discussService.insertDiscuss(discuss);
            return "redirect:/welcome/discuss.do";
        }else {
            return "redirect:/welcome/login.do";
        }
    }
    @RequestMapping(value = "/addReply.do", method = RequestMethod.POST)
    public String addreply(Discuss discuss, HttpServletRequest request, HttpServletResponse response) {
        User nowuser = UserUtil.getUser(request);
        String id=request.getParameter("discussid");
        if (nowuser != null) {
            discuss.setReply(Integer.parseInt(id));
            discuss.setReplyuserid(nowuser.getUserid());
            discuss.setDiscussDatetime(new Date());
            discuss.setIsshow(1);
            discuss.setWcount(0);
            discussService.insert(discuss);
            return "redirect:/welcome/discussdetail.do?discussid="+id;
        }else {
            return "redirect:/welcome/login.do";
        }
    }
}
