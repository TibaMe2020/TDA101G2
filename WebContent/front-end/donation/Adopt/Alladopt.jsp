<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.donation.adopt_info.model.*"%>
<%@ page import="java.util.*"%>
    
<%
   Adopt_infoService AdoptSvc = new Adopt_infoService();
   List<Adopt_infoVO> adoptlist = AdoptSvc.getAll();
   pageContext.setAttribute("list",adoptlist);
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css">
<style>
 /* Font */
  @import url('https://fonts.googleapis.com/css?family=Quicksand:400,700');

  /* Design */
  *,
  *::before,
  *::after {
    box-sizing: border-box;
  }

  html {
  }

  body.background-blue {
    background-color: #ecf9ff;
    color: #272727;
    font-family: 'Quicksand', serif;
    font-style: normal;
    font-weight: 400;
    letter-spacing: 0;
/*     padding: 1rem; */
  }

  .main{
    max-width: 1200px;
/*     margin: 0 auto; */
  }

  h1 {
      font-size: 24px;
      font-weight: 400;
      text-align: center;
  }
   h3{
  	font-size: inherit;
  }
    .imageLink { 
   	height: 200px; 
  	width: 320px!important; 
   } 

  img {
/*     height: auto; */
/*     max-width: 100%; */
	height:100%;
	width: 100%;
    vertical-align: middle;
  }

a.btn.card_btn {
    color: #ffffff;
    padding: 0.8rem;
    font-size: 14px;
    text-transform: uppercase;
    border-radius: 4px;
    font-weight: 400;
    display: block;
    width: 100%;
    cursor: pointer;
    border: 2px solid rgba(255, 255, 255, 0.2);
    background: transparent;
    outline:none; //hh
    
  }

  a.card_btn:hover {
    background-color: rgba(255, 255, 255, 0.12);
  }

  .cards {
    display: flex;
    flex-wrap: wrap;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .cards_item {
    display: flex;
    padding: 1rem;
  }

  @media (min-width: 40rem) {
    .cards_item {
      width: 50%;
    }
  }

  @media (min-width: 56rem) {
    .cards_item {
      width: 33.3333%;
    }
  }

  .card {
    background-color: white;
    border-radius: 0.25rem;
    box-shadow: 0 20px 40px -14px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    overflow: hidden;
  }

  .card_content {
    padding: 1rem;
    background: linear-gradient(to bottom left, #EF8D9C 40%, #FFC39E 100%);
  }

  .card_title {
    color: #ffffff;
    font-size: 1.1rem;
    font-weight: 700;
    letter-spacing: 1px;
    text-transform: capitalize;
    margin: 0px;
  }

  .card_text {
    color: #ffffff;
    font-size: 0.875rem;
    line-height: 1.5;
    margin-bottom: 1.25rem;
    font-weight: 400;
  }
  .made_by{
    font-weight: 400;
    font-size: 13px;
    margin-top: 35px;
    text-align: center;
  }
  /*限制圖片大小*/
  .imgin {
    max-width:500px;
    max-height: 300px;
    }
    /* body {
      color: #2f4f4f;
      font-family: 'Roboto', Arial;
      font-size: 87.5%;
      line-height: 1.5;
      text-align: left;
      /*background-color:lightgray;*/
    }
    * {
  box-sizing: border-box;
  }

   /* html {
  font-size: 100%;
  } */

  /* body {
  word-wrap: break-word;
  }  */

  .body,
  .imageCaption {
  line-height: 1.4;
  }

  .clearfix::after {
  content: "";
  display: table;
  clear: both;
  }

/*圖片位置置中用*/
  .galleryWrapper {
  font-size: 0.85rem;
  width: 90%;
  margin: 3rem auto;
  }

/*圖片本身大小*/
  .galleryWrapper,
  .imageBox {
  max-width: 60rem;
  }

  .header {
  font: 2.1em 'Oswald', sans-serif;
/*   margin: 1.25rem 0; */
  padding: 0 0.5rem;
  }

   .imageBox,
  .header {
  text-align: center;
  }

  .imageContainer {
  padding: 0 0.5rem 1.25rem;
  }

  .imageLink {
  display: inline-block;
  }

  .imageLink,
  .imageBox {
  line-height: 0;
  }

  /* .imageLink:nth-of-type(-n+3) { //圖片上下縫隙
  margin-bottom: 0.8rem;
  } */

  /*圖片填滿*/
  .imageBox,
  .largeImage,
  .image,
  .imageCaption {
  width: 100%;
  }

  /*圖片另一邊沒隱藏*/
  .image,
  .largeImage {
  -webkit-transform: perspective(1px);
  -moz-transform: perspective(1px);
  transform: perspective(1px);

  }

  .overlayContainer {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 250;
  }

  .overlayContainer,
  .imageCaption {
  bottom: 0;
  }

  .relativeContainer {
  position: relative;
  margin: 0 0.5rem;
  }

  /*點擊放大的時候沒置中*/
  .imageBox,
  .imageCaption {
  position: absolute;

  }

  /* 黑背景框*/
  .imageBox {
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  /* width: 20%; */

  }

  /*點擊圖片是否放大*/
  .display {
  display: block;
  }

  /*圖片字幕大小*/
  .imageCaption {
  margin: 0;
  /* font-size: 1.3em; */
   font-size: 0.5em;
  padding: 0.325rem;
  }



  /***Colors***/


  .imageLink {
  outline: none;
  -webkit-transition: all ease .2s;
  transition: all ease .2s;
  }

  .imageLink:hover,
  .imageLink:focus {
  opacity: 0.8;
  }

  /*背景底色*/
  .overlayContainer {
  visibility: hidden;
  opacity: 0;
  background-color: rgba(51, 51, 51, .9);
  -webkit-transition: all ease .5s;
  transition: all ease .5s;
  }

  .imageBox {
  cursor: pointer;
  }

  .largeImage {
  box-shadow: 5px 5px 5px 0px rgb(0, 0, 0);
  }

  .imageCaption {
  background: rgba(51, 51, 51, .8);
  color: #fff;
  }

  .opacity {
  visibility: visible;
  opacity: 1;
  }



  @media only screen and (min-width: 30em) {

  .imageLink {
  float: left;
  width: 48.33%;
  }

  .imageLink:nth-of-type(1),
  .imageLink:nth-of-type(3) {
  margin-right: 3.33%;
  }

  .imageLink:nth-of-type(3) {
  margin-bottom: 0;
  }

  a.btn.card_btn{
  text-align:center;
  text-decoration:none;
  
  }
   div.row1{ 
     position: relative; 
	top: 55px;
    left: 100px;
    } 
  }
</style>
  <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
	
</head>
<body class="background-blue">

<body>
<%@ include file="/front-end/member/header.jsp"%>

<div class="container-fluid" >
<div class="row1">
<i class="fas fa-bone"></i>
<a href="<%=request.getContextPath()%>/front-end/donation/myMain/donation.jsp">公益首頁</a><span> > 動物認養</span>

</div>
</div>
 <section class="galleryWrapper">
    <div class="imageContainer clearfix">

    <div class="main">
      <ul  class="cards">
<%--          <%@ include file="page1.file" %> --%>
	<c:forEach var="adopt_infoVO" items="${list}" >
        <li class="cards_item">
          <div class="card">
            <a class="imageLink" href="<%=request.getContextPath()%>/Adopt/DBGifReader3?adopt_id=${adopt_infoVO.adopt_id}" title="Dog data">
              <img class="imgin" src="<%=request.getContextPath()%>/Adopt/DBGifReader3?adopt_id=${adopt_infoVO.adopt_id}" alt="${adopt_infoVO.adopt_description}">
            </a>
            <div class="card_content">
              <h2 class="card_title">${adopt_infoVO.adopt_name}</h2>
          	  <p class="card_text">${adopt_infoVO.adopt_description}</p>
              <a class="btn card_btn" href='<%=request.getContextPath()%>/front-end/donation/AdoptForm/adoptForm2.jsp?adopt_id=${adopt_infoVO.adopt_id}'>我要認養</a>
            
            </div>
          </div>
        </li>
           </c:forEach>   
          </ul>
    </div>

<%--    <%@ include file="page2.file" %>  --%>


    <div class="overlayContainer">

      <div class="imageBox">
        <div class="relativeContainer">
          <img class="largeImage" src="" alt="">
          <p class="imageCaption"></p>
        </div>  <!-- /relativeContainer -->
      </div>  <!-- /imageBox -->

    </div>  <!-- overlayContainer -->
</div>

  </section>
  <%@ include file="/front-end/member/footer.jsp"%>

</body>


  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <script type="text/javascript" src="../JS/lightbox.js" charset="utf-8"></script>
</html>