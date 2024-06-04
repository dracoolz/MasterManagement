<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderSlipBean" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受注明細</title>
</head>

<body >
<h1 align="center">受注明細です</h1>

<div>
<p>注文ID: <%= request.getParameter("orderId") %></p>
<p>注文日: <%= request.getParameter("orderDate") %></p>
<p>顧客名: <%= request.getParameter("customerName") %></p>
</div>

<table border="1" align="center">
    <tr>
        <th>商品ID</th>
        <th>商品名</th>
        <th>販売単価</th>
        <th>仕入単価</th>
        <th>注文数</th>
        <th>キャンセル数</th>
        <th>返品数</th>
        <th>合計金額</th>
        <th>粗利</th>
    </tr>
        
    <% for (OrderSlipBean orderSlip : (ArrayList<OrderSlipBean>)request.getAttribute("orderSlipList")) { %>
    <tr>
        <td><%= orderSlip.getProductId() %></td>
        <td><%= orderSlip.getProductName() %></td>
        <td><%= orderSlip.getSalePrice() %></td>
        <td><%= orderSlip.getUnitCost() %></td>
        <td><%= orderSlip.getOrderQty() %></td>
        <td><%= orderSlip.getCancelQty() %></td>
        <td><%= orderSlip.getRefundQty() %></td>
        <td><%= orderSlip.getSaleAmount() %></td>
        <td><%= orderSlip.getGrossProfit() %></td>
    </tr>
    <% } %>
</table><br>


  <form id="myForm" action="./orderList" method="GET" align="center">
    <input type="hidden" name="no" id="noInput" value="2">
 　<input type="submit" value="戻る">

</body>
</html>
