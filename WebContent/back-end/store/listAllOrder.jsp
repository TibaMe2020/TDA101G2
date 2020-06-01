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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有店家訂單 - listAllOrder.jsp</title>
<style>
	table, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>
	<a href='<%=request.getContextPath()%>/back-end/store/select_page.jsp'>回首頁</a>
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
		<th>修改</th>
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
<%-- 		<%for(int i=1;i<=3;i++){%> --%>
<!-- 		<td> -->
<%-- 			<img style="width:100px;height:100px;" src="<%=request.getContextPath()%> --%>
<%-- 			/store/ShowImg?store_id=${storeVO.store_id}&store_image=<%=i%>"/> --%>
<!-- 		</td> -->
<%-- 		<%}%> --%>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller" style="margin-bottom: 0px;">
		     <input type="submit" value="修改">
		     <input type="hidden" name="storeId"  value="${store_orderVO.store_order_id}">
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller" style="margin-bottom: 0px;">
		     <input type="submit" value="刪除">
		     <input type="hidden" name="storeId"  value="${store_orderVO.store_order_id}">
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
	</tr>
	</c:forEach>
	</table>
	<%@ include file="page2.file" %>
</body>
</html>