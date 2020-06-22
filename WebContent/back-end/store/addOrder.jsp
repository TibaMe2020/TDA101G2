<%@page import="com.store_order.model.Store_orderVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Store_orderVO store_orderVO = (Store_orderVO) request.getAttribute("store_orderVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增訂單 - addOrder.jsp</title>
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
					<h3 class="title-tab">新增訂單</h3>
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
					<span style="color: red">${errorMsgs.error}</span>
					<jsp:useBean id="storeSvc" scope="page"
						class="com.store.model.StoreService" />
					<form method="post"
						action="<%=request.getContextPath()%>/store/OrderController">
						<table style="display: inline-table;">
							<tr>
								<td>店家:</td>
								<td><select size="1" name="storeId" id="storeVo.store_id"
									onchange="loadOrderId(event)">
										<c:forEach var="storeVo" items="${storeSvc.all}">
											<option value="${storeVo.store_id}"
												${(storeVo.store_id==store_orderVO.store_id)? 'selected':'' } />${storeVo.store_id}-${storeVo.store_name}
						</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>會員ID:</td>
								<td><input type="TEXT" name="memberId" size="45"
									value="<%=(store_orderVO == null) ? "" : store_orderVO.getMember_id()%>" />
									<span style="color: red">${errorMsgs.error_member}</span></td>
							</tr>
							<tr>
								<td>預約者姓名:</td>
								<td><input type="TEXT" name="bookerName" size="45"
									value="<%=(store_orderVO == null) ? "" : store_orderVO.getStore_order_name()%>" />
									<span style="color: red">${errorMsgs.error_name}</span></td>
							</tr>
							<tr>
								<td>預約者email:</td>
								<td><input type="TEXT" name="bookerEmail" size="45"
									value="<%=(store_orderVO == null) ? "" : store_orderVO.getStore_order_email()%>" />
									<span style="color: red">${errorMsgs.error_email}</span></td>
							</tr>
							<tr>
								<td>預約者電話:</td>
								<td><input type="TEXT" name="bookerPhoneNumber" size="45"
									value="<%=(store_orderVO == null) ? "" : store_orderVO.getStore_order_phone_num()%>" />
									<span style="color: red">${errorMsgs.error_phone}</span></td>
							</tr>
							<tr>
								<td>預約日期:</td>
								<td><input type="TEXT" name="bookingDate" size="45"
									value="<%=(store_orderVO == null) ? "" : store_orderVO.getStore_order_date_time()%>" />
									<span style="color: red">${errorMsgs.error_date}</span></td>
							</tr>
							<tr>
								<td>checkout日:</td>
								<td><input type="TEXT" name="checkOutDate" size="45"
									value="<%=(store_orderVO == null) ? "" : store_orderVO.getStore_order_end_date()%>" />
									<span style="color: red">${errorMsgs.error_checkout}</span></td>
							</tr>
							<tr>
								<td>預約人數:</td>
								<td><input type="TEXT" name="orderPersons" size="45"
									value="<%=(store_orderVO == null) ? "" : store_orderVO.getStore_order_persons()%>" />
									<span style="color: red">${errorMsgs.error_persons}</span></td>
							</tr>
							<tr>
								<td>付款方式:</td>
								<td><select size="1" name="orderPayment">
										<option value="paypal" />paypal
										<option value="信用卡" />信用卡
								</select></td>
							</tr>
							<tr>
								<td>備註:</td>
								<td><textarea name="orderNote"
										style="resize: none; width: 321.44px; height: 100px;"
										value="<%=(store_orderVO == null) ? "" : store_orderVO.getStore_order_note()%>" /></textarea>
									<span style="color: red">${errorMsgs.error_note}</span></td>
							</tr>
							<tr>
								<td>狀態:</td>
								<td><input type="TEXT" name="orderState" size="45"
									value="<%=(store_orderVO == null) ? "0" : store_orderVO.getStore_order_state()%>" />
									<span style="color: red">${errorMsgs.error_state}</span></td>
							</tr>
						</table>
						<table id="stable" style="display: inline-table;">
						</table>
						<input type="hidden" name="action" value="insert"> <input
							type="submit" value="送出新增">
					</form>
				</div>
				</div>
			</div>
		</div>

	</div>

	</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/jquery.js"></script>
	<script>
		var e = document.getElementById("storeVo.store_id");
		var storeId = e.options[e.selectedIndex].value;
		console.log(storeId);
		
		function loadOrderId(event){
			console.log(event.target.value);
			var store_id = event.target.value;
            let td_html = "";
			$.ajax({
		        url: "<%=request.getContextPath()%>/ServiceController_Ajax",
						type : "GET", // GET | POST | PUT | DELETE | PATCH
						data : {
							"action" : "getSerivceList",
							"storeId" : store_id
						}, // 傳送資料到指定的 url
						dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
						beforeSend : function() { // 在 request 發送之前執行
						},
						statusCode : { // 狀態碼
							200 : function(res) {
								console.log("200")
							},
							404 : function(res) {
								console.log("400")
							},
							500 : function(res) {
								console.log("500")
							}
						},
						error : function(xhr) { // request 發生錯誤的話執行
							console.log(xhr.responseText);
						},

						success : function(data) { // request 成功取得回應後執行
							$("#stable").empty();
							if (data.length != 0) {
								let stable = "<tr class='service_list'>"
										+ "<td>服務編號</td>"
										+ "<td style='text-align:center'>服務項目</td>"
										+ "<td>價錢</td>"
										+ "<td style='text-align:center'>數量(1-999)</td>"
										+ "</tr>";
								$("#stable").prepend(stable);
								$
										.each(
												data,
												function(index, item) {
													td_html += "<tr><th value="+item.service_id+">"
															+ item.service_id
															+ "</th>"
															+ "<th value="+item.service_detail+">"
															+ item.service_detail
															+ "</th>"
															+ "<th value="+item.service_price+">"
															+ item.service_price
															+ "</th>"
															+ "<th>"
															+ "<input type='text' size='20' name='pets'></input>"
															+ "<input type='hidden' name='service_id' value="+item.service_id+"></input>"
															+ "</th></tr>";
												});
								$("tr.service_list").after(td_html);
							}
						}
					})
		}
	</script>
</body>
</html>