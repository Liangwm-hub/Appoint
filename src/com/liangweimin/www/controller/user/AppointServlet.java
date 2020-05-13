package com.liangweimin.www.controller.user;

import com.liangweimin.www.po.Release;
import com.liangweimin.www.service.TeacherService;
import com.liangweimin.www.service.UserService;
import com.liangweimin.www.util.Constant;
import com.liangweimin.www.util.MethodUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * @author 梁伟民
 */
@WebServlet(name = "AppointServlet", urlPatterns = "/user/AppointServlet")
public class AppointServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //获得导师的数据
        int id = Integer.parseInt(request.getParameter("id"));
        String appointTime = request.getParameter("appointTime");
        String place = request.getParameter("place");

        //调用service找到发布的预约
        TeacherService teacherService = new TeacherService();
        Release release = teacherService.queryRelease(id, appointTime, place);

        //取出session中的sno
        HttpSession session = request.getSession();
        int sno = (int) session.getAttribute("sno");

        //找到sno对应学生的名字
        UserService userService = new UserService();
        String userName = userService.queryUserBySno(sno).getName();

        //文件名
        String fileName = null;

        //检查前台的form是否有multipart属性
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            //ServletFileUpload需要FileItemFactory参数
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {

                //通过parseRequest解析form的请求字段,保存到items集合中(即:前台传来的数据都保存到items里)
                List<FileItem> items = upload.parseRequest(request);

                //用迭代器遍历items
                Iterator<FileItem> iterator = items.iterator();

                if (iterator.hasNext()) {
                    //item表示集合的一个元素
                    FileItem item = iterator.next();

                    //获取item的名字,文件字段用getName()
                    fileName = item.getName();

                    //文件名不能为为空或者""
                    if (MethodUtil.haveFile(fileName)) {
                        //现限制文件为图片
                        //获取后缀 (如a.png, "."的索引+1 之后对应的即为后缀)
                        String ext = fileName.substring(fileName.indexOf(".") + 1);

                        //不是图片的情况
                        if (!((Constant.PNG.equals(ext)) || (Constant.GIF.equals(ext)) || (Constant.JPG.equals(ext)))) {
                            PrintWriter writer = response.getWriter();
                            writer.println("您上传的不是图片!");
                            //终止
                            return;
                        }

                        //获取文件内容,并上传
                        //定义文件路径:指定上传的位置(服务器路径)

                        //动态获取目录
                        String path = request.getServletContext().getRealPath("/upload");

                        //路径和文件名
                        File file = new File(path, fileName);

                        //上传
                        item.write(file);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //调用service预约
        boolean success = userService.appoint(sno, userName, fileName, release);

        PrintWriter writer = response.getWriter();
        if (success) {
            writer.println("请求成功!请等待审核结果.");
        } else {
            writer.println("上传失败");
        }


    }
}
