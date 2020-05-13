<%@ page import="com.liangweimin.www.po.User" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/3/31
  Time: 15:11
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
    <title>updateUser</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url("${pageContext.request.contextPath}/img/背景.jpg") no-repeat;
        }
    </style>

</head>
<body>


<%
    User user = (User) request.getAttribute("user");
%>
<br><br>

<table align="center">
    <tr>
        <td><h4>学 号:</h4></td>
        <td><input type="text" name="sno" value="<%=user.getSno()%>" readonly="readonly" class="form-control"></td>
    </tr>
    <tr>
        <td><h4>姓 名:</h4></td>
        <td><input type="text" name="name" value="<%=user.getName()%>" readonly="readonly" class="form-control"></td>
    </tr>
    <tr>
        <td><h4>性 别:</h4></td>
        <td><input type="text" name="sex" value="<%=user.getSex()%>" readonly="readonly" class="form-control"></td>
    </tr>
    <tr>
        <td><h4>专业班级:</h4></td>
        <td><input type="text" name="majorClass" value="<%=user.getMajorClass()%>" readonly="readonly" class="form-control"></td>
    </tr>
    <tr>
        <td><h4>联系电话:</h4></td>
        <td><input type="text" name="phone" value="<%=user.getPhone()%>" readonly="readonly" class="form-control"></td>
    </tr>
</table>
<br>
<div align="center">
    <td><a href="manager/FreezeUserServlet?sno=<%=user.getSno()%>" class="btn btn-primary">冻结该账号</a></td>
</div>

</body>
</html>

