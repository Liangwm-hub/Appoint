package com.liangweimin.www.view.teacher;

import com.liangweimin.www.po.Release;
import com.liangweimin.www.po.Teacher;
import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 导师发布预约
 * @author 梁伟民
 */
@WebServlet(name = "SetAppointmentServlet", urlPatterns = "/SetAppointmentServlet")
public class SetAppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获得数据
        int id = (int) request.getSession().getAttribute("id");
        String appointTime = request.getParameter("appointTime");
        String place = request.getParameter("place");

        //通过id查找导师
        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.queryTeacherById(id);

        //封装,调用service发布预约
        Release release = new Release(id, teacher.getName(), teacher.getSex(), teacher.getCollege(), teacher.getPhone(), appointTime, place,teacher.getScope());
        boolean success = teacherService.setAppointment(release);

        if (success){
            response.sendRedirect("FindReleaseByPageServlet");
        }else {
            PrintWriter printWriter = response.getWriter();
            printWriter.println("发布失败!请检查您的输入");
        }
    }
}
