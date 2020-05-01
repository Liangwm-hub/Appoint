package com.liangweimin.www.controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liangweimin.www.po.ChatMessage;
import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 梁伟民
 */
@WebServlet(name = "GetNewByTeacherServlet", urlPatterns = "/GetNewByTeacherServlet")
public class GetNewByTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取finalMessageId,chatId
        int finalMessageId = Integer.parseInt(request.getParameter("finalMessageId"));
        int chatId = Integer.parseInt(request.getParameter("chatId"));

        //2.根据finalMessageId获取新聊天记录内容
        TeacherService teacherService = new TeacherService();
        List<ChatMessage> newMessage = teacherService.getNewMessage(finalMessageId,chatId);

        //3.转换为JSON字符串返回给浏览器
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newMessage);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
    }
}
