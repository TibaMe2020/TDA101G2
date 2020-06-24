<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<style>


</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<body>
    <div class="jumbotron">
    <h1 class="display-4">付款成功</h1>
    <p class="lead">感謝您,為了守護流浪動物們所盡的一份心力!!</p>
    <hr class="my-4">
    <p>接著您可以前往捐款頁面去查詢您剛剛所捐款的紀錄。</p>
    <a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/front-end/donation/myMain/donation.jsp" role="button">返回公益首頁</a>
<%--     <a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/front-end/donation/DonationForm/selectdata.jsp" role="button">前往捐款查詢</a> --%>
  </div>
</body>
</html>