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
@WebServlet(name = "ProcessSelectedByManagerServlet", urlPatterns = "/ProcessSelectedByManagerServlet")
public class ProcessSelectedByManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有的num(预约的编号)
        String[] nums = request.getParameterValues("num");

        //2.调用service批准
        if (nums != null) {
            ManagerService managerService = new ManagerService();
            managerService.processSelected(nums);
        }

        //3.跳转回到所有请求的页面
        response.sendRedirect("AllRequestsServlet");
    }
}
