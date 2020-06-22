<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PETBOX</title>
    <link rel="stylesheet" href="../../resources/vendors/bootstrap/css/bootstrap.min.css">
    <link href="./css/store.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file="/front-end/member/header.jsp"%>
    <!-- 中間大圖片 -->

    <div class="row justify-content-center middlerow">
        <div class="col carousel main_img nopadding">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="<%=request.getContextPath()%>/resources/images/宣傳主圖1.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="<%=request.getContextPath()%>/resources/images/宣傳主圖2.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="<%=request.getContextPath()%>/resources/images/宣傳主圖4.jpg" alt="Third slide">
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
    </div>
    <!-- </div> -->
    <!-- 分類圖片 -->
    <div class="container-fluid">
        <div class="row align-items-start">
            <div class="row col-sm-5ths justify-content-center classrow">
                <div class="col-3 imgbox">
                    <a href="Introduction.jsp?type=restaurant"><img class="class_img" src="<%=request.getContextPath()%>/resources/images/餐廳.png">
                        <!-- <a href="http://localhost:8081/TDA101G2/Store_frontController?action=store&type=restaurant"><img class="class_img" src="./images/餐廳.png"> -->
                        <br>
                        <span class="class_text">寵物餐廳</span>
                    </a>
                </div>
                <div class="col-9 ">
                    <p class="introduction_text">寵物友善餐廳：<br>
                        可以帶著您的寵物進一銅進餐廳裡享用美食，非常適合和其他寵物主人做在此交流.聚會。也有某些餐廳本身就有養寵物，可以讓想養卻不能養的你，在這裡盡情撫摸牠們~</p>
                </div>
            </div>
            <div class="row col-sm-5ths justify-content-center classrow">
                <div class="col-3 imgbox">
                    <a href="Introduction.jsp?type=hostel"><img class="class_img" src="<%=request.getContextPath()%>/resources/images/旅館.png">
                        <!-- <a href="http://localhost:8081/TDA101G2/Store_frontController?action=store&type=hostel"><img class="class_img" src="./images/旅館.png"> -->
                        <br>
                        <span class="class_text">寵物旅館</span>
                    </a>
                </div>
                <div class="col-9 ">
                    <p class="introduction_text">寵物的家：<br>讓您在出遠門或多日不在家時，能有專門替您照顧心愛的寵物，不至於擔心伙食。也提供寵物保姆可以讓您寵物寄宿或是幫忙遛寵物。
                    </p>
                </div>
            </div>

            <div class="row col-sm-5ths justify-content-center classrow">
                <div class="col-3 imgbox">
                    <a href="Introduction.jsp?type=grooming"><img class="class_img" src="<%=request.getContextPath()%>/resources/images/美容.png">
                        <!-- <a href="http://localhost:8081/TDA101G2/Store_frontController?action=store&type=grooming"><img class="class_img" src="./images/美容.png"> -->
                        <br>
                        <span class="class_text">寵物美容</span>
                    </a>
                </div>
                <div class="col-9 ">
                    <p class="introduction_text">
                        寵物SPA：<br>提供專業的美容和按摩等服務。從基本的修剪指甲、整理毛髮、清潔身體，到特殊的做造型、皮膚毛髮調理等不一樣的服務。多家寵物美容應有盡有。</p>
                </div>
            </div>
            <div class="row col-sm-5ths justify-content-center classrow">
                <div class="col-3 imgbox">
                    <a href="Introduction.jsp?type=school"><img class="class_img" src="<%=request.getContextPath()%>/resources/images/學校.png">
                        <!-- <a href="http://localhost:8081/TDA101G2/Store_frontController?action=store&type=school"><img class="class_img" src="./images/學校.png"> -->
                        <br>
                        <span class="class_text">寵物學校</span>
                    </a>
                </div>
                <div class="col-9 ">
                    <p class="introduction_text">寵物訓練：<br>煩惱自己家的寵物說不聽，或是教不來嗎?想要讓寵物學會握手之類最一般的技能或是特殊才藝嗎?來寵物學校找尋適合您的寵物訓練班吧!
                    </p>
                </div>
            </div>
            <div class="row col-sm-5ths justify-content-center classrow">
                <div class="col-3 imgbox">
                    <a href="Introduction.jsp?type=hospital"><img class="class_img" src="<%=request.getContextPath()%>/resources/images/醫院.png">
                        <!-- <a href="http://localhost:8081/TDA101G2/Store_frontController?action=store&type=hospital"><img class="class_img" src="./images/醫院.png"> -->
                        <br>
                        <span class="class_text">寵物診所</span>
                    </a>
                </div>
                <div class="col-9 ">
                    <p class="introduction_text">寵物醫療：<br>提供各個寵物診所的預約，幫你照顧好家裡毛小孩的身體健康。別忘了，寵物也是要做定期的健康檢查唷~</p>
                </div>
            </div>
        </div>
    </div>
    <br>
	<%@ include file="/front-end/member/footer.jsp"%>


<!--     <script src="../../resources/vendors/jquery/jquery.js"></script> -->
<!--     <script src="../../resources/vendors/popper/popper.min.js"></script> -->
<!--     <script src="../../resources/vendors/bootstrap/js/bootstrap.min.js"></script> -->
</body>

</html>