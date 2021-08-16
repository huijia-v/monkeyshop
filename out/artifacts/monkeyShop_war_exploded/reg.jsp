<%--
  Created by IntelliJ IDEA.
  User: huijia
  Date: 2021/7/21
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src=js/jquery-1.12.4.min.js></script>
    <script src="js/function.js"></script>

    <style>
        .reg P .error {
            display:inline-block;
            border:1px solid #ff855a;
            background-color:#ffe8e0;
            height:20px;
            line-heigth:25px;
            padding:0px 5px;
            margin-left:20px;
        }

    </style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form action="register" method="post" onsubmit="return checkForm(this)">
        <h1 style="padding: 0px; margin: 0px; font-size: 30px; background: #3344AA;text-align: center;line-height: 60px;color: #FFFFFF">注册</h1>
        <p><input type="text" name="userName" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="用户名"><span class="error">用户名不能为空</span></p>
        <p><input type="text" name="name" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="用户姓名"><span></span></p>
        <p><input type="text" name="passwd" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="密码"><span></span></p>
        <p><input type="text" name="rePasswd" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="确认密码"><span></span></p>
        <p>
            <input style="width:15px; height:15px" type="radio" name="sex" value="T" checked="checked">男
            <input style="width:15px; height:15px" type="radio" name="sex" value="F">女
        </p>
        <p>
            <input type="date" name="birthday" value="" placeholder="生日"><span></span> </p>
        <p><input type="text" name="email" value="" placeholder="邮箱"><span></span></p>
        <p><input type="text" name="mobile" value="" placeholder="电话号码"><span></span></p>
        <p><input type="text" name="address" value="" placeholder="地址"><span></span></p>
        <p><input class="code" type="text" name="code" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="验证码">
            <img src="getcode" alt="看不清？换一张" onclick="change(this)"><span class="error"></span></p>
        <p><input type="submit" name="" value="注册"></p>
        <p>完成此注册，即表明您同意了我们的 <a href="#">
            使用条款和隐私策略 </a>
        </p>
        <p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
</body>
</html>
