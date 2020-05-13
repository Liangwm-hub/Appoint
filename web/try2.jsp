<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.net.URLDecoder" %>
<%--<%@page isELIgnored="false"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>私聊导师</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url("${pageContext.request.contextPath}/img/背景.jpg") no-repeat;
        }
    </style>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>聊天窗口</title>


    <input type="hidden" id="userName" value="${param.userName}">;
    <input type="hidden" id="teacherName" value="${param.teacherName}">;

    <script type="text/javascript" src="jquery-3.4.1.min.js"></script>
    <script type="text/javascript">

        $(function () {

            //用于保存当前本地最新的聊天记录ID值，初始值为0，目的是第一次加载时获取全部聊天记录
            var finalMessageId = 0;

            //获取传过来的chatId,学生姓名,导师姓名
            var chatId = ${param.chatId};
            var userName = document.getElementById("userName").value;
            var teacherName = document.getElementById("teacherName").value;

            askForNew();

            //声明函数：询问服务器端是否存在新的聊天记录
            function askForNew() {

                $.post("AskByUserServlet", {"finalMessageId": finalMessageId, "chatId": chatId}, function (hasNew) {

                    if (hasNew === 'true') {

                        //给服务器端发送请求，获取最新的聊天记录
                        getNew();
                    }

                }, "text");

                //注意：一定要使用函数的引用，不能加()
                setTimeout(askForNew, 2000);

            }

            //声明函数：获取新的聊天记录内容
            function getNew() {

                var $content = $("#content");

                $.post("GetNewByUserServlet", {
                    "finalMessageId": finalMessageId,
                    "chatId": chatId
                }, function (newMessage) {

                    for (var i = 0; i < newMessage.length; i++) {

                        var messageId = newMessage[i].messageId;
                        var senderIdentity = newMessage[i].senderIdentity;
                        var messageContent = newMessage[i].messageContent;
                        var createTime = newMessage[i].createTime;

                        if (senderIdentity === "学生") {
                            var htmlStr = "<div align='right'>" + " [" + createTime + "] " + userName + "<br>" + messageContent + "</div>" + "<br>";
                        } else {
                            var htmlStr = "<div>" + teacherName + " [" + createTime + "] " + "<br>" + messageContent + "</div>" + "<br>";
                        }

                        console.log(htmlStr);
                        $content.append(htmlStr);

                        finalMessageId = messageId;

                    }

                    //获取#showMessage对应的DOM对象，通过scrollTop属性设置滚动条的显示位置
                    $content[0].scrollTop = 10000000;

                }, "json");

            }

            //给多行文本框绑定键盘按下事件
            $("#txt").keypress(function (event) {

                //在用户按下回车键时，发送聊天消息
                //通过事件对象的keyCode属性获取当前按下的键对应的ASCII码
                if (event.keyCode === 13) {

                    event.preventDefault();

                    //获取聊天消息的内容
                    var messageContent = $.trim(this.value);

                    //内容不能为空
                    var p = new RegExp(/\s+/g);
                    if (messageContent != null && messageContent !== "" && messageContent !== p) {

                        //使用Ajax技术将聊天消息发送到服务器端
                        $.post("SendMessageByUserServlet", {
                            "chatId": chatId,
                            "userName": userName,
                            "teacherName": teacherName,
                            "messageContent": messageContent
                        });

                    } else {
                        alert("请输入有效信息")
                    }

                    //清空多行文本框
                    $("#txt").val("");

                }

            });



            var message;
            var isPicture;
            $('#picture').on('input propertyChange', function () {
                message = document.getElementById('picture');
                alert(message);
            });
            //实时获得文本区域的值
            if (message == null || message === "") {
                $('#txt').on('input propertyChange', function () {
                    message = $(this).val();
                });
                isPicture = false;
            } else {
                isPicture = true;
            }

            //给按钮绑定单击事件
            $("#btn").click(function (event) {

                //内容不能为空
                var p = new RegExp(/\s+/g);
                if (message != null && message !== "" && message !== p) {

                    if (isPicture) {
                        alert("AAA");
                        var formData = new FormData();
                        formData.append("attrName", message.files[0]);

                        alert(message);

                        $.ajax({
                            type: "POST",
                            url: "MServlet",
                            data: formData,
                            async: false,
                            cache: false,
                            contentType: false,
                            processData: false,
                            success: function (msg) {
                                if (msg) {
                                    alert('提交成功！');
                                }
                            }
                        });

                    } else {

                        alert("BBB");
                        //使用Ajax技术将聊天消息发送到服务器端
                        $.post("SendMessageByUserServlet", {
                            "chatId": chatId,
                            "userName": userName,
                            "teacherName": teacherName,
                            "messageContent": message
                        });
                    }
                } else {
                    alert("请输入有效信息")
                }

                //清空文本区域的内容
                $("#txt").val("");
                message = "";

            });


        });


    </script>
</head>
<body>

<style type="text/css">

    #container {

        width: 600px;

        height: 430px;

        border: 1px solid #7b6b6b;

        margin: 60px auto;

        position: relative;

    }

    #content {

        width: 600px;

        height: 377px;

        border-bottom: 1px solid #ccc;

        overflow-y: auto;

    }

    #content ul {

        margin: 0;

        padding: 0;

    }

    #btn_picture {

        position: absolute;

        top: 383px;
    }

    #txt {

        margin: 0;

        position: absolute;

        /*left: 5px;*/
        left: 43px;

        top: 383px;

        border-radius: 2px;

        border: 1px solid #ccc;

        /*width: 505px;*/
        width: 490px;

        height: 35px;

    }

    #btn {

        /*margin-right: 10px;*/

        position: absolute;

        margin: 0;

        /*left: 524px;*/
        left: 540px;

        top: 383px;

    }

    #sendMessage {

        background: #ece7e766;

        width: 600px;

        height: 50px;

    }

    #scroll {

        position: relative;

    }

    /*隐藏原input*/
    .upload-btn {
        display: none;
    }

    /*美化自定义按钮*/
    .file-input-trigger {
        padding: 2px 5px;
        border-radius: 5px;
        border: 1px solid #3888C7;
        background-color: #3888C7;
        outline: none;
        color: #fff;
        cursor: pointer;
    }

    label {
        position: relative;
    }

    #fileinp {
        position: absolute;
        left: 0;
        top: 0;
        opacity: 0;
    }

    #btn {
        margin-right: 5px;
    }

    #text {
        color: red;
    }

</style>

<div id="container">

    <div id="content">

        <div id="scroll">

            <ul id="save"></ul>

        </div>

    </div>


    <div id="sendMessage">
        <%--        <button id="btn_picture" type="button" οnclick="input_picture" class="btn btn-default" aria-label="Left Align">--%>
        <%--            <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>--%>
        <%--        </button>--%>
        <%--        <input id="input_picture" hidden type="file" >--%>
        <%--        <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>--%>

        <%--    <input type="file" class="upload-btn" hidden="hidden">--%>
        <%--    <span type="text" class="file-input-trigger">选择文件</span>--%>
        <%--    <p class="file-name"></p>--%>

        <%--    <label for="fileinp">--%>
        <%--        <input type="button" id="bt" value=""><span id="text">请上传Word文档</span>--%>
        <%--        <input type="file" id="fileinp">--%>
        <%--    </label>--%>
        <textarea id="txt" class="form-control"></textarea>
        <button id="btn" class="btn btn-primary">发送</button>
    </div>

</div>

</body>
</html>