<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.donation.npo_info.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Npo_infoVO npo_infoVO = (Npo_infoVO) request.getAttribute("npo_infoVO"); 
%>

<html>
<head>
<title>員工資料 - listOneNpo.jsp</title>

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
		 <h3>員工資料 - ListOneNpo.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/donation/Npo/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>公益團體編號</th>
		<th>公益團體名稱</th>
		<th>圖片</th>
		<th>公益團體介紹</th>
	
	</tr>
	<tr>
		<td><%=npo_infoVO.getNpo_id()%></td>
		<td><%=npo_infoVO.getNpo_name()%></td>
		<td><img src="<%=request.getContextPath()%>/Npo/DBGifReader2?npo_id=${npo_infoVO.npo_id}"></td>
		<td><%=npo_infoVO.getNpo_description()%></td>
	
	</tr>

</table>

</body>
</html>