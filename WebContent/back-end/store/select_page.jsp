<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store:Home</title>
</head>
<body>
	<h3>店家查詢</h3>
	<ul>
		<li><a href='listAllStore.jsp'>List</a> all Stores. </li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller">
				<b>輸入店家編號 (如S07001):</b> 
				<input type="text" name="storeId"> 
				<input type="hidden" name="action" value="getOneForDisplay">
				<input type="submit" value="送出">
				<span style="color: red ">${errorMsgs.error}</span>
			</FORM>
		</li>
		<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller">
				<b>選擇店家編號：</b> 
				<select size="1" name="storeId">
					<c:forEach var="storeVo" items="${storeSvc.all}">
						<option value="${storeVo.store_id}"/>${storeVo.store_id}
					</c:forEach>
				</select>
				<input type="hidden" name="action" value="getOneForDisplay">
				<input type="submit" value="送出">		
			</FORM>
		</li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller">
				<b>選擇店家名稱：</b> 
				<select size="1" name="storeId">
					<c:forEach var="storeVo" items="${storeSvc.all}">
						<option value="${storeVo.store_id}"/>${storeVo.store_name}
					</c:forEach>
				</select>
				<input type="hidden" name="action" value="getOneForDisplay">
				<input type="submit" value="送出">		
			</FORM>
		</li>
	</ul>
	
	<h3>店家管理</h3>
	<ul>
	  <li><a href='addStore.jsp'>新增</a>一間店家</li>
	</ul>
</body>
</html>