package com.liangweimin.www.view.manager;

import com.liangweimin.www.service.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁伟民
 */
@WebServlet(name = "UnsealUserServlet",urlPatterns = "/UnsealUserServlet")
public class UnsealUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获得数据(学号)
        int sno = Integer.parseInt(request.getParameter("sno"));

        //调用service冻结
        ManagerService managerService = new ManagerService();
        managerService.unsealUser(sno);

        //跳转回所有请求的页面
        response.sendRedirect("FindFreezeUserByPageServlet");

    }
}
