<%@page import="com.donation.adopt_info.model.Adopt_infoVO"%>
<%@page import="com.donation.adopt_info.model.Adopt_infoService"%>
<%@page import="com.donation.npo_info.model.Npo_infoVO"%>
<%@page import="com.donation.npo_info.model.Npo_infoService"%>
<%@page
		import="com.donation.donation_form_info.model.Donation_form_infoVO"%>
<%@page
		import="com.donation.donation_form_info.model.Donation_form_infoService"%>
<%@page import="com.donation.adopt_form_info.model.Adopt_form_infoVO"%>
<%@page
		import="com.donation.adopt_form_info.model.Adopt_form_infoService"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page
		import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Donation Record</title>
<link rel="stylesheet"
		href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
		crossorigin="anonymous">
<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link rel="stylesheet"
		href="<%=request.getContextPath()%>/front-end/member/css/donationRecord.css">
</head>

<body>



		<%@ include file="header.jsp"%>
		<%
			Adopt_form_infoService afSvc = new Adopt_form_infoService();
			Npo_infoService npoSvc = new Npo_infoService();
			List<Npo_infoVO> npos = npoSvc.getAll();
			Adopt_infoService adoptSvc = new Adopt_infoService();
			List<Adopt_infoVO> adopts = adoptSvc.getAll();

			pageContext.setAttribute("npos", npos);
			pageContext.setAttribute("adopts", adopts);
			// 			adoptList = adoptList.stream()
			// 					.filter( a -> a.getMember_id().equals(member_id))
			// 					.collect(Collectors.toList());
			List<Adopt_form_infoVO> adoptList = new ArrayList<>();

			for (Adopt_form_infoVO afVO : afSvc.getAll()) {
				if (member_id.equals(afVO.getMember_id())) {
					adoptList.add(afVO);
				}
			}
			pageContext.setAttribute("adoptList", adoptList);

			Donation_form_infoService doSvc = new Donation_form_infoService();
			List<Donation_form_infoVO> donationList = new ArrayList<>();
			for (Donation_form_infoVO dfVO : doSvc.getAll()) {
				if (member_id.equals(dfVO.getMember_id())) {
					donationList.add(dfVO);
				}
			}
			pageContext.setAttribute("donationList", donationList);
			// 			System.out.println(adoptList);
			// 			System.out.println(donationList);
		%>
		<div class="container-fluid">
				<div class="row content-height">
						<div class="col-2">
								<%@ include file="sidebar.jsp"%>
						</div>
						<!-- Main content -->
						<div class="col-9">
								<div class="content-title">公益紀錄</div>
								<div class="content-container">
										<ul class="nav nav-pills mb-3 d-flex" id="pills-tab"
												role="tablist">
												<li class="nav-item dropdown nav-tab"><a
														class="nav-link active" data-toggle="pill"
														href="#donation-record" role="button" aria-haspopup="true"
														aria-expanded="false">捐款紀錄</a></li>
												<li class="nav-item nav-tab "><a class="nav-link"
														data-toggle="pill" href="#adopt-record" role="button"
														aria-haspopup="true" aria-expanded="false">領養紀錄</a></li>
										</ul>

										<div class="tab-content" id="pills-tabContent">
												<div class="tab-pane fade show active" id="donation-record"
														role="tabpanel" aria-labelledby="pills-1-tab">
														<!-- 							捐款紀錄 -->
														<c:forEach items="${donationList}" var="donation">
																<c:forEach items="${npos}" var="npo">
																		<c:if test="${npo.npo_id == donation.npo_id}">
																				<div class="card bg-white border-0">
																						<div class="card-body row">
																								<div class="product-image col-2">
																										<img
																												src="<%=request.getContextPath()%>/Npo/DBGifReader2?npo_id=${donation.npo_id}"
																												class="rounded">
																								</div>
																								<div class="col-8 text-left">
																										<h5 class="card-title">${npo.npo_name}</h5>
																										<p class="card-text">
																												${npo.npo_description}</p>
																								</div>
																								<div class="col-2 text-left align-self-center">
																										<h5 class="card-title">
																												<fmt:formatNumber maxFractionDigits="0"
																														value="${donation.donation_money}"
																														type="currency" />
																										</h5>
																										<p class="card-text">
																												<fmt:formatDate
																														value="${donation.create_time}"
																														pattern="yyyy-MM-dd" />
																										</p>
																								</div>
																						</div>
																				</div>
																		</c:if>
																</c:forEach>
														</c:forEach>

												</div>

												<div class="tab-pane fade" id="adopt-record" role="tabpanel"
														aria-labelledby="pills-2-tab">
														<!-- 							領養 -->
														<c:forEach items="${adoptList}" var="adopt">
																<c:forEach items="${adopts}" var="ad">
																		<c:if test="${adopt.adopt_id == ad.adopt_id}">
																				<div class="card bg-white border-0">
																						<div class="card-body row">
																								<div class="product-image col-2">
																										<img
																												src="<%=request.getContextPath()%>/Adopt/DBGifReader3?adopt_id=${adopt.adopt_id}"
																												class="rounded">
																								</div>
																								<div class="col-8 text-left">
																										<h5 class="card-title">${ad.adopt_name}</h5>
																										<p class="card-text">
																										  ${ad.adopt_description}
																										</p>
																								</div>
																								<div class="col-2 text-left align-self-center">
																										<h5 class="card-title"></h5>
																										<p class="card-text">
																										<fmt:formatDate value="${adopt.create_time}"
																														pattern="yyyy-MM-dd" />
																										</p>
																								</div>
																						</div>
																				</div>
																		</c:if>
																</c:forEach>
														</c:forEach>
												</div>
										</div>

								</div>
						</div>
				</div>

				<%@ include file="footer.jsp"%>
</body>

</html>