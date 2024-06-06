<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.BigCategoryBean"%>
<%@ page import="dao.BigCategoryDao"%>
<%@ page import="bean.SmallCategoryBean"%>
<%@ page import="dao.SmallCategoryDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ管理</title>
<link rel="stylesheet" src="categoryMod.css">
</head>

<body>
<script>
function formSwitch() {
    hoge = document.getElementsByName('maker')
    if (hoge[0].checked) {
        document.getElementById('bcform').style.display = "";
        document.getElementById('scform').style.display = "none";
    } else if (hoge[1].checked) {
        document.getElementById('bcform').style.display = "none";
        document.getElementById('scform').style.display = "";
    } else {
        document.getElementById('bcform').style.display = "none";
        document.getElementById('scform').style.display = "none";
    }
}
window.onload = function(){
	document.getElementById('bcform').style.display = "";
    document.getElementById('scform').style.display = "none";
	}
</script>
<% ArrayList<SmallCategoryBean> scList = (ArrayList<SmallCategoryBean>)session.getAttribute("sclist"); %>
<% ArrayList<BigCategoryBean> bcList = (ArrayList<BigCategoryBean>)session.getAttribute("bclist"); %>
<% if(request.getParameter("submit").equals("登録")){ %>
<form action ="./control?type=category" method="post">
	<div align="center">
		<div align="left">
			<p>カテゴリ管理</p>
		</div>
        <strong>カテゴリ情報の登録</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <form>
        <table>
        	<tr>
        	<td><div class="form-check">
        		<input class="form-check-input" type="radio" name="maker" value="bc" onclick="formSwitch()" checked>
        		<label class="form-check-label"> 大カテゴリ</label>
    			</div></td>
    		<td><div class="form-check">
        		<input class="form-check-input" type="radio" name="maker" value="sc" onclick="formSwitch()" >
        		<label class="form-check-label"> 小カテゴリ</label>
    			</div></td>
        </table>
    	</form>
    		<div id="bcform">
	       		<table>
					<tr><td>　</td></tr>
					<tr>
						<td align="right">大カテゴリID：</td>
						<td><input type="text" name ="bc_id" value=""></td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td align="right">大カテゴリ名：</td>
						<td><input type="text" name ="bc_category" value=""></td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td>　<font color="red">
							<%if(request.getAttribute("errmsg1")!=null){ %>
								<%= request.getAttribute("errmsg1")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg2")!=null){ %>
								<%= request.getAttribute("errmsg2")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg3")!=null){ %>
								<%= request.getAttribute("errmsg3")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg4")!=null){ %>
								<%= request.getAttribute("errmsg4")%>
							<% } %>
							</font>　</td>
					</tr>
				</table>
    		</div>
    		<div id="scform">
        	<table>
					<tr><td>　</td></tr>
					<tr>
						<td align="right">小カテゴリID：</td>
						<td><input type="text" name ="sc_id" value=""></td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td align="right">大カテゴリ名：</td>
						<td><select name = "bc_id2">
							<option value="0"></option>
							<% for(int i=0; i < bcList.size(); i++){ %>
							<option value="<%=bcList.get(i).getBc_id() %>"><%=bcList.get(i).getBc_category() %></option>
							<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td align="right">小カテゴリ名：</td>
						<td><input type="text" name ="sc_category" value=""></td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td>　<font color="red">
							<%if(request.getAttribute("errmsg1")!=null){ %>
								<%= request.getAttribute("errmsg1")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg2")!=null){ %>
								<%= request.getAttribute("errmsg2")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg3")!=null){ %>
								<%= request.getAttribute("errmsg3")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg4")!=null){ %>
								<%= request.getAttribute("errmsg4")%>
							<% } %>
							</font>　</td>
					</tr>
				</table>
    		</div>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="登録"></td>
				<td><input type ="reset" value="リセット"></td>
				<td><input type ="submit" name="submit" value="戻る"></td>
			</tr>
		</table>
    </div>
</form>
<% } %>

	
<% if(request.getParameter("submit").equals("変更")){ %>
<% if(request.getParameter("type").equals("bc")){ %>
<form action ="./control?type=category&maker=bc" method="post">
	<div align="center">
		<div align="left">
			<p>カテゴリ管理</p>
		</div>
        <strong>カテゴリ情報の変更</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
    		<div>
	       		<table>
					<tr><td>　</td></tr>
					<tr>
						<td align="right">大カテゴリID：</td>
						<td><%=session.getAttribute("bc_id") %></td>
					</tr>
					<tr>
						<td align="right">大カテゴリ名：</td>
						<td><input type="text" name ="bc_category" value=""></td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td>　<font color="red">
							<%if(request.getAttribute("errmsg1")!=null){ %>
								<%= request.getAttribute("errmsg1")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg2")!=null){ %>
								<%= request.getAttribute("errmsg2")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg3")!=null){ %>
								<%= request.getAttribute("errmsg3")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg4")!=null){ %>
								<%= request.getAttribute("errmsg4")%>
							<% } %>
							</font>　</td>
					</tr>
				</table>
    		</div>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="変更"></td>
				<td><input type ="reset" value="リセット"></td>
				<td><input type ="submit" name="submit" value="戻る"></td>
			</tr>
		</table>
    </div>
</form>
<% } %>
<% } %>

<% if(request.getParameter("submit").equals("変更")){ %>
<% if(request.getParameter("type").equals("sc")){ %>
<form action ="./control?type=category&maker=sc" method="post">
	<div align="center">
		<div align="left">
			<p>カテゴリ管理</p>
		</div>
        <strong>カテゴリ情報の変更</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
    		<div>
        	<table>
					<tr><td>　</td></tr>
					<tr>
						<td align="right">小カテゴリID：</td>
						<td><%=session.getAttribute("sc_id") %></td>
					</tr>
					<tr>
						<td align="right">大カテゴリID：</td>
						<td>:<select id ="bc1" name = "bc_id">
							<option value=""></option>
							<% for(int i=0; i < bcList.size(); i++){ %>
							<option value="<%=bcList.get(i).getBc_id() %>"><%=bcList.get(i).getBc_category() %></option>
							<%} %>
							</select></td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td align="right">小カテゴリ名：</td>
						<td><input type="text" name ="sc_category" value=""></td>
					</tr>
					<tr>
						<td align="right">(必須)</td>
					</tr>
					<tr>
						<td>　<font color="red">
							<%if(request.getAttribute("errmsg1")!=null){ %>
								<%= request.getAttribute("errmsg1")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg2")!=null){ %>
								<%= request.getAttribute("errmsg2")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg3")!=null){ %>
								<%= request.getAttribute("errmsg3")%><br>
							<% } %>
							<%if(request.getAttribute("errmsg4")!=null){ %>
								<%= request.getAttribute("errmsg4")%>
							<% } %>
							</font>　</td>
					</tr>
				</table>
    		</div>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="変更"></td>
				<td><input type ="reset" value="リセット"></td>
				<td><input type ="submit" name="submit" value="戻る"></td>
			</tr>
		</table>
    </div>
</form>
<% } %>
<% } %>
</body>
</html>
