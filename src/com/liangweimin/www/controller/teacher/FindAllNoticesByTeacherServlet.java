package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.po.Notice;
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
@WebServlet(name = "FindAllNoticesByTeacherServlet", urlPatterns = "/teacher/FindAllNoticesByTeacherServlet")
public class FindAllNoticesByTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service层
        TeacherService teacherService = new TeacherService();
        List<Notice> notices = teacherService.findAllNotices();

        //存入request
        request.setAttribute("notices",notices);

        //请求转发
        request.getRequestDispatcher("/view/teacher/readNoticesByTeacher.jsp").forward(request,response);
    }
}
