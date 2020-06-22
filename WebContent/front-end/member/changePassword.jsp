<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
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
	<%@ include file="header.jsp"%>


	<%
		member_id = request.getParameter("member_id");
		MemberService mbSvc = new MemberService();
		MemberVO memberVO1 = mbSvc.getOne(member_id);
		pageContext.setAttribute("memberVO", memberVO1);
	%>

	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-lg-2"></div>
			<!-- Main content -->
			<div class="col-lg-8 col-md-12">
				<h2>更改密碼</h2>
				<form method="post" id="change-password-form" class="main-form"
					action="<%=request.getContextPath()%>/member/controller">
					<div class="row justify-content-around">
						<div class="col-lg-6 text-left">

							<div class="form-group">
								<label for="change-password">新密碼</label>
								<input type="password" name="password" id="change-password"
									class="form-control ${not empty errorMsgs.changePassword ? 'is-invalid' : ''}" required>
							<div class="${not empty errorMsgs.changePassword ? 'invalid-feedback' : ''}">
                 ${empty errorMsgs.changePassword ? '' : errorMsgs.changePassword}</div> 
							</div>
							<div class="form-group">
								<label for="change-confirm">確認密碼</label>
								<input type="password" name="confirm" id="change-confirm"
									class="form-control ${not empty errorMsgs.changeConfirm ? 'is-invalid' : ''}" required>
							<div class="${not empty errorMsgs.changeConfirm ? 'invalid-feedback' : ''}">
                     ${empty errorMsgs.changeConfirm ? '' : errorMsgs.changeConfirm}</div>
							</div>
							<input type="hidden" name="member_id"
								value="${memberVO.member_id}"> 
							<input type="hidden"
								name="action" value="change_password">
							<div style="color: #dc3545">${errorMsgs.error}</div>
							<button type="submit" class="btn btn-primary">送出</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>