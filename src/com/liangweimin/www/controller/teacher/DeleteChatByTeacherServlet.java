package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.service.TeacherService;

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
@WebServlet(name = "DeleteChatByTeacherServlet", urlPatterns = "/DeleteChatByTeacherServlet")
public class DeleteChatByTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获得数据
        int chatId = Integer.parseInt(request.getParameter("chatId"));

        //调用service删除
        TeacherService teacherService = new TeacherService();
        boolean success = teacherService.deleteChat(chatId);

        if (success){
            response.sendRedirect("FindAllChatByTeacherServlet");
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("删除失败!");
        }
    }
}
