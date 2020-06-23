<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/adminLogin.css">

</head>


  <body class="d-flex align-items-center"
  style="background-image: url('<%=request.getContextPath()%>/resources/images/bg.jpg');">
  <i class="fa fa-sliders" aria-hidden="true"></i>
  <div class="container-fluid d-flex justify-content-center">
    <div class="card bg-white rounded border-0">
      <div class="card-body bg-white rounded ">
        <form action="<%=request.getContextPath()%>/admin/controller" method="post" id="login-form" class="main-form">
          <div class="row justify-content-center">
            <div class="col-11 text-left">
              <h3 class="card-title text-center">後台登入</h3>
              <div class="form-group">
                <label for="admin_account">帳號</label>
                <input type="text" name="admin_account" id="admin_account" 
                class="form-control ${not empty errorMsgs.admin_account ? 'is-invalid' : ''}" required>
                <div class="${not empty errorMsgs.admin_account ? 'invalid-feedback' : ''}">
                  ${empty errorMsgs.admin_account ? '' : errorMsgs.admin_account}</div>
              </div>
              <div class="form-group">
                <label for="admin_password">密碼</label>
                <input type="password" name="admin_password" id="admin_password" 
                class="form-control ${not empty errorMsgs.admin_password ? 'is-invalid' : ''}" required>
                <div class="${not empty errorMsgs.admin_password ? 'invalid-feedback' : ''}">
                    ${empty errorMsgs.admin_password ? '' : errorMsgs.admin_password}</div>
              </div>
              <input type="hidden" name="action" value="login">
              <button type="submit" class="btn btn-dark">登入</button>
              <span style="color: #dc3545">${errorMsgs.account}</span>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>


  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
  </script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
  </script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
    integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous">
  </script>
  <script>
//D避免重新整理的時候重新送出form表單
// 	if ( window.history.replaceState ) {
//   	window.history.replaceState( null, null, window.location.href );
// 	}
  </script>

</body>

</html>