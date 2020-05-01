package com.liangweimin.www.controller.manager;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * @author 梁伟民
 */
@WebServlet(name = "DownloadFileByManagerServlet", urlPatterns = "/DownloadFileByManagerServlet")
public class DownloadFileByManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取该文件的名字
        String fileName = request.getParameter("fileName");

        //有文件的情况
        if (fileName != null && !("".equals(fileName))) {

            //下载文件:需要设置消息头
            //类型为二进制文件类型(任意文件)
            response.addHeader("contentType", "application/octet-stream");
            //URLEncoder.encode(fileName,"utf-8") 文件名转码,防止文件名乱码
            response.addHeader("content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));

            //通过文件地址,将文件转为输入流
            InputStream in = getServletContext().getResourceAsStream("/upload/" + fileName);

            //通过输出流,将刚才转为输入流的文件,输出给用户
            ServletOutputStream out = response.getOutputStream();

            byte[] bs = new byte[1024];
            int len = -1;
            while ((len = in.read(bs)) != -1) {
                out.write(bs, 0, len);
            }

            //关闭资源
            out.close();
            in.close();
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("没有文件");
        }

    }
}
