<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <form action="ProductSearchServlet" method="post">
            <input type="hidden" name="action" value="search">
            商品名：<input type="text" name="product_name" placeholder="商品名" value="<%= request.getParameter("product_name") %>">
            
            <% for (int i = 0; i < 3; i++) { %>
                <div>
                    大カテゴリ：
                    <select id="large_category<%= i %>" name="large_category" onchange="this.form.submit()">
                        <option value="">選択してください</option>
                        <%
                        List<ProductViewBean> largeCategories = (List<ProductViewBean>) request.getAttribute("largeCategories");
                        if (largeCategories != null) {
                            for (ProductViewBean category : largeCategories) {
                                String selected = request.getParameterValues("large_category") != null && request.getParameterValues("large_category")[i] != null && request.getParameterValues("large_category")[i].equals(String.valueOf(category.getBcId())) ? "selected" : "";
                                %>
                                <option value="<%= category.getBcId() %>" <%= selected %>><%= category.getBcCategory() %></option>
                                <%
                            }
                        }
                        %>
                    </select>
                    小カテゴリ：
                    <select id="small_category<%= i %>" name="small_category">
                        <option value="">選択してください</option>
                        <%
                        String largeCategoryId = request.getParameterValues("large_category") != null ? request.getParameterValues("large_category")[i] : null;
                        List<ProductViewBean> smallCategories = (List<ProductViewBean>) request.getAttribute("smallCategories" + largeCategoryId);
                        if (smallCategories != null) {
                            for (ProductViewBean category : smallCategories) {
                                String selected = request.getParameterValues("small_category") != null && request.getParameterValues("small_category")[i] != null && request.getParameterValues("small_category")[i].equals(category.getScCategory()) ? "selected" : "";
                                %>
                                <option value="<%= category.getScCategory() %>" <%= selected %>><%= category.getScCategory() %></option>
                                <%
                            }
                        }
                        %>
                    </select>
                </div>
            <% } %>
            
            <input type="submit" name="productSearch" value="検索">
            <input type="hidden" name="referer" value="<%= request.getHeader("Referer") %>">
        </form>
        <table align="center" border="1">
            <tr align="center">
                <th>商品名</th>
                <th>大カテゴリ</th>
                <th>小カテゴリ</th>
                <th></th>
            </tr>
            <%
            List<ProductViewBean> products = (List<ProductViewBean>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (ProductViewBean product : products) {
            %>
            <tr>
                <td><%= product.getProductName() %></td>
                <td><%= product.getBcCategory() %></td>
                <td><%= product.getScCategory() %></td>
                <td>
                    <form action="ProductSearchServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="addProduct">
                        <input type="hidden" name="selectedProductId" value="<%= product.getProductId() %>">
                        <input type="hidden" name="selectedProductName" value="<%= product.getProductName() %>">
                        <input type="hidden" name="referer" value="<%= request.getHeader("Referer") %>">
                        <input type="submit" value="追加" name="productSend">
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
        <h2>選択された商品</h2>
        <table align="center" border="1">
            <tr align="center">
                <th>商品ID</th>
                <th>商品名</th>
            </tr>
            <%
            List<ProductViewBean> selectedProducts = (List<ProductViewBean>) session.getAttribute("selectedProducts");
            if (selectedProducts != null && !selectedProducts.isEmpty()) {
                for (ProductViewBean selectedProduct : selectedProducts) {
            %>
            <tr>
                <td><%= selectedProduct.getProductId() %></td>
                <td><%= selectedProduct.getProductName() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr><td colspan="2" align="center">選択された商品がありません</td></tr>
            <%
            }
            %>
        </table>
        <div align="center">
        <form action="SearchCustomerServlet" method="post">
            <input type="hidden" name="referer" value="<%= request.getHeader("Referer") %>">
            <input type="submit" value="戻る">
        </form>
        </div>
    </main>
</body>
</html>