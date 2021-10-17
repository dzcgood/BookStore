<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 邓智超
  Date: 2021/5/15
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<!--"../../UserServlet"-->
<body>
<h1>登录</h1>
<p style="color: red; font-weight: 900">${message}</p>
<form action="<c:url value="/UserServlet"/>" method="post" target="_top">
    <input type="hidden"  name = "method" value="login">
    用户名：<input type="text" name="username" value="${form.username}" />&nbsp;&nbsp;<span style="color: red; font-weight: 900">${errors.username }</span><br/>
    密　码：<input type="password" name="user_password" value="${form.user_password}"/>&nbsp;&nbsp;<span style="color: red; font-weight: 900">${errors.user_password }</span><br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
