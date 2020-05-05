<%@ page import="com.liangweimin.www.bean.PageBean" %>
<%@ page import="com.liangweimin.www.po.Release" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liangweimin.www.service.TeacherService" %>
<%@ page import="com.liangweimin.www.po.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/4/2
  Time: 22:11
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

<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" >
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 23px;" >
    <li class="active">
        <a href="FindReleaseByPageServlet">出行计划</a>
    </li>
    <li>
        <a href="ProcessRequestServlet">处理请求</a>
    </li>
    <li>
        <a href="setAppointment.jsp">发布预约</a>
    </li>
    <li>
        <a href="FindAllChatByTeacherServlet">聊天记录</a>
    </li>
    <li>
        <a href="FindAllNoticesByTeacherServlet">通知</a>
    </li>
</ul>


<%
    //获取当前的预约范围scope
    int id = (int) session.getAttribute("id");
    TeacherService teacherService = new TeacherService();
    Teacher teacher = teacherService.queryTeacherById(id);
    int scope = teacher.getScope();
%>

<h4>
    <form class="form-inline" action="AppointScopeServlet" method="post">
        学生可预约未来 <input style="width:50px" type="text" name="scope" class="form-control"
                       placeholder="<%=scope%>"> 天的时间段
        <input type="submit" value="更改">
    </form>
</h4>


<table border="1" class="table table-hover">

    <tr>
        <th>职工号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>所属学院</th>
        <th>联系电话</th>
        <th>见面时间</th>
        <th>见面地点</th>
        <th>操作</th>
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
        <td><a href="QueryReleaseServlet?id=<%=u.getId()%>&appointTime=<%=u.getAppointTime()%>&place=<%=u.getPlace()%>">修改</a>
        </td>
        <td>
            <a href="DeleteReleaseServlet?id=<%=u.getId()%>&appointTime=<%=u.getAppointTime()%>&place=<%=u.getPlace()%>">删除</a>
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
            <a href="FindReleaseByPageServlet?currentPage=<%=pb.getCurrentPage()-1%>&rows=5" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <%
            for (int i = 1; i <= pb.getTotalPage(); i++) {
                if (pb.getCurrentPage() == i) {
        %>
        <li class="active"><a href="FindReleaseByPageServlet?currentPage=<%=i%>&rows=5"><%=i%>
        </a></li>
        <%
        } else {
        %>
        <li><a href="FindReleaseByPageServlet?currentPage=<%=i%>&rows=5"><%=i%>
        </a></li>
        <%
                }
            }
        %>

        <li>
            <a href="FindReleaseByPageServlet?currentPage=<%=pb.getCurrentPage()+1%>&rows=5" aria-label="Next">
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

