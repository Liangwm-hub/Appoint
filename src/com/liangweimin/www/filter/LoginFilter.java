//package com.liangweimin.www.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * 防止恶意登录
// * @author 梁伟民
// */
//@WebFilter("/*")
//public class LoginFilter implements Filter {
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        //强制转换
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) resp;
//
//        //1.获取资源请求路径
//        String uri = request.getRequestURI();
//
//        //2.判断是否包含登录/注册相关资源路径,同时要排除 css/js/图片/验证码等资源
//        if (uri.contains("/userLogin.jsp") || uri.contains("/UserLoginServlet") || uri.contains("/teacherLogin.jsp") || uri.contains("/TeacherLoginServlet") || uri.contains("/managerLogin.jsp") || uri.contains("/ManagerLoginServlet")
//                || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/font/") || uri.contains("/img.jsp")
//                || uri.contains("/index.jsp") || uri.equals("/Appoint__war_exploded/") || uri.contains("UserRegisterServlet") || uri.contains("userRegister.jsp")) {
//            //包含,就是想登录,放行
//            chain.doFilter(req, resp);
//        } else {
//            //不包含,需要验证用户是否已经登陆
//
//            //3.从session获取信息
//            HttpSession session = request.getSession();
//
//            //获取user,teacher,manager 特有的编号
//            Object sno1 = session.getAttribute("sno");
//            Object id1 = session.getAttribute("id");
//            Object managerId1 = session.getAttribute("managerId");
//
//            //user
//            int sno = (sno1 == null) ? 0 : (int) sno1;
//            //teacher
//            int id = (id1 == null) ? 0 : (int) id1;
//            //manager
//            int managerId = (managerId1 == null) ? 0 : (int) managerId1;
//
//            if (sno != 0 || id != 0 || managerId != 0) {
//                //已经登陆,放行
//                chain.doFilter(req, resp);
//            } else {
//                //没有登录,则跳转登录页面
//                response.sendRedirect("index.jsp");
//            }
//
//        }
//
//    }
//
//    @Override
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//}
