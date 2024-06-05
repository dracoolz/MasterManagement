<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDao, bean.ProductViewBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>
    <main>
        <div align="right">
            <p>
                <%="ようこそ、"+session.getAttribute("username")+"さん" %>
            </p>
            <a href="/first">ログアウト</a>
        </div>
        <h1 align="center">商品検索</h1>
        <form action="/Master/searchProduct" method="post">
            <input type="hidden" name="action" value="search">
            商品名：<input type="text" name="product_name" placeholder="商品名">
            <input type="hidden" name="referer" value="<%= request.getParameter("referer") %>">
            <input type="submit" name="productSearch" value="検索">
        </form>
        <table align="center" border="1">
            <tr align="center">
                <th>ID</th>
                <th>商品名</th>
                <th>小カテゴリ1</th>
                <th>小カテゴリ2</th>
                <th>小カテゴリ3</th>
                <th>販売単価</th>
                <th></th>
            </tr>
            <%
            List<ProductViewBean> list = (List<ProductViewBean>) request.getAttribute("list");
            if (list != null && !list.isEmpty()) {
                for (ProductViewBean product : list) {
                    List<String> smallCategories = product.getSmallCategories();
                    int size = smallCategories.size();
            %>
            <tr>
                <td><%= product.getProductId() %></td>
                <td><%= product.getProductName() %></td>
                <td><%= size > 0 ? smallCategories.get(0) : "" %></td>
                <td><%= size > 1 ? smallCategories.get(1) : "" %></td>
                <td><%= size > 2 ? smallCategories.get(2) : "" %></td>
                <td><%= product.getRetailPrice() %></td>
                <td>
                    <form action="/Master/searchProduct" method="post" style="display:inline;">
                        <input type="hidden" name="selectedProductId" value="<%= product.getProductId() %>">
                        <input type="hidden" name="selectedProductName" value="<%= product.getProductName() %>">
                        <input type="hidden" name="referer" value="<%= request.getParameter("referer") %>">
                        <input type="hidden" name="action" value="productSend">
                        <input type="submit" value="追加">
                    </form>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="7" align="center">検索結果がありません</td></tr>
            <%
            }
            %>
        </table>
        <form action="/Master/searchProduct" method="post">
            <input type="hidden" name="referer" value="<%= request.getParameter("referer") %>">
            <input type="submit" value="戻る">
        </form>
    </main>
</body>
</html>