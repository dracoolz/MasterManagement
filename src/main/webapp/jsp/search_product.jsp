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
            
            商品名：<input type="text" name="product_name" placeholder="商品名" >

            <% 
            ProductDao productDao = new ProductDao();
            List<ProductViewBean> largeCategories = productDao.getLargeCategories();
            request.setAttribute("largeCategories", largeCategories);
            for (int i = 0; i < 3; i++) { 
            %>
                <div>
                    大カテゴリ：
                    <select id="large_category<%= i %>" name="large_category" onchange="this.form.submit()">
                        <option value="">選択してください</option>
                        <% 
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
                        if (largeCategoryId != null && !largeCategoryId.isEmpty()) {
                            List<ProductViewBean> smallCategories = productDao.getSmallCategories(Integer.parseInt(largeCategoryId));
                            for (ProductViewBean category : smallCategories) {
                                String selected = request.getParameterValues("small_category") != null && request.getParameterValues("small_category")[i] != null && request.getParameterValues("small_category")[i].equals(String.valueOf(category.getScId())) ? "selected" : "";
                                %>
                                <option value="<%= category.getScId() %>" <%= selected %>><%= category.getScCategory() %></option>
                                <%
                            }
                        }
                        %>
                    </select>
                </div>
            <% } %>
            <input type="submit" value="検索">
            <input type="hidden" name="action" value="search">
        </form>
        
        
        
             <table align="center" border="1">
            <tr align="center">
                <th>取引先名</th>
                <th>担当者</th>
                <th>都道府県</th>
                <th></th>
            </tr>
            <%
            List<ProductViewBean> list = (List<ProductViewBean>) request.getAttribute("list");
            if (list != null && !list.isEmpty()) {
                for (ProductViewBean cus : list) {
            %>
            <tr>
                <td><%= cus.getProductId() %></td>
                <td><%= cus.getProductName() %></td>
                <td><%= cus.getBcCategory() %></td>
                <td>
                    <form action="/Master/searchProduct" method="post" style="display:inline;">
                        <input type="hidden" name="getProductId" value="<%= cus.getProductId() %>">
                        <input type="hidden" name="getProductName" value="<%= cus.getProductName() %>">
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
    </main>
</body>
</html>