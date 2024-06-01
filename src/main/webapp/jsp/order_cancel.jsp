<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderSlipViewBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キャンセル処理</title>
</head>
<body>
	<!-- mojicode siteisitemiru -->
	<%request.setCharacterEncoding("UTF-8"); %>
	<%response.setContentType("text/html;charset=UTF-8"); %>

	<!-- getAttribute from OrderCancelServlet-->
	<%-- ArrayList<HumanBean> beanList = (ArrayList<HumanBean>) request.getAttribute("users");--%>
	<%String orderId = (String)session.getAttribute("orderId"); %>
	<%String customerName = (String)session.getAttribute("customerName"); %>
	<%String orderDate = (String)session.getAttribute("orderDate"); %> 
	<%ArrayList<OrderSlipViewBean> orderSlipList = (ArrayList<OrderSlipViewBean>)session.getAttribute("orderSlipViewList");%>
	
	<!-- title -->
	<h1 align="center">キャンセル処理</h1>
	
	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	<form>
		<input type="submit" name="" value="受注内容全てをキャンセルする">
	</form>
	
	<table>
		<tr><td>注文番号</td></tr>
		<tr><td><%= orderId%></td></tr>
	</table>
	<table>
		<tr><td>取引先名</td></tr>
		<tr><td><%= customerName %></td></tr>
	</table>
	<table>
		<tr><td>注文日</td></tr>
		<tr><td><%= orderDate %></td></tr>
	</table>
	
	<table>
		<tr>
			<td>商品ID</td>
			<td>商品名</td>
			<td>注文数</td>
			<td>キャンセル数</td>
			<td>返品数</td>
		</tr>
		<%if(orderSlipList != null){ %>
			<%for(OrderSlipViewBean item:orderSlipList){%>
				<tr>
					<td><%= item.getProId()%></td>
					<td><%= item.getPiName() %></td>
					<td><%= item.getOrderQty() %></td>
					<td><input type="number" name="CancelQty" value="0" min="0" max="<% item.getOrderQty - item.getCancelQty - item.getRefundQty%>"></td>
					<td><%= item.getRefundQty() %></td>
					<td><input type="submit" name="submit" value="削除"></td>
				</tr>
				<% }%>
		<%} %>
	</table>
	<!-- getParameter from html or jsp 's form?-->
	<% String str = request.getParameter(""); %>

	<!-- form link -->
	<form action="/NockWeb/control" method="post">
	</form>
</body>
</html>