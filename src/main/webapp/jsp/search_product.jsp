<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.ProductViewBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

    
</head>
<body>
	<% ArrayList<SalesBean> list = (ArrayList<SalesBean>) request.getAttribute("productlist"); %>
    <div align="center">
        <strong id="topPage">商品検索</strong>
        <div align="right">
            <p>
                <%="ようこそ、"+ session.getAttribute("username") + "さん" %>
            </p>
            <a href="/first">ログアウト</a>
        </div>
        
        
        
        <form>
        商品名<input type="text">
         <br>
         <select name="bigCategory" >
                <% for(ProductViewBean category : bigCategory) { %>
                    <option value="<%= category.getBcId() %>"><%= category.getBcCategory() %></option>
                <% } %>
  </select>
  
  <select name="smallCategory">
            <option value="">-- 小カテゴリを選択 --</option>
            <% if (smallCategory != null) {
                for(ProductViewBean category : smallCategory) { %>
                    <option value="<%= category.getId() %>"><%= category.getName() %></option>
                <% } 
            } %>
        </select>
        
        </form>

</body>
</html>