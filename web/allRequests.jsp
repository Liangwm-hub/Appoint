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

    <script>
        window.onload = function () {
            //给删除选中按钮添加单击事件
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

<%--导航条--%>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#"><font color="black" size="4em">处理预约请求</font></a></li>
    <li role="presentation"><a href="FindRegisterByPageServlet"><font color="black" size="4em">审批用户注册</font></a></li>
    <li role="presentation"><a href="teacherRegister.jsp"><font color="black" size="4em">注册导师账号</font></a></li>
    <li role="presentation"><a href="FindFreezeUserByPageServlet"><font color="black" size="4em">冻结的账号</font></a></li>
    <li role="presentation"><a href="notice.jsp"><font color="black" size="4em">发布通知</font></a></li>
    <li role="presentation"><a href="FindAllNoticesByManagerServlet"><font color="black" size="4em">已发布的通知</font></a></li>
</ul>
<br>

<div style="float: right">
    <a class="btn btn-primary" href="javascript:void(0);" id="processSelected">批准选中</a>
</div>
<br><br>


<form id="form" action="ProcessSelectedByManagerServlet" method="post">
    <table border="1" class="table table-hover">
        <tr>
            <th><input type="checkbox" id="firstCb"></th>
            <th>导师职工号</th>
            <th>导师姓名</th>
            <th>学生学号</th>
            <th>学生姓名</th>
            <th>见面时间</th>
            <th>见面地点</th>
            <th>预约状态</th>
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
            <td><%=u.getId()%>
            </td>
            <td><%=u.getTeacherName()%>
            </td>
            <td><a href="FindIllegalUserServlet?sno=<%=u.getSno()%>"> <%=u.getSno()%> </a>
            </td>
            <td><%=u.getUserName()%>
            </td>
            <td><%=u.getAppointTime()%>
            </td>
            <td><%=u.getPlace()%>
            </td>
            <td><%=u.getStatus()%>
            </td>
            <td><a href="ProcessSelectedByManagerServlet?num=<%=u.getNum()%>">批准</a>
            </td>
            <td>
                <a href="RefuseSelectedByManagerServlet?num=<%=u.getNum()%>">拒绝</a>
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
            <a href="AllRequestsServlet?currentPage=<%=pb.getCurrentPage()-1%>&rows=10" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <%--        <c:forEach begin="1" end="${pb.totalPage}" var="i">--%>
        <%--            <li><a href="#">${i}</a></li>--%>
        <%--        </c:forEach>--%>

        <%
            for (int i = 1; i <= pb.getTotalPage(); i++) {
                if (pb.getCurrentPage() == i) {
        %>
        <li class="active"><a href="AllRequestsServlet?currentPage=<%=i%>&rows=10"><%=i%>
        </a></li>
        <%
        } else {
        %>
        <li><a href="AllRequestsServlet?currentPage=<%=i%>&rows=10"><%=i%>
        </a></li>
        <%
                }
            }
        %>

        <li>
            <a href="AllRequestsServlet?currentPage=<%=pb.getCurrentPage()+1%>&rows=10" aria-label="Next">
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
