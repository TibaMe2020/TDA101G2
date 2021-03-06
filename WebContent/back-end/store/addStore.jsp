<%@page import="com.store.model.StoreVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<%-- <%= storeVO==null %> --${storeVO.store_id} --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家資料新增 - addStore.jsp</title>
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
					<h3 class="title-tab">新增店家</h3>
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
				<div class="row justify-content-star">
				<div class="col-1"></div>
				<div class="col-10">
					<span style="color: red">${errorMsgs.error}</span>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/store/Controller"
						name="form1" enctype="multipart/form-data">
						<table>
							<tr>
								<td>店家名稱:<font color=red><b>*</b></td>
								<td><input type="TEXT" name="storeName" size="45"
									value="<%=(storeVO == null) ? "緯育餐廳" : storeVO.getStore_name()%>" />
									<span style="color: red">${errorMsgs.error_name}</span></td>

							</tr>
							<tr>
								<td>分類:<font color=red><b>*</b></font></td>
								<td><select size="1" name="storeClass"
									onchange="changeService(event)">
										<option value="餐廳"
											<c:if test="${storeVO.store_class=='餐廳' }">selected</c:if>>餐廳</option>
										<option value="旅館"
											<c:if test="${storeVO.store_class=='旅館' }">selected</c:if>>旅館</option>
										<option value="美容"
											<c:if test="${storeVO.store_class=='美容' }">selected</c:if>>美容</option>
										<option value="學校"
											<c:if test="${storeVO.store_class=='學校' }">selected</c:if>>學校</option>
										<option value="醫院"
											<c:if test="${storeVO.store_class=='醫院' }">selected</c:if>>醫院</option>
								</select></td>

							</tr>
							<tr>
								<td>地址:<font color=red><b>*</b></td>
								<td><input type="TEXT" name="storeAdress" size="45"
									value="<%=(storeVO == null) ? "台北商業大學" : storeVO.getStore_adress()%>" />
									<span style="color: red">${errorMsgs.error_adress}</span></td>

							</tr>
							<tr>
								<td>電話:<font color=red><b>*</b></td>
								<td><input type="TEXT" name="storePhoneNumber" type="text"
									value="<%=(storeVO == null) ? "0912345678" : storeVO.getStore_phone_number()%>">
									<span style="color: red">${errorMsgs.error_phone}</span></td>
							</tr>
							<tr>
								<td>簡介:</td>
								<td><textarea name="storeIntroduction"
										style="resize: none; width: 321.44px; height: 100px;" /><%=(storeVO == null) ? "" : storeVO.getStore_introduction()%></textarea></td>
							</tr>
							<tr>
								<td>點閱數:</td>
								<td><input type="TEXT" name="storeClicks"
									value="<%=(storeVO == null) ? "100" : storeVO.getStore_clicks()%>" />
									<span style="color: red">${errorMsgs.error_clicks}</span></td>
							</tr>
							<tr>
								<td>公休日1:</td>
								<td><select size="1" name="storeFirstbreak">
										<option value=0
											<c:if test="${storeVO.store_firstbreak==''}">selected</c:if>></option>
										<option value=1
											<c:if test="${storeVO.store_firstbreak==1 }">selected</c:if>>周一</option>
										<option value=2
											<c:if test="${storeVO.store_firstbreak==2 }">selected</c:if>>週二</option>
										<option value=3
											<c:if test="${storeVO.store_firstbreak==3 }">selected</c:if>>週三</option>
										<option value=4
											<c:if test="${storeVO.store_firstbreak==4 }">selected</c:if>>週四</option>
										<option value=5
											<c:if test="${storeVO.store_firstbreak==5 }">selected</c:if>>週五</option>
										<option value=6
											<c:if test="${storeVO.store_firstbreak==6 }">selected</c:if>>週六</option>
										<option value=7
											<c:if test="${storeVO.store_firstbreak==7 }">selected</c:if>>週日</option>
								</select></td>
							</tr>
							<tr>
								<td>公休日2:</td>
								<td><select size="1" name="storeSecondbreak">
										<option value=0
											<c:if test="${storeVO.store_secondbreak=='' }">selected</c:if>></option>
										<option value=1
											<c:if test="${storeVO.store_secondbreak==1 }">selected</c:if>>周一</option>
										<option value=2
											<c:if test="${storeVO.store_secondbreak==2 }">selected</c:if>>週二</option>
										<option value=3
											<c:if test="${storeVO.store_secondbreak==3 }">selected</c:if>>週三</option>
										<option value=4
											<c:if test="${storeVO.store_secondbreak==4 }">selected</c:if>>週四</option>
										<option value=5
											<c:if test="${storeVO.store_secondbreak==5 }">selected</c:if>>週五</option>
										<option value=6
											<c:if test="${storeVO.store_secondbreak==6 }">selected</c:if>>週六</option>
										<option value=7
											<c:if test="${storeVO.store_secondbreak==7 }">selected</c:if>>週日</option>
								</select></td>
							</tr>

							<tr>
								<td>營業時段1:</td>
								<td><input type="TEXT" name="storeOpenhours1" size="45"
									value="<%=(storeVO == null) ? "10:00-12:00" : storeVO.getStore_openhours1()%>" />
								</td>
							</tr>
							<tr>
								<td>用餐時間:</td>
								<td><input type="TEXT" name="storeTimelimit" size="45"
									value="<%=(storeVO == null) ? "120" : storeVO.getStore_timelimit()%>" />
								</td>
							</tr>
							<tr>
								<td>最大人數:</td>
								<td><input type="TEXT" name="storeMaxcapacity" size="45"
									value="<%=(storeVO == null) ? "50" : storeVO.getStore_maxcapacity()%>" />
								</td>
							</tr>
							<tr>
								<td>狀態:</td>
								<td><input type="TEXT" name="storeOn" size="45"
									value="<%=(storeVO == null) ? "0" : storeVO.getStore_on()%>" />
									<%-- 				<span style="color: red ">${errorMsgs.error}</span> --%>
								</td>
							</tr>

						</table>
						<input type="file" name="storeImage1"
							onchange="loadImageFile(event)"> <input type="file"
							name="storeImage2" onchange="loadImageFile(event)"> <input
							type="file" name="storeImage3" onchange="loadImageFile(event)">
						<br> <input type="hidden" name="action" value="insert">
						<input type="submit" value="送出新增">
					</FORM>
					<div style="width: 200px; height: 200px; display: inline-block;">
						<img id="image1" style="width: 100%;" />
					</div>
					<div style="width: 200px; height: 200px; display: inline-block;">
						<img id="image2" style="width: 100%;" />
					</div>
					<div style="width: 200px; height: 200px; display: inline-block;">
						<img id="image3" style="width: 100%;" />
					</div>
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
	<script>
		function loadImageFile(event) {
			console.log(event.target.name);
			switch (event.target.name) {
			case 'storeImage1':
				var image1 = document.getElementById('image1');
				image1.src = URL.createObjectURL(event.target.files[0]);
				break;
			case 'storeImage2':
				var image2 = document.getElementById('image2');
				image2.src = URL.createObjectURL(event.target.files[0]);
				break;
			case 'storeImage3':
				var image3 = document.getElementById('image3');
				image3.src = URL.createObjectURL(event.target.files[0]);
				break;
			}
		};
		function changeService(event) {
			console.log(event.target.value);
		}
	</script>

</body>
</html>