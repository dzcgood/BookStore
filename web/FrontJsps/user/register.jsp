<%--
  Created by IntelliJ IDEA.
  User: 邓智超
  Date: 2021/5/7
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<div class="padding-all">
    <div class="header">
        <h1>注册</h1>
    </div>

    <div class="design-w3l">
        <div class="mail-form-agile">
            <p style="color: red; font-weight: 900">${message}</p>
            <form action="../../UserServlet" method="post">
                <input type="hidden" name="method" value="register"/>
                <input type="text" name="username"  placeholder="User Name " value="${form.username}"/>	&nbsp;&nbsp;&nbsp;<span style="color: red; font-weight: 900">${errors.username }</span><br/>

                <input type="password" name="user_password" class="padding" placeholder="Password"  value="${form.user_password}"/>	&nbsp;&nbsp;&nbsp;<span style="color: red; font-weight: 900">${errors.user_password }</span><br/>

                <input type="text" name="user_email"placeholder="Email" value="${form.user_email}"/>	&nbsp;&nbsp;&nbsp;<span style="color: red; font-weight: 900">${errors.user_email }</span><br/>

                <input type="submit" value="SIGN UP"/>
            </form>
        </div>
        <div class="clear"> </div>
    </div>

</div>
</body>
</html>
