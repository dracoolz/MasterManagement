<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<div align="center">
		<strong>受注管理メニュー</strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="./first">ログアウト</a>
		</div>
	</div>
	<div align="center">
		
		<a href="./orderList">受注一覧</a><br><br>
		<a href="./master?no=2">受注追加</a><br><br>
		<a href="./manage">受注内容変更</a><br><br>
		<a href="./master?no=2">キャンセル処理</a><br><br>
		<a href="./manage">返品処理</a><br><br>
		<a href="./total">戻る</a><br><br>
		
	</div>
</body>



</html>