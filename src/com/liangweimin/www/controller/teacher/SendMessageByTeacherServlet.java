package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.po.ChatMessage;
import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 梁伟民
 */
@WebServlet(name = "SendMessageByTeacherServlet", urlPatterns = "/SendMessageByTeacherServlet")
public class SendMessageByTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        //获得聊天编号,导师学生姓名
        int chatId = Integer.parseInt(request.getParameter("chatId"));
        String teacherName = request.getParameter("teacherName");
        String userName = request.getParameter("userName");

        //获得消息内容
        String messageContent = request.getParameter("messageContent");

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(date);

        //设置身份为学生
        String senderIdentity = "导师";

        //封装
        ChatMessage chatMessage = new ChatMessage(chatId, messageContent, "文字", teacherName, userName, senderIdentity, currentTime);

        //调用service
        TeacherService teacherService = new TeacherService();
        teacherService.sendMessage(chatMessage);

    }
}
