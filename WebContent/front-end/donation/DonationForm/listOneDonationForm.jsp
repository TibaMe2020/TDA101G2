<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.donation.donation_form_info.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Donation_form_infoVO donation_form_infoVO = (Donation_form_infoVO) request.getAttribute("donation_form_infoVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<jsp:useBean id ="npoSvc" scope="page" class="com.donation.npo_info.model.Npo_infoService"/>

<html>
<head>
<title>認養表單資料 - listOneDonationForm.jsp</title>

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
		 <h3>認養表單資料 - ListOneDonationForm.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/donation/DonationForm/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>捐款日期</th>
		<th>捐款地區</th>
		<th>捐款姓名</th>
		<th>捐款金額</th>
		<th>付款方式</th>
	</tr>
	<tr>
		<td><%=donation_form_infoVO.getCreate_time()%></td>
		<td>${npoSvc.getOneNpo_info(donation_form_infoVO.npo_id).npo_name}</td>		
<%-- 		<td>${adoptVO.getOneAdopt}(adopt_form_infoVO.adopt_id).adopt_name}</td> --%>
		<td><%=donation_form_infoVO.getDonator_name()%></td>
		<td><%=donation_form_infoVO.getDonation_money()%></td>
<%-- 		<td>${adoptVO.getOneAdopt}(adopt_form_infoVO.adopt_id).adopt_money}</td> --%>
		<td><%=donation_form_infoVO.getPayment()%></td>		
	
		
	</tr>
</table>

</body>
</html>