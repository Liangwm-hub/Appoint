<%@ page import="com.liangweimin.www.po.Notice" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/4/17
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通知详情</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>

    <style>
        body {
            background: url("${pageContext.request.contextPath}/img/背景.jpg") no-repeat;
        }
    </style>

</head>
<body>


<%
    Notice notice = (Notice) request.getAttribute("notice");
%>

<div class="page-header" align="center">
    <h1><%=notice.getNoticeTitle()%>
    </h1>
</div>
<pre>
<div align="center" style="white-space: pre-wrap;text-indent:2em;">
        <textarea cols="70" rows="12" readonly="readonly"
                  style="font-size: 20px"><%=notice.getNoticeContent()%></textarea>
</div>
</pre>

<div align="center">
    <a href="teacher/DownloadFileByTeacherServlet?fileName=<%=notice.getFileName()%>"><h4>附属文件</h4></a>
</div>

</body>
</html>
