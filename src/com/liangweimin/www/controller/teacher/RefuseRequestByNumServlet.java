package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 导师拒绝学生的预约
 * @author 梁伟民
 */
@WebServlet(name = "RefuseRequestByNumServlet",urlPatterns = "/teacher/RefuseRequestByNumServlet")
public class RefuseRequestByNumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取num(预约的编号)
        int num = Integer.parseInt(request.getParameter("num"));

        //2.调用service批准
        TeacherService teacherService = new TeacherService();
        teacherService.refuseRequest(num);

        //3.跳转回到所有请求的页面
        response.sendRedirect("ProcessRequestServlet");
    }
}
