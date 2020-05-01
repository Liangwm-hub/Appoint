package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 批量批准选中的请求
 * @author 梁伟民
 */
@WebServlet(name = "ProcessSelectedServlet", urlPatterns = "/ProcessSelectedServlet")
public class ProcessSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有的num(预约的编号)
        String[] nums = request.getParameterValues("num");

        //2.调用service批准
        if (nums != null) {
            TeacherService teacherService = new TeacherService();
            teacherService.processSelected(nums);
        }

        //3.跳转回到所有请求的页面
        response.sendRedirect("ProcessRequestServlet");
    }
}
