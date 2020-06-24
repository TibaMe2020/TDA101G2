<%@page import="com.store.service.model.ServiceVO"%>
<%@page import="com.store.service.model.ServiceService"%>
<%@page import="java.util.List"%>
<%@page import="com.store_order.model.Store_orderVO"%>
<%@page import="com.store_order.model.Store_orderService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Store_orderService orderSvc = new Store_orderService();
    List<Store_orderVO> list = orderSvc.getAll();
    pageContext.setAttribute("list",list);

 	String store_id = (String)request.getAttribute("store_id");
 
	ServiceService serviceSvc = new ServiceService();
	pageContext.setAttribute("serviceSvc", serviceSvc);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有店家訂單 - listAllOrder.jsp</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/adminStatistics.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<style>
	table, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>
<header class="bg-white">
		<div class="container-fluid">
			<div class="row header">
				<div
					class="col-2 d-flex align-items-center justify-content-center border-right border-bottom">
					<img src="<%=request.getContextPath()%>/resources/images/admin.svg"
						alt="admin"> &nbsp;
					<h5 class="text-center admin-tab">Admin</h5>
				</div>
				<div
					class="col-10 d-flex align-items-center justify-content-center border-bottom">
					<h3 class="title-tab">所有訂單</h3>
				</div>
			</div>
		</div>

	</header>
	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-2">
				<%@ include file="/back-end/sidebar.jsp"%>
			</div>

			<div class="col-10">
				<div class="row justify-content-star myform">
				<div class="col-1"></div>
				<div class="col-10">
	<%@ include file="page1.file" %>
	<span style="color: red ">${errorMsgs.error}</span>
	<table>
	<tr>
		<th>訂單編號</th>
		<th>店家編號</th>
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
		<th>訂單明細</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="store_orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${store_orderVO.store_order_id}</td>
		<td>${store_orderVO.store_id}</td>
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
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/OrderController" style="margin-bottom: 0px;">
		     <input type="submit" value="訂單明細">
		     <input type="hidden" name="storeId"  value="${store_orderVO.store_order_id}">
		     <input type="hidden" name="action" value="getDetailList"></FORM>
		</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/OrderController" style="margin-bottom: 0px;">
		     <input type="submit" value="刪除">
		     <input type="hidden" name="storeOrderId"  value="${store_orderVO.store_order_id}">
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
	</tr>
	</c:forEach>
	</table>
	<%@ include file="page2.file" %>
	<br>
	<table>
		<caption>訂單編號-${store_id}</caption>
		<tr>
			<th>訂單明細編號</th>
			<th>服務項目</th>
			<th>寵物數</th>
		</tr>
<%-- 		<jsp:useBean id="serviceSvc" scope="page" class="com.service.model.ServiceService" /> --%>
		<c:forEach var="Store_order_detailVO" items="${detailList}" >
		<tr>
			<td>${Store_order_detailVO.store_order_detail_id}</td>
			<td>${Store_order_detailVO.service_id}-${serviceSvc.selectByServiceID(Store_order_detailVO.service_id).service_detail}</td>
			<td>${Store_order_detailVO.order_detail_pets}</td>
		</tr>
	    </c:forEach>
	</table>
				</div>
		</div>

	</div>

	</div>
	</div>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous">
	</script>
	
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous">
	</script>
</body>
</html>