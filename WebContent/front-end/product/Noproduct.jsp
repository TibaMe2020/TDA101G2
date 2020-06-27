<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>無商品</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css">
    <link href="<%=request.getContextPath()%>/front-end/product/css/ProductClass.css" rel="stylesheet">
</head>

<body>
	<%@ include file="/front-end/member/header.jsp"%>
  <!-- SEARCHBOX -->
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Product">   
    <div class="container">
        <div class="row">
            <div class="input-group">           
                <input type="text" class="form-control" placeholder="請輸入商品名字" name="name">
                <div class="input-group-append">                          
                    <button class="btn btn-secondary" type="submit">                     
                        <input type="hidden" name="action" value="listProduct_ByName">
                        <svg class="bi bi-search" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                            <path fill-rule="evenodd"
                                d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                        </svg>
                    </button>                
                </div>                
            </div>
        </div>
    </div>
</FORM>
    <!-- Keyword -->
    <div class="container">
        <div class="row" id="keyword">

            <div class="col-sm-12 col-md-2">

                <a class="nav-link " href="#">經典狗狗美食</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">好用寵物用品</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">經典狗屋</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link " href="#">寵物飼料機</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link " href="#">攝像鏡頭</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link " href="#">貓倍麗</a>

            </div>

        </div>

        
     <div class="container">
        <div class="row">
            <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Nofindproduct/NoResultProduct.jpg" style="
    width: 1076px;">
            <p style="font-size: 24px; margin-left: 360px;">非常抱歉，您收尋的商品目前無進貨。 <br>
            請返回 <a href="<%=request.getContextPath()%>/front-end/product/ProductHomepage.jsp" style="color:red; font-size:40px;">商城首頁</a> 再次收尋 !
            </p>
        </div>

    </div>
       <div class="container">
       
        </div>
   <%@ include file="/front-end/member/footer.jsp"%>
<!--     <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script> -->
<!-- 	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script> -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> -->
<%--     <script src="<%=request.getContextPath()%>/front-end/product/js/ProductCalss.js"></script> --%>
</body>
</html>