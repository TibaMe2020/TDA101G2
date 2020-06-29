<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Insert title here</title>
</head>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css">
  <style>
    body {
    font-family: Georgia;
    margin: 0;
  }

  .wrapper {
    width: 100%;
    max-width: 1080px;
    padding: 0 10px;
    margin: 0 auto;
  }

  h1.logo {
    background-image: url(https://image.ibb.co/nuSZNa/logo.png);
    background-repeat: no-repeat;
    width: 100px;
    text-indent: -10000px;
    float: left;
  }

  header nav {
    float: right;
  }

  header nav h2 {
    text-indent: -10000px;
    height: 0;
    margin: 0;
  }

  header nav li {
    float: left;
    list-style-type: none;
    margin: 10px 20px;
  }

  header nav li a {
    text-decoration: none;
    color: #333;
    font-size: 18px;
  }

  #main-banner, #main-banner img {
    width: 100%;
  }

  #main-banner img {
    border-bottom: 6px solid #f34949;
  }

  /* #home-menu h2 {
    background-image: url(https://image.ibb.co/iKBuNa/menu_flag.png);
    background-repeat: no-repeat;
    width: 156px;
    height: 74px;
    text-indent: -10000px;
    margin: 0 auto;
    position: relative;
    top: -4px;
  } */

  #home-menu ul {
    padding: 0;
    list-style-type: none;
  }

  #home-menu ul:after {
    content: "";
    clear: both;
    display: block;
  }

  #home-menu li {
    float: left;
    width: 42%;
    padding: 0;
    margin: 20px 0 20px 8%;
  }

  #home-menu li:nth-child(odd) {
    margin: 20px 8% 20px 0;
  }

  .dish {
    float: left;
    color: #555;
    font-weight: bold;
    position: relative;
    z-index: 1;
    background: #fff;
    padding-right: 15px;
  }

  .price {
    float: right;
    color: #555;
    font-weight: bold;
    position: relative;
    z-index: 1;
    background: #fff;
    padding-left: 15px;
  }

  .description {
    clear: both;
    display: block;
    color: #999;
    font-style: italic;
    font-size: 14px;
    padding-top: 10px;
    border-top: 1px solid #ddd;
    position: relative;
    top: -8px;
  }

    #fo {
      margin-top: 100px;
    }

    #fo ul {
      padding: 0;
    }

    #fo ul:after {
      content: "";
      display: block;
      clear: both;
    }

    #fo li {
      float: left;
      width: 18%;
      margin: 1%;
      list-style-type: none;
    }

    #fo li img {
      width: 100%;
      margin-bottom: 10px;
    }

    #fo li p {
      text-align: center;
    }

    #fo li span {
      color: #333;
    }
  #featured {
    margin-top: 100px;
  }

  #featured ul {
    padding: 0;
  }

  #featured ul:after {
    content: "";
    display: block;
    clear: both;
  }

  #featured li {
    float: left;
    width: 23%;
    margin: 1%;
    list-style-type: none;
  }

  #featured li img {
    width: 100%;
    margin-bottom: 10px;
  }

  #featured li a {
    color: #333;
    text-decoration: none;
    float: left;
  }

  #featured li span {
    float: right;
    color: #333;
  }

  .items {
      width: 90%;
      margin: 0px auto;
      margin-top: 100px
  }

  .slick-slide {
      margin: 10px
  }

  .slick-slide img {
      width: 100%;
      border: 0px solid #fff
  }

  </style>
  <style>
    .column {
    	margin: 15px 15px 0;
    	padding: 0;
    }
    .column:last-child {
    	padding-bottom: 60px;
    }
    .column::after {
    	content: '';
    	clear: both;
    	display: block;
    }
    .column div {
    	position: relative;
    	float: left;
    	 width: 100%;
    	margin: 0 0 0 25px;
    	padding: 0;
    }
    .column div:first-child {
    	margin-left: 0;
    }
    .column div span {
    	position: absolute;
    	bottom: -20px;
    	left: 0;
    	z-index: -1;
    	display: block;
 width: 100%;
     	margin: 0;
    	padding: 0;
    	color: #444;
    	font-size: 18px;
    	text-decoration: none;
    	text-align: center;
    	-webkit-transition: .3s ease-in-out;
    	transition: .3s ease-in-out;
    	opacity: 0;
    }
     figure { 
     	width:100%;
    	overflow: hidden; 
     } 
    figure:hover+span {
    	bottom: -36px;
    	opacity: 1;
    }
  .hover08 figure img {
    	-webkit-filter: grayscale(100%);
    	filter: grayscale(100%);
    	-webkit-transition: .3s ease-in-out;
    	transition: .3s ease-in-out;
    }
    .hover08 figure:hover img {
    	-webkit-filter: grayscale(0);
    	filter: grayscale(0);
    }
 p.pcenter{
     position: relative;
    top: 18px;
 }
 img.imgcenter{
     top: 13px;
    position: relative;
 }
  
  </style>
<body>
  <header>
<%@ include file="/front-end/member/header.jsp"%>

      </header>
      <!-- Start homepage content -->

      <!-- <div id="main-banner">
        <img src="https://image.ibb.co/hiVAvv/banner.jpg" alt="Welcome to Resto">
      </div> -->
      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="https://cdn2.ettoday.net/images/4395/e4395074.jpg" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="https://cdn.hk01.com/di/media/images/1434223/org/2c38649ac163b13c7098f2239cc3197c.jpg/VaFNC1xwrfwsyzwNS1vGlJNiZkmZmnkin8gDx5_IA8c?v=w1920" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="https://as.chdev.tw/web/article/b/0/4/b1814323-0790-4b70-a3b1-6cbc87d37d1f/A0951614.jpg" class="d-block w-100" alt="...">
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
      <div class="wrapper">
      <section id="fo">
        <!-- <h2>選單</h2> -->
<ul>
  <li>
  <div class="hover08 column">
  <div>
    <a href="<%=request.getContextPath()%>/front-end/donation/Adopt/Alladopt.jsp"><figure><img src="<%=request.getContextPath()%>/resources/images/petpic1.png"/></figure></a>
    <p href="#">動物認養</p>
  </div>
  </div>
  </li>
  <li>
<!--     <img src="https://cdn2.iconfinder.com/data/icons/veterinary-line/96/animal_shelter_pet_adoption-512.png"> -->
<%--     <a href="<%=request.getContextPath()%>/front-end/donation/Npo/Allnpo.jsp"><p href="#">公益團體捐款</p></a> --%>
<!--     <span class="rating"></span> -->
  <div class="hover08 column">   
<div>
    <a href="<%=request.getContextPath()%>/front-end/donation/Npo/Allnpo.jsp"><figure><img src="<%=request.getContextPath()%>/resources/images/petpic2.png"/></figure></a>
    <p href="#">公益團體捐款</p>
  </div>
  </div>
  </li>
   <li>
  <div class="hover08 column">
  <div>
    <a href="<%=request.getContextPath()%>/front-end/donation/DonationForm/selectdata.jsp"><figure><img class="imgcenter" src="<%=request.getContextPath()%>/resources/images/petpic3.png"/></figure></a>
    <p class="pcenter" href="#">捐款查詢</p>
  </div>
  </div>
  </li> 
 <li>
  <div class="hover08 column">
  <div>
    <a href="<%=request.getContextPath()%>/front-end/donation/Result/lineTest.jsp"><figure><img src="<%=request.getContextPath()%>/resources/images/petpic4.png"/></figure></a>
    <p href="#">成果發表</p>
  </div>
  </div>
  </li> 

  <li>
  <div class="hover08 column">
  <div>
   <a href="<%=request.getContextPath()%>/front-end/donation/myMain/question.jsp"><figure><img src="<%=request.getContextPath()%>/resources/images/petpic5.png"/></figure></a>
   <p href="#">Q&A中心</p></a>
   </div>
  </div>
  </li>
</ul>


      </section>

      <section id="featured">
<!--         <h3>愛心清單</h3> -->
<!--         <h4>----------------------------------------------------------------------------------------------------------------------</h4> -->

<!--         <ul> -->
<!--           <li> -->
<!--             <img src="https://pic.pimg.tw/livilife16888/1339552981-308415971.jpg"> -->
<!--             <a href="#">阿布</a><br> -->
<!--             <br><a>昨天車禍急需治療</a> -->
<!--             <span class="rating"></span> -->
<!--           </li> -->
<!--           <li> -->
<!--             <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTk0WpEtTdd5dAyPPNFlqK2Jctd1DR-nFFWTw&usqp=CAU"> -->
<!--             <a href="#">大布</a><br> -->
<!--             <br><a>昨天車禍急需治療</a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTNRAfZUzXcryadpzYPjv9Qsb2OOCdBU_vt8tyZeWwtZ_Y-cN29&usqp=CAU"> -->
<!--             <a href="#">中布</a><br> -->
<!--             <br><a>昨天車禍急需治療</a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR1MdWY4vS_pTEAkY2Cr73eGthcRWe5UIw5SA&usqp=CAU"> -->
<!--             <a href="#">小小布</a><br> -->
<!--             <br><a>昨天車禍急需治療</a> -->
<!--           </li> -->
	
<!--         </ul> -->
      </section>
      </div>


      <!-- End homepage content -->

      <footer>
	<%@ include file="/front-end/member/footer.jsp"%>

      </footer>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>