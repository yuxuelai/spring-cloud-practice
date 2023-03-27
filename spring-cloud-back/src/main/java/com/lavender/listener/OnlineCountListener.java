package com.lavender.listener;


// 监听器实现接口 HttpListener

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener("OnlineCount")
public class OnlineCountListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext =se.getSession ().getServletContext ();

        Integer onlineCount = (Integer) servletContext.getAttribute ("OnlineCount");

        if(onlineCount==null){

            onlineCount = 1;
        }else{

            int count = onlineCount;

            onlineCount= count + 1;

        }
        servletContext.setAttribute ("OnlineCount",onlineCount);


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        // 销毁session
        ServletContext servletContext = se.getSession ().getServletContext ();

        Integer onlineCount = (Integer) servletContext.getAttribute ("OnlineCount");

        if(onlineCount==null){

            onlineCount= 1;

        }else{

            int count = onlineCount;

            onlineCount = count - 1;

        }

        servletContext.setAttribute ("OnlineCount",onlineCount);




    }
}
