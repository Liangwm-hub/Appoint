package com.liangweimin.www.controller.manager;

import com.liangweimin.www.po.Manager;
import com.liangweimin.www.service.ManagerService;
import com.liangweimin.www.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 管理员登录
 *
 * @author 梁伟民
 */
@WebServlet(name = "ManagerLoginServlet", urlPatterns = "/manager/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //输入的验证码
        String checkCode = request.getParameter("checkCode");
        //真正的验证码
        String imgCheckCode = (String) request.getSession().getAttribute("checkCode");

        //判断验证码是否正确
        if (imgCheckCode.equals(checkCode)) {

            //获得数据
            int managerId = Integer.parseInt(request.getParameter("managerId"));
            String password = request.getParameter("password");
            //将密码进行加密
            String encrypt = Md5Util.md5Encrypt(password);

            //封装
            Manager manager = new Manager(managerId, encrypt);

            //调用service层的方法
            ManagerService managerService = new ManagerService();
            boolean success = managerService.login(manager);

            //登录成功与否的操作
            if (success) {
                //把managerId存进session
                HttpSession session = request.getSession();
                session.setAttribute("managerId", managerId);

                //转发到显示预约界面的servlet
                response.sendRedirect("AllRequestsServlet");
            } else {
                //重新登录
                response.sendRedirect(request.getContextPath() + "/view/manager/managerLogin.jsp");
            }

        } else {
            //验证码不正确
            response.sendRedirect(request.getContextPath() + "/view/manager/managerLogin.jsp");
        }
    }
}
