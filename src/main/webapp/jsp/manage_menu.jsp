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
        <h1>受注管理メニュー</h1>
        <div class="button-group">
            <a href="/list">受注一覧</a>
            <a href="/">受注追加</a>
            <a href="/">受注内容変更</a>
            <a href="/">キャンセル処理</a>
            <a href="/">返品処理</a>
            <a href="/">戻る</a>
        </div>
    </div>
</body>
</html>