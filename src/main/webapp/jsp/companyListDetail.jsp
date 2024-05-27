<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品別売上詳細</title>
    <link rel="stylesheet" href="../css/companyListDetail.css">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', { 'packages': ['corechart', 'bar'] });
        google.charts.setOnLoadCallback(drawCharts);

        function drawCharts() {
            drawBarChart();
            drawPieChart();
        }

        function drawBarChart() {
            var data = google.visualization.arrayToDataTable([
                ['Year', '売上'],
                ['2014', 400],
                ['2015', 460],
                ['2016', 300],
                ['2017', 540]
            ]);

            var options = {
                chart: {
                    title: "売上金額の推移",
                    titleTextStyle: {
                        color: 'black'
                    }
                }
            };

            var chart = new google.charts.Bar(document.getElementById('barChart'));

            chart.draw(data, google.charts.Bar.convertOptions(options));
        }

        function drawPieChart() {
            var data = google.visualization.arrayToDataTable([
                ['カテゴリ', '売上'],
                ['ペン', 50],
                ['消しゴム', 25],
                ['シール', 20],
                ['ステッカー', 5]
            ]);

            var options = {
                title: 'カテゴリ',
                titleTextStyle: {
                    color: 'black'
                }
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
    <div align="center">
        <strong>取引先別売上詳細</strong>
        <div align="right">
            <p>
                <%="ようこそ、"+session.getAttribute("username")+"さん" %>
            </p>
            <a href="/first">ログアウト</a>
        </div>
        <div class="top-button">
            <button type="button">年表示</button>
            <button type="button">月表示</button>
            <button type="button">戻る</button>
        </div>
        <!-- table -->
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
				</tr>
			</table>
        </div>
        <div align="left" class="company_info">
        	<h3>株式会社　A</h3>
        	<p>取引先ＩＤ			***</p>
        	<p>2024年05月の売上		***万円</p>
        	<p>先年度比				***％</p>
        </div>
        <div class="chart-1">
            <div id="barChart" style="width: 400px; height: 300px;"></div>
            <div>
                <table>
                    <thead>
                        <tr>
                            <th>年</th>
                            <th>月</th>
                            <th>売上</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>2024</td>
                            <td>12</td>
                            <td>45</td>
                        </tr>
                        <tr>
                            <td>2024</td>
                            <td>10</td>
                            <td>0</td>
                        </tr>
                        <tr>
                            <td>2024</td>
                            <td>04</td>
                            <td>0</td>
                        </tr>
                        <tr>
                            <td>2024</td>
                            <td>02</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>2023</td>
                            <td>11</td>
                            <td>5</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="chart-2">
            <div>
                <p>
                    <h3>購入した商品カテゴリの売上</h3>
                    ランキング
                </p>
                <table>
                    <thead>
                        <tr>
                            <th>カテゴリ</th>
                            <th>売上</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>ペン</td>
                            <td>50</td>
                        </tr>
                        <tr>
                            <td>消しゴム</td>
                            <td>25</td>
                        </tr>
                        <tr>
                            <td>シール</td>
                            <td>20</td>
                        </tr>
                        <tr>
                            <td>ステッカー</td>
                            <td>5</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="piechart" style="width: 500px; height: 300px;"></div>
        </div>
        <div class="footer_button">
			<button type="button">トップページ</button>
			<button type="button">戻る</button>
		</div>
    </div>
</body>
</html>