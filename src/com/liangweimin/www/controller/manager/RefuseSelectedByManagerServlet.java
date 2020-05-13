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
@WebServlet(name = "RefuseSelectedByManagerServlet",urlPatterns = "/manager/RefuseSelectedByManagerServlet")
public class RefuseSelectedByManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取num(预约的编号)
        int num = Integer.parseInt(request.getParameter("num"));

        //2.调用service批准
        ManagerService managerService = new ManagerService();
        managerService.refuseRequest(num);

        //3.跳转回到所有请求的页面
        response.sendRedirect("AllRequestsServlet");
    }
}
