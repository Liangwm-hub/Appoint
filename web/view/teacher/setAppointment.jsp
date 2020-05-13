<%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/3/31
  Time: 23:11
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
    <title>setAppointment</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" style="max-height: 200px;max-width: 200px;">
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 21px;">
    <li>
        <a href="teacher/FindReleaseByPageServlet">出行计划</a>
    </li>
    <li>
        <a href="teacher/ProcessRequestServlet">处理请求</a>
    </li>
    <li class="active">
        <a href="view/teacher/setAppointment.jsp">发布预约</a>
    </li>
    <li>
        <a href="teacher/FindAllChatByTeacherServlet">聊天记录</a>
    </li>
    <li>
        <a href="teacher/FindAllNoticesByTeacherServlet">通知</a>
    </li>
</ul>


<div id="contain">
    <br><br>

    <form class="form-inline" action="teacher/SetAppointmentServlet" method="post">
        <table align="center">
            <tr>
                <td><h4>输入见面时间:&nbsp </h4></td>
                <td><input type="text" name="appointTime" placeholder="格式：2020-10-01 10:30" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>输入见面地点:&nbsp </h4></td>
                <td><input type="text" name="place" placeholder="place" class="form-control"></td>
            </tr>
        </table>
        <br>
        <div align="center">
            <td><input class="btn btn-primary" type="submit" value="设&nbsp&nbsp&nbsp置" align="center"></td>
        </div>
    </form>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</div>

<style type="text/css">
    #contain {
        background-image: url("${pageContext.request.contextPath}/img/背景.jpg");
    }
</style>
</body>
</html>

