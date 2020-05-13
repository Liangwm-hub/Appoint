package com.liangweimin.www.controller.manager;

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
@WebServlet(name = "FindIllegalUserServlet",urlPatterns = "/manager/FindIllegalUserServlet")
public class FindIllegalUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //获得sno
        int sno = Integer.parseInt(request.getParameter("sno"));

        //获得对应学号的 User对象
        UserService userService = new UserService();
        User user = userService.queryUserBySno(sno);

        //请求转发到updateUser进行展示
        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/manager/freezeUser.jsp").forward(request,response);
    }
}
