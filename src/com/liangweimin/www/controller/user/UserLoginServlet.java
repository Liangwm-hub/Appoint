package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.User;
import com.liangweimin.www.service.UserService;
import com.liangweimin.www.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 梁伟民
 */
@WebServlet(name = "UserLoginServlet", urlPatterns = "/user/UserLoginServlet")
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
            //将密码进行加密
            String encrypt = Md5Util.md5Encrypt(password);

            //封装
            User user = new User(sno, encrypt);

            //调用service层的方法
            UserService userService = new UserService();
            boolean success = userService.login(user);

            //登录成功
            if (success) {

                //把导师 sno 存进 session
                HttpSession session = request.getSession();
                session.setAttribute("sno",sno);

                //进入发布预约界面的servlet
                request.getRequestDispatcher("FindReleaseByUserServlet").forward(request,response);
            } else {
                //重新登录
                response.sendRedirect("/view/user/userLogin.jsp");
            }

        } else {
            //验证码不正确
            response.sendRedirect("/view/user/userLogin.jsp");
        }
    }
}
