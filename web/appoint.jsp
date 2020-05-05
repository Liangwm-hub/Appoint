<%@ page import="com.liangweimin.www.bean.PageBean" %>
<%@ page import="com.liangweimin.www.po.Release" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/3/31
  Time: 17:20
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
    <title>appoint</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<%
    int sno = (int) session.getAttribute("sno");
%>

<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" >
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 23px;" >
    <li class="active">
        <a href="FindReleaseByUserServlet">预约导师</a>
    </li>
    <li>
        <a href="MyAppointmentServlet">我的预约</a>
    </li>
    <li>
        <a href="QueryUserBySnoServlet">个人信息</a>
    </li>
    <li>
        <a href="FindAllChatByUserServlet">聊天记录</a>
    </li>
    <li>
        <a href="FindAllNoticesByUserServlet">通知</a>
    </li>
</ul>

<br>

<table class="table table-hover">
    <tr>
        <form action="SearchByKeywordsServlet" method="post">
            <input type="text" name="keywords" placeholder="请输入关键字">
            <input type="submit" value="搜索"/><br>
        </form>
    </tr>
    <br>
    <tr>
        <th>职工号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>所属学院</th>
        <th>联系电话</th>
        <th>见面时间</th>
        <th>见面地点</th>
        <th>私聊</th>
        <th>操作</th>
    <tr/>

    <%
        PageBean<Release> pb = (PageBean<Release>) request.getAttribute("pb");
        List<Release> lists = pb.getList();
        if (lists != null) {
            for (Release u :
                    lists) {
    %>

    <tr>
        <td><%=u.getId()%>
        </td>
        <td><%=u.getName()%>
        </td>
        <td><%=u.getSex()%>
        </td>
        <td><%=u.getCollege()%>
        </td>
        <td><%=u.getPhone()%>
        </td>
        <td><%=u.getAppointTime()%>
        </td>
        <td><%=u.getPlace()%>
        </td>
        <td><a href="ChatToTeacherServlet?sno=<%=sno%>&teacherId=<%=u.getId()%>">进入聊天室</a>
        </td>
        <td>
            <form action="AppointServlet?id=<%=u.getId()%>&appointTime=<%=u.getAppointTime()%>&place=<%=u.getPlace()%>"
                  method="post" enctype="multipart/form-data">
                <input type="file" name="picture">
                <input type="submit" value="预       约">
            </form>
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
            <a href="FindReleaseByUserServlet?currentPage=<%=pb.getCurrentPage()-1%>&rows=5" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <%
            for (int i = 1; i <= pb.getTotalPage(); i++) {
                if (pb.getCurrentPage() == i) {
        %>
        <li class="active"><a href="FindReleaseByUserServlet?currentPage=<%=i%>&rows=5"><%=i%>
        </a></li>
        <%
        } else {
        %>
        <li><a href="FindReleaseByUserServlet?currentPage=<%=i%>&rows=5"><%=i%>
        </a></li>
        <%
                }
            }
        %>

        <li>
            <a href="FindReleaseByUserServlet?currentPage=<%=pb.getCurrentPage()+1%>&rows=5" aria-label="Next">
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
