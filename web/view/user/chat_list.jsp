<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">
        td, th {
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
        <a href="user/FindReleaseByUserServlet">预约导师</a>
    </li>
    <li>
        <a href="user/MyAppointmentServlet">我的预约</a>
    </li>
    <li>
        <a href="user/QueryUserBySnoServlet">个人信息</a>
    </li>
    <li class="active">
        <a href="user/FindAllChatByUserServlet">聊天记录</a>
    </li>
    <li>
        <a href="user/FindAllNoticesByUserServlet">通知</a>
    </li>
</ul>

<br>

<table class="table table-bordered table-hover">
    <tr class="success">
        <th>聊天列表</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${requestScope.chatRooms}" var="chatRoom">
        <tr>
            <td>${chatRoom.teacherName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/view/user/chat.jsp?chatId=${chatRoom.chatId}&userName=${chatRoom.userName}&teacherName=${chatRoom.teacherName}"
                   class="btn btn-primary">进入聊天室</a>
                <a href="user/DeleteChatByUserServlet?chatId=${chatRoom.chatId}&userSno=${chatRoom.userSno}"
                   class="btn btn-danger">删除聊天记录</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
