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
 * 私聊时询问是否有新的消息
 * @author 梁伟民
 */
@WebServlet(name = "AskByTeacherServlet", urlPatterns = "/teacher/AskByTeacherServlet")
public class AskByTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求参数：finalMessageId,chatId
        String finalMessageId = request.getParameter("finalMessageId");
        String chatId = request.getParameter("chatId");

        //2.根据finalMessageId查询是否存在最新的聊天记录
        TeacherService teacherService = new TeacherService();
        boolean hasNew = teacherService.hasNew(finalMessageId,chatId);

        System.out.println(hasNew);

        //3.将布尔类型的返回值以Ajax响应的形式返回给浏览器
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(hasNew+"");
    }
}
