package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.User;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁伟民
 */
@WebServlet(name = "QueryUserBySnoServlet",urlPatterns = "/user/QueryUserBySnoServlet")
public class QueryUserBySnoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //获得sno
        int sno = (int) request.getSession().getAttribute("sno");

        //获得对应学号的 User对象
        UserService userService = new UserService();
        User user = userService.queryUserBySno(sno);

        //请求转发到updateUser进行展示
        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/user/updateUser.jsp").forward(request, response);

    }
}
