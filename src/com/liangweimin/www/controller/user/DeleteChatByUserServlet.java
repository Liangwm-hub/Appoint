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
@WebServlet(name = "DeleteChatByUserServlet", urlPatterns = "/DeleteChatByUserServlet")
public class DeleteChatByUserServlet extends HttpServlet {
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
        UserService userService = new UserService();
        boolean success = userService.deleteChat(chatId);

        if (success){
            response.sendRedirect("FindAllChatByUserServlet");
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("删除失败!");
        }

    }
}
