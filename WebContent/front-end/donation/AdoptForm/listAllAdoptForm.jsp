<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.donation.adopt_form_info.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Adopt_form_infoService AdoptFormSvc = new Adopt_form_infoService();
    List<Adopt_form_infoVO> list = AdoptFormSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有認養表單資料 - listAllAdoptForm.jsp</title>

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
		 <h3>所有認養表單資料 - listAllAdoptForm.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/donation/AdoptForm/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>認養表單編號</th>
<!-- 		<th>認養動物編號</th> -->
<!-- 		<th>認養會員編號</th> -->
		<th>認養證書姓名</th>
		<th>付款人</th>
		<th>付款方式</th>
		<th>是否需要實體證書</th>
		<th>聯絡電話</th>
		<th>住家地址</th>
		<th>電子郵件</th>
<!-- 		<th>上次更新</th> -->

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="adopt_form_infoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${adopt_form_infoVO.adopt_form_id}</td>
<%-- 		<td>${adopt_infoVO.adopt_name}</td> --%>
<%-- 		<td>${adopt_infoVO.adopt_name}</td> --%>
			<td>${adopt_form_infoVO.adopt_person}</td>
			<td>${adopt_form_infoVO.payadopt_person}</td>
			<td>${adopt_form_infoVO.adopt_payment}</td>
			<td>${adopt_form_infoVO.adopt_certificate}</td> 
			<td>${adopt_form_infoVO.adopt_phone_num}</td> 
			<td>${adopt_form_infoVO.address}</td> 
			<td>${adopt_form_infoVO.adopt_email}</td> 			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AdoptForm/adoptform.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adopt_id"  value="${adopt_form_infoVO.adopt_form_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AdoptForm/adoptform.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adopt_form_id"  value="${adopt_form_infoVO.adopt_form_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>