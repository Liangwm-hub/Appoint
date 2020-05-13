package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.po.Release;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查询对应的预约
 * @author 梁伟民
 */
@WebServlet(name = "QueryReleaseServlet",urlPatterns = "/teacher/QueryReleaseServlet")
public class QueryReleaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //获得数据
        int id = Integer.parseInt(request.getParameter("id"));
        String appointTime = request.getParameter("appointTime");
        String place = request.getParameter("place");

        //把 id appointTime place 封装到一个对象里
        Release release = new Release(id, appointTime, place);

        //转发到updateRelease进行展示
        request.setAttribute("release", release);
        request.getRequestDispatcher("/view/teacher/updateRelease.jsp").forward(request, response);
    }
}
