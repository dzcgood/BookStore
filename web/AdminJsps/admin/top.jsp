<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background: rgb(78,78,78);color: white;">
  <h1 style="text-align: center; ">DzcGood BookStore 后台管理</h1>
  <div style="font-size: 10pt;">
      <c:choose>
          <c:when test="${empty sessionScope.session_admin}">
              <a href="<c:url value="/AdminJsps/login.jsp"/>" target="_parent">管理员登录</a>
          </c:when>
          <c:otherwise>
              管理员：${sessionScope.session_admin.admin_name}&nbsp;&nbsp;|&nbsp;&nbsp;
              <a href="<c:url value="/AdminServlet?method=quit"/>" target="_parent">退出</a>
              <br/>
          </c:otherwise>
      </c:choose>
  </div>
  </body>
</html>
