package com.liangweimin.www.controller.manager;

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
@WebServlet(name = "RefuseRegisterServlet",urlPatterns = "/manager/RefuseRegisterServlet")
public class RefuseRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获得参数
        int sno = Integer.parseInt(request.getParameter("sno"));

        //调用service
        ManagerService managerService = new ManagerService();
        managerService.refuseUserRegister(sno);

        //跳转回到所有注册的页面
        response.sendRedirect("FindRegisterByPageServlet");
    }
}
