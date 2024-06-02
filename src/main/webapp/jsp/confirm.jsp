<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderSlipBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キャンセル確認</title>
</head>
<body>
	<!-- mojicode siteisitemiru -->
	<%request.setCharacterEncoding("UTF-8"); %>
	<%response.setContentType("text/html;charset=UTF-8"); %>
	
	<!-- session getAttribute -->
	<%int orderId = (int)session.getAttribute("orderId"); %>
	<%String customerName = (String)session.getAttribute("customerName"); %>
	<%String orderDate = (String)session.getAttribute("orderDate"); %>
	<%ArrayList<OrderSlipBean> cancelList = (ArrayList<OrderSlipBean>)session.getAttribute("cancelList"); %>
	<%String cancelComment = (String)session.getAttribute("cancelComment"); %>
	<!-- getAttribute from ConfirmServlet-->
	<%String message = (String)request.getAttribute("message"); %>
	<%String code = (String)session.getAttribute("code"); %>
	
	<!-- title -->
	<h1 align="center">
	<%if(("add").equals(code)){ %>
		<%="受注追加" %>
	<%}else if(("orderChange").equals(code)){ %>
		<%="受注内容変更" %>
	<%}else if(("cancelChange").equals(code)){ %>
		<%="キャンセル変更" %>
	<%}else if(("refundChange").equals(code)){ %>
		<%="返品変更" %>
	<%}else if(("newCancel").equals(code)){ %>
		<%="キャンセル確認" %>
	<%}else if(("newRefund").equals(code)){ %>
		<%="返品確認" %>
	<%} %>
	</h1>
	
	<!-- キャンセル確認 code=cancel -->
	<!-- 返品確認 code=refund -->
	
	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	
	<form action="dbUpdate" method="post">
		<table>
			<tr><td>注文番号</td></tr>
			<tr><td><%= orderId%></td></tr>
		</table>
		<table>
			<tr><td>取引先名</td></tr>
			<tr><td><%= customerName%></td></tr>
		</table>
		<table>
			<tr><td>注文日</td></tr>
			<tr><td><%= orderDate%></td></tr>
		</table>
		
		<table border="1">
			<tr>
				<td>商品ID</td>
				<td>商品名</td>
				<td>注文数</td>
				<td>キャンセル数</td>
				<td>最終注文数</td>
			</tr>
			<%if(cancelList != null){ %>
			<%for(OrderSlipBean cancelItem:cancelList){%>
				<tr>
					<td><%= cancelItem.getProductId()%></td>
					<td><%= cancelItem.getProductName() %></td>
					<td><%= cancelItem.getOrderQty() %></td>
					<td><%= cancelItem.getCancelQty() %></td>
					<td><%= cancelItem.getOrderQty() - cancelItem.getCancelQty() - cancelItem.getRefundQty() %></td>
				</tr>
			<% }%>
		<%} %>
		</table>
		<br>
		<table>
			<tr>
				<td>キャンセル理由</td>
			</tr>
			<tr>
				<td>
					<%if(cancelComment != ""){ %>
						<%=cancelComment %>
					<%}else {%>
						<%="コメントはありません" %>
					<%} %>	
				</td>
			</tr>
		</table>
		<input type="submit" name="pageFlag" value="確定">
		<input type="button" name="pageFlag" value="戻る">
	</form>
</body>
</html>