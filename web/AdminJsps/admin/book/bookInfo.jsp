<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>

	  <script type="text/javascript">
		  function setMethod(method) {
			var  ele = document.getElementById("method");
			ele.value = method;
		  }
	  </script>

  </head>
  
  <body>
  <div>
    <img src="<c:url value='/${book.book_image}'/>" border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="<c:url value='/AdminBookServlet'/> " method="post">
	  <input type="hidden" name="method" value="" id = "method"/>
	  <input type="hidden" name="book_id" value="${book.book_id}">
	  <input type="hidden" name="book_image" value="${book.book_image}">
  	图书名称：<input type="text" name="book_name" value="${book.book_name}"/><br/><br/>
  	图书单价：<input type="text" name="book_price" value="${book.book_price}"/>元<br/><br/>
  	图书作者：<input type="text" name="book_author" value="${book.book_author}"/><br/><br/>
  	图书分类：<select style="width: 150px; height: 20px;" name="category_id">

	  <c:forEach items="${categoryList}" var="c" >
		<%--	selected ?	  --%>
		  <option value="${c.category_id}" <c:if test="${c.category_id eq book.category.category_id}">selected="selected"</c:if> >${c.category_name}</option>
	  </c:forEach>

    	</select><br/><br/>
  	<input type="submit" value="删除" onclick="setMethod('delete');return confirm('删除成功！')"/>
  	<input type="submit" value="编辑" onclick="setMethod('edit');return confirm('修改成功!')"/>
  </form>
  </body>
</html>
