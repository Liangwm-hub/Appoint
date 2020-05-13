<%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/5/12
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>

</head>
<body>

<a href="test/TestServlet">aaa</a>

<form action="test/TestServlet" method="post">
    <input type="text" name="id">
    <input type="submit">
</form>

</body>
</html>
