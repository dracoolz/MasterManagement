<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ管理</title>
<link rel="stylesheet" src="categoryComp.css">
</head>

<body>
<% String str =  request.getParameter("submit");%>
<% if(request.getParameter("category").equals("bc")){ %>
	<% if(str.equals("登録")){ %>
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>大カテゴリ情報の登録</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>登録しました</p>
	        <form action= "./manageControl?no=2" method="post">
			<P>
			<input type ="submit"  name="submit" value="次へ">
			</p>
			</form>
	    </div>
	<% }else if(str.equals("変更")){ %>
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>大カテゴリ情報の変更</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>変更しました</p>
	        <form action= "./manageControl?no=2" method="post">
			<P>
			<input type ="submit"  name="submit" value="次へ">
			</p>
			</form>
	    </div>
	<% }else if(str.equals("削除")){ %>
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>大カテゴリ情報の削除</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>削除しました</p>
	        <form action= "./manageControl?no=2" method="post">
			<P>
			<input type ="submit"  name="submit" value="次へ">
			</p>
			</form>
	    </div>
	<% } %>
<%}else if(request.getParameter("category").equals("sc")){ %>
	<% if(str.equals("登録")){ %>
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>小カテゴリ情報の登録</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>登録しました</p>
	        <form action= "./manageControl?no=2" method="post">
			<P>
			<input type ="submit"  name="submit" value="次へ">
			</p>
			</form>
	    </div>
	<% }else if(str.equals("変更")){ %>
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>小カテゴリ情報の変更</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>変更しました</p>
	        <form action= "./manageControl?no=2" method="post">
			<P>
			<input type ="submit"  name="submit" value="次へ">
			</p>
			</form>
	    </div>
	<% }else if(str.equals("削除")){ %>
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>小カテゴリ情報の削除</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>削除しました</p>
	        <form action= "./manageControl?no=2" method="post">
			<P>
			<input type ="submit"  name="submit" value="次へ">
			</p>
			</form>
	    </div>
	<% } %>
<%} %>

</body>
</html>