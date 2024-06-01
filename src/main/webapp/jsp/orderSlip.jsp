<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderSlipViewBean" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>受注明細です</h1>

　　<p>注文ID: <%= request.getParameter("orderId") %></p>
    <p>注文日: <%= request.getParameter("orderDate") %></p>
    <p>顧客名: <%= request.getParameter("customerName") %></p>
    <table border="1">
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
        
         <% for (OrderSlipViewBean orderSlip : (ArrayList<OrderSlipViewBean>)request.getAttribute("orderSlipList")) { %>
            <tr>
                <td><%= orderSlip.getProductId() %></td>
                <td><%= orderSlip.getProductName() %></td>
                <td></td>
                <td></td>
                <td><%= orderSlip.getOrderQty() %></td>
                <td><%= orderSlip.getCancelQty() %></td>
                <td><%= orderSlip.getRefundQty() %></td>
                <td></td>
                <td></td>
            </tr>
        <% } %>
    </table>
</body>




</html>