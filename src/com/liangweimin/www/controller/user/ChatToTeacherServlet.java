package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.ChatRoom;
import com.liangweimin.www.po.Teacher;
import com.liangweimin.www.po.User;
import com.liangweimin.www.service.TeacherService;
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
@WebServlet(name = "ChatToTeacherServlet", urlPatterns = "/ChatToTeacherServlet")
public class ChatToTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获得数据
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int userSno = Integer.parseInt(request.getParameter("sno"));

        //获取学生和导师的信息
        UserService userService = new UserService();
        User user = userService.queryUserBySno(userSno);

        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.queryTeacherById(teacherId);

        //封装
        ChatRoom chatRoom = new ChatRoom(teacherId, teacher.getName(), userSno, user.getName());
        //创建对应的聊天室
        userService.createChatRoom(chatRoom);

        //跳转到所有聊天页面
        response.sendRedirect("FindAllChatByUserServlet");

    }
}
