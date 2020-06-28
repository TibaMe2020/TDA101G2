<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:useBean id="Product_Service" scope="page" class="com.product.model.Product_Service"></jsp:useBean>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查詢到商品</title>

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
    <div class="container">
        <div class="row">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="請輸入商品名字">
                <div class="input-group-append">
                    <button class="btn btn-secondary" type="button">
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
    <!-- Keyword -->
    <div class="container">
        <div class="row" id="keyword">
<c:forEach items="${Product_Service.getbykeyword()}" var="product_VO" begin="0" end="5">
            <div class="col-sm-12 col-md-2">
                <a class="nav-link" href="<%=request.getContextPath()%>/front-end/product/Product.jsp?product_id=${product_VO.product_id}">${product_VO.name}</a>
            </div>         
</c:forEach>
            

        </div>            
  

    <!--查出來商品清單 -->
    <div class="container" id="SpecialFood">
        <div class="row">

<c:forEach items="${listProduct_ByName}" var="product_VO" >
	<c:choose>
          <c:when test="${not empty product_VO.product_id}">
                    	<div class="col-sm-12 col-md-3" id="new_product">
                            <div>
                                <a href="<%=request.getContextPath()%>/front-end/product/Product.jsp?product_id=${product_VO.product_id}">
                                   <img src="<%=request.getContextPath()%>/Product_Image?image=1&product_id=${product_VO.product_id}"  id="new_product" style="width:250px; height:200px;"/>
                                    <br />
                                    <p>${product_VO.name}</p>
                                </a>
                            </div>
                        </div>
            </c:when>
	</c:choose> 
</c:forEach>
             
        </div>
    </div>
    <br />
    <!-- 篩選 -->

    <div class="container">
       
        </div>

    </div>
    <br />
    

    <%@ include file="/front-end/member/footer.jsp"%>
   <script src="<%=request.getContextPath()%>/front-end/product/js/ProductCalss.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   
   <script >
   	$(window).on("load", function(){
   		noResult();
   	});
   	
   	function noResult(){
   		let noResultInfo = `<img src="<%=request.getContextPath()%>/resources/images/ProductImage/Nofindproduct/NoResultProduct.jpg" style="width: 1076px;">
            <p style="font-size: 24px; margin-left: 360px;">非常抱歉，您收尋的商品目前無進貨。 <br>
            請返回 <a href="<%=request.getContextPath()%>/front-end/product/ProductHomepage.jsp" style="color:red; font-size:40px;">商城首頁</a> 再次收尋 !
            </p>`;
            
		if($("#new_product").length<1){
			$("#SpecialFood").find(".row").html(noResultInfo);
		}
   	}
   	
   
   </script>
</body>
</html>