<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog.post.model.*"%>
<%
  PostVO postVO = (PostVO) request.getAttribute("postVO");
%> 
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<html>
<head>
<title>文章資料 - listOnePost.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>文章資料 - listOnePost.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog/Post/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章id</th>
		<th>會員id</th>
		<th>文章分類</th>
		<th>文章圖片1</th>
		<th>文章圖片2</th>
		<th>文章圖片3</th>
		<th>文章圖片4</th>
		<th>文章圖片5</th>
		<th>文章內容</th>
		<th>讚數</th>
		<th>留言數</th>
		<th>分享數</th>
		<th>發文時間</th>
		<th>更新時間</th>
	</tr>	
	<tr>
		<td>${postVO.post_id}</td>
		<td>${postVO.member_id}</td>
		<td>${postVO.post_class}</td>
		<% for(int i = 1; i <= 5; i++){ %>
			<td><img src="<%=request.getContextPath()%>/Post/DBGifReader2?post_id=${postVO.post_id}&count=<%=i%>" width="200px" height="200px"></td>
		<% }; %>
		<td>${postVO.post_content}</td>
		<td>${postVO.post_like}</td>
		<td>${postVO.post_message_count}</td>
		<td>${postVO.post_share}</td>
		<td>${postVO.create_time}</td>
		<td>${postVO.update_time}</td>
	</tr>
</table>

</body>
</html>