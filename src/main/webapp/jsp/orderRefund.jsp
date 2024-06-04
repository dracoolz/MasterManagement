<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderSlipBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>返品処理</title>
</head>
<body>
	<!-- mojicode siteisitemiru -->
	<%request.setCharacterEncoding("UTF-8"); %>
	<%response.setContentType("text/html;charset=UTF-8"); %>

	<!-- getAttribute from OrderCancelServlet-->
	<%int orderId = (int)session.getAttribute("orderId"); %>
	<%String customerName = (String)session.getAttribute("customerName"); %>
	<%String orderDate = (String)session.getAttribute("orderDate"); %> 
	<%ArrayList<OrderSlipBean> refundSlip = (ArrayList<OrderSlipBean>)session.getAttribute("refundSlip");%>
	<%String refundComment = (String)session.getAttribute("refundComment"); %>
	
	<%String errMsg = (String)request.getAttribute("errMsg"); %>
	
	<!-- title -->
	<h1 align="center">返品処理</h1>
	
	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	<form action="?" method="post">
		<input type="submit" name="pageFlag" value="受注内容全てを返品する" formaction="confirm">
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
	
	<table border="1">
		<tr>
			<td>商品ID</td>
			<td>商品名</td>
			<td>注文数</td>
			<td>キャンセル数</td>
			<td>返品数</td>
		</tr>
		<%if(refundSlip != null){ %>
			<%for(OrderSlipBean item:refundSlip){%>
				<tr>
					<td><%= item.getProductId()%></td>
					<td><%= item.getProductName() %></td>
					<td><%= item.getOrderQty() %></td>
					<td><%= item.getCancelQty() %></td>
					<td><input type="number" name="refundQty" value="<%=item.getRefundQty() %>" min="0" max="<%= item.getOrderQty() - item.getCancelQty()%>"></td>
				</tr>
			<% }%>
		<%} %>
	</table>
	<br>
	返品理由<br>
	<textarea name="refundComment" cols="100" rows="10"><%if(refundComment != null) {%><%=refundComment %><%} %></textarea><br>
	<%if(errMsg != null){ %>
		<div style="color:red;"><%=errMsg %></div><br> 
	<%} %>
	
	<input type="hidden" name="code" value="refund">
	<input type="submit" name="pageFlag" value="確認" formaction="confirm">
	<input type="button" name="back" value="戻る" onclick="location.href='orderList'">
	</form>
	
</body>
</html>