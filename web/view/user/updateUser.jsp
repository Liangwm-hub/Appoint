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


</head>
<body>

<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" style="max-height: 200px;max-width: 200px;">
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 21px;">
    <li>
        <a href="user/FindReleaseByUserServlet">预约导师</a>
    </li>
    <li>
        <a href="user/MyAppointmentServlet">我的预约</a>
    </li>
    <li class="active">
        <a href="user/QueryUserBySnoServlet">个人信息</a>
    </li>
    <li>
        <a href="user/FindAllChatByUserServlet">聊天记录</a>
    </li>
    <li>
        <a href="user/FindAllNoticesByUserServlet">通知</a>
    </li>
</ul>

<%
    User user = (User) request.getAttribute("user");
%>

<div id="contain">
    <marquee behavior="scroll" direction="right">
        您必须完善身份信息才能进行预约操作!
    </marquee>
    <br><br>

    <form class="form-inline" action="user/UpdateUserServlet" method="post">
        <table align="center">
            <tr>
                <td><h4>学 号:</h4></td>
                <td><input type="text" name="sno" value="<%=user.getSno()%>" readonly="readonly" class="form-control">
                </td>
            </tr>
            <tr>
                <td><h4>姓 名:</h4></td>
                <td><input type="text" name="name" value="<%=user.getName()%>" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>性 别:</h4></td>
                <td><input type="text" name="sex" value="<%=user.getSex()%>" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>专业班级:</h4></td>
                <td><input type="text" name="majorClass" value="<%=user.getMajorClass()%>" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>联系电话:</h4></td>
                <td><input type="text" name="phone" value="<%=user.getPhone()%>" class="form-control"></td>
            </tr>
        </table>
        <br>
        <div align="center">
            <td><input class="btn btn-primary" type="submit" value="修改信息"></td>
        </div>
    </form>
    <br><br><br><br><br><br>
    <br><br><br><br><br><br>
</div>

<style type="text/css">
    #contain {
        background-image: url("${pageContext.request.contextPath}/img/背景.jpg");
    }
</style>
</body>
</html>

