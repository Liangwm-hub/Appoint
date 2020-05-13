<%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/3/29
  Time: 23:57
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

    <style>
        body {
            background: url("img/背景.jpg") no-repeat;
        }
    </style>

</head>
<body>

<div class="page-header" align="center">
    <h1>会面预约系统 <small>欢迎您！</small></h1>
</div>

<div align="center">
    <a href="view/user/userLogin.jsp" class="btn btn-primary btn-lg active" role="button">学生登录</a><br><br>
    <a href="view/teacher/teacherLogin.jsp" class="btn btn-primary btn-lg active" role="button">导师登录</a>
    <a href="view/manager/managerLogin.jsp" class="btn btn-primary btn-lg active" role="button">管理员登录</a>
</div>

</body>
</html>
