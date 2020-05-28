<%@	page import="com.store.model.StoreVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
  StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家資料 - listOneEmp.jsp</title>
<style>
	table, th, td {
		border: 1px solid black;
	}

</style>
</head>
<body>
	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table>
	<tr>
		<th>店家編號</th>
		<th>會員編號</th>
		<th>分類</th>
		<th>名稱</th>
		<th>地址</th>
		<th>電話</th>
		<th>簡介</th>
		<th>點閱數</th>
		<th>公休日1</th>
		<th>公休日2</th>
		<th>營業時段1</th>
		<th>用餐時間(分)</th>
	</tr>
	<tr>
		<td><%=storeVO.getStore_id()%></td>
		<td><%=storeVO.getMember_id()%></td>
		<td><%=storeVO.getStore_class()%></td>
		<td><%=storeVO.getStore_name()%></td>
		<td><%=storeVO.getStore_adress()%></td>
		<td><%=storeVO.getStore_phone_number()%></td> 
		<td><%=storeVO.getStore_introduction()%></td>
		<td><%=storeVO.getStore_clicks()%></td>
		<td><%=storeVO.getStore_firstbreak()%></td>
		<td><%=storeVO.getStore_secondbreak()%></td>
		<td><%=storeVO.getStore_openhours1()%></td>
		<td><%=storeVO.getStore_timelimit()%></td>
	
	</tr>
</table>
<a href='<%=request.getContextPath()%>/back-end/store/select_page.jsp'>回select_page</a>
</body>
</html>