package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.po.Release;
import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 导师修改预约
 * @author 梁伟民
 */
@WebServlet(name = "UpdateReleaseServlet",urlPatterns = "/UpdateReleaseServlet")
public class UpdateReleaseServlet extends HttpServlet {
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
        String oldAppointTime = request.getParameter("oldAppointTime");
        String oldPlace = request.getParameter("oldPlace");
        String appointTime = request.getParameter("appointTime");
        String place = request.getParameter("place");

        //封装要修改的内容(见面时间和地点)
        Release release = new Release(appointTime, place);

        //调用service修改预约
        TeacherService teacherService = new TeacherService();
        boolean success = teacherService.updateRelease(id,oldAppointTime,oldPlace,release);

        if (success){
            response.sendRedirect("FindReleaseByPageServlet");
        }else {
            PrintWriter printWriter = response.getWriter();
            printWriter.println("修改失败!请检查您的输入!");
        }
    }
}
