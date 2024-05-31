<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" src="productComp.css">
</head>

<body>

<% if(request.getParameter("submit").equals("登録")){ %>
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong>商品情報の登録</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>登録しました</p>
        <form action= "Master/master?no=3" method="post">
		<P>
		<input type ="submit"  name="submit" value="次へ">
		</p>
		</form>
    </div>
<% } %>
	
<% if(request.getParameter("submit").equals("変更")){ %>
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong>商品情報の変更</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>変更しました</p>
        <form action= "Master/master?no=3" method="post">
		<P>
		<input type ="submit"  name="submit" value="次へ">
		</p>
		</form>
    </div>
<% } %>
	
<% if(request.getParameter("submit").equals("削除")){ %>
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong>商品情報の削除</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>削除しました</p>
        <form action= "Master/master?no=3" method="post">
		<P>
		<input type ="submit"  name="submit" value="次へ">
		</p>
		</form>
    </div>
<% } %>

</body>
</html>