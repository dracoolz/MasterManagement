<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderSlipBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受注追加</title>
</head>
<body>
	<!-- getAttribute from OrderAddservlet -->
	<% ArrayList<OrderSlipBean> addSlip = (ArrayList<OrderSlipBean>) session.getAttribute("addSlip");%>
	<% String customerName = (String)session.getAttribute("addCustomerName"); %>
	<% String customerErrMsg = (String)request.getAttribute("customerErrMsg"); %>
	<% String productErrMsg = (String)request.getAttribute("productErrMsg"); %>
	<% String errMsg = (String)request.getAttribute("errMsg"); %>
	<% String message = (String)request.getAttribute("message"); %>
	
	<!-- title -->
	<h1 align="center">受注追加</h1>

	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	<form action="?" method="post">
		<input type="textbox" name="selCustomerId" placeholder="取引先ID検索">
		<input type="submit" name="customerFlag" value="追加" formaction="orderAdd">
		<input type="submit" name="customerFlag" value="取引先検索へ" formaction="searchCustomer/?url=orderAdd">
		<br>取引先名：<input type="textbox" name="addCustomerName" value="<%if(customerName != null){%><%=customerName%><%} %>" readonly>
		<input type="submit" name="customerFlag" value="削除" formaction="orderAdd">
		<br>
		<input type="textbox" name="selProductId" placeholder="商品ID検索へ">
		<input type="submit" name="productFlag" value="追加" formaction="orderAdd">
		<input type="submit" name="productFlag" value="商品検索へ">
		<br>
	
		<table border="1">
			<tr>
				<td>商品ID</td>
				<td>商品名</td>
				<td>注文数</td>
				<td></td>
			</tr>
			<%if(addSlip != null){ %>
				<%for(OrderSlipBean product:addSlip){%>
					<tr>
						<td><%= product.getProductId()%></td>
						<td><%= product.getProductName() %></td>
						<td><input type="number" name="orderQty" value="<%=product.getOrderQty() %>" min="1" max=""></td>
						
						<form action="?" method="post">
							<input type="hidden" name="delProductId" value="<%= product.getProductId()%>">
							<td><input type="submit" name="productFlag" value="削除" formaction="orderAdd"></td>
						</form>
					</tr>
				<% }%>
			<%} %>
		</table>
		<%if(customerErrMsg != null){ %>
			<div style="color:red;"><%=customerErrMsg %></div>
		<%} %>
		<%if(productErrMsg != null){ %>
			<div style="color:red;"><%=productErrMsg %></div>
		<%} %>
		<%if(errMsg != null){ %>
			<div style="color:red;"><%=errMsg%></div>
		<%} %>
		<%if(message != null){ %>
			<%=message%>
		<%} %>
		<br>
		<input type="hidden" name="code" value="add">
		<input type="submit" name="pageFlag" value="確認" formaction="confirm">
		<input type="submit" name="back" value="戻る" formaction="manageMenu">
	</form>
	
</body>
</html>