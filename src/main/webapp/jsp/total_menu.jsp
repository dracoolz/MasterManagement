<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <main>
		<div align="right">
			<p>
				<%="ようこそ、"+session.getAttribute("username")+"さん" %>
			</p>
			<a href="/first">ログアウト</a>
		</div>
        <h1>受注管理システム（総合メニュー）</h1>
        <div class="button-group">
            <a href="/manage_menu">受注管理</a>
            <a href="/">売上日計一覧</a>
            <a href="/">戻る</a>
        </div>
    </div>
</body>
</html>