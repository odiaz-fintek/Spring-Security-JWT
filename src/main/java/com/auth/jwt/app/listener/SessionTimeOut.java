//package com.auth.jwt.app.listener;
//
//import org.springframework.stereotype.Component;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
//@Component
//public class SessionTimeOut implements HttpSessionListener {
//
//    @Override
//    public void sessionCreated(HttpSessionEvent event) {
//        event.getSession().setMaxInactiveInterval(10); // 5 minutes
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent event) {
//        // Optional: Perform cleanup tasks when session is destroyed
//    }
//
//}
