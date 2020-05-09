<%@ page import="com.liangweimin.www.bean.PageBean" %>
<%@ page import="com.liangweimin.www.po.Appoint" %>
<%@ page import="com.liangweimin.www.po.Release" %>
<%@ page import="java.util.List" %><%--
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

    <style>
        th,td{
            text-align: center;
        }
    </style>

    <script>
        window.onload = function () {
            //给批准选中按钮添加单击事件
            document.getElementById("processSelected").onclick = function () {
                //确认提交
                if (confirm("您确定要批量批准选中条目吗?")) {
                    //提交表单
                    document.getElementById("form").submit();
                }
            }

            //1.获取第一个cb
            document.getElementById("firstCb").onclick = function () {
                //2.获取下边列表中所有的cb
                var cbs = document.getElementsByName("num");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.设置cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;
                }
            }
        }

    </script>

</head>
<body>

<div style="float: left">
    <img src="${pageContext.request.contextPath}/img/GDUT.gif" style="max-height: 200px;max-width: 200px;">
</div>
<%--导航条--%>
<ul class="nav nav-tabs" style="font-size: 21px;" >
    <li>
        <a href="FindReleaseByPageServlet">出行计划</a>
    </li>
    <li class="active">
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
<br>


<div style="float: left">
    <a class="btn btn-primary" href="javascript:void(0);" id="processSelected">批准选中</a>
</div>
<br><br>

<form id="form" action="ProcessSelectedServlet" method="post">
    <table border="1" class="table table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>学号</th>
            <th>学生姓名</th>
            <th>见面时间</th>
            <th>见面地点</th>
            <th>图片</th>
            <th>私聊</th>
            <th>操作</th>
            <th>操作</th>
        <tr/>

        <%
            PageBean<Appoint> pb = (PageBean<Appoint>) request.getAttribute("pb");
            List<Appoint> lists = pb.getList();
            if (lists != null) {
                for (Appoint u :
                        lists) {
        %>

        <tr>
            <td><input type="checkbox" name="num" value="<%=u.getNum()%>">
            </td>
            <td><%=u.getSno()%>
            </td>
            <td><%=u.getUserName()%>
            </td>
            <td><%=u.getAppointTime()%>
            </td>
            <td><%=u.getPlace()%>
            </td>
            <td><a href="DownloadPictureServlet?num=<%=u.getNum()%>">查看图片</a>
            </td>
            <td><a href="ChatToUserServlet?userSno=<%=u.getSno()%>&teacherId=<%=u.getId()%>">进入聊天室</a>
            </td>
            <td><a href="ProcessSelectedServlet?num=<%=u.getNum()%>">批准</a>
            </td>
            <td>
                <a href="RefuseRequestByNumServlet?num=<%=u.getNum()%>">拒绝</a>
            </td>
        </tr>
        <%
                }
            }
        %>

    </table>
</form>

<nav aria-label="Page navigation">
    <ul class="pagination">

        <li>
            <a href="ProcessRequestServlet?currentPage=<%=pb.getCurrentPage()-1%>&rows=10" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <%
            for (int i = 1; i <= pb.getTotalPage(); i++) {
                if (pb.getCurrentPage() == i) {
        %>
        <li class="active"><a href="ProcessRequestServlet?currentPage=<%=i%>&rows=10"><%=i%>
        </a></li>
        <%
        } else {
        %>
        <li><a href="ProcessRequestServlet?currentPage=<%=i%>&rows=10"><%=i%>
        </a></li>
        <%
                }
            }
        %>

        <li>
            <a href="ProcessRequestServlet?currentPage=<%=pb.getCurrentPage()+1%>&rows=10" aria-label="Next">
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
