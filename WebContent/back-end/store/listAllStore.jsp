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
					<h3 class="title-tab">所有店家</h3>
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
				<div class="col-auto"></div>
				<div class="col-11">
	<%@ include file="page1.file" %>
	<span style="color: red ">${errorMsgs.error}</span>
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
		<th>刪除</th>
	</tr>
	
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
		<td>${storeVO.store_maxcapacity}</td>
		<%for(int i=1;i<=3;i++){%>
		<td>
			<img style="width:100px;height:100px;" src="<%=request.getContextPath()%>
			/store/ShowImg?store_id=${storeVO.store_id}&store_image=<%=i%>"/>
		</td>
		<%}%>
		<td>${storeVO.store_on}</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller" style="margin-bottom: 0px;">
		     <input type="submit" value="修改">
		     <input type="hidden" name="storeId"  value="${storeVO.store_id}">
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller" style="margin-bottom: 0px;">
		     <input type="submit" value="刪除">
		     <input type="hidden" name="storeId"  value="${storeVO.store_id}">
		     <input type="hidden" name="action" value="delete"></FORM>
		</td>
	</tr>
	</c:forEach>
	</table>
	<%@ include file="page2.file" %>
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