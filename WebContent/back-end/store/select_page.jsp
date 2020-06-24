<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store:Home</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/adminStatistics.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<style type="text/css">
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
					<h3 class="title-tab">店家</h3>
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
				<div class="row myform">
				<div class="col-1"></div>
				<div class="col-10">
				<h3>店家查詢</h3>
					<ul>
						<li><a href='listAllStore.jsp'>List</a> all Stores.</li>
						<li>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/Controller">
								<b>輸入店家編號 (如S07001):</b> <input type="text" name="storeId">
								<input type="hidden" name="action" value="getOneForDisplay">
								<input type="submit" value="送出"> <span
									style="color: red">${errorMsgs.error}</span>
							</FORM>
						</li>
						<jsp:useBean id="storeSvc" scope="page"
							class="com.store.model.StoreService" />
						<li>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/Controller">
								<b>選擇店家編號：</b> <select size="1" name="storeId">
									<c:forEach var="storeVo" items="${storeSvc.all}">
										<option value="${storeVo.store_id}" />${storeVo.store_id}
					</c:forEach>
								</select> <input type="hidden" name="action" value="getOneForDisplay">
								<input type="submit" value="送出">
							</FORM>
						</li>
						<li>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/Controller">
								<b>選擇店家名稱：</b> <select size="1" name="storeId">
									<c:forEach var="storeVo" items="${storeSvc.all}">
										<option value="${storeVo.store_id}" />${storeVo.store_name}
					</c:forEach>
								</select> <input type="hidden" name="action" value="getOneForDisplay">
								<input type="submit" value="送出">
							</FORM>
						</li>
					</ul>

					<h3>店家管理</h3>
					<ul>
						<li><a href='addStore.jsp'>新增</a>一間店家</li>
					</ul>
					<h3>訂單查詢</h3>
					<ul>
						<li><a href='listAllOrder.jsp'>List</a> all Store orders.</li>
						<li>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/store/OrderController">
								<b>查詢店家訂單：</b> <select size="1" name="storeId">
									<c:forEach var="storeVo" items="${storeSvc.all}">
										<option value="${storeVo.store_id}" />${storeVo.store_id}-${storeVo.store_name}
					</c:forEach>
								</select> <input type="hidden" name="action" value="getOneForDisplay">
								<input type="submit" value="送出">
							</FORM>
						</li>
					</ul>
					<h3>訂單管理</h3>
					<ul>
						<li><a href='addOrder.jsp'>新增</a>訂單</li>
					</ul>
				</div>
				</div>
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