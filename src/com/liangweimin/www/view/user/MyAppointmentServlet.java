package com.liangweimin.www.view.user;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.po.Appoint;
import com.liangweimin.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 显示用户的预约
 * @author 梁伟民
 */
@WebServlet(name = "MyAppointmentServlet",urlPatterns = "/MyAppointmentServlet")
public class MyAppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //1.获取参数
        // 当前页码
        String currentPage = request.getParameter("currentPage");
        //每页显示记录的条数
        String rows = request.getParameter("rows");

        //获取session里的id
        HttpSession session = request.getSession();
        int sno = (int) session.getAttribute("sno");

        //如果直接访问FindUserByPageServlet，默认currentPage="1"，rows="5"
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null|| "".equals(rows)){
            rows="5";
        }

        //2.调用service查询
        UserService userService = new UserService();
        PageBean<Appoint> pb=userService.findAppointmentByPage(sno,currentPage,rows);

        //3.将PageBean存入request
        request.setAttribute("pb",pb);

        //4.转发到releaseList.jsp
        request.getRequestDispatcher("myAppointment.jsp").forward(request,response);

    }
}
