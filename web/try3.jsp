<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>index</title>

    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url("${pageContext.request.contextPath}/img/背景.jpg") no-repeat;
        }
    </style>

</head>
<body>

<%--<%--%>
<%--    String path = request.getContextPath();--%>
<%--    System.out.println(path);--%>

<%--    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--    System.out.println(basePath);--%>

<%--%>--%>

<img src="upload/海表.jpg" class="img-rounded" style="max-width: 100px;max-height: 100px">

<%--<img src="img/图片2.jpg" class="img-rounded" style="max-width: 100px;max-height: 100px">--%>

<%--<button type="button" class="btn btn-default" aria-label="Left Align">--%>
<%--    <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>--%>
<%--</button>--%>

<%--<style type="text/css">--%>
<%--    label{--%>
<%--        position: relative;--%>
<%--    }--%>
<%--    #fileinp{--%>
<%--        position: absolute;--%>
<%--        left: 0;--%>
<%--        top: 0;--%>
<%--        opacity: 0;--%>
<%--    }--%>
<%--    #btn{--%>
<%--        margin-right: 5px;--%>
<%--    }--%>
<%--    #text{--%>
<%--        color: red;--%>
<%--    }--%>
<%--</style>--%>

<%--<label for="fileinp">--%>
<%--    <input type="button" id="btn" value=""><span id="text" class="glyphicon glyphicon-picture" aria-hidden="true"></span>--%>
<%--    <input type="file" id="fileinp">--%>
<%--</label>--%>

<%--</div>--%>

</body>
</html>