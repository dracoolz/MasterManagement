<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderListViewBean" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
    <h1>Order List</h1>
    <table border="1">
        <tr>
            <th>注文番号</th>
            <th>注文日</th>
            <th>取引先名</th>
        </tr>
        <% 
            ArrayList<OrderListViewBean> orderList = (ArrayList<OrderListViewBean>) request.getAttribute("orderList");
            for (OrderListViewBean bean : orderList) {
        %>
        <tr>
            <td><%= bean.getOrderId() %></td>
            <td><%= bean.getDate() %></td>
            <td><%= bean.getCustomerName() %></td>
            <td>
                <form action="orderSlip" method="GET"> <!-- 詳細ボタンのフォーム -->
                     <input type="hidden" name="orderId" value="<%= bean.getOrderId() %>">
                     <input type="hidden" name="orderDate" value="<%= bean.getDate() %>">
                     <input type="hidden" name="customerName" value="<%= bean.getCustomerName() %>">
                     <input type="submit" value="詳細">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</body>




</html>