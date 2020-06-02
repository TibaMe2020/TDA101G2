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
</head>
<body>
	<a href='<%=request.getContextPath()%>/back-end/store/select_page.jsp'>回首頁</a>
	<h3>新增訂單 :</h3><span style="color: red ">${errorMsgs.error}</span>
	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
	<form method="post" action="<%=request.getContextPath()%>/store/OrderController">
		<table>
			<tr>
				<td>店家:</td>
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
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getMember_id()%>" />
					<span style="color: red ">${errorMsgs.error_member}</span>
				</td>
			</tr>
			<tr>
				<td>預約者姓名:</td>
				<td><input type="TEXT" name="bookerName" size="45" 
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getStore_order_name()%>" />
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
			</tr>
			<tr>
				<td>預約者email:</td>
				<td><input type="TEXT" name="bookerEmail" size="45" 
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getStore_order_email()%>" />
					<span style="color: red ">${errorMsgs.error_email}</span>
				</td>
			</tr>
			<tr>
				<td>預約者電話:</td>
				<td><input type="TEXT" name="bookerPhoneNumber" size="45" 
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getStore_order_phone_num()%>" />
					<span style="color: red ">${errorMsgs.error_phone}</span>
				</td>
			</tr>
			<tr>
				<td>預約日期:</td>
				<td><input type="TEXT" name="bookingDate" size="45" 
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getStore_order_date_time()%>" />
					<span style="color: red ">${errorMsgs.error_date}</span>
				</td>
			</tr>
			<tr>
				<td>checkout日:</td>
				<td><input type="TEXT" name="checkOutDate" size="45" 
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getStore_order_end_date()%>" />
					<span style="color: red ">${errorMsgs.error_checkout}</span>
				</td>
			</tr>
			<tr>
				<td>預約人數:</td>
				<td><input type="TEXT" name="orderPersons" size="45" 
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getStore_order_persons()%>" />
					<span style="color: red ">${errorMsgs.error_persons}</span>
				</td>
			</tr>
			<tr>
				<td>付款方式:</td>
				<td>
					<select size="1" name="orderPayment">
							<option value="paypal"/>paypal
							<option value="信用卡"/>信用卡
					</select>
				</td>
			</tr>
			<tr>
				<td>備註:</td>
				<td><textarea name="orderNote" style="resize:none;width:321.44px;height:100px;" 
					 	value="<%= (store_orderVO==null)? "" : store_orderVO.getStore_order_note()%>" /></textarea>
					<span style="color: red ">${errorMsgs.error_note}</span>
				</td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><input type="TEXT" name="orderState" size="45" 
					 	value="<%= (store_orderVO==null)? "0" : store_orderVO.getStore_order_state()%>" />
					<span style="color: red ">${errorMsgs.error_state}</span>
				</td>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</form>
</body>
</html>