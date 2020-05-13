<%@ page import="java.util.List" %>
<%@ page import="com.liangweimin.www.po.Notice" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/4/17
  Time: 17:22
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
    <title>index</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">
        td,th{
            text-align: center;
        }
    </style>
</head>
<body>


<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" style="max-height: 200px;max-width: 200px;">
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 21px;" >
    <li>
        <a href="teacher/FindReleaseByPageServlet">出行计划</a>
    </li>
    <li>
        <a href="teacher/ProcessRequestServlet">处理请求</a>
    </li>
    <li>
        <a href="view/teacher/setAppointment.jsp">发布预约</a>
    </li>
    <li>
        <a href="teacher/FindAllChatByTeacherServlet">聊天记录</a>
    </li>
    <li class="active">
        <a href="teacher/FindAllNoticesByTeacherServlet">通知</a>
    </li>
</ul>

<br>

<table class="table table-bordered table-hover">
    <tr class="success">
        <th>通知列表</th>
        <th>操作</th>
    </tr>
    <%
        List<Notice> list = (List<Notice>) request.getAttribute("notices");
        if (list != null) {
            for (Notice notice :
                    list) {
    %>
    <tr>
        <td>
            <a href="teacher/QueryNoticeByTeacherServlet?noticeId=<%=notice.getNoticeId()%>">
                <h4><%=notice.getNoticeTitle()%>
                </h4></a>
        </td>
        <td>
            <a href="teacher/QueryNoticeByTeacherServlet?noticeId=<%=notice.getNoticeId()%>" class="btn btn-primary">查看详情</a>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>


</body>
</html>
