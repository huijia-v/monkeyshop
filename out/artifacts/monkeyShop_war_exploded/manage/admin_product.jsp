<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="admin_menue.jsp" %>
<title>用户菜单</title>
<!--/sidebar-->
<div class="main-wrap">
    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">分类管理</span></div>
    </div>

    <div class="result-wrap">
        <form action="/monkeyShop/manage/admin_douserdel" id="myform" method="post">
            <div class="result-title">
                <div class="result-list">
                    <a href="/monkeyShop/manage/admin_toproductadd"><i class="icon-font"></i>新增图书</a>
                    <%--                    <a id="batchDel" href="javascript:delmore('你确定删除这些用户吗？', 'myform')"><i class="icon-font"></i>批量删除</a>--%>
                    <!--  a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a-->
                </div>
            </div>
            <div class="result-content">
                <table class="result-tab" width="40%">
                    <tr>
                        <th>ID</th>
                        <th>商品名称</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="p" items='${plist}'>
                        <tr>
                            <td>${p.product_id}</td>
                            <td><img src="../images/product/${p.product_filename}" width="80" height="80">
                                ${p.product_name}
                            </td>
                            <td>
                                <a href="">修改</a>
                                <a href="">删除</a>
                            </td>
                        </tr>

                    </c:forEach>
                    <script>
                        function catedel(id){
                            if(confirm("你确定要删除这个分类 吗?")){
                                location.href="admin_docatedel?id="+id;
                            }
                        }


                    </script>


                </table>
                <div class="list-page">

                </div>

            </div>
        </form>
    </div>
</div>
<!--/main-->
</div>
</body>
</html>