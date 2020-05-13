<%--
  Created by IntelliJ IDEA.
  User: 梁伟民
  Date: 2020/4/24
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>

<head>

    <meta charset="utf-8">

    <title>聊天对话框</title>

    <style type="text/css">

        #container {

            width: 250px;

            height: 350px;

            border: 1px solid #7b6b6b;

            margin: 0 auto;

            position: relative;

        }

        #content {

            width: 250px;

            height: 300px;

            border-bottom: 1px solid #ccc;

            overflow-y: auto;

        }

        #content ul {

            margin: 0;

            padding: 0;

        }

        #Img {

            width: 30px;

            height: 30px;

            position: absolute;

            left: 10px;

            top: 310px;

            border-radius: 15px;

        }

        #txt {

            margin: 0;

            position: absolute;

            left: 50px;

            top: 315px;

            border-radius: 2px;

            border: 1px solid #ccc;

            width: 133px;

            height: 18px;

        }

        #btn {

            margin-right: 10px;

            position: absolute;

            margin: 0;

            left: 197px;

            top: 314px;

        }

        #edit {

            background: #ece7e766;

            width: 250px;

            height: 50px;

        }

        .showTxt {

            width: auto;

            height: auto;

            max-width: 230px;

            background: #008000a8;

            border: 0;

            font-size: 15px;

            color: white;

            padding: 5px;

            border-radius: 2px;

            list-style: none;

            margin-top: 5px;

            display: list-item;

        }

        .left {

            text-align: left;

            margin-left: 50px;

            float: left;

        }

        .right {

            text-align: right;

            margin-right: 50px;

            float: right;

        }

        .showImg {

            width: 26px;

            height: 26px;

            border-radius: 13px;

        }

        .leftImg {

            left: 10px;

            position: absolute;

        }

        .rightImg {

            right: 10px;

            position: absolute;

        }

        #scroll {

            position: relative;

        }

    </style>

</head>

<body>

<div id="container">

    <div id="content">

        <div id="scroll">

            <ul id="save"></ul>

        </div>

    </div>

    <div id="edit">

        <input type="text" name="" id="txt">

        <input type="button" name="" value="发送" id="btn">

    </div>

</div>

<script type="text/javascript">

    //获取元素

    var oCont = document.getElementById('content');

    var oImg = document.getElementById('Img');

    var oTxt = document.getElementById('txt');

    var oBtn = document.getElementById('btn');

    var oSTxt = document.getElementsByClassName('showTxt');

    var oSave = document.getElementById('save');

    var num = 0;

    //切换头像

    oImg.οnclick = function () {

        num++;

        if (num % 2 == 0)

            oImg.src = '1.jpg';

        else

            oImg.src = '2.jpg';

    }

    //发送事件

    oBtn.οnclick = function () {

        addCon();

    }

    function addCon() {

//定义需要添加的元素

        var newLi = document.createElement("li");

        var newImg = document.createElement('img');

//判断聊天的对象是哪一方，文字框出现在左边还是右边

        if (num % 2 == 0) {

//添加对话框

            newLi.innerHTML = oTxt.value;

            newLi.className = 'showTxt right';

            oSave.appendChild(newLi);

            oTxt.value = '';

            //添加头像

            newImg.src = oImg.src;

            newImg.className = 'showImg rightImg';

            newLi.appendChild(newImg);

            //清除浮动

            var div = document.createElement('div');

            div.style = 'clear:both';

            oSave.appendChild(div);

        } else {

            newLi.innerHTML = oTxt.value;

            newLi.className = 'showTxt left';

            oSave.appendChild(newLi);

            oTxt.value = '';

            newImg.src = oImg.src;

            newImg.className = 'showImg leftImg';

            newLi.appendChild(newImg);

            var div = document.createElement('div');

            div.style = 'clear:both';

            oSave.appendChild(div);

        }

    }

</script>

</body>

</html>
