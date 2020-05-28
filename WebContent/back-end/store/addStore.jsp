<%@page import="com.store.model.StoreVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
  StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<%= storeVO==null %> --${storeVO.store_id}
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
			<td><input type="TEXT" name="ename" size="45" 
				 value="<%= (storeVO==null)? "吳永志" : storeVO.getStore_name()%>" /></td>
		</tr>
		<tr>
			<td>分類:<font color=red><b>*</b></font></td>
			<td><select size="1" name="deptno">
					<option value="餐廳" >餐廳</option>
					<option value="旅館" >旅館</option>
					<option value="美容" >美容</option>
					<option value="學校" >學校</option>
					<option value="醫院" >醫院</option>
			</select></td>
		</tr>
		<tr>
			<td>地址:<font color=red><b>*</b></td>
			<td><input type="TEXT" name="job" size="45"
				 value="<%= (storeVO==null)? "MANAGER" : storeVO.getStore_adress()%>" /></td>
		</tr>
		<tr>
			<td>電話:<font color=red><b>*</b></td>
			<td><input name="hiredate" id="f_date1" type="text"></td>
		</tr>
		<tr>
			<td>簡介:</td>
			<td><input type="TEXT" name="sal" size="45"
				 value="<%= (storeVO==null)? "10000" : storeVO.getStore_introduction()%>" /></td>
		</tr>
		<tr>
			<td>公休日1:</td>
			<td><input type="TEXT" name="comm" size="45"
				 value="<%= (storeVO==null)? "100" : storeVO.getStore_firstbreak()%>" /></td>
		</tr>
		<tr>
			<td>公休日2:</td>
			<td><input type="TEXT" name="comm" size="45"
				 value="<%= (storeVO==null)? "100" : storeVO.getStore_secondbreak()%>" /></td>
		</tr>
		<tr>
			<td>營業時段1:</td>
			<td><input type="TEXT" name="comm" size="45"
				 value="<%= (storeVO==null)? "100" : storeVO.getStore_openhours1()%>" /></td>
		</tr>
		<tr>
			<td>用餐時間:</td>
			<td><input type="TEXT" name="comm" size="45"
				 value="<%= (storeVO==null)? "100" : storeVO.getStore_timelimit()%>" /></td>
		</tr>
	
	
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增"></FORM>
</body>
</html>