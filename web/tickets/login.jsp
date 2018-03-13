<%--
  Created by IntelliJ IDEA.
  User: foxwel
  Date: 2018/3/12
  Time: 下午4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html><body>
<form method='POST' action='/Main" + "'>
用户名: <input type='text' name='userName' value='" + cookieUserName + "'>
密码: <input type='password' name='passWord' value=''>
<input type='submit' name='Submit' value='登陆'>
总人数
登陆人数
游客人数
    <%
        String userName = (String) request.getAttribute("userName");
        String passWord = (String) request.getAttribute("passWord");
    %>
    <p>总人数<%=userName%></p>
    <p>登陆人数<%=passWord%></p>
</form>
</body>
</html>