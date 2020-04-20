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

<%
    int sno = (int) session.getAttribute("sno");
%>

<%--导航条--%>
<ul class="nav nav-tabs">
    <li role="presentation"><a href="FindReleaseByUserServlet"><font color="black" size="4em">预约导师</font></a></li>
    <li role="presentation"><a href="MyAppointmentServlet"><font color="black" size="4em">我的预约</font></a></li>
    <li role="presentation"><a href="QueryUserBySnoServlet?sno=<%=sno%>"><font color="black" size="4em">个人信息</font></a></li>
    <li role="presentation" class="active"><a href="FindAllNoticesByUserServlet"><font color="black" size="4em">通知</font></a></li>
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
            <a href="QueryNoticeByUserServlet?noticeId=<%=notice.getNoticeId()%>">
                <h4><%=notice.getNoticeTitle()%>
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
