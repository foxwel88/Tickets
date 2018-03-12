<%@ taglib prefix="MyTag" uri="/WEB-INF/tlds/MyTag.tld"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.OrderListBean" %>

<MyTag:checkSession>
<html>
	用户<%
		String userName = (String)request.getAttribute("userName");
		out.print(userName);
	%>
	的订单列表
	<table>
		<style>
			table td {
				text-align: center
			}
		</style>

		<tr>
			<th>ID</th>
			<th>货品名称</th>
			<th>订购数量</th>
			<th>单价</th>
			<th>订单总价</th>
			<th>下单时间</th>
		</tr>
		<%
			OrderListBean orderList = (OrderListBean)request.getAttribute("orderList");
		%>
		<jsp:useBean id="order" class="edu.nju.tickets.model.OldOrder" scope="page"></jsp:useBean>
		<%
			for (int i = 0; i < orderList.getOrderList().size(); ++i) {
				pageContext.setAttribute("order", orderList.getOrderList().get(i));

		%>
				<tr>
					<td><jsp:getProperty name="order" property="id" /></td>"
					<td><jsp:getProperty name="order" property="name" /></td>"
					<td><jsp:getProperty name="order" property="num" /></td>"
					<td><jsp:getProperty name="order" property="price" /></td>"
					<td><jsp:getProperty name="order" property="totalPrice" /></td>"
					<td><jsp:getProperty name="order" property="time" /></td>"
					<%
						if (orderList.getOrderList().get(i).getIsOK() == 0) {
							out.print("<td><p style='color:red'>缺货</p></td>");
						}
					%>
				</tr>
		<%}%>
	</table>

	<form action='/Main'>
		<%
			int pageNum = (Integer) request.getAttribute("pageNum");
			int pageID = (Integer) request.getAttribute("pageID");
			for (int i = 1; i <= pageNum; ++i) {
				if (i == pageID) {
		%>
					<button style='color:blue' name='pageID' value="<%=i%>">
						<%=i%>
					</button>
		<%
				} else {
		%>
					<button name='pageID' value="<%=i%>">
						<%=i%>
					</button>
		<%
				}
			}
		%>
	</form>

	<form action='/Login'>
		<button name='logout' value='logout'>登出</button>
	</form>

	<%
		int allNum = (Integer) request.getAttribute("allNum");
		int loginNum = (Integer) request.getAttribute("loginNum");
		int otherNum = (Integer) request.getAttribute("otherNum");
	%>
	<p>总人数<%=allNum%></p>
	<p>登陆人数<%=loginNum%></p>
	<p>游客人数<%=otherNum%></p>


</html>
</MyTag:checkSession>