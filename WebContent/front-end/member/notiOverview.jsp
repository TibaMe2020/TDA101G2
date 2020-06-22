<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page
	import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Notifications</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/notifications.css">
</head>


<body>
	<%@ include file="header.jsp"%>


	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-2">
				<%@ include file="sidebar.jsp"%>
			</div>
			<!-- Main content -->
			<div class="col-9">
				<div class="content-title">通知總覽</div>
				<div class="content-container">
					<c:if test="${ not empty notiMap }">
						<c:forEach var="noti" items="${ notiMap.entrySet() }">
							<div class="card">
								<a>
									<div class="card-body">
										<p class="card-text">${noti.getValue().get(1)}</p>
										<p class="card-text noti-time">${noti.getValue().get(0)}</p>
									</div>
								</a>
							</div>
						</c:forEach>
					</c:if>

				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>

</html>