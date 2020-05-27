<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>店家查詢</p>
	<ul>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/Controller">
				<b>輸入店家編號 (如S07001):</b> 
				<input type="text" name="storeId"> 
				<input type="hidden" name="action" value="getOneForDisplay">
				<input type="submit" value="送出">
				<span style="color: red ">${errorMsgs.error}</span>
			</FORM>
		</li>

	</ul>
</body>
</html>