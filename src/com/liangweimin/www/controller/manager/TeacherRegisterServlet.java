package com.liangweimin.www.controller.manager;

import com.liangweimin.www.po.Teacher;
import com.liangweimin.www.service.ManagerService;
import com.liangweimin.www.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 梁伟民
 */
@WebServlet(name = "TeacherRegisterServlet", urlPatterns = "/TeacherRegisterServlet")
public class TeacherRegisterServlet extends HttpServlet {
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
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String sex = request.getParameter("sex");
            String college = request.getParameter("college");
            String phone = request.getParameter("phone");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");

            if (name != null && password1 != null && password1.equals(password2)) {

                //将密码进行加密
                String encrypt = Md5Util.md5Encrypt(password1);
                //封装
                Teacher teacher = new Teacher(id, name, encrypt,sex,college,phone);

                //调用service层的方法
                ManagerService managerService = new ManagerService();
                boolean success = managerService.teacherRegister(teacher);

                //注册请求成功
                if (success) {
                    writer.println("注册成功!");
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

