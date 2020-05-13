<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.net.URLDecoder" %>
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

                $.post("user/AskByUserServlet", {"finalMessageId": finalMessageId, "chatId": chatId}, function (hasNew) {

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

                $.post("user/GetNewByUserServlet", {
                    "finalMessageId": finalMessageId,
                    "chatId": chatId
                }, function (newMessage) {

                    for (var i = 0; i < newMessage.length; i++) {

                        var messageId = newMessage[i].messageId;
                        var senderIdentity = newMessage[i].senderIdentity;
                        var messageContent = newMessage[i].messageContent;
                        var messageType = newMessage[i].messageType;
                        var createTime = newMessage[i].createTime;

                        if (messageType === "文字") {
                            if (senderIdentity === "学生") {
                                var htmlStr = "<div align='right'>" + " [" + createTime + "] " + userName + "<br>" + messageContent + "</div>" + "<br>";
                            } else {
                                var htmlStr = "<div>" + teacherName + " [" + createTime + "] " + "<br>" + messageContent + "</div>" + "<br>";
                            }
                            $content.append(htmlStr);

                        } else {
                            var img = document.createElement("img");
                            if (senderIdentity === "学生") {

                                var htmlStr = "<div align='right'>" + " [" + createTime + "] " + userName + "</div>";
                                img.src = "upload/" + messageContent;
                                img.align = "right";
                                img.className = 'img-rounded';
                                img.style = 'max-width: 100px;max-height: 100px';

                                $content.append(htmlStr);
                                $content.append(img);
                                $content.append("<br><br><br><br>");
                                img.onclick = AddImgClickEvent();

                            } else {

                                var htmlStr = "<div>" + teacherName + " [" + createTime + "] " + "</div>";
                                img.src = "upload/" + messageContent;
                                img.className = 'img-rounded';
                                img.style = 'max-width: 100px;max-height: 100px';

                                $content.append(htmlStr);
                                $content.append(img);
                                $content.append("<br><br>");
                                img.onclick = AddImgClickEvent();
                            }
                        }

                        finalMessageId = messageId;

                    }

                    //获取#showMessage对应的DOM对象，通过scrollTop属性设置滚动条的显示位置
                    $content[0].scrollTop = 10000000;

                }, "json");

            }

            function AddImgClickEvent() {
                var objs = document.getElementsByTagName("img");
                for (var i = 0; i < objs.length; i++) {
                    objs[i].onclick = function () {
                        window.open(this.src);
                    };
                    objs[i].style.cursor = "pointer";
                }
            }


            var messageContent;
            var isPicture;
            //获得图片
            $('#picture').on('input propertyChange', function () {
                message = document.getElementById('picture');
                messageContent = document.getElementById('picture');
                isPicture = true;
            });

            if (messageContent == null || messageContent === "") {
                $('#txt').on('input propertyChange', function () {
                    messageContent = $(this).val();
                });
                isPicture = false;
            }

            //给多行文本框绑定键盘按下事件
            $("#txt").keypress(function (event) {

                //在用户按下回车键时，发送聊天消息
                //通过事件对象的keyCode属性获取当前按下的键对应的ASCII码
                if (event.keyCode === 13) {

                    event.preventDefault();

                    //内容不能为空
                    var p = new RegExp(/\s+/g);
                    if (messageContent != null && messageContent !== "" && messageContent !== p) {

                        if (isPicture) {
                            var formData = new FormData();
                            formData.append("attrName", messageContent.files[0]);

                            alert(messageContent);

                            $.ajax({
                                type: "POST",
                                url: "user/SendPictureByUserServlet?chatId=" + chatId + "&userName=" + userName + "&teacherName=" + teacherName,
                                data: formData,
                                async: false,
                                cache: false,
                                contentType: false,
                                processData: false,
                            });
                            document.getElementById('pre-img').src = "";
                            document.getElementById('picture').src = "";
                        } else {

                            //使用Ajax技术将聊天消息发送到服务器端
                            $.post("user/SendMessageByUserServlet", {
                                "chatId": chatId,
                                "userName": userName,
                                "teacherName": teacherName,
                                "messageContent": messageContent
                            });
                        }

                    } else {
                        alert("请输入有效信息")
                    }

                    //清空多行文本框
                    $("#txt").val("");
                    messageContent = "";
                    isPicture = false;

                }
            });


            var message;
            //实时获得文本区域的值
            if (message == null || message === "") {
                $('#txt').on('input propertyChange', function () {
                    message = $(this).val();
                });
                isPicture = false;
            }

            //给按钮绑定单击事件
            $("#btn").click(function (event) {

                //内容不能为空
                var p = new RegExp(/\s+/g);
                if (message != null && message !== "" && message !== p) {

                    if (isPicture) {
                        var formData = new FormData();
                        formData.append("attrName", message.files[0]);

                        $.ajax({
                            type: "POST",
                            url: "user/SendPictureByUserServlet?chatId=" + chatId + "&userName=" + userName + "&teacherName=" + teacherName,
                            data: formData,
                            async: false,
                            cache: false,
                            contentType: false,
                            processData: false,
                        });
                        document.getElementById('pre-img').src = "";
                        document.getElementById('picture').src = "";
                    } else {

                        //使用Ajax技术将聊天消息发送到服务器端
                        $.post("user/SendMessageByUserServlet", {
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
                isPicture = false;

            });


            $(document).ready(function () {
                $("#picture").change(function () {
                    var current_pic = document.getElementById('picture').files[0];
                    preview_picture(current_pic);
                });
            });

            function preview_picture(pic) {
                var r = new FileReader();
                r.readAsDataURL(pic);
                r.onload = function (e) {
                    document.getElementById('pre-img').src = this.result;
                }
            }

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


    label {
        position: relative;
    }


    #btn {
        margin-right: 5px;
    }

    #pre-img {
        position: absolute;
        left: -102px;
        top: 373px;
    }


</style>

<div id="container">

    <div id="content">

        <div id="scroll">

            <ul id="save"></ul>

        </div>

    </div>


    <div id="sendMessage">
        <label class="btn" id="btn_picture">
            <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>
            <input type="file" id="picture" style="display: none">
        </label>
        <textarea id="txt" class="form-control"></textarea>
        <button id="btn" class="btn btn-primary">发送</button>
    </div>

    <img src="" id="pre-img" style="max-width: 100px;max-height: 100px"/>
</div>

</body>
</html>