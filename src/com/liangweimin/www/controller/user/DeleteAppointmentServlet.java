package com.liangweimin.www.controller.user;

import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 梁伟民
 */
@WebServlet(name = "DeleteAppointmentServlet",urlPatterns = "/DeleteAppointmentServlet")
public class DeleteAppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获得数据
        int num = Integer.parseInt(request.getParameter("num"));

        //调用service取消预约
        UserService userService = new UserService();
        boolean success = userService.deleteAppointment(num);

        //取消成功
        if (success){
            response.sendRedirect("MyAppointmentServlet");
        }else {
            PrintWriter writer = response.getWriter();
            writer.println("取消失败!");
        }

    }
}
