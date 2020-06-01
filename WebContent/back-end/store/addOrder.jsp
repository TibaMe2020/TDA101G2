<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增訂單 - addOrder.jsp</title>
</head>
<body>
	<a href='<%=request.getContextPath()%>/back-end/store/select_page.jsp'>回首頁</a>
	<h3>新增訂單 :</h3><span style="color: red ">${errorMsgs.error}</span>
	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
	<form method="post" action="<%=request.getContextPath()%>/store/OrderController">
		<table>
			<tr>
				<td>
					<select size="1" name="storeId">
						<c:forEach var="storeVo" items="${storeSvc.all}">
							<option value="${storeVo.store_id}"/>${storeVo.store_id}-${storeVo.store_name}
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>會員ID:</td>
				<td><input type="TEXT" name="memberId" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者姓名:</td>
				<td><input type="TEXT" name="bookerName" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者email:</td>
				<td><input type="TEXT" name="bookerEmail" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者電話:</td>
				<td><input type="TEXT" name="bookerPhoneNumber" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者電話:</td>
				<td><input type="TEXT" name="bookingDate" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者電話:</td>
				<td><input type="TEXT" name="checkOutDate" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者電話:</td>
				<td><input type="TEXT" name="orderPersons" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者電話:</td>
				<td><input type="TEXT" name="orderNote" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者電話:</td>
				<td><input type="TEXT" name="orderState" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>