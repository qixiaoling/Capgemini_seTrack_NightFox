package com.capgemini.NightFox.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//
//@Component
//public class MyCorsFilter {
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
////        response.setHeader("Access-Control-Expose-Headers", "authorization");
//        filterChain.doFilter(servletRequest, res);
//    }
//
//    public void destroy() {
//    }
//}
