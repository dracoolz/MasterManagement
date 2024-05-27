<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品売上一覧</title>
<link rel="stylesheet" href="../css/companyList.css">
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
	<div align="center">
		<strong>取引先別売上一覧</strong>
		<div align="right">
			<p>
				<%="ようこそ、"+session.getAttribute("username")+"さん" %>
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
		</a> 
		<br><br>
		<div class="form-container">
			<div class="form-group">
				<label for="customer_name">取引先名：</label> <input type="text"
					id="customer_name" size="20">
			</div>
			<div class="form-group">
				<label for="contact_name">担当者：</label> <input type="text"
					id="contact_name" size="20">
			</div>
			<div class="form-group">
				<label for="district">県：</label> <input type="text" id="district"
					size="20">
			</div>
		</div>
		<button type="button">検索</button>
		<div class="table">
			<table>
				<tr>
					<th>取引先ID▽</th>
					<th>取引先名▽</th>
					<th>売上(万円)▽</th>
					<th>先年度比(%)▽</th>
				</tr>
				<tr>
					<td>xxx</td>
					<td>xxx</td>
					<td>xxx</td>
					<td>xxx</td>
					<td>
						<button type="button">詳細</button>
					</td>
				</tr>
			</table>
		</div>
		<div class="footer_button">
			<button type="button">トップページ</button>
			<button type="button">戻る</button>
		</div>
	</div>
</body>
</html>
