<%@ page import="java.util.List" %>
<%@ page import="com.liangweimin.www.po.ChatRoom" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<%--导航条--%>
<ul class="nav nav-tabs">
    <li role="presentation"><a href="FindReleaseByPageServlet"><font color="black" size="4em">出行计划</font></a></li>
    <li role="presentation"><a href="ProcessRequestServlet"><font color="black" size="4em">处理请求</font></a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/setAppointment.jsp"><font color="black" size="4em">发布预约</font></a></li>
    <li role="presentation" class="active"><a href="FindAllChatByTeacherServlet"><font color="black" size="4em">聊天记录</font></a></li>
    <li role="presentation"><a href="FindAllNoticesByTeacherServlet"><font color="black" size="4em">通知</font></a></li>

</ul>

<br>

<table class="table table-bordered table-hover">
    <tr class="success">
        <th>聊天列表</th>
        <th>操作</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${requestScope.chatRooms}" var="chatRoom">
        <tr>
            <td>${chatRoom.userName}</td>
            <td><a href="${pageContext.request.contextPath}/view/teacher/chat.jsp?chatId=${chatRoom.chatId}&userName=${chatRoom.userName}&teacherName=${chatRoom.teacherName}">进入聊天室</a> </td>
            <td><a href="DeleteChatByTeacherServlet?chatId=${chatRoom.chatId}&teacherId=${chatRoom.teacherId}">删除聊天记录</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
