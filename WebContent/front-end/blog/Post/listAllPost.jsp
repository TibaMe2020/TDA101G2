<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.post.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PostService service = new PostService();
    List<PostVO> list = service.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有文章 - listAllPost.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有文章 - listAllPost.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog/Post/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
		<th>留言時間</th>
		<th>更新時間</th>
	</tr>
	<c:forEach var="postVO" items="${list}">
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
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Post/Post.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="post_id"  value="${postVO.post_id}">
			     <input type="hidden" name="action"	value="getOneForUpdate">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Post/Post.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="post_id" value="${postVO.post_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>	
</table>

</body>
</html>