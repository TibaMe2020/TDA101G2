<%@page import="com.product_version.model.Version_VO"%>
<%@page import="com.product_version.model.Version_Service"%>
<%@page import="com.product.model.Product_Service"%>
<%@page import="com.product.model.Product_VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.stream.Collectors"%>
<!DOCTYPE html>
<html>

<jsp:useBean id="Product_Service" scope="page" class="com.product.model.Product_Service"></jsp:useBean>



<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>寵物服飾</title>

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

  <c:forEach items="${Product_Service.getbykeyword()}" var="product_VO" begin="0" end="5">
            <div class="col-sm-12 col-md-2">
                <a class="nav-link" href="<%=request.getContextPath()%>/front-end/product/Product.jsp?product_id=${product_VO.product_id}">${product_VO.name}</a>
            </div>         
</c:forEach>   

        </div>

        <!-- 輪播 -->
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
           <div class="carousel-inner" id="carousel-photo">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="<%=request.getContextPath()%>/resources/images/ProductImage/carser/carser.png" id="carousel-photo">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="<%=request.getContextPath()%>/resources/images/ProductImage/carser/carser1.png" id="carousel-photo">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="<%=request.getContextPath()%>/resources/images/ProductImage/carser/carser4.jpg" id="carousel-photo">
                </div>

            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <!-- 各大精選廠牌 -->

    <div class="container" id="factory">
        <h3 style="text-align:left">各大精選廠牌</h3>
        <div class="row">
            <div class="col-sm-12 col-md-3">
                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand.jpg" id="brand_photo">
            </div>
            <div class="col-sm-12 col-md-3">
                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand1.jpg" id="brand_photo">
            </div>
            <div class="col-sm-12 col-md-3">
                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand2.jpg" id="brand_photo">
            </div>
            <div class="col-sm-12 col-md-3">
                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand3.png" id="brand_photo">
            </div>
        </div>
        <div class="row" style="margin-top:10px;">
            <div class="col-sm-12 col-md-3">
                 <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand4.png" id="brand_photo">
            </div>
            <div class="col-sm-12 col-md-3">
                 <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand5.png" id="brand_photo">
            </div>
            <div class="col-sm-12 col-md-3">
                 <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand6.png" id="brand_photo">
            </div>
            <div class="col-sm-12 col-md-3">
                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Brand/Brand7.jpg" id="brand_photo">
            </div>
        </div>
    </div>
    <!-- 精選食物 -->
    <div class="container" id="SpecialFood">
        <h3 style="text-align:left">精選服飾</h3>
        <div class="row">
           
<c:forEach items="${Product_Service.highPrice()}" var="product_VO" begin="0" end="3">
                    	 <div class="col-sm-12 col-md-3">
                            <div class="card">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/Product_Image?image=1&product_id=${product_VO.product_id}"  id="hotimage" style="height: 200px;">
                                <div class="card-body">
                                    <h4 class="card-title">${product_VO.name}</h4>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=1 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=2 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=3 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=4 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=5 ? 'color: #EF8216' : ''}"></i></span>
                                    <p class="card-text">$${product_VO.price}</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href="<%=request.getContextPath()%>/front-end/product/Product.jsp?product_id=${product_VO.product_id}" value="購買">
                                        	購買
                                    </a>
                                </div>
                            </div>
                        </div>
</c:forEach>           
           
           
        </div>
    </div>
    <br />
    <!-- 篩選 -->

    <div class="container">
        <div style="border:1px solid black">
            <div>
                <h3 class="select" style="text-align: left;">篩選
                    <input class="btn btn-primary Select-Filter" type="submit" value="最新日期" id="NewDate">
                    <input class="btn btn-primary Select-Filter" type="submit" value="價錢最高" id="HighPrice">
                    <input class="btn btn-primary Select-Filter" type="submit" value="價錢最低" id="LowPrice">
                    <input class="btn btn-primary Select-Filter" type="submit" value="最高評分" id="HighScore">
                </h3>

            </div>
            <div id="newProduct">
                <div class="row">
   <c:forEach items="${Product_Service.highPrice()}" var="product_VO" begin="0" end="7">
                    <div class="col-sm-12 col-md-3">
                            <div class="card">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/Product_Image?image=1&product_id=${product_VO.product_id}"  id="hotimage" style="height: 200px;">
                                <div class="card-body">
                                    <h4 class="card-title">${product_VO.name}</h4>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=1 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=2 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=3 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=4 ? 'color: #EF8216' : ''}"></i></span>
                                    <span class="star"><i class="fas fa-star" style=" ${product_VO.score >=5 ? 'color: #EF8216' : ''}"></i></span>
                                    <p class="card-text">$${product_VO.price}</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href="<%=request.getContextPath()%>/front-end/product/Product.jsp?product_id=${product_VO.product_id}" value="購買">
                                        	購買
                                    </a>
                                </div>
                            </div>
                        </div>
</c:forEach>                                  
                </div>
            </div>

            <br>
        </div>

    </div>
    <br />
    <br />
    <br />

    <%@ include file="/front-end/member/footer.jsp"%>
   <script src="<%=request.getContextPath()%>/front-end/product/js/ProductCalss.js"></script>
</body>
</html>