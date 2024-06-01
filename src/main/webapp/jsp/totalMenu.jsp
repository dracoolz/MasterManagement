<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>A
	<div align="center">
		<strong>受注管理システム（総合メニュー）</strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="./first">ログアウト</a>
		</div>
	</div>
	<div align="center">
		
		<a href="./manageMenu">受注管理</a><br><br>
		<a href="./master?no=2">売上日計一覧</a><br><br>
		
		<td><input type="button" name="submit"onclick="location.href='./login'" value="メインメニューに戻る"></td>
	</div>
</body>



</html>