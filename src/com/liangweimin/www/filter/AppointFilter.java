package com.liangweimin.www.filter;

import com.liangweimin.www.po.User;
import com.liangweimin.www.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 学生必须完善身份信息,才能预约
 * @author 梁伟民
 */
@WebFilter(filterName = "AppointFilter", value = "/AppointServlet")
public class AppointFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取预约学生的学号
        int sno = (int) request.getSession().getAttribute("sno");

        UserService userService = new UserService();
        User user = userService.queryUserBySno(sno);

        //身份信息已经完善则放行
        if (user.getSex() != null && user.getMajorClass() != null && user.getPhone() != null && (!"".equals(user.getSex())) && (!"".equals(user.getMajorClass())) && (!"".equals(user.getPhone())) ) {
            chain.doFilter(req, resp);
        } else {
            //否则,跳转到完善身份信息的界面
            response.sendRedirect("QueryUserBySnoServlet?sno=" + sno);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
