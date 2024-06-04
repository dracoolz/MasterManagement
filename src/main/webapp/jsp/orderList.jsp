<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderListBean" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body >
    <h1 align="center">Order Listです</h1>
     <p align="center">
        <%
        // Servletから渡されたメッセージを取得して表示
                    String message = (String)request.getAttribute("message");
                    out.print(message);
        %>
    </p>
    <div align="center">
    
    </div>
    <form action="orderList?no=3" method="post" >
	    <label><input type="radio" name="status" value="all_order" checked>全件</label><br>
	    <label><input type="radio" name="status" value="cancel_only"> キャンセルのみ</label><br>
	    <label><input type="radio" name="status" value="refund_only"> 返品のみ</label><br>
	    <label for="customerId">取引先ID:</label>
	    <input type="text" id="customerId" name="customerId">
	    <input type="submit" value="検索">
　　</form>


    <form action="searchCustomer" method="GET" align="right"> <!-- 取引先検索画面のフォーム -->
           <input type="submit" value="取引先検索画面へ">
    </form>
    
    
    <table border="1" align="center" >
        <tr>
            <th>注文番号</th>
            <th>注文日</th>
            <th>取引先名</th>
            <th>合計金額</th>
            
        </tr>
        <% ArrayList<OrderListBean> orderList = (ArrayList<OrderListBean>) request.getAttribute("orderList");
           if (orderList != null) {
			for (OrderListBean bean : orderList) {
		%>
		 <tr>
            <td><%= bean.getOrderId() %></td>
            <td><%= bean.getOrderDate() %></td>
            <td><%= bean.getCustomerName() %></td>
            <td><%= bean.getTotalAmountMoney() %></td>
            <td>
              <form action="<%= session.getAttribute("source") %>" method="GET"> <!-- source をセッションから取得 -->
                     <input type="hidden" name="orderId" value="<%= bean.getOrderId() %>">
                     <input type="hidden" name="orderDate" value="<%= bean.getOrderDate() %>">
                     <input type="hidden" name="customerName" value="<%= bean.getCustomerName() %>">
                     <input type="submit" value="<%= session.getAttribute("button") %>">
                </form>
            </td>
        </tr>
        <%} } %>
    </table>
    <div align="center">
     <a  href="./manageMenu" >戻る</a>
    </div>
   
    
</body>




</html>