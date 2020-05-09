package com.liangweimin.www.controller.teacher;

import com.liangweimin.www.service.TeacherService;
import com.liangweimin.www.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 导师设置预约期限
 * @author 梁伟民
 */
@WebServlet(name = "AppointScopeServlet", urlPatterns = "/AppointScopeServlet")
public class AppointScopeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        //获得设置的可预约时间范围
        String scope1 = request.getParameter("scope");

        //获取session中的id
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        //scope不为空
        if ( isNumber(scope1) ) {
            //转成int类型
            int scope = Integer.parseInt(scope1);
            //必须在1~100之间
            if (scope >= Constant.APPOINT_MIN_SCOPE && scope <= Constant.APPOINT_MAX_SCOPE) {
                //调用service修改scope
                TeacherService teacherService = new TeacherService();
                boolean success = teacherService.updateScope(id, scope);

                if (success) {
                    //返回
                    response.sendRedirect("FindReleaseByPageServlet");
                } else {
                    PrintWriter writer = response.getWriter();
                    writer.println("修改失败!");
                }
            } else {
                PrintWriter writer = response.getWriter();
                writer.println("修改失败,范围只能在1~100间!");
            }
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("修改失败,请检查您的输入!");
        }
    }


    /**
     * 一个用来判断输入字符串是否为数字的方法
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {

            //isDigit(char ch)判断字符是否是数字
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }

        }
        //str不能为""
        return !"".equals(str);
    }
}
