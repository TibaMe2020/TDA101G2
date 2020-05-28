<%@page import="com.store.model.StoreVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.store.model.StoreService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    StoreService storeSvc = new StoreService();
    List<StoreVO> list = storeSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有店家資料 - listAllStore.jsp</title>
<style>
	table, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="storeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${storeVO.store_id}</td>
		<td>${storeVO.member_id}</td>
		<td>${storeVO.store_class}</td>
		<td>${storeVO.store_name}</td>
		<td>${storeVO.store_adress}</td>
		<td>${storeVO.store_phone_number}</td> 
		<td>${storeVO.store_introduction}</td>
		<td>${storeVO.store_clicks}</td>
		<td>${storeVO.store_firstbreak}</td>
		<td>${storeVO.store_secondbreak}</td>
		<td>${storeVO.store_openhours1}</td>
		<td>${storeVO.store_timelimit}</td>
	
	</tr>
	</c:forEach>
	</table>
	<%@ include file="page2.file" %>
</body>
</html>