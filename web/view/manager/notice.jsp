<%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/3/30
  Time: 23:07
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
    <title>发布通知</title>

    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>


<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" style="max-height: 200px;max-width: 200px;">
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 21px;" >
    <li>
        <a href="manager/AllRequestsServlet">处理预约请求</a>
    </li>
    <li>
        <a href="manager/FindRegisterByPageServlet">审批用户注册</a>
    </li>
    <li>
        <a href="view/manager/teacherRegister.jsp">注册导师账号</a>
    </li>
    <li>
        <a href="manager/FindFreezeUserByPageServlet">冻结的账号</a>
    </li>
    <li class="active">
        <a href="view/manager/notice.jsp">发布通知</a>
    </li>
    <li>
        <a href="manager/FindAllNoticesByManagerServlet">已发布的通知</a>
    </li>
</ul>

<div id="contain">
<marquee behavior="scroll" direction="right">
    全体师生都会看到您发的通知!
</marquee>
<br>

<form class="form-inline" action="manager/NoticeServlet" method="post" enctype="multipart/form-data">
    <table align="center">
        <tr>
            <td>
                <div style="font-family:宋体">
                    通知标题
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <input name="noticeTitle" class="form-control"><br>
            </td>
        </tr>
        <tr>
            <td>
                <div style="font-family:宋体">
                    <br>通知内容:
                </div>
            </td>
        </tr>
        <tr>
            <td>
               <textarea name="noticeContent" class="form-control" cols="70" rows="12"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <br><input type="file" name="file">
            </td>
        </tr>
        <tr>
            <td>
                <br><input class="btn btn-primary" type="submit" value="&nbsp发&nbsp布&nbsp通&nbsp知&nbsp" align="center">
            </td>
        </tr>
    </table>
</form>
<br><br><br>

</div>

<style type="text/css">
    #contain{
        background-image: url("${pageContext.request.contextPath}/img/背景.jpg");
    }
</style>
</body>
</html>
