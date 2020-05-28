<%@page import="com.store.model.StoreVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
  StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<%-- <%= storeVO==null %> --${storeVO.store_id} --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家資料新增 - addStore.jsp</title>
</head>
<body>
	<h3>資料新增:</h3>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller" name="form1">
		<table>
			<tr>
				<td>店家名稱:<font color=red><b>*</b></td>
				<td><input type="TEXT" name="storeName" size="45" 
					 	value="<%= (storeVO==null)? "緯育餐廳" : storeVO.getStore_name()%>" />
	<!-- 				<input type="hidden" name="action" value="insert"> -->
					<span style="color: red ">${errorMsgs.error_name}</span>
				</td>
				
			</tr>
			<tr>
				<td>分類:<font color=red><b>*</b></font></td>
				<td><select size="1" name="storeClass">
						<option value="餐廳" >餐廳</option>
						<option value="旅館" >旅館</option>
						<option value="美容" >美容</option>
						<option value="學校" >學校</option>
						<option value="醫院" >醫院</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<td>地址:<font color=red><b>*</b></td>
				<td><input type="TEXT" name="storeAdress" size="45"
					 value="<%= (storeVO==null)? "台北商業大學" : storeVO.getStore_adress()%>" />
					 <span style="color: red ">${errorMsgs.error_adress}</span>
				</td>
				
			</tr>
			<tr>
				<td>電話:<font color=red><b>*</b></td>
				<td><input type="TEXT" name="storePhoneNumber" type="text"
				value="<%= (storeVO==null)? "0200000000" : storeVO.getStore_phone_number()%>">
					<span style="color: red ">${errorMsgs.error_phone}</span>
				</td>
			</tr>
			<tr>
				<td>簡介:</td>
				<td><input type="TEXT" name="storeIntroduction" size="45"
					 value="<%= (storeVO==null)? "" : storeVO.getStore_introduction()%>" />
<%-- 				 	<span style="color: red ">${errorMsgs.error}</span> --%>
				</td>
			</tr>
			<tr>
				<td>點閱數:</td>
				<td><input type="TEXT" name="storeClicks" size="45"
					value="<%= (storeVO==null)? "100" : storeVO.getStore_clicks()%>" /> 
				 	<span style="color: red ">${errorMsgs.error_clicks}</span>
				</td>
			</tr>
			<tr>
				<td>公休日1:</td>
				<td><select size="1" name="storeFirstbreak">
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
				<td><select size="1" name="storeSecondbreak">
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
				<td><input type="TEXT" name="storeOpenhours1" size="45"
					 value="<%= (storeVO==null)? "10:00-12:00" : storeVO.getStore_openhours1()%>" />
<%-- 					<span style="color: red ">${errorMsgs.error}</span> --%>
				</td>
			</tr>
			<tr>
				<td>用餐時間:</td>
				<td><input type="TEXT" name="storeTimelimit" size="45"
					 value="<%= (storeVO==null)? "120" : storeVO.getStore_timelimit()%>" />
	<%-- 				<span style="color: red ">${errorMsgs.error}</span> --%>
				</td>
			</tr>
			<tr>
				<td>最大人數:</td>
				<td><input type="TEXT" name="storeMaxcapacity" size="45"
					 value="<%= (storeVO==null)? "50" : storeVO.getStore_maxcapacity()%>" />
	<%-- 				<span style="color: red ">${errorMsgs.error}</span> --%>
				</td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><input type="TEXT" name="storeOn" size="45"
					 value="<%= (storeVO==null)? "0" : storeVO.getStore_on()%>" />
	<%-- 				<span style="color: red ">${errorMsgs.error}</span> --%>
				</td>
			</tr>
		
		</table>
		<span style="color: red ">${errorMsgs.error}</span>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>
	
	<a href='<%=request.getContextPath()%>/back-end/store/select_page.jsp'>回select_page</a>
</body>
</html>