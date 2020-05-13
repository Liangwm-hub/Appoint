package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.User;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户修改个人信息
 * @author 梁伟民
 */
@WebServlet(name = "UpdateUserServlet",urlPatterns = "/user/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        //获得数据
        int sno = Integer.parseInt(request.getParameter("sno"));
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String majorClass = request.getParameter("majorClass");
        String phone = request.getParameter("phone");

        //封装
        User user = new User(sno, name, sex, majorClass, phone);
        //调用业务逻辑层 修改
        UserService userService = new UserService();
        boolean result = userService.updateUser(sno, user);


        if (result) {
            //修改成功,转发到原来的页面
            request.setAttribute("user", user);
            request.getRequestDispatcher("/view/user/updateUser.jsp").forward(request, response);
        } else {
            //修改失败，提示修改失败
            PrintWriter writer = response.getWriter();
            writer.println("修改失败!");
        }
    }
}
