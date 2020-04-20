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
    <title>userLogin</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url("img/背景.jpg") no-repeat;
        }
    </style>

    <script type="text/javascript" src="jquery-3.4.1.min.js"></script>
    <script type="text/javascript" >
        //重新请求图片
        function reloadCheckImg() {
            //attr()改属性，把src换成后面的内容
            $("img").attr("src","img.jsp?s="+(Math.random()));//参数不一样，请求不一样，强制刷新
        }
    </script>

</head>
<body>

<div class="page-header" align="center">
    <h3>管理员登录</h3>
</div>

<form class="form-inline" action="ManagerLoginServlet" method="post">
    <table align="center">
        <tr>
            <td><h4>账 号: </h4></td>
            <td><input type="text" name="managerId" placeholder="manager id" class="form-control"></td>
        </tr>
        <tr>
            <td><h4>密 码: </h4></td>
            <td><input type="password" name="password" placeholder="password" class="form-control"></td>
        </tr>
        <tr>
            <td><h4>输入验证码：</h4></td>
            <td>
                <input type="text" name="checkCode" id="checkCodeId" size="6" placeholder="check code" class="form-control">
                <a href="javascript:reloadCheckImg();">
                    <img src="${pageContext.request.contextPath}/img.jsp" title="点击切换">
                </a>
            </td>
        </tr>
    </table>
    <br>
    <div align="center">
        <td><input class="btn btn-primary" type="submit" value="&nbsp&nbsp登&nbsp&nbsp&nbsp&nbsp&nbsp录&nbsp&nbsp" align="center"></td>
    </div>
</form>
<br>

</body>
</html>