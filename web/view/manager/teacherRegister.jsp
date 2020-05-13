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
    <title>学生注册</title>

    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">


    <script type="text/javascript" src="../../jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        //重新请求图片
        function reloadCheckImg() {
            //attr()改属性，把src换成后面的内容
            $("img").attr("src", "img.jsp?s=" + (new Date().getDate()));//参数不一样，请求不一样，强制刷新
        }
    </script>

</head>
<body>

<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" style="max-height: 200px;max-width: 200px;">
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 21px;">
    <li>
        <a href="manager/AllRequestsServlet">处理预约请求</a>
    </li>
    <li>
        <a href="manager/FindRegisterByPageServlet">审批用户注册</a>
    </li>
    <li class="active">
        <a href="view/manager/teacherRegister.jsp">注册导师账号</a>
    </li>
    <li>
        <a href="manager/FindFreezeUserByPageServlet">冻结的账号</a>
    </li>
    <li>
        <a href="view/manager/notice.jsp">发布通知</a>
    </li>
    <li>
        <a href="manager/FindAllNoticesByManagerServlet">已发布的通知</a>
    </li>
</ul>

<div id="contain">
    <br>
    <form class="form-inline" action="manager/TeacherRegisterServlet" method="post">
        <table align="center">
            <tr>
                <td><h4>导师职工号: </h4></td>
                <td><input type="text" name="id" placeholder="id" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>姓 名: </h4></td>
                <td><input type="text" name="name" placeholder="name" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>性 别: </h4></td>
                <td><input type="text" name="sex" placeholder="sex" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>所属学院: </h4></td>
                <td><input type="text" name="college" placeholder="college" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>联系电话: </h4></td>
                <td><input type="text" name="phone" placeholder="phone" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>密 码: </h4></td>
                <td><input type="password" name="password1" placeholder="password" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>确认密码: </h4></td>
                <td><input type="password" name="password2" placeholder="password" class="form-control"></td>
            </tr>
            <tr>
                <td><h4>输入验证码：</h4></td>
                <td>
                    <input type="text" name="checkCode" id="checkCodeId" size="6" placeholder="check code"
                           class="form-control">
                    <a href="javascript:reloadCheckImg();">
                        <img src="${pageContext.request.contextPath}/img.jsp" title="点击切换">
                    </a>
                </td>
            </tr>
        </table>
        <br>
        <div align="center">
            <td><input class="btn btn-primary" type="submit" value="&nbsp&nbsp注&nbsp&nbsp&nbsp&nbsp&nbsp册&nbsp&nbsp"
                       align="center"></td>
        </div>
    </form>
    <br><br><br>
    <br><br><br><br>

</div>
<style type="text/css">
    #contain {
        background-image: url("${pageContext.request.contextPath}/img/背景.jpg");
    }
</style>
</body>
</html>
