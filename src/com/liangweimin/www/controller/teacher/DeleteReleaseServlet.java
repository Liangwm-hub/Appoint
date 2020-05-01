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
 * 导师删除预约
 * @author 梁伟民
 */
@WebServlet(name = "DeleteReleaseServlet",urlPatterns = "/DeleteReleaseServlet")
public class DeleteReleaseServlet extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
        String appointTime = request.getParameter("appointTime");
        String place = request.getParameter("place");

        //调用service删除预约
        TeacherService teacherService = new TeacherService();
        boolean success = teacherService.deleteRelease(id,appointTime,place);

        if (success){
            response.sendRedirect("FindReleaseByPageServlet");
        }else {
            PrintWriter printWriter = response.getWriter();
            printWriter.println("删除失败!");
        }
    }
}
