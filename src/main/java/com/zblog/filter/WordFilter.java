package com.zblog.filter;
import com.zblog.util.WordsUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2017/3/19.
 */
public class WordFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //在过滤器中用装饰模式把 原装request的功能增强了
        //---拦截后台调用的getParamter()方法
        MyRequest req = new MyRequest((HttpServletRequest)request);

        filterChain.doFilter(req, response);//放行
    }

    public void destroy() {

    }
}
class MyRequest extends HttpServletRequestWrapper{
    public MyRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String content) {
        String str = super.getParameter("content");
        System.err.println(str);
        List<String> list = WordsUtil.getWords();
        for(String word : list){
            str = str.replaceAll(word, "*");
        }
        System.err.println(str);
        return str;
    }



}