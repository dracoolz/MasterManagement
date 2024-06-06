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
	
	<!-- getAttribute from ConfirmServlet-->
	<%String message = (String)request.getAttribute("message"); %>
	<%String code = (String)session.getAttribute("code"); %>
	
	<!-- title -->
	<h1 align="center">
	<%if(("add").equals(code)){ %>
		<%="受注追加確認" %>
	<%}else if(("change").equals(code)){ %>
		<%="受注内容変更確認" %>
	<%}else if(("cancel").equals(code)){ %>
		<%="キャンセル確認" %>
	<%}else if(("refund").equals(code)){ %>
		<%="返品確認" %>
	<%} %>
	</h1>
	
	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	<!-- message -->
	<%=message %>
	
	<!-- 追加確認 -->
	<%if(("add").equals(code)) {%>
		<%ArrayList<OrderSlipBean> addSlip = (ArrayList<OrderSlipBean>)session.getAttribute("addSlip"); %>
		<%String customerId = (String)session.getAttribute("addCustomerId"); %>
		<%String customerName = (String)session.getAttribute("addCustomerName"); %>
		<%String orderDate = (String)session.getAttribute("orderDate"); %>
		<form action="dbUpdate" method="post">
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
					<td>販売単価</td>
					<td>仕入単価</td>
					<td>注文数</td>
					<td>売上金額</td>
					<td>粗利</td>
				</tr>
				<%for(OrderSlipBean addItem:addSlip){%>
					<tr>
						<td><%= addItem.getProductId()%></td>
						<td><%= addItem.getProductName()%></td>
						<td><%= addItem.getSalePrice()%></td>
						<td><%= addItem.getUnitCost()%></td>
						<td><%= addItem.getOrderQty()%></td>
						<td><%= addItem.getSaleAmount()%></td>
						<td><%= addItem.getGrossProfit()%></td>
					</tr>
				<%} %>
			</table>
			<input type="submit" name="pageFlag" value="確定">
			<input type="button" name="back" value="戻る" onclick="location.href='orderAdd'">
		</form>
	<%} %>
	
	<!-- 変更確認 -->
		
	
	<!-- キャンセル確認 code=cancel -->
	<%if(("cancel").equals(code)) {%>
		<%ArrayList<OrderSlipBean> cancelSlip = (ArrayList<OrderSlipBean>)session.getAttribute("cancelSlip"); %>
		<%String cancelComment = (String)session.getAttribute("cancelComment"); %>
		<!-- session getAttribute -->
		<%int orderId = (int)session.getAttribute("orderId"); %>
		<%String customerName = (String)session.getAttribute("customerName"); %>
		<%String orderDate = (String)session.getAttribute("orderDate"); %>
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
					<td>返品数</td>
					<td>最終注文数</td>
				</tr>
	
				<%for(OrderSlipBean cancelItem:cancelSlip){%>
					<tr>
						<td><%= cancelItem.getProductId()%></td>
						<td><%= cancelItem.getProductName() %></td>
						<td><%= cancelItem.getOrderQty() %></td>
						<td><%= cancelItem.getCancelQty() %></td>
						<td><%= cancelItem.getRefundQty() %></td>
						<td><%= cancelItem.getOrderQty() - cancelItem.getCancelQty() - cancelItem.getRefundQty() %></td>
					</tr>
				<% }%>
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
			<input type="button" name="back" value="戻る" onclick="location.href='orderCancel'">
		</form>
	<%} %>
	
	
	<!-- 返品確認 code=refund -->
	<%if(("refund").equals(code)) {%>
		<%ArrayList<OrderSlipBean> refundSlip = (ArrayList<OrderSlipBean>)session.getAttribute("refundSlip"); %>
		<%String refundComment = (String)session.getAttribute("refundComment"); %>
		<!-- session getAttribute -->
		<%int orderId = (int)session.getAttribute("orderId"); %>
		<%String customerName = (String)session.getAttribute("customerName"); %>
		<%String orderDate = (String)session.getAttribute("orderDate"); %>
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
					<td>返品数</td>
					<td>最終注文数</td>
				</tr>
				<%for(OrderSlipBean refundItem:refundSlip){%>
					<tr>
						<td><%= refundItem.getProductId()%></td>
						<td><%= refundItem.getProductName() %></td>
						<td><%= refundItem.getOrderQty() %></td>
						<td><%= refundItem.getCancelQty() %></td>
						<td><%= refundItem.getRefundQty() %></td>
						<td><%= refundItem.getOrderQty() - refundItem.getCancelQty() - refundItem.getRefundQty() %></td>
					</tr>
				<% }%>
			</table>
			<br>
			<table>
				<tr>
					<td>返品理由</td>
				</tr>
				<tr>
					<td>
						<%if(refundComment != ""){ %>
							<%=refundComment %>
						<%}else {%>
							<%="コメントはありません" %>
						<%} %>	
					</td>
				</tr>
			</table>
			<input type="submit" name="pageFlag" value="確定">
			<input type="button" name="back" value="戻る" onclick="location.href='orderRefund'">
		</form>
	<%} %>
</body>
</html>