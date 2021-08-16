<%--
  Created by IntelliJ IDEA.
  User: huijia
  Date: 2021/7/24
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl">
            <a href="index.html"><img src="img/logo.png"/></a></h1>

            <div class="fr clearfix" id="top1"><p class="fl">
                <c:if test="${islogin !=1 }">

                    <a href="login.jsp" id="login">登录</a><a href="register.jsp" id="reg">注册</a>
                </c:if>

                <c:if test="${islogin ==1 }">

                    你好: <a href="login.jsp" id="login">${name.user_name }</a>
                </c:if>

                <c:if test="${isAdminLogin  ==1 }">

                    <a href="manger/admin_index.jsp" id="login">进入后台管理</a>
                </c:if>


            </p>

                <form action="#" method="get" class="fl"><input type="text" placeholder="热门搜索：干花花瓶"/><input
                        type="button"/></form>
                <div class="btn fl clearfix"><a href="mygxin.jsp"><img src="img/grzx.png"/></a><a href="#" class="er1">

                    <img src="img/ewm.png"/></a>
                    <c:if test="${isLogin ==1 }">
                        <a href="cart.html">  <img src="img/gwc.png"/></a>
                    </c:if>
                    <p><a href="#"><img src="img/smewm.png"/></a></p></div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="indexselect">首页</a></li>

            <c:forEach var="f" items="${flist }">

                <li><a href="selectproductlist?fid=${f.cate_id }">${f.cate_name }</a>
                    <div class="sList2">
                        <div class="clearfix">

                            <c:forEach var="c" items="${clist }">

                                <c:if test="${f.cate_id == c.cate_parent_id }">
                                    <a href="selectproductlist?cid=${c.cate_id }">${c.cate_name }</a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </li>
            </c:forEach>


        </ul>
    </div>
</div>