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

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url("img/背景.jpg") no-repeat;
        }
    </style>

</head>
<body>

<ul class="nav nav-tabs">
    <li role="presentation"><a href="AllRequestsServlet"><font color="black" size="4em">处理预约请求</font></a></li>
    <li role="presentation"><a href="FindRegisterByPageServlet"><font color="black" size="4em">审批用户注册</font></a></li>
    <li role="presentation"><a href="#"><font color="black" size="4em">注册导师账号</font></a></li>
    <li role="presentation"><a href="FindFreezeUserByPageServlet"><font color="black" size="4em">冻结的账号</font></a></li>
    <li role="presentation" class="active"><a href="" ><font color="black" size="4em">发布通知</font></a></li>
    <li role="presentation"><a href="FindAllNoticesByManagerServlet"><font color="black" size="4em">已发布的通知</font></a></li>

</ul>

<marquee behavior="scroll" direction="right">
    全体师生都会看到您发的通知!
</marquee>
<br>

<form class="form-inline" action="NoticeServlet" method="post" enctype="multipart/form-data">
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

</body>
</html>
