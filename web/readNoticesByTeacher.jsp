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

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<%--导航条--%>
<ul class="nav nav-tabs">
    <li role="presentation"><a href="FindReleaseByPageServlet"><font color="black" size="4em">出行计划</font></a></li>
    <li role="presentation"><a href="#"><font color="black" size="4em">处理请求</font></a></li>
    <li role="presentation"><a href="setAppointment.jsp"><font color="black" size="4em">发布预约</font></a></li>
    <li role="presentation"><a href="FindAllChatByTeacherServlet?"><font color="black" size="4em">聊天记录</font></a></li>
    <li role="presentation" class="active"><a href="FindAllNoticesByTeacherServlet"><font color="black"
                                                                                          size="4em">通知</font></a></li>

</ul>
<br>

<table border="1" class="table table-hover">
    <%
        List<Notice> list = (List<Notice>) request.getAttribute("notices");
        if (list != null) {
            for (Notice notice :
                    list) {
    %>
    <tr>
        <td>
            <a href="QueryNoticeByTeacherServlet?noticeId=<%=notice.getNoticeId()%>"><h4><%=notice.getNoticeTitle()%>
            </h4></a>
        </td>
    </tr>
    <%

            }
        }
    %>

</table>

</body>
</html>
