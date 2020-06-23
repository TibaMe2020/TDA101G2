<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>


<head>
    <title>寵物網路商城</title>
    <!-- Required meta tags -->

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/product/css/ProductHomepage.css">
</head>


<body>
<%@ include file="/front-end/member/header.jsp"%>
<!-- serachbox -->
    <div class="container">
        <div class="row">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="請輸入商品名字">
                <div class="input-group-append">
                    <button class="btn btn-secondary" type="button">
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
    <!-- 關鍵字 -->
    <div class="container">
        <div class="row" id="keyword">

            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">經典狗狗美食</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">好用寵物用品</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">經典狗屋</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">寵物飼料機</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">攝像鏡頭</a>

            </div>
            <div class="col-sm-12 col-md-2">

                <a class="nav-link" href="#">貓倍麗</a>

            </div>

        </div>
        <!-- 輪播圖片 -->
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
        <hr />
        <!-- 商品類別 -->
        <div class="container" id="class_product">
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-sm-12 col-md-3 d-flex flex-column align-items-center">
                        <p>食品</p>
                        <!-- 導入食物頁面 -->
                        <a href="<%=request.getContextPath()%>/front-end/product/ProductClassFood.jsp">
                            <div class="item food">
                                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Classimage/food.png" />
                                <i class="fa fa-hamburger"></i>
                            </div>
                            <br />
                        </a>


                    </div>
                    <div class="col-sm-12 col-md-3 d-flex flex-column align-items-center">
                        <p>服飾</p>
                        <!-- 導入服飾頁面 -->
                        <a href="<%=request.getContextPath()%>/front-end/product/ProductClassShirt.jsp">
                            <div class="item cloth">
                                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Classimage/shirt.png" />
                                <i class="fa fa-tshirt"></i>
                            </div>
                        </a>
                        <br />

                    </div>
                    <div class="col-sm-12 col-md-3 d-flex flex-column align-items-center">
                        <p>住家</p>
                        <!-- 導入住家頁面 -->
                        <a href="<%=request.getContextPath()%>/front-end/product/ProductClassHome.jsp">
                            <div class="item home">
                                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Classimage/Home.png" />
                                <i class="fa fa-home"></i>
                            </div>
                        </a>
                        <br />

                    </div>
                    <div class="col-sm-12 col-md-3 d-flex flex-column align-items-center">
                        <p>用品</p>
                        <!-- 導入用品頁面 -->
                         <a href="<%=request.getContextPath()%>/front-end/product/ProductClassProduct.jsp">
                            <div class="item box">
                                <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Classimage/product.png" />
                                <i class="fa fa-box"></i>
                            </div>
                        </a>
                        <br />
                    </div>
                </div>
            </div>
            <br>

            <!-- 新品上架 -->
            <div>
                <h3 style="text-align:left">新品上架</h3>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12 col-md-3" id="new_product">
                            <div>
                                <a href="#">
                                   <img src="<%=request.getContextPath()%>/resources/images/ProductImage/New_Product/New_Product2.jpg" />
                                    <br />
                                    <p>迪里奧食品</p>
                                </a>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3" id="new_product">
                            <div>
                                <a href="http://127.0.0.1:5501/Product/Product.html">
                                   <img src="<%=request.getContextPath()%>/resources/images/ProductImage/New_Product/New_Product3.png" />
                                    <br />
                                    <p>迪里奧滾輪</p>
                                </a>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3" id="new_product">
                            <div>
                                <a href="http://127.0.0.1:5500/Product/Product.html">
                                  <img src="<%=request.getContextPath()%>/resources/images/ProductImage/New_Product/New_Product4.png" />
                                    <br />
                                    <p>寵物智慧飲水機</p>
                                </a>

                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3" id="new_product">
                            <div>
                                <a href="http://127.0.0.1:5500/Product/Product.html">
                                    <img src="<%=request.getContextPath()%>/resources/images/ProductImage/New_Product/New_Product1.png" />
                                    <br />
                                    <p>柏肯特食品</p>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 防疫活動 -->
            <div class="activity">
                <h3>防疫活動</h3>
               <img src="<%=request.getContextPath()%>/resources/images/ProductImage/Activity/activity.png"/>
            </div>
            <hr />

            <!-- 熱門商品 -->
            <div>
                <h3 style="text-align:left">熱門商品</h3>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12 col-md-3">
                            <div class="card">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/Hot_product/Hot_product1.png" alt="Card image">
                                <div class="card-body">
                                    <h4 class="card-title">寵物飼料</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$2400</del> $1500</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/Hot_product/Hot_product2.png" alt="Card image" >
                                <div class="card-body">
                                    <h4 class="card-title">高級罐頭</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <p class="card-text"><del>$4500</del> $4300</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/Hot_product/Hot_product3.png" alt="Card image" >
                                <div class="card-body">
                                    <h4 class="card-title">貓咪罐頭</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$4500</del> $100</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/Hot_product/Hot_product4.png" alt="Card image" >
                                <div class="card-body">
                                    <h4 class="card-title">舒適踏墊</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <p class="card-text"><del>$250</del> $200</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
            <hr />
            <!-- 每日精選 -->
            <div style="border:1px solid black">
                <h3 class="daily">每日精選</h3>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day.png"
                                    id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">韓國經典飼料</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$8400</del> $5600</p>

                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>

                                </div>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day2.jpg"
                                    id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">日本貓咪罐頭</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$4500</del> $350</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day3.jpg"
                                    id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">寵物餵食器</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <p class="card-text"><del>$3520</del> $2550</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day4.png"
                                    id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">Cesar罐頭</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$5740</del> $4420</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day5.jpg" id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">療育玩具</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$470</del> $300</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day6.jpg"
                                    id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">舒適貓砂</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$4500</del> $260</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>

                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day7.png" id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">經典狗屋</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$5500</del> $5400</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-3">
                            <div class="card" style="width:100%">
                                <img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/ProductImage/day/day8.png" id="Cardimage">
                                <div class="card-body">
                                    <h4 class="card-title">阿肯色狗食</h4>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star" style="color: #EF8216;"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <span class="star"><i class="fas fa-star"></i></span>
                                    <p class="card-text"><del>$5800</del> $5100</p>
                                    <a class="btn btn-primary text-white" type="button"
                                        href='http://127.0.0.1:5501/Product/Product.html' value="購買">
                                        購買
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <br />
            </div>
        </div>

        <br />
        <br />
        <br />
    </div>
		<%@ include file="/front-end/member/footer.jsp"%>
    <!-- <script src="/Login/Loginun.js"></script> -->
</body>
</html>