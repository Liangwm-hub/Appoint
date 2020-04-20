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

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url("img/背景.jpg") no-repeat;
        }
    </style>

</head>
<body>

<%--导航条--%>
<ul class="nav nav-tabs">
    <li role="presentation"><a href="FindReleaseByPageServlet"><font color="black" size="4em">出行计划</font></a></li>
    <li role="presentation"><a href="ProcessRequestServlet"><font color="black" size="4em">处理请求</font></a></li>
    <li role="presentation" class="active"><a href="#"><font color="black" size="4em">发布预约</font></a></li>
    <li role="presentation"><a href="FindAllNoticesByTeacherServlet"><font color="black" size="4em">通知</font></a></li>

</ul>
<br><br>

<form class="form-inline" action="SetAppointmentServlet" method="post">
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

</body>
</html>

