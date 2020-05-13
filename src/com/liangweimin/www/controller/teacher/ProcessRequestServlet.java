package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.po.Appoint;
import com.liangweimin.www.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 导师处理请求
 * @author 梁伟民
 */
@WebServlet(name = "ProcessRequestServlet",urlPatterns = "/teacher/ProcessRequestServlet")
public class ProcessRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //1.获取参数
        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页显示记录的条数
        String rows = request.getParameter("rows");

        //如果直接访问FindUserByPageServlet，默认currentPage="1"，rows="10"
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null|| "".equals(rows)){
            rows="10";
        }

        //从session中取出id
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        //2.调用service查询
        TeacherService teacherService = new TeacherService();
        PageBean<Appoint> pb = teacherService.findRequestByPage(id, currentPage, rows);

        //3.将PageBean存入request
        request.setAttribute("pb",pb);

        //4.转发到processRequest.jsp
        request.getRequestDispatcher("/view/teacher/processRequest.jsp").forward(request,response);

    }
}
