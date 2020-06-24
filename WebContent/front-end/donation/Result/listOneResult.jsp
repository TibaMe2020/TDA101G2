<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.donation.donation_result.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Donation_resultVO donation_resultVO = (Donation_resultVO) request.getAttribute("donation_resultVO"); 
%>

<html>
<head>
<title>成果資料 - listOneResult.jsp</title>

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
		 <h3>成果資料 - ListOneResult.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/donation/Result/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>成果編號</th>
<!-- 	<th>公益團體名稱</th> -->
		<th>圖片</th>
		<th>成果月份</th>
		<th>成果內文</th>
		
	
	</tr>
	<tr>
		<td><%=donation_resultVO.getResult_id()%></td>
<%-- 	<td><%=npo_infoVO.getNpo_name()%></td> --%>
		<td><img src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=${donation_resultVO.result_id}"></td>
		<td><%=donation_resultVO.getResult_month()%></td>
		<td><%=donation_resultVO.getResult_content()%></td>
		
	
	</tr>
	<script>

</script>
</table>

</body>
</html>