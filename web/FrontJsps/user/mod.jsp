<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改你的信息</title>
    
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
</style>
      <script type="text/javascript">
          function setMethod(method) {
              var  ele = document.getElementById("method");
              ele.value = method;
          }
      </script>
  </head>
  
  <body>
    <h1>修改你的信息</h1>
    <form action="<c:url value='/UserServlet'/> " method="post" target="_top">
        <input type="hidden" name="method" value="edit"/>
        <input type="hidden" name="userid" value="${session_user.userid}"/>
        <input type="hidden" name="user_code" value="${session_user.user_code}"/>
        <input type="hidden" name="user_state" value="${session_user.user_state}"/>
        用户名：<input type="text" name="username" value="${session_user.username}" />
    	密码：<input type="text" name="user_password" value="${session_user.user_password}"/>
        邮箱：<input type="text" name="user_email" value="${session_user.user_email}"/>
    	<input onclick="return confirm('修改成功！请重新登录')"type="submit" value="修改信息"/>
    </form>
  </body>
</html>
