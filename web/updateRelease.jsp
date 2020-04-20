<%@ page import="com.liangweimin.www.po.Release" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/4/2
  Time: 23:19
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
    <title>updateRelease</title>

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
    Release release = (Release) request.getAttribute("release");
%>

<div class="page-header" align="center">
    <h3>修改见面时间和地点</h3>
</div>
<br><br>

<form class="form-inline" action="UpdateReleaseServlet" method="post">
    <table align="center">
        <tr>
            <td><h4>输入您的职工号:&nbsp </h4></td>
            <td><input type="text" name="id" value="<%=release.getId()%>"  class="form-control" readonly="readonly"></td>
        </tr>
        <tr>
            <td><h4>原见面时间:&nbsp </h4></td>
            <td><input type="text" name="oldAppointTime" value="<%=release.getAppointTime()%>"  class="form-control" readonly="readonly"></td>
        </tr>
        <tr>
            <td><h4>原见面地点:&nbsp </h4></td>
            <td><input type="text" name="oldPlace" value="<%=release.getPlace()%>"  class="form-control" readonly="readonly"></td>
        </tr>
        <tr>
            <td><h4>新的见面时间:&nbsp </h4></td>
            <td><input type="text" name="appointTime" placeholder="格式：2020-10-01 10:30" class="form-control"></td>
        </tr>
        <tr>
            <td><h4>新的见面地点:&nbsp </h4></td>
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
