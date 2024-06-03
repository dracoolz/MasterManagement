<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.ProductBean"%>
<%@ page import="bean.BigCategoryBean"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/js/jquery.tablesorter.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/css/theme.default.min.css">
</head>

<body>
<script src="../js/sc_category.js"></script>
<script src="../js/tableSorter.js"></script>
<%ArrayList<ProductBean> proList =(ArrayList<ProductBean>)request.getAttribute("list"); %>
<% ArrayList<BigCategoryBean> bcList = (ArrayList<BigCategoryBean>)request.getAttribute("bclist"); %>
<form action ="./DUcontrol?type=product" method="post">
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong>商品情報の一覧</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
       	<div align="right">
       		<table>
       			<tr>
	       			<td><input type ="submit"  name="submit" value="登録"></td>
	       			<td><button type="button"
								onclick="location.href='./master?no=1'">戻る</td></td>
       			</tr>
       		</table>
       	</div>
       	<div>
       		<table>
       			<tr>
       				<td align="right">商品名:</td>
       				<td><input type="text" name="searchName"></td>
       			</tr>
       			<tr>
       				<td align="right">大カテゴリ:</td>
       				<td><select name = "bc">
						<option value="">選択してください</option>
						<% for(int i=0; i < bcList.size(); i++){ %>
						<option value="<%=i+1 %>"><%=bcList.get(i).getBc_category() %></option>
						<%} %>
						</select></td>
       			</tr>
       			<tr>
       				<td align="right">小カテゴリ:</td>
       				<td>:<select id ="sc" name = "sc_1">
						<option value=""></option>
						</select></td>
       			</tr>
       			<tr>
       				<td align="center"><input type ="submit"  name="submit" value="検索"></td>
       			</tr>
       		</table>
    	</div>
       	<div>
       		<table border="1" style="border-collapse: collapse" class="tablesorter" id="product_table">
       		<thead>
       			<tr>
	       			<td>商品ID</td>
	       			<td>商品名</td>
	       			<td colspan="3">小カテゴリ</td>
	       			<td>販売単価</td>
	       			<td>仕入単価</td>
	       			<td colspan="2"></td>
       			</tr>
       		</thead>
       		<% for(int i=0; i < proList.size(); i++) { %>
       			<tr>
	       			<td><%=proList.get(i).getPro_id() %></td>
	       			<td><%=proList.get(i).getPi_name() %></td>
	       			<td><%=proList.get(i).getSc_category_1() %></td>
	       			<td><%=proList.get(i).getSc_category_2() %></td>
	       			<td><%=proList.get(i).getSc_category_3() %></td>
	       			<td><%=proList.get(i).getWholesale() %></td>
	       			<td><%=proList.get(i).getWholesale() * (5/6) %></td>
	       			<td><input type ="submit"  name="submit" value="変更"></td>
	       			<td><input type ="submit"  name="submit" value="削除"></td>
       			</tr>
       		<%} %>
       		</table>
       	</div>
       	<font color="red">
			<%if(request.getAttribute("errmsg1")!=null){ %>
				<%= request.getAttribute("errmsg1")%><br>
			<% } %>
		</font>
</form>
		<a class="pagetop" href="#"><div class="pagetop__arrow"></div></a>
    </div>
</body>
</html>