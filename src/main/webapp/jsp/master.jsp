<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>マスタメニュー</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body>
	<div align="center">
		<strong style="font-size: 30px;">マスタ管理メニュー</strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="./first">ログアウト</a>
		</div>
	</div>
	<div align="center">
		<a href="../managecontrol?no=0">ユーザ管理</a><br><br>
		<a href="../managecontrol?no=1">カテゴリ管理</a><br><br>
		<a href="../managecontrol?no=2">商品管理</a><br><br>
		<a href="../managecontrol?no=3">取引先別売上一覧表</a><br><br>
		<a href="../managecontrol?no=4">商品別売上一覧表</a><br><br>
		<a href="./managecontrol?no=5">受注管理システムへ</a><br><br>
		<td>
			<input type="button" name="submit" onclick="location.href='./login'" value="メインメニューに戻る">
		</td>
		<td><input type="button" name="submit" onclick="location.href='./login'" value="メインメニューに戻る"></td>
	</div>
</body>
</html>