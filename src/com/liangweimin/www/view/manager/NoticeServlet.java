package com.liangweimin.www.view.manager;

import com.liangweimin.www.po.Notice;
import com.liangweimin.www.service.ManagerService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * @author 梁伟民
 */
@WebServlet(name = "NoticeServlet", urlPatterns = "/NoticeServlet")
public class NoticeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        //三个属性
        String noticeTitle = null;
        String noticeContent = null;
        String fileName = null;


        //检查前台的form是否有multipart属性
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {

            //创建一个DiskFileItemFactory
            FileItemFactory factory = new DiskFileItemFactory();
            //创建一个文件上传解码器,ServletFileUpload需要factory参数
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {

                //通过parseRequest解析form的请求字段,保存到items集合中(即:前台传来的数据都保存到items里)
                List<FileItem> items = upload.parseRequest(request);

                //用迭代器遍历items
                Iterator<FileItem> iterator = items.iterator();

                while (iterator.hasNext()) {
                    System.out.println(iterator.hasNext());
                    //item表示集合的一个元素
                    FileItem item = iterator.next();

                    //isFormField() 用来判断是否是 通过表单上传的普通字段
                    if (item.isFormField()) {

                        //获取item的名字,普通字段用getFieldName()
                        String fieldName = item.getFieldName();

                        if ("noticeTitle".equals(fieldName)) {
                            //获得title
                            noticeTitle = item.getString("utf-8");
                        } else if ("noticeContent".equals(fieldName)) {
                            //获得content
                            noticeContent = item.getString("utf-8");
                        }
                    } else {
                        //不是普通字段,则为文件字段

                        //获取item的名字,文件字段用getName()
                        fileName = item.getName();

                        //上传文件不能为空
                        if (fileName != null && !("".equals(fileName))) {
                            //动态获取指定上传的位置(服务器路径)
                            String path = request.getServletContext().getRealPath("/upload");

                            //路径和文件名
                            File file = new File(path, fileName);

                            //上传
                            item.write(file);
                        }

                    }
                }

                //封装,调用service层,在notice表新增一条记录
                Notice notice = new Notice(noticeTitle, noticeContent, fileName);
                ManagerService managerService = new ManagerService();
                boolean success = managerService.issueNotice(notice);

                if (success) {
                    System.out.println("上传成功");
                    response.sendRedirect("notice.jsp");
                } else {
                    PrintWriter writer = response.getWriter();
                    writer.println("发布失败,请检查您的输入!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
