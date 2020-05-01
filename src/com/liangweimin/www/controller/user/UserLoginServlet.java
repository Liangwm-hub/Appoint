package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.User;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 学生用户登录
 * @author 梁伟民
 */
@WebServlet(name = "UserLoginServlet", urlPatterns = "/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
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
            int sno = Integer.parseInt(request.getParameter("sno"));
            String password = request.getParameter("password");

            //封装
            User user = new User(sno, password);

            //调用service层的方法
            UserService userService = new UserService();
            boolean success = userService.login(user);

            //登录成功与否的操作
            if (success) {
                //把sno存进session
                HttpSession session = request.getSession();
                session.setAttribute("sno", sno);

                //转发到显示预约界面的servlet
                request.getRequestDispatcher("FindReleaseByUserServlet").forward(request, response);
            } else {
                //重新登录
                response.sendRedirect("userLogin.jsp");
            }

        } else {
            //验证码不正确
            response.sendRedirect("userLogin.jsp");
        }
    }
}
