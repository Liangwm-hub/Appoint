package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.po.ChatRoom;
import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 梁伟民
 */
@WebServlet(name = "FindAllChatByTeacherServlet", urlPatterns = "/teacher/FindAllChatByTeacherServlet")
public class FindAllChatByTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获得学生学号
        int teacherId = (int) request.getSession().getAttribute("id");

        //调用service
        TeacherService teacherService = new TeacherService();
        List<ChatRoom> chatRooms = teacherService.findAllChat(teacherId);

        //把集合存入request
        request.setAttribute("chatRooms", chatRooms);

        //请求转发
        request.getRequestDispatcher("/view/teacher/chat_list.jsp").forward(request, response);

    }
}
