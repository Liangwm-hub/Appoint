package com.liangweimin.www.controller.user;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.po.Release;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 学生分页查询老师发布的预约
 * @author 梁伟民
 */
@WebServlet(name = "FindReleaseByUserServlet", urlPatterns = "/user/FindReleaseByUserServlet")
public class FindReleaseByUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 当前页码
        String currentPage = request.getParameter("currentPage");
        //每页显示记录的条数
        String rows = request.getParameter("rows");

        //如果直接访问FindUserByPageServlet，默认currentPage="1"，rows="5"
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        //2.调用service查询
        UserService userService = new UserService();
        PageBean<Release> pb = userService.findReleaseByPage(currentPage, rows);

        //3.将PageBean存入request
        request.setAttribute("pb", pb);

        //4.转发到appoint.jsp
        request.getRequestDispatcher("/view/user/appoint.jsp").forward(request, response);

    }

}

