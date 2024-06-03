<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.CustomerDao, bean.CustomerViewBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先検索</title>
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
        <form action="/Master/searchCustomer" method="post">
            <input type="hidden" name="action" value="search">
            取引先名：<input type="text" name="cus_name" placeholder="取引先名">
            担当者：<input type="text" name="contact_name" placeholder="担当者">
            都道府県：<input type="text" name="district" placeholder="都道府県">
            <input type="hidden" name="referer" value="<%= request.getParameter("referer") %>">
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
            List<CustomerViewBean> list = (List<CustomerViewBean>) request.getAttribute("list");
            if (list != null && !list.isEmpty()) {
                for (CustomerViewBean cus : list) {
            %>
            <tr>
                <td><%= cus.getCusName() %></td>
                <td><%= cus.getContactName() %></td>
                <td><%= cus.getDistrict() %></td>
                <td>
                    <form action="/Master/searchCustomer" method="post" style="display:inline;">
                        <input type="hidden" name="selectedCusId" value="<%= cus.getCusId() %>">
                        <input type="hidden" name="selectedCusName" value="<%= cus.getCusName() %>">
                        <input type="hidden" name="referer" value="<%= request.getParameter("referer") %>">
                        <input type="submit" value="追加" name="customerSend">
                    </form>
                </td>
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
        Integer noOfPages = (Integer)request.getAttribute("noOfPages");
        Integer currentPage = (Integer)request.getAttribute("currentPage");
        String cusNameParam = request.getParameter("cus_name");
        String contactNameParam = request.getParameter("contact_name");
        String districtParam = request.getParameter("district");
        if (noOfPages != null && currentPage != null && noOfPages > 1) {
            if (currentPage > 1) {
        %>
            <a href="/Master/searchCustomer?action=search&page=<%= currentPage - 1 %>&cus_name=<%= cusNameParam %>&contact_name=<%= contactNameParam %>&district=<%= districtParam %>&referer=<%= request.getParameter("referer") %>">&laquo; 前へ</a>
            <%
                }
                for (int i = 1; i <= noOfPages; i++) {
                    if (i == currentPage) {
            %>
            <span><%= i %></span>
            <%
                    } else {
            %>
            <a href="/Master/searchCustomer?action=search&page=<%= i %>&cus_name=<%= cusNameParam %>&contact_name=<%= contactNameParam %>&district=<%= districtParam %>&referer=<%= request.getParameter("referer") %>"><%= i %></a>
            <%
                    }
                }
                if (currentPage < noOfPages) {
            %>
            <a href="/Master/searchCustomer?action=search&page=<%= currentPage + 1 %>&cus_name=<%= cusNameParam %>&contact_name=<%= contactNameParam %>&district=<%= districtParam %>&referer=<%= request.getParameter("referer") %>">次へ &raquo;</a>
            <%
                }
            }
            %>
        </div>
        <form action="/Master/searchCustomer" method="post">
            <input type="hidden" name="referer" value="<%= request.getParameter("referer") %>">
            <input type="submit" value="戻る">
        </form>
    </main>
</body>
</html>