<%@ page import="com.liangweimin.www.bean.PageBean" %>
<%@ page import="com.liangweimin.www.po.Release" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liangweimin.www.po.Appoint" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/3/31
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>appoint</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url("img/背景.jpg") no-repeat;
        }
    </style>

</head>
<body>

<%
    int sno = (int) session.getAttribute("sno");
%>

<%--导航条--%>
<ul class="nav nav-tabs">
    <li role="presentation"><a href="FindReleaseByUserServlet"><font color="black" size="4em">预约导师</font></a></li>
    <li role="presentation" class="active"><a href="#"><font color="black" size="4em">我的预约</font></a></li>
    <li role="presentation"><a href="QueryUserBySnoServlet?sno=<%=sno%>"><font color="black" size="4em">个人信息</font></a>
    </li>
    <li role="presentation"><a href="FindAllNoticesByUserServlet"><font color="black" size="4em">通知</font></a></li>

</ul>

<h4>您可以看到近30天的预约记录</h4>

<table class="table table-hover">
    <tr>
        <th>姓名</th>
        <th>所属学院</th>
        <th>联系电话</th>
        <th>见面时间</th>
        <th>见面地点</th>
        <th>状态</th>
        <th>操作</th>
    <tr/>

    <%
        PageBean<Appoint> pb = (PageBean<Appoint>) request.getAttribute("pb");
        List<Appoint> lists = pb.getList();
        if (lists != null) {
            for (Appoint u :
                    lists) {
    %>

    <tr>
        <td><%=u.getTeacherName()%>
        </td>
        <td><%=u.getTeacherCollege()%>
        </td>
        <td><%=u.getTeacherPhone()%>
        </td>
        <td><%=u.getAppointTime()%>
        </td>
        <td><%=u.getPlace()%>
        </td>
        <td><%=u.getStatus()%>
        </td>
        <td>
            <a href="DeleteAppointmentServlet?num=<%=u.getNum()%>">取消预约</a>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>
<nav aria-label="Page navigation">
    <ul class="pagination">

        <li>
            <a href="MyAppointmentServlet?currentPage=<%=pb.getCurrentPage()-1%>&rows=5" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <%
            for (int i = 1; i <= pb.getTotalPage(); i++) {
                if (pb.getCurrentPage() == i) {
        %>
        <li class="active"><a href="MyAppointmentServlet?currentPage=<%=i%>&rows=5"><%=i%>
        </a></li>
        <%
        } else {
        %>
        <li><a href="MyAppointmentServlet?currentPage=<%=i%>&rows=5"><%=i%>
        </a></li>
        <%
                }
            }
        %>

        <li>
            <a href="MyAppointmentServlet?currentPage=<%=pb.getCurrentPage()+1%>&rows=5" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
        <span style="font-size: 25px;margin-left: 5px;">
            共${pb.totalCount}条记录，共${pb.totalPage}页
        </span>
    </ul>
</nav>


</body>
</html>
