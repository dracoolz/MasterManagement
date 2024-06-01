<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <main>
		<div align="right">
			<p>
				<%="ようこそ、"+session.getAttribute("username")+"さん" %>
			</p>
			<a href="/first">ログアウト</a>
		</div>
        <h1 align="center">取引先検索</h1>
        <form action="CustomerSearchServlet" method="post">
        <input 111 type="hidden" name="action" value="search">
        取引先名：<input type="text" name="cus_name" placeholder="取引先名">
        担当者：<input type="text" name="contact_name" placeholder="担当者">
        都道府県：<input type="text" name="district" placeholder="都道府県">
        <input type="submit" name="customerSearch" value="検索">
        </form>
        <table align="center" border="1">
            <tr align="center">
                <th>取引先名</th>
                <th>担当者</th>
                <th>都道府県</th>
                <th></th>
            </tr>
            
            
            <%
            ArrayList<CustomerViewBean> list = (ArrayList<CustomerViewBean>) request.getAttribute("list");
            if (list != null && !list.isEmpty()) {
                for (CustomerViewBean cus : list) {
            %>
            <tr>
                <td><%= cus.getCusName() %></td>
                <td><%= cus.getContactName() %></td>
                <td><%= cus.getDistrict() %></td>
                <td>                    
                		<form action="SearchCustomerServlet" method="post" style="display:inline;">
                        <input type="hidden" name="selectedCusName" value="<%= cus.getCusName() %>">
                        <input type="submit" value="追加" name="customerSend">
                    </form></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="4" align="center">検索結果がありません</td></tr>
            <%
            }
            %>
        </table>
        
        
        <div align="center">
        <%
        int noOfPages = (int)request.getAttribute("noOfPages");
        int currentPage = (int) request.getAttribute("currentPage");
        if (noOfPages > 1) {
            if (currentPage > 1) {
        %>
            <a href="SearchCustomerServlet?action=search&page=<%= currentPage - 1 %>">&laquo; 前へ</a>
            <%
                }
                for (int i = 1; i <= noOfPages; i++) {
                    if (i == currentPage) {
            %>
            <span><%= i %></span>
            <%
                    } else {
            %>
            <a href="SearchCustomerServlet?action=search&page=<%= i %>"><%= i %></a>
            <%
                    }
                }
                if (currentPage < noOfPages) {
            %>
            <a href="SearchCustomerServlet?action=search&page=<%= currentPage + 1 %>">次へ &raquo;</a>
            <%
                }
            }
            %>
        </div>
        <form action="previousPage.jsp" method="post">
            <input type="submit" value="戻る">
        </form>
    </main>
    <input type="submit" value="戻る">
</body>
</html>