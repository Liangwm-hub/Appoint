package com.liangweimin.www.controller.user;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.po.Release;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 搜索老师发布的预约
 * @author 梁伟民
 */
@WebServlet(name = "SearchByKeywordsServlet", urlPatterns = "/SearchByKeywordsServlet")
public class SearchByKeywordsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //1.获取参数

        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页显示记录的条数
        String rows = request.getParameter("rows");

        //如果直接访问FindUserByPageServlet，默认currentPage="1"，rows="5"
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || rows.equals("")) {
            rows = "5";
        }

        //获得关键字
        String keywords = request.getParameter("keywords");
        //把keywords存进session中
        HttpSession session = request.getSession();

        if (keywords != null) {
            session.setAttribute("keywords", keywords);
        } else {
            //换页时keywords为空，需要取出session中的keywords
            keywords = (String) session.getAttribute("keywords");
        }


        //2.调用service查询
        UserService userService = new UserService();
        PageBean<Release> pb = userService.searchReleaseByPage(keywords, currentPage, rows);

        //3.将PageBean存入request
        request.setAttribute("pb", pb);

        //4.转发到releaseList.jsp
        request.getRequestDispatcher("searchRelease.jsp").forward(request, response);

    }
}
