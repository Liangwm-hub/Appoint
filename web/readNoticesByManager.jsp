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

<ul class="nav nav-tabs">
    <li role="presentation" ><a href="AllRequestsServlet"><font color="black" size="4em">处理预约请求</font></a></li>
    <li role="presentation"><a href="FindRegisterByPageServlet"><font color="black" size="4em">审批用户注册</font></a></li>
    <li role="presentation"><a href="#"><font color="black" size="4em">注册导师账号</font></a></li>
    <li role="presentation"><a href="FindFreezeUserByPageServlet"><font color="black" size="4em">冻结的账号</font></a></li>
    <li role="presentation"><a href="notice.jsp"><font color="black" size="4em">发布通知</font></a></li>
    <li role="presentation" class="active"><a href="FindAllNoticesByManagerServlet"><font color="black" size="4em">已发布的通知</font></a></li>

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
            <a href="QueryNoticeByManagerServlet?noticeId=<%=notice.getNoticeId()%>"><h5><%=notice.getNoticeTitle()%>
            </h5></a>
        </td>
        <td>
            <a href="DeleteNoticeByIdServlet?noticeId=<%=notice.getNoticeId()%>">删除该通知</a>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>

</body>
</html>
