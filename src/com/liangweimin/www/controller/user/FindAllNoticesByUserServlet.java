package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.Notice;
import com.liangweimin.www.service.UserService;

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
@WebServlet(name = "FindAllNoticesByUserServlet", urlPatterns = "/user/FindAllNoticesByUserServlet")
public class FindAllNoticesByUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service层
        UserService userService = new UserService();
        List<Notice> notices = userService.findAllNotices();

        //存入request
        request.setAttribute("notices",notices);

        //请求转发
        request.getRequestDispatcher("/view/user/readNoticesByUser.jsp").forward(request,response);
    }
}
