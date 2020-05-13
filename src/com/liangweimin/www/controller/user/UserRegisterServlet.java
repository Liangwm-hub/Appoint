package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.User;
import com.liangweimin.www.service.UserService;
import com.liangweimin.www.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 学生注册
 *
 * @author 梁伟民
 */
@WebServlet(name = "UserRegisterServlet", urlPatterns = "/user/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        //输入的验证码
        String checkCode = request.getParameter("checkCode");
        //真正的验证码
        String imgCheckCode = (String) request.getSession().getAttribute("checkCode");

        PrintWriter writer = response.getWriter();

        //判断验证码是否正确
        if (imgCheckCode.equals(checkCode)) {

            //获得数据
            int sno = Integer.parseInt(request.getParameter("sno"));
            String name = request.getParameter("name");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");

            if (name != null && password1 != null && password1.equals(password2)) {

                //将密码进行加密
                String encrypt = Md5Util.md5Encrypt(password1);
                //封装
                User user = new User(sno, name, encrypt);

                //调用service层的方法
                UserService userService = new UserService();
                boolean success = userService.userRegister(user);

                //注册请求成功
                if (success) {
                    writer.println("注册请求成功，请等待管理员的审核！");
                } else {
                    writer.println("注册失败，请检查您的输入！");
                }
            } else {
                writer.println("注册失败，请检查您的输入！");
            }
        } else {
            //验证码不正确
            writer.println("注册失败，请检查您的验证码！");
        }
    }
}
