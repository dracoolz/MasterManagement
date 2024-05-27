<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品別売上詳細</title>
    <link rel="stylesheet" href="../css/productListDetail.css">
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
                ['地区', '売上'],
                ['東京', 50],
                ['京都', 25],
                ['大阪', 20],
                ['兵庫', 5]
            ]);

            var options = {
                title: '地区別売上',
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
        <strong>商品別売上詳細</strong>
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
                    <th>商品ID▽</th>
                    <th>商品名▽</th>
                    <th colspan="3">小カテゴリ▽</th>
                    <th>販売単価▽</th>
                    <th>仕入単価▽</th>
                    <th>販売数▽</th>
                    <th>粗利▽</th>
                    <th>先年度比</th>
                </tr>
                <tr>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                    <td>xxx</td>
                </tr>
            </table>
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
                    <h3>地区別売上</h3>
                    ランキング
                </p>
                <table>
                    <thead>
                        <tr>
                            <th>地区</th>
                            <th>売上</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>東京</td>
                            <td>50</td>
                        </tr>
                        <tr>
                            <td>京都</td>
                            <td>25</td>
                        </tr>
                        <tr>
                            <td>大阪</td>
                            <td>20</td>
                        </tr>
                        <tr>
                            <td>兵庫</td>
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