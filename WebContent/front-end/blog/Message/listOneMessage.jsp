<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog.message.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MessageVO messageVO = (MessageVO) request.getAttribute("messageVO"); //FollowServlet.java(Concroller), 存入req的followVO物件
%>

<html>
<head>
<title>留言資料 - listOneMessage.jsp</title>

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
		 <h3>留言資料 - listOneMessage.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/blog/Message/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>留言id</th>
		<th>會員id</th>
		<th>文章id</th>
		<th>留言內容</th>
		<th>留言時間</th>
		<th>更新時間</th>
	</tr>	
	<tr>
		<td><%=messageVO.getMessage_id()%></td>
		<td><%=messageVO.getMember_id()%></td>
		<td><%=messageVO.getPost_id()%></td>
		<td><%=messageVO.getMessage_content()%></td>
		<td><%=messageVO.getCreate_time()%></td>
		<td><%=messageVO.getUpdate_time()%></td>
	</tr>
</table>

</body>
</html>