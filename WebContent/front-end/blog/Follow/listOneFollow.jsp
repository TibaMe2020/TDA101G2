<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.blog.follow.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  FollowVO followVO = (FollowVO) request.getAttribute("followVO");
%>

<html>
<head>
<title>關注資料 - listOneFollow.jsp</title>

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
		 <h3>關注資料 - listOneFollow.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>關注id</th>
		<th>會員id</th>
		<th>被關注的會員id</th>
		<th>關注時間</th>
	</tr>	
	<tr>
		<td><%=followVO.getFollow_id()%></td>
		<td><%=followVO.getMember_id()%></td>
		<td><%=followVO.getFollowed_member_id()%></td>
		<td><%=followVO.getCreate_time()%></td>
	</tr>
</table>

</body>
</html>