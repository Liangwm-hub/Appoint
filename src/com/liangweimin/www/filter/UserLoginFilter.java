package com.liangweimin.www.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 梁伟民
 */
@WebFilter(filterName = "UserLoginFilter",value ={"/view/user/*","/user/*"})
public class UserLoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //1.获取资源请求路径
        String uri = request.getRequestURI();

        //2.判断是否包含登录/注册相关资源路径,同时要排除 css/js/图片/验证码等资源
        if (uri.contains("/userLogin.jsp") || uri.contains("/UserLoginServlet") || uri.contains("/css/") ||
                uri.contains("/js/") || uri.contains("/font/") || uri.contains("/img.jsp") ||
                uri.contains("/index.jsp") || "/Appoint__war_exploded/".equals(uri) || uri.contains("/set_password.jsp") ||
                uri.contains("UserRegisterServlet") || uri.contains("userRegister.jsp")) {
            //包含,就是想登录,放行
            chain.doFilter(req, resp);
        } else {
            //不包含,需要验证用户是否已经登陆

            //3.从session获取信息
            HttpSession session = request.getSession();

            //获取 user 特有的编号
            Object id = session.getAttribute("sno");

            int sno = (id == null) ? 0 : (int) id;

            if (sno != 0) {
                //已经登陆,放行
                chain.doFilter(req, resp);
            } else {
                //没有登录,则跳转登录页面
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
