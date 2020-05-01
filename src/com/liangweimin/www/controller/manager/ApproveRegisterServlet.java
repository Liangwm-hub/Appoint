package com.liangweimin.www.controller.manager;

import com.liangweimin.www.service.ManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 批量批准用户注册
 * @author 梁伟民
 */
@WebServlet(name = "ApproveRegisterServlet",urlPatterns = "/ApproveRegisterServlet")
public class ApproveRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有的snos(用户的学号数组)
        String[] snos = request.getParameterValues("sno");

        //2.调用service批准
        if (snos != null) {
            ManagerService managerService = new ManagerService();
            managerService.processUserRegister(snos);
        }

        //3.跳转回到所有注册的页面
        response.sendRedirect("FindRegisterByPageServlet");
    }

}
