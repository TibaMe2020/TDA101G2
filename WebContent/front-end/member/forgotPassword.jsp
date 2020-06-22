<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Forgot Password</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<!--   air_datepicker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/forgotPassword.css">
</head>

<body>

<%@ include file="header.jsp"%>

  <div class="container-fluid">
    <div class="row content-height">
      <div class="col-lg-2"></div>
      <!-- Main content -->
      <div class="col-lg-8 col-md-12">
        <h2>忘記密碼</h2>
        <form action="<%=request.getContextPath()%>/member/controller" method="post" id="forgot-password-form" class="main-form">
          <div class="row justify-content-around">
            <div class="col-lg-6 text-left">
              <div class="text-center">
                <p>
                  <h6 style="color: gray; ">
                    送出後會寄送信件至您填寫的電子郵件，會請您再重新設置一份新的密碼
                  </h6>
                </p>
              </div>
              <div class="form-group">
                <label for="forgot-email">電子郵件</label>
                
                <input type="text" name="email" id="forgot-password" class="form-control 
                ${not empty errorMsgs.forgotEmail ? 'is-invalid' : ''} " required>
                <div class="${not empty errorMsgs.forgotEmail ? 'invalid-feedback' : ''}">
                    ${empty errorMsgs.forgotEmail ? '' : errorMsgs.forgotEmail}</div>
              </div>
              <input type="hidden" name="action" value="forgot_password">
              <button type="submit" class="btn btn-primary">送出</button>
            </div>
          </div>
        </form>
      </div>
      <div class="col-lg-2"></div>
    </div>
  </div>
	<%@ include file="footer.jsp"%>

</body>

</html>