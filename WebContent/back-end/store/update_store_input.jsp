<%@page import="com.store.model.StoreVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%= storeVO==null %> --${storeVO.store_id}
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家資料修改 - update_store_input</title>
</head>
<body>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller" name="form1">
		<table>
			<tr>
				<td>店家編號:<font color=red><b>*</b></font></td>
				<td>${storeVO.store_id}</td>
				
			</tr>
			<tr>
				<td>名稱:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="storeName" size="45" value="<%=storeVO.getStore_name()%>" /></td>
				
			</tr>
			<tr>
				<td>分類:</td>
				<td><select name="storeClass" size="" value="${storeVO.getClass()}">
						<option value="餐廳" >餐廳</option>
						<option value="旅館" >旅館</option>
						<option value="美容" >美容</option>
						<option value="學校" >學校</option>
						<option value="醫院" >醫院</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="storeAdress" size="45"	value="<%=storeVO.getStore_adress()%>" /></td>
			</tr>
			<tr>
				<td>電話::</td>
				<td><input name="storePhoneNumber" type="text" value="<%=storeVO.getStore_phone_number()%>"/></td>
			</tr>
			<tr>
				<td>簡介:</td>
				<td><input type="TEXT" name="storeIntroduction" size="45"	value="<%=storeVO.getStore_introduction()%>" /></td>
			</tr>
			<tr>
				<td>點閱數:</td>
				<td><input type="TEXT" name="storeClicks" size="45" value="<%=storeVO.getStore_clicks()%>" /></td>
			</tr>
			<tr>
				<td>公休日1:</td>
				<td><select size="1" name="storeFirstbreak" value="<%=storeVO.getStore_firstbreak()%>">
						<option value=0></option>
						<option value=1 >周一</option>
						<option value=2 >週二</option>
						<option value=3 >週三</option>
						<option value=4 >週四</option>
						<option value=5 >週五</option>
						<option value=6 >週六</option>
						<option value=7 >週日</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>公休日2:</td>
				<td><select size="1" name="storeSecondbreak" value="<%=storeVO.getStore_secondbreak()%>">
						<option value=0></option>
						<option value=1 >周一</option>
						<option value=2 >週二</option>
						<option value=3 >週三</option>
						<option value=4 >週四</option>
						<option value=5 >週五</option>
						<option value=6 >週六</option>
						<option value=7 >週日</option>
					</select>
				</td>
			</tr>

			<tr>
				<td>營業時段1:</td>
				<td><input type="TEXT" name="storeOpenhours1" size="45" value="<%=storeVO.getStore_openhours1()%>" /></td>
			</tr>
			<tr>
				<td>用餐時間:</td>
				<td><input type="TEXT" name="storeTimelimit" size="45" value="<%=storeVO.getStore_timelimit()%>" /></td>
			</tr>
			<tr>
				<td>最大人數:</td>
				<td><input type="TEXT" name="storeMaxcapacity" size="45" value="<%=storeVO.getStore_maxcapacity()%>" />
				</td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><input type="TEXT" name="storeOn" size="45" value="<%=storeVO.getStore_on()%>" /></td>
			</tr>
		
		</table>
		<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="storeId" value="<%=storeVO.getStore_id()%>">
		<input type="submit" value="送出修改">
	</FORM>
	<a href='<%=request.getContextPath()%>/back-end/store/listAllStore.jsp'>回listAllStore</a>
</body>
</html>