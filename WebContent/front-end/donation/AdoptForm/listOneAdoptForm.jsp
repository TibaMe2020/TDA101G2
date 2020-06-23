<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.donation.adopt_form_info.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Adopt_form_infoVO adopt_form_infoVO = (Adopt_form_infoVO) request.getAttribute("adopt_form_infoVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>認養表單資料 - listOneAdoptForm.jsp</title>

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
		 <h3>認養表單資料 - ListOneAdoptForm.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/donation/AdoptForm/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>認養表單編號</th>
		<th>認養動物姓名</th>
		<th>認養人</th>
		<th>付款人</th>
		<th>認養金額</th>
		<th>付款方式</th>
		<th>是否需要實體證書</th>
		<th>住家地址</th>
		<th>電子郵件</th>
	</tr>
	<tr>
		<td><%=adopt_form_infoVO.getAdopt_form_id()%></td>
<%-- 		<td>${adoptVO.getOneAdopt}(adopt_form_infoVO.adopt_id).adopt_name}</td> --%>
		<td><%=adopt_form_infoVO.getAdopt_person()%></td>
		<td><%=adopt_form_infoVO.getPayadopt_person()%></td>
<%-- 		<td>${adoptVO.getOneAdopt}(adopt_form_infoVO.adopt_id).adopt_money}</td> --%>
		<td><%=adopt_form_infoVO.getAdopt_payment()%></td>		
		<td><%=adopt_form_infoVO.getAdopt_certificate()%></td>
		<td><%=adopt_form_infoVO.getAddress()%></td>
		<td><%=adopt_form_infoVO.getAdopt_email()%></td>
		
	</tr>
</table>

</body>
</html>