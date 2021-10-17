<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单详细</title>

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
        * {
            font-size: 11pt;
        }

        div {
            border: solid 2px gray;
            width: 75px;
            height: 75px;
            text-align: center;
        }

        li {
            margin: 10px;
        }

        #pay {
            background: url(<c:url value='/Image/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -412px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }

        #pay:HOVER {
            background: url(<c:url value='/Image/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -448px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }
    </style>
</head>

<body>
<h1>当前订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
    <tr bgcolor="gray" bordercolor="gray">
        <td colspan="6">
            订单编号：${order.order_id}　成交时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${order.order_time}"/>
            金额：<font color="red"><b>${order.order_total}元</b></font>
        </td>
    </tr>
    <c:forEach items="${order.orderItemList}" var="orderItem">
        <tr bordercolor="gray" align="center">
            <td width="15%">
                <div><img src="<c:url value='/${orderItem.book.book_image}'/>" height="75"/></div>
            </td>
            <td>书名:${orderItem.book.book_name}</td>
            <td>单价:${orderItem.book.book_price}</td>
            <td>作者:${orderItem.book.book_author}</td>
            <td>数量:${orderItem.item_count}</td>
            <td>小计:${orderItem.item_subtotal}元</td>
        </tr>
    </c:forEach>


</table>
<br/>
<form method="post" action="OrderServlet" id="form" target="_parent">
    <input type="hidden" name="method" value="pay"/>
    <input type="hidden" name="order_id" value="${order.order_id}">
    收货地址：<input type="text" name="address" size="50" value="广东省广州市华南师范大学计算机学院邓智超收"/><br/>
    <br/>
    选择银行：<br/>
    <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
    <img src="<c:url value="/Image/Banks/icbc.bmp"/>" align="middle"/>
    <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
    <img src="<c:url value="/Image/Banks/bc.bmp"/>" align="middle"/><br/><br/>
    <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
    <img src="<c:url value="/Image/Banks/abc.bmp"/>" align="middle"/>
    <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
    <img src="<c:url value="/Image/Banks/ccb.bmp"/>" align="middle"/><br/><br/>
    <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
    <img src="<c:url value="/Image/Banks/bcc.bmp"/>" align="middle"/><br/>
    <input type="submit" name="pay" value="模拟付款">
</form>

</body>
</html>

