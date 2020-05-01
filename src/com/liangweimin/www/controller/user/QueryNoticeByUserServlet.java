package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.Notice;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁伟民
 */
@WebServlet(name = "QueryNoticeByUserServlet", urlPatterns = "/QueryNoticeByUserServlet")
public class QueryNoticeByUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        //获得数据id
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));

        //调用service查找通知
        UserService userService = new UserService();
        Notice notice = userService.queryNoticeById(noticeId);

        //存入request
        request.setAttribute("notice",notice);

        //请求转发
        request.getRequestDispatcher("user_notice_content.jsp").forward(request,response);
    }
}
