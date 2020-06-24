<%@page import="com.store_closed.model.Store_closedVO"%>
<%@page import="com.store_closed.model.Store_closedService"%>
<%@page import="com.store.service.model.ServiceService"%>
<%@page import="com.store.service.model.ServiceVO"%>
<%@	page import="com.store.model.StoreVO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
  StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%  
	ServiceService serviceSvc = new ServiceService();
	List<ServiceVO> serviceVO = serviceSvc.selectByStore(storeVO.getStore_id());
	pageContext.setAttribute("serviceVOlist",serviceVO);
%>
<%  
	Store_closedService closedSvc = new Store_closedService();
	Set<Store_closedVO> closed = closedSvc.selectByStore(storeVO.getStore_id());
	pageContext.setAttribute("closedlist",closed);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家資料 - listOneEmp.jsp</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/adminStatistics.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<style type="text/css">
	table, th, td {
		border: 1px solid black;
	}


div.myform a {
	color: blue;
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
					<h3 class="title-tab">單一店家所有明細</h3>
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
	<h4 style="display:inline-table;"><%=storeVO.getStore_name()%></h4>
	<span><a href='<%=request.getContextPath()%>/back-end/store/listAllStore.jsp'>回所有店家</a></span>
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
			<th>最大人數</th>
			<th>圖1</th>
			<th>圖2</th>
			<th>圖3</th>
			<th>狀態</th>
			<th>修改</th>
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
			<td><%=storeVO.getStore_maxcapacity()%></td>
			<%
		      for(int i=1;i<=3;i++){
		        %><td><img style="width:100px;height:100px;" src="<%=request.getContextPath()%>/store/ShowImg?store_id=${storeVO.store_id}&store_image=<%=i%>"/></td><% 
		      }
		    %>
			<td><%=storeVO.getStore_on()%></td>
					<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller" style="margin-bottom: 0px;">
		    	<input type="submit" value="修改">
		    	<input type="hidden" name="storeId"  value="${storeVO.store_id}">
		    	<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		
		</tr>
	</table>
	<c:if test="${storeVO.store_class== '旅館' or storeVO.store_class== '美容' or storeVO.store_class== '學校'  }">
		<table style="display:inline-table;">
			<caption>服務項目</caption>
			<tr>
				<td>服務編號</td>
				<td>服務內容</td>
				<td>價格</td>
				<td>服務上限</td>
				<td>服務時間</td>
				<td>狀態</td>
			</tr>
			<c:forEach var="serviceVO" items="${serviceVOlist}">
			<tr>
				<th>${serviceVO.service_id}</th>
				<th>${serviceVO.service_detail}</th>
				<th>${serviceVO.service_price}</th>
				<th>${serviceVO.service_limit}</th>
				<th>${serviceVO.service_time}</th>
				<th>${serviceVO.service_state}</th>
			</tr>
			</c:forEach>	
		</table>
		<table style="display:inline-table;">
			<caption>休假日</caption>
			<tr>
				<td>編號</td>
				<td>日期</td>
			</tr>
			<c:forEach var="closedVO" items="${closedlist}">
			<tr>
				<th>${closedVO.store_closed_id}</th>
				<th>${closedVO.store_closed_day}</th>
			</tr>
			</c:forEach>
		</table>
	</c:if>
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