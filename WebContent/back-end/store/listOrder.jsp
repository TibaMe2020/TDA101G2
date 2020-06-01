<%@page import="java.util.ArrayList"%>
<%@page import="com.store_order_detail.model.Store_order_detailVO"%>
<%@page import="com.store_order_detail.model.Store_order_detailService"%>
<%@page import="com.store_order.model.Store_orderService"%>
<%@page import="java.util.List"%>
<%@page import="com.store_order.model.Store_orderVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	String store_id = (String)request.getAttribute("store_orderVO"); %>
<%
	Store_orderService orderSvc = new Store_orderService();
    List<Store_orderVO> list = orderSvc.selectByStore(store_id);
    pageContext.setAttribute("list",list);
%>
<%-- <% --%>
<!--  	Store_order_detailService detailSvc = new Store_order_detailService(); -->
<!--    	List<Store_order_detailVO> detailList = detailSvc.selectByOrderId(); -->
<%--  %> --%>
<%-- <%  List<Store_order_detailVO> detailList = (List<Store_order_detailVO>)request.getAttribute("store_order_detailVO"); %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單資料 - listOneOrder.jsp</title>
<style>
	table, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>
	<a href='<%=request.getContextPath()%>/back-end/store/select_page.jsp'>回首頁</a>
	<h4>店家編號：<%=store_id%></h4>
	<c:if test="${fn:length(list)==0}">目前沒有任何訂單</c:if>
	<c:if test="${fn:length(list)!=0}">
		<table>
		<tr>
			<th>訂單編號</th>
			<th>會員</th>
			<th>預約者姓名</th>
			<th>電子郵件</th>
			<th>電話</th>
			<th>預約日期</th>
			<th>結束日其</th>
			<th>預約人數</th>
			<th>結帳方式</th>
			<th>備註</th>
			<th>預約狀態</th>
			<th>創建日期</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<c:forEach var="store_orderVO" items="${list}">
			<tr>
				<td>${store_orderVO.store_order_id}</td>
				<td>${store_orderVO.member_id}</td>
				<td>${store_orderVO.store_order_name}</td>
				<td>${store_orderVO.store_order_email}</td>
				<td>${store_orderVO.store_order_phone_num}</td> 
				<td>${store_orderVO.store_order_date_time}</td>
				<td>${store_orderVO.store_order_end_date}</td>
				<td>${store_orderVO.store_order_persons}</td>
				<td>${store_orderVO.store_order_payment}</td>
				<td>${store_orderVO.store_order_note}</td>
				<td>${store_orderVO.store_order_state}</td>
				<td>${store_orderVO.create_time}</td>
			</tr>
		</c:forEach>
		</table>
<!-- 		<table style="display:inline-table;"> -->
<%-- 			<caption>訂單明細</caption> --%>
<!-- 			<tr> -->
<!-- 				<td>訂單明細編號</td> -->
<!-- 				<td>訂單編號</td> -->
<!-- 				<td>服務編號</td> -->
<!-- 				<td>寵物數</td> -->
<!-- 			</tr> -->
<%-- 			<c:forEach var="order_detailVO" items="${store_orderVO.store_order_id}"> --%>
<!-- 			<tr> -->
<%-- 				<th>${order_detailVO.store_order_detail_id}</th> --%>
<%-- 				<th>${order_detailVO.store_order_id}</th> --%>
<%-- 				<th>${order_detailVO.service_id}</th> --%>
<%-- 				<th>${order_detailVO.order_detail_pets}</th> --%>
<!-- 			</tr> -->
<%-- 			</c:forEach>	 --%>
<!-- 		</table>	 -->
	</c:if>
</body>
</html>