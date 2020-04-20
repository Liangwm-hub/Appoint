package com.liangweimin.www.view.manager;

import com.liangweimin.www.service.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 梁伟民
 */
@WebServlet(name = "DeleteNoticeByIdServlet", urlPatterns = "/DeleteNoticeByIdServlet")
public class DeleteNoticeByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获得id
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));

        //调用service删除
        ManagerService managerService = new ManagerService();
        boolean success = managerService.deleteNoticeById(noticeId);

        if (success){
            response.sendRedirect("FindAllNoticesByManagerServlet");
        }else {
            PrintWriter writer = response.getWriter();
            writer.println("删除失败!");
        }
    }
}
