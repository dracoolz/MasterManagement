<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.SalesBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品売上一覧</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/productList.css">
<script>
    function incrementMonth() {
        const monthSelect = document.getElementById("month");
        let currentMonth = parseInt(monthSelect.value);
        if (currentMonth < 12) {
            monthSelect.value = currentMonth + 1;
        } else {
            monthSelect.value = 1;
            incrementYear();
        }
    }

    function decrementMonth() {
        const monthSelect = document.getElementById("month");
        let currentMonth = parseInt(monthSelect.value);
        if (currentMonth > 1) {
            monthSelect.value = currentMonth - 1;
        } else {
            monthSelect.value = 12;
            decrementYear();
        }
    }

    function incrementYear() {
        const yearSelect = document.getElementById("year");
        let currentYear = parseInt(yearSelect.value);
        yearSelect.value = currentYear + 1;
    }

    function decrementYear() {
        const yearSelect = document.getElementById("year");
        let currentYear = parseInt(yearSelect.value);
        yearSelect.value = currentYear - 1;
    }
</script>
</head>
<body>
	<% ArrayList<SalesBean> list = (ArrayList<SalesBean>) request.getAttribute("productlist"); %>
	<div align="center">
		<strong>商品別売上一覧</strong>
		<div align="right">
			<p>
				<%="ようこそ、" + session.getAttribute("username") + "さん"%>
			</p>
			<a href="/first">ログアウト</a>
		</div>
		<a href="javascript:decrementMonth();">
			<div class="arrow-left"></div>
		</a>
		<!-- date -->
		<select name="year" id="year">
			<option value="2024">2024</option>
			<option value="2023">2023</option>
			<option value="2022">2022</option>
			<option value="2021">2021</option>
		</select> <select name="month" id="month">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select> <a href="javascript:incrementMonth();">
			<div class="arrow-right"></div>
		</a> <br>
		<div class="form-container">
			<div class="form-group">
				<label for="product_name">商品名：</label> <input type="text"
					id="product_name" size="20">
			</div>
			<div class="form-group">
				<label for="big">大カテゴリ：</label> <select name="big" id="big">
					<option value="">選択してください</option>
					<option value="big_3">big_3</option>
					<option value="big_2">big_2</option>
					<option value="big_1">big_1</option>
				</select>
			</div>
			<div class="form-group">
				<label for="small">小カテゴリ：</label> <select name="small" id="small">
					<option value="">選択してください</option>
					<option value="small_3">small_3</option>
					<option value="small_2">small_2</option>
					<option value="small_1">small_1</option>
				</select>
			</div>
		</div>
		<button type="button">検索</button>
		<!-- table -->
		<div class="table">

			<table border="1">
				<tr align="center">
					<th class="thead">商品ID▽</th>
					<th class="thead">商品名▽</th>
					<th class="thead">カテゴリ▽</th>
					<th class="thead">販売単価 ▽</th>
					<th class="thead">仕入単価 ▽</th>
					<th class="thead">販売数▽</th>
					<th class="thead">粗利▽</th>
					<th class="thead">先年度比(%)▽</th>
					<th></th>
				</tr>
				<% for (int i = 0; i < list.size(); i++) { %>
				<tr align="center">
					<td><%= list.get(i).getPro_id() %></td>
					<td><%= list.get(i).getPi_name() %></td>
					<td><%= list.get(i).getCategory() %></td>
					<td><%= list.get(i).getSale_price() %>円</td>
					<td><%= list.get(i).getStock_price() %>円</td>
					<td><%= list.get(i).getSale_amount() %></td>
					<td><%= list.get(i).getProfit() %>円</td>
					<td><%= list.get(i).getComparison() %></td>
					<form method="post" action="./detail?no=2">
					<td>
						<button type="submit" name="idValue" value="<%= list.get(i).getPro_id() %>">詳細</button>
					</td>
					</form>
				</tr>
				<% } %>
			</table>
		</div>

		<div class="footer_button">
			<button type="button">トップページ</button>
			<button type="button" onclick="location.href='./master'">戻る</button>
		</div>
	</div>
</body>
</html>
