<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "DzcGood");
function load() {
	bar1.colorStyle = 1;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=true;
	/**图书分类管理模块*/
	bar1.add("分类管理", "查看分类", "/bookStore/AdminCategoryServlet?method=findAll", "body");
	bar1.add("分类管理", "添加分类", "<c:url value='/AdminJsps/admin/category/add.jsp'/>", "body");
	/**图书管理模块*/
	bar1.add("图书管理", "查看图书", "<c:url value='/AdminBookServlet?method=findAll'/>", "body");
	bar1.add("图书管理", "添加图书", "<c:url value='/AdminBookServlet?method=addPre'/>", "body");
	/**订单管理模块*/
	bar1.add("订单管理", "所有订单", "<c:url value='/AdminOrderServlet?method=findAll'/>", "body");
	bar1.add("订单管理", "未付款订单", "<c:url value='/AdminOrderServlet?method=getOrdersByOrder_State&order_state=1'/>", "body");
	bar1.add("订单管理", "已付款订单", "<c:url value='/AdminOrderServlet?method=getOrdersByOrder_State&order_state=2'/>", "body");
	bar1.add("订单管理", "未收货订单", "<c:url value='/AdminOrderServlet?method=getOrdersByOrder_State&order_state=3'/>", "body");
	bar1.add("订单管理", "已完成订单", "<c:url value='/AdminOrderServlet?method=getOrdersByOrder_State&order_state=4'/>", "body");
	/**用户管理模块*/
	bar1.add("用户管理","用户资料","<c:url value="/AdminUserServlet?method=findAll"/>","body" );

	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>
