package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.ChatRoom;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查找所有聊天
 *
 * @author 梁伟民
 */
@WebServlet(name = "FindAllChatByUserServlet", urlPatterns = "/user/FindAllChatByUserServlet")
public class FindAllChatByUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获得学生学号
        int userSno = (int) request.getSession().getAttribute("sno");

        //调用service
        UserService userService = new UserService();
        List<ChatRoom> chatRooms = userService.findAllChat(userSno);

        //把集合存入request
        request.setAttribute("chatRooms", chatRooms);

        //请求转发
        request.getRequestDispatcher("/view/user/chat_list.jsp").forward(request, response);
    }
}
