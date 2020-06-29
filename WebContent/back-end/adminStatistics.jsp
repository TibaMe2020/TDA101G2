<%@page import="com.blog.post.model.PostVO"%>
<%@page import="com.blog.post.model.PostService"%>
<%@page import="com.store.model.StoreService"%>
<%@page
		import="com.donation.donation_form_info.model.Donation_form_infoVO"%>
<%@page
		import="com.donation.donation_form_info.model.Donation_form_infoService"%>
<%@page import="com.donation.adopt_form_info.model.Adopt_form_infoVO"%>
<%@page
		import="com.donation.adopt_form_info.model.Adopt_form_infoService"%>
<%@page import="com.product.model.Product_Service"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page
		import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>
<%@page import="com.admin.model.AdminVO"%>

<%@page import="com.admin.model.AdminService"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Statistics</title>
<link rel="stylesheet"
		href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		crossorigin="anonymous">
<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link href="https://unpkg.com/filepond/dist/filepond.css"
		rel="stylesheet">
<link rel="stylesheet" type="text/css"
		href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet"
		href="<%=request.getContextPath()%>/back-end/css/adminStatistics.css">
</head>

<%
	//D會員總數
	MemberService mbSvc = new MemberService();
	List<MemberVO> mlist = mbSvc.getAll();
	int totalMember = mlist.size();
	pageContext.setAttribute("totalMember", totalMember);
	System.out.println("totalMember");
	//D商城總消費金額
	Product_Service pdSvc = new Product_Service();
	Map<String, String> pmap = pdSvc.getByClass();
	pageContext.setAttribute("salesTotal", pmap);
	System.out.println("salesTotal");
	//D領養動物數量
	Adopt_form_infoService afSvc = new Adopt_form_infoService();
	List<Adopt_form_infoVO> adoptList = afSvc.getAll();
	long adoptTotal = adoptList.stream().count();
	pageContext.setAttribute("adoptTotal", adoptTotal);
	System.out.println("adoptTotal");

	//D捐款總金額
	Donation_form_infoService doSvc = new Donation_form_infoService();
	List<Donation_form_infoVO> donationList = doSvc.getAll();
	Integer donationTotal = donationList.stream().map(d -> d.getDonation_money()).reduce(0, Integer::sum);
	pageContext.setAttribute("donationTotal", donationTotal);
	System.out.println("donationTotal");
	//D總文章數
	PostService poSvc = new PostService();
	List<PostVO> posts = poSvc.getAll();
	long postCount = posts.stream().count();
	pageContext.setAttribute("postCount", postCount);
	System.out.println("postCount");
	//D店家種類比例
	StoreService stSvc = new StoreService();
	Map<String, Integer> stores = stSvc.getAllCalculated();
	pageContext.setAttribute("stores", stores);
	System.out.println("stores");
%>

<body>
		<header class="bg-white">
				<div class="container-fluid">
						<div class="row header">
								<div
										class="col-2 d-flex align-items-center justify-content-center border-right border-bottom">
										<img
												src="<%=request.getContextPath()%>/resources/images/admin.svg"
												alt="admin"> &nbsp;
										<h5 class="text-center admin-tab">Admin</h5>
								</div>
								<div
										class="col-10 d-flex align-items-center justify-content-center border-bottom">
										<h3 class="title-tab">數據統計</h3>
								</div>
						</div>
				</div>

		</header>

		<div class="container-fluid">
				<div class="row content-height">
						<div class="col-2">
								<%@ include file="sidebar.jsp"%>
						</div>

						<div class="col-10">
								<div class="row justify-content-center">
										<div class="col-10 row justify-content-center height-100">
												<div class="col-3 statistics align-self-center"
														id="total-member">
														<div class="card rounded" style="width: 18rem;">
																<div class="card-body rounded d-flex justify-content-around align-items-center"  style="background-color: rgb(79,129,188);">
																		<div>
																			<i class="far fa-user fa-2x text-white"></i>
																		</div>
																		<div >
																			<h5 class="card-title text-white">總會員數</h5>
																			<p class="card-text text-white">${totalMember}</p>
																		</div>
																</div>
														</div>
												</div>
												<div class="col-3 statistics align-self-center"
														id="total-posts">
														<div class="card " style="width: 18rem;">
																<div class="card-body rounded d-flex justify-content-around align-items-center" style="background-color: rgb(155,187,88);">
																	<div>
																		<i class="far fa-edit fa-2x text-white"></i>
																	</div>
																	<div>
																		<h5 class="card-title text-white">總文章數</h5>
																		<p class="card-text text-white">${postCount}</p>
																	</div>
																</div>
														</div>
												</div>
												<div class="col-3 statistics align-self-center"
														id="total-donation">
														<div class="card rounded" style="width: 18rem;">
																<div class="card-body rounded d-flex justify-content-around align-items-center" style="background-color:rgb(35,191,170);">
																		<div>
																			<i class="fas fa-hand-holding-heart text-white fa-2x"></i>
																		</div>
																		<div>
																			<h5 class="card-title text-white">捐款總金額</h5>
																			<p class="card-text text-white">
																			<fmt:formatNumber value = "${donationTotal}" maxFractionDigits="0" type = "currency"/>
																			</p>																		
																		</div>
																</div>
														</div>
												</div>
												<div class="col-3 statistics align-self-center"
														id="total-adopt">
														<div class="card rounded" style="width: 18rem;">
																<div class="card-body rounded d-flex justify-content-around align-items-center" style="background-color:rgb(128,100,161);">
																	<div>
																		<i class="fas fa-dog text-white fa-2x"></i>
																	</div>
																	<div>
																		<h5 class="card-title text-white">動物領養數量</h5>
																		<p class="card-text text-white">${adoptTotal}</p>																	
																	</div>
																</div>
														</div>
												</div>
												<div
														class="col-10 d-flex justify-content-between chart-holder">
														<div id="product-chart"
																style="height: 500px; width: 500px;"
																class="shadow rounded"></div>
														<div id="store-chart" style="height: 500px; width: 500px;"
																class="shadow rounded"></div>
												</div>
										</div>
								</div>

						</div>

				</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
				integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
				crossorigin="anonymous">
			
		</script>
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
		<script type="text/javascript" charset="utf8"
				src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js">
			
		</script>
		<script type="text/javascript"
				src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

		<script>
			window.onload = function() {
				let chart1 = new CanvasJS.Chart("product-chart", {
					title : {
						text : "銷售統計",
						fontSize : 20
					},
					data : [ {
						type : "doughnut",
						dataPoints : [ {
							label : "食品",
							y : '${salesTotal.get("食品")}'
						}, {
							label : "服飾",
							y : '${salesTotal.get("服飾")}'
						}, {
							label : "住家",
							y : '${salesTotal.get("住家")}'
						}, {
							label : "用品",
							y : '${salesTotal.get("用品")}'
						} ]
					} ]
				});
				chart1.render();

				let chart2 = new CanvasJS.Chart("store-chart", {
					title : {
						text : "寵物店家種類比例",
						fontSize : 20
					},
					dataPointWidth : 30,
					data : [ {
						type : "doughnut",
						dataPoints : [ {
							label : "學校",
							y : '${stores.get("學校")}'
						}, {
							label : "醫院",
							y : '${stores.get("醫院")}'
						}, {
							label : "旅館",
							y : '${stores.get("旅館")}'
						}, {
							label : "美容",
							y : '${stores.get("美容")}'
						}, {
							label : "餐廳",
							y : '${stores.get("餐廳")}'
						} ]
					} ]
				});
				chart2.render();
			}
		</script>

</body>

</html>