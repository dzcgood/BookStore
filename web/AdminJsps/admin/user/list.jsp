<%--
  Created by IntelliJ IDEA.
  User: 邓智超
  Date: 2021/5/22
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户信息</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        body {background: rgb(254,238,189);}
        table {font-family: 宋体; font-size: 11pt; border-color: rgb(78,78,78);  width: 60%;}
        #th {background: rgb(78,78,78);}
    </style>
</head>

<body>
<h2 style="text-align: center;">分类列表</h2>
<table align="center" border="1" cellpadding="0" cellspacing="0">
    <tr id="th" bordercolor="rgb(78,78,78)">
        <th>user_id</th>
        <th>user_name</th>
        <th>user_password</th>
        <th>user_email</th>
        <th>user_state</th>
        <th>操作</th>

    </tr>
    <c:forEach items="${userList}" var="c">
        <tr bordercolor="rgb(78,78,78)">
            <td>${c.userid}</td>
            <td>${c.username}</td>
            <td>${c.user_password}</td>
            <td>${c.user_email}</td>
            <td>${c.user_state}</td>
            <td>
                <a href="<c:url value='/AdminUserServlet?method=editPre&userid=${c.userid}'/>">修改信息</a>
            </td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
