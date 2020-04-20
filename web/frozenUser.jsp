<%@ page import="com.liangweimin.www.bean.PageBean" %>
<%@ page import="com.liangweimin.www.po.Appoint" %>
<%@ page import="com.liangweimin.www.po.Release" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liangweimin.www.po.User" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/4/5
  Time: 18:30
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
    <title>releaseList</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<%--导航条--%>
<ul class="nav nav-tabs">
    <li role="presentation" ><a href="AllRequestsServlet"><font color="black" size="4em">处理预约请求</font></a></li>
    <li role="presentation" ><a href="FindRegisterByPageServlet"><font color="black" size="4em">审批用户注册</font></a></li>
    <li role="presentation"><a href="teacherRegister.jsp"><font color="black" size="4em">注册导师账号</font></a></li>
    <li role="presentation" class="active"><a href="FindFreezeUserByPageServlet"><font color="black" size="4em">冻结的账号</font></a></li>
    <li role="presentation"><a href="notice.jsp"><font color="black" size="4em">发布通知</font></a></li>
    <li role="presentation"><a href="FindAllNoticesByManagerServlet"><font color="black" size="4em">已发布的通知</font></a></li>

</ul>
<br><br>

    <table border="1" class="table table-hover">
        <tr>
            <th>学生学号</th>
            <th>学生姓名</th>
            <th>操作</th>
        <tr/>

        <%
            PageBean<User> pb = (PageBean<User>) request.getAttribute("pb");
            List<User> lists = pb.getList();
            if (lists != null) {
                for (User u :
                        lists) {
        %>

        <tr>
            <td><%=u.getSno()%>
            </td>
            <td><%=u.getName()%>
            </td>
            <td><a href="UnsealUserServlet?sno=<%=u.getSno()%>">解封</a>
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>

<nav aria-label="Page navigation">
    <ul class="pagination">

        <li>
            <a href="FindFreezeUserByPageServlet?currentPage=<%=pb.getCurrentPage()-1%>&rows=10" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <%
            for (int i = 1; i <= pb.getTotalPage(); i++) {
                if (pb.getCurrentPage() == i) {
        %>
        <li class="active"><a href="FindFreezeUserByPageServlet?currentPage=<%=i%>&rows=10"><%=i%>
        </a></li>
        <%
        } else {
        %>
        <li><a href="FindFreezeUserByPageServlet?currentPage=<%=i%>&rows=10"><%=i%>
        </a></li>
        <%
                }
            }
        %>

        <li>
            <a href="FindFreezeUserByPageServlet?currentPage=<%=pb.getCurrentPage()+1%>&rows=10" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
        <span style="font-size: 25px;margin-left: 5px;">
            共${pb.totalCount}条记录，共${pb.totalPage}页
        </span>
    </ul>
</nav>

</body>
</html>
