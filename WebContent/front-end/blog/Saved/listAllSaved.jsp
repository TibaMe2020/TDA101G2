<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.saved.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    SavedService service = new SavedService();
    List<SavedVO> list = service.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有收藏資料 - listAllSaved.jsp</title>

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
		 <h3>所有收藏資料 - listAllSaved.jsp</h3>
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
		<th>收藏文章id</th>
		<th>會員id</th>
		<th>文章id</th>
		<th>收藏時間</th>
	</tr>
	<c:forEach var="savedVO" items="${list}">
		
		<tr>
			<td>${savedVO.saved_post_id}</td>
			<td>${savedVO.member_id}</td>
			<td>${savedVO.post_id}</td>
			<td>${savedVO.create_time}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Saved/Saved.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="saved_post_id"  value="${savedVO.saved_post_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		
	</c:forEach>
</table>

</body>
</html>