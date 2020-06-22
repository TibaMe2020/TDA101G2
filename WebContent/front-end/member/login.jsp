<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>homepage</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/changePassword.css">
</head>
<body>
	<%@ include file="/front-end/member/header.jsp"%>



	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-lg-2"></div>
			<!-- Main content -->
			<div class="col-lg-8 col-md-12">
				<h2>登入</h2>
				
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>

	<%@ include file="/front-end/member/footer.jsp"%>

</body>
</html>