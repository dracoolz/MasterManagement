<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ProductBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受注追加</title>
</head>
<body>
	<!-- getAttribute from OrderAddservlet -->
	<% ArrayList<ProductBean> productList = (ArrayList<ProductBean>) session.getAttribute("productList");%>
	<% String customerName = (String)session.getAttribute("customerName"); %>
	<% String errMsg = (String)request.getAttribute("errMsg"); %>
	
	<!-- title -->
	<h1 align="center">受注追加</h1>

	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	<form action="?" method="post">
		<input type="textbox" name="customerId" placeholder="取引先ID検索へ">
		<input type="submit" name="pageFlag" value="追加" formaction="orderAdd">
		<input type="submit" name="pageFlag" value="取引先検索へ" formaction="searchCustomer/?url=orderAdd">
		<br>取引先名：<input type="textbox" name="customer_name" value="<%if(customerName != null){%><%=customerName%><%} %>" readonly>
		<input type="submit" name="pageFlag" value="削除" formaction="orderAdd">
	</form>
	<br>
	
	<form action="" method="post">
		<input type="textbox" name="product_id" placeholder="商品ID検索">
		<input type="submit" name="submit" value="追加">
		<input type="submit" name="submit" value="商品検索へ">
	</form>
	<br>
	
	<form action="" method="post">
		<table border="1">
			<tr>
				<td>商品ID</td>
				<td>商品名</td>
				<td>注文数</td>
				<td></td>
			</tr>
			<%if(productList != null) {%>
				<%for(ProductBean product:productList){%>
				<tr>
					<td><%= product.getPro_id()%></td>
					<td><%= product.getPi_name() %></td>
					<td><input type="number" name="orderQty" value="1" min="1" max=""></td>
					<td><input type="submit" name="submit" value="削除"></td>
				</tr>
				<% }%>
			<% }%>
		</table>
		<%if(errMsg != null){ %>
			<%=errMsg %>
		<%} %>
		<br>
		
		<input type="submit" name="submit" value="確認">
		<input type="button" name="back" value="戻る">
	</form>
	
	
	<!-- mojicode siteisitemiru -->
	<%--request.setCharacterEncoding("UTF-8"); --%>
	<%--response.setContentType("text/html;charset=UTF-8"); --%>

	<!-- getAttribute from servlet?-->
	<%-- ArrayList<HumanBean> beanList = (ArrayList<HumanBean>) request.getAttribute("users");--%>
	
	<!-- getParameter from html or jsp 's form?-->
	<%-- String str = request.getParameter(""); --%>

	<!-- form link -->
	
</body>
</html>