<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.follow.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	FollowVO followVO = new FollowVO();
	String member_id = followVO.getMember_id();
    FollowService service = new FollowService();
    List<FollowVO> list = service.getByMemberId(member_id);
    pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>某個會員的關注資料 - listAllByMemberId.jsp</title>

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
		 <h3>某個會員的關注資料 - listAllByMemberId.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>關注id</th>
		<th>會員id</th>
		<th>被關注者會員id</th>
		<th>關注時間</th>
	</tr>
	<c:forEach var="followVO" items="${list}">
		
		<tr>
<%-- 			<td><%=followVO.getFollow_id()%></td> --%>
<%-- 			<td><%=followVO.getMember_id()%></td> --%>
<%-- 			<td><%=followVO.getFollowed_member_id()%></td> --%>
<%-- 			<td><%=followVO.getCreate_time()%></td> --%>
			
			<td>${followVO.follow_id}</td>
			<td>${followVO.member_id}</td>
			<td>${followVO.followed_member_id}</td>
			<td>${followVO.create_time}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Follow/Follow.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="follow_id" value="<%=followVO.getFollow_id()%>">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>