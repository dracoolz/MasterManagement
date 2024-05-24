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
        <strong>商品別売上一覧</strong>
        <div align="right">
            <p>
                <%="ようこそ、"+session.getAttribute("username")+"さん" %>
            </p>
            <a href="/first">ログアウト</a>
        </div>
        <div class="arrow-left"></div>
        <!-- date -->
        <select name="year" id="year">
            <option value="2024">2024</option>
            <option value="2023">2023</option>
            <option value="2022">2022</option>
            <option value="2021">2021</option>
        </select>
        <select name="month" id="month">
            <option value="4">4</option>
            <option value="3">3</option>
            <option value="2">2</option>
            <option value="1">1</option>
        </select>
        <div class="arrow-right"></div>
        <br>
        <p>商品名：<input type="text" id="product_name" size="20"></p>
        <p>
            大カテゴリ：
            <select name="big" id="big">
                <option value="">選択してください</option>
                <option value="big_3">big_3</option>
                <option value="big_2">big_2</option>
                <option value="big_1">big_1</option>
            </select>
        </p>
        <p>
            小カテゴリ：
            <select name="small" id="small">
                <option value="">選択してください</option>
                <option value="small_3">small_3</option>
                <option value="small_2">small_2</option>
                <option value="small_1">small_1</option>
            </select>
        </p>
        <button type="button">検索</button>
        <!-- table -->
        <div class="table">
            <table>
                <tr>
                    <th>dir商品ID▽</th>
                    <th>商品名▽</th>
                    <th colspan="3">小カテゴリ▽</th>
                    <th>販売単価▽</th>
                    <th>仕入単価▽</th>
                    <th>販売数▽</th>
                    <th>粗利▽</th>
                    <th>先年度比</th>
                    <th></th>
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