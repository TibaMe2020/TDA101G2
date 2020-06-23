<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
        <h3 style="text-align:left">精選食物</h3>
        <div class="row">
            <div class="col-sm-12 col-md-3">
                <div class="card" style="width:100%">
                    <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt.jpg" id="Cardimage">
                    <div class="card-body">
                        <h4 class="card-title">可愛寵物服飾</h4>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star"></i></span>
                        <p class="card-text"><del>$200</del> $150</p>
                        <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-3">
                <div class="card" style="width:100%">
                    <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt1.jpg" id="Cardimage">
                    <div class="card-body">
                        <h4 class="card-title">經典寵物服飾</h4>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star"></i></span>
                        <p class="card-text"><del>$450</del> $420</p>
                        <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-3">
                <div class="card" style="width:100%">
                    <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt2.jpg" id="Cardimage">
                    <div class="card-body">
                        <h4 class="card-title">中大型狗連帽衣服</h4>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star"></i></span>
                        <p class="card-text"><del>$2400</del> $2200</p>
                        <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-3">
                <div class="card" style="width:100%">
                    <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt3.jpg" id="Cardimage">
                    <div class="card-body">
                        <h4 class="card-title">寵物防寒羽絨衣</h4>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                        <span class="star"><i class="fas fa-star"></i></span>
                        <p class="card-text"><del>$4500</del> $3880</p>
                        <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                    </div>
                </div>
            </div>
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
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt4.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">寵物雨衣</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$4000</del> $3000</p>
                                <a href="http://${host}${webCtx}/front-end/product/Product.jsp?product_id=${p.product_id}" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt5.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">單身狗造型衣服</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$4700</del> $2340</p>
                                <a href="http://${host}${webCtx}/front-end/product/Product.jsp?product_id=${p.product_id}" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt6.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">造型寵物衣服</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$2470</del> $1200</p>
                                <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt7.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">帥氣版防風衣</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$740</del> $200</p>
                                <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt8.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">寵物用生日派對帽</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$1400</del> $740</p>
                                <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt9.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">日版小型犬雨衣</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$2500</del> $1400</p>
                                <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt10.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">小型犬護目鏡</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$4500</del> $100</p>
                                <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-3">
                        <div class="card" style="width:100%">
                            <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/ClassShirt/ClassShirt11.jpg" id="Cardimage1"
                                style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">小型犬專用護腳襪</h4>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <p class="card-text"><del>$1400</del> $1300</p>
                                <a href="#" class="btn btn-primary stretched-link"> 購 買 </a>
                            </div>
                        </div>

                    </div>


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