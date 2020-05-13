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


    <script type="text/javascript" src="jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {

            var message;
            //实时获得文本区域的值
            $('#picture').on('input propertyChange', function () {
                message = document.getElementById('picture');

                alert(message);
                // var blob = window.URL.createObjectURL(message);
                // $("#img").attr('src', blob);//将blob字符串 赋予给 img标签  即可完成图片的预览
            });

            //给按钮绑定单击事件
            $("#btn").click(function (event) {
                alert("AASS")
                // //内容不能为空
                // var p = new RegExp(/\s+/g);
                // if (message != null && message !== "" && message !== p) {
                //
                //     //使用Ajax技术将聊天消息发送到服务器端
                //     $.post("SendMessageByUserServlet", {
                //         "chatId": chatId,
                //         "userName": userName,
                //         "teacherName": teacherName,
                //         "messageContent": message
                //     });
                // } else {
                //     alert("请输入有效信息")
                // }


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


                //清空文本区域的内容
                $("#txt").val("");
                message = "";


            });


        });
    </script>

</head>
<body>

<%--<input type="file" style="display:none">--%>
<%--<button id="btn" type="button" οnclick="input_picture" class="btn btn-default" aria-label="Left Align">--%>
<%--    <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>--%>
<%--</button>--%>

<label class="btn">
    <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>
    <input type="file" id="picture">
    <button id="btn" class="btn btn-primary">发送</button>
</label>

</body>
</html>