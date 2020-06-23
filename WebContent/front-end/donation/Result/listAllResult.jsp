<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.donation.donation_result.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Donation_resultService ResultSvc = new Donation_resultService(); //介面
    List<Donation_resultVO> list = ResultSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id ="npoSvc" scope="page" class="com.donation.npo_info.model.Npo_infoService"/>

<html>
<head>
<title>所有成果資料 - listAllResult.jsp</title>

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
		 <h3>所有成果資料 - listAllResult.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/donation/Result/select_page.jsp"><img src="images/I'm+A+Star.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>公益團體名稱</th>
<!-- 	<th>成果名稱</th> -->
		<th>圖片</th>
		<th>成果月份</th>
		<th>成果內文</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="donation_resultVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${npoSvc.getOneNpo_info(donation_resultVO.npo_id).npo_name}</td>		
<%-- 		<td>${donation_resultVO.npo_id}</td> --%>
			<td><img src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=${donation_resultVO.result_id}"></td>
			<td>${donation_resultVO.result_month}</td>
			<td>${donation_resultVO.result_content}</td>

<%-- ${inpo_infoVO.npo_image} --%>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="result_id"  value="${donation_resultVO.result_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="result_id"  value="${donation_resultVO.result_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>