<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.donation.donation_form_info.model.*"%>
<%@ page import="com.donation.npo_info.model.*"%>

<%
Donation_form_infoVO donation_form_infoVO = (Donation_form_infoVO) request.getAttribute("donation_form_infoVO");
%>

<% String npo_id = request.getParameter("npo_id");
Npo_infoService npoSvc = new Npo_infoService();
Npo_infoVO npo_infoVO = npoSvc.getOneNpo_info(npo_id);
pageContext.setAttribute("npo_infoVO",npo_infoVO);
%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css">
	
<title>Insert title here</title>
<style>
span.errorwh{
    position: relative;
    right: -201px;
    top: -19px;
    color:red;
}
label.ce{
right: 80px;
    position: relative;
    top: 39px;
}
label.ca{
right: -145px;
    position: relative;
    top: 3px;
}
   p.p5{
   position: relative;
    top: 48px;
    right: 39px;
   }
div.bs-example {
	margin: 100px;
}

/* body { */
/* 	overflow-x: hidden; */
/* } */

div.bac {
 	height: 1000px;  
	background-repeat: no-repeat;
 	background-position: center; 
	background-size: contain
}

div.container{
/*   border: 1px solid red; */
}
div.row1{
position: relative;
    top: 100px;
    right: 300px;}
    span.error{
    color:red;}

body.background-white{
	overflow-x: hidden;
	background-color: white;
}
div.divforp{
position: relative;
    top: 6px;
    right: 56px;
}
legend{
    position: relative;
    top: 60px;
    right: 169px;
}
div.form-check{
position: relative;
     top: 68px;
    right: 70px;
}
div.form-check.check2{
    top: -1px;
    right: -180px;
}
div.form-group.f1{
position: relative;
    top: 60px;
}
label.ladd{
    right: 160px;
    position: relative;
}
label.lt{
    position: relative;
    right: 58px;
    top: 20px;
}
textarea{
position: relative;
    top: 92px;
    right: 138px;
}
button.btn.btn-primary.yy{
    position: relative;
    top: 75px;
    right: -43px;
}
button.btn.btn-primary{
    position: relative;
  top: 35px;
    right: -50px;
}
input#yes{
	top: 41px;
    right: 108px;
    position: relative;
}
input#no{
top: 5px;
    position: relative;
    right: -126px;
}
input#cb.p6{
    top: 39px;
    right: -68px;
    position: relative;
}
input#tt.p6{
position: relative;
    top: 39px;
    right: -68px;
}
</style>

<style>

  body.zz {
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-line-pack: center;
            align-content: center;
        -webkit-box-align: center;
            -ms-flex-align: center;
                align-items: center;
        -webkit-box-pack: center;
            -ms-flex-pack: center;
                justify-content: center;
        min-height: 100vh;
        -ms-flex-wrap: wrap;
            flex-wrap: wrap;
        font-family: 'Raleway';
    }

    .payment-title {
        width: 100%;
        text-align: center;
    }
    .form-container .field-container:first-of-type {
        grid-area: name;
    }

    .form-container .field-container:nth-of-type(2) {
        grid-area: number;
    }

    .form-container .field-container:nth-of-type(3) {
        grid-area: expiration;
    }

    .form-container .field-container:nth-of-type(4) {
        grid-area: security;
    }

    .field-container input {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }

    .field-container {
        position: relative;
    }

    .form-container {
        display: grid;
        grid-column-gap: 10px;
        grid-template-columns: auto auto;
        grid-template-rows: 90px 90px 90px;
        grid-template-areas: "name name""number number""expiration security";
        max-width: 400px;
        padding: 20px;
        color: #707070;
    }
    label#name.cardl { 
         padding-bottom: 5px; 
         font-size: 13px; 
     } 
   input#cardnumber.cardl { 
           margin-top: 3px; 
         padding: 15px; 
         font-size: 16px; 
         width: 100%; 
         border-radius: 3px; 
         border: 1px solid #dcdcdc; 
     } 
     input#name.cardl { 
         margin-top: 3px; 
         padding: 15px; 
         font-size: 16px; 
         width: 100%; 
         border-radius: 3px; 
         border: 1px solid #dcdcdc; 
     } 
        input#expirationdate.cardl { 
         margin-top: 3px; 
         padding: 15px; 
         font-size: 16px; 
         width: 100%; 
         border-radius: 3px; 
         border: 1px solid #dcdcdc; 
     } 
        input#securitycode.cardl { 
         margin-top: 3px; 
         padding: 15px; 
         font-size: 16px; 
         width: 100%; 
         border-radius: 3px; 
         border: 1px solid #dcdcdc; 
     } 
.ccicon {
        height: 38px;
        position: absolute;
        right: 6px;
        top: calc(50% - 17px);
        width: 60px;
    }

    /* CREDIT CARD IMAGE STYLING */
    .preload * {
        -webkit-transition: none !important;
        -moz-transition: none !important;
        -ms-transition: none !important;
        -o-transition: none !important;
    }
    
      .containerxx { 
        width: 100%; 
        max-width: 400px; 
        max-height: 251px; 
         height: 54vw; 
         padding: 20px; 
     } 

  #ccsingle {
        position: absolute;
        right: 15px;
        top: 20px;
    }

    #ccsingle svg {
        width: 100px;
        max-height: 60px;
    }

    .creditcard svg#cardfront,
    .creditcard svg#cardback {
        width: 100%;
        -webkit-box-shadow: 1px 5px 6px 0px black;
        box-shadow: 1px 5px 6px 0px black;
        border-radius: 22px;
    }

    #generatecard{
        cursor: pointer;
        float: right;
        font-size: 12px;
        color: #fff;
        padding: 2px 4px;
        background-color: #909090;
        border-radius: 4px;
        cursor: pointer;
        float:right;
    }

    /* CHANGEABLE CARD ELEMENTS */
    .creditcard .lightcolor,
    .creditcard .darkcolor {
        -webkit-transition: fill .5s;
        transition: fill .5s;
    }

    .creditcard .lightblue {
        fill: #03A9F4;
    }

    .creditcard .lightbluedark {
        fill: #0288D1;
    }

    .creditcard .red {
        fill: #ef5350;
    }

    .creditcard .reddark {
        fill: #d32f2f;
    }

    .creditcard .purple {
        fill: #ab47bc;
    }

    .creditcard .purpledark {
        fill: #7b1fa2;
    }

    .creditcard .cyan {
        fill: #26c6da;
    }

    .creditcard .cyandark {
        fill: #0097a7;
    }

    .creditcard .green {
        fill: #66bb6a;
    }

    .creditcard .greendark {
        fill: #388e3c;
    }

    .creditcard .lime {
        fill: #d4e157;
    }

    .creditcard .limedark {
        fill: #afb42b;
    }

    .creditcard .yellow {
        fill: #ffeb3b;
    }

    .creditcard .yellowdark {
        fill: #f9a825;
    }

    .creditcard .orange {
        fill: #ff9800;
    }

    .creditcard .orangedark {
        fill: #ef6c00;
    }

    .creditcard .grey {
        fill: #bdbdbd;
    }

    .creditcard .greydark {
        fill: #616161;
    }

    /* FRONT OF CARD */
    #svgname {
        text-transform: uppercase;
    }

    #cardfront .st2 {
        fill: #FFFFFF;
    }

    #cardfront .st3 {
        font-family: 'Source Code Pro', monospace;
        font-weight: 600;
    }

    #cardfront .st4 {
        font-size: 54.7817px;
    }

    #cardfront .st5 {
        font-family: 'Source Code Pro', monospace;
        font-weight: 400;
    }

    #cardfront .st6 {
        font-size: 33.1112px;
    }

    #cardfront .st7 {
        opacity: 0.6;
        fill: #FFFFFF;
    }

    #cardfront .st8 {
        font-size: 24px;
    }

    #cardfront .st9 {
        font-size: 36.5498px;
    }

    #cardfront .st10 {
        font-family: 'Source Code Pro', monospace;
        font-weight: 300;
    }

    #cardfront .st11 {
        font-size: 16.1716px;
    }

    #cardfront .st12 {
        fill: #4C4C4C;
    }

    /* BACK OF CARD */
    #cardback .st0 {
        fill: none;
        stroke: #0F0F0F;
        stroke-miterlimit: 10;
    }

    #cardback .st2 {
        fill: #111111;
    }

    #cardback .st3 {
        fill: #F2F2F2;
    }

    #cardback .st4 {
        fill: #D8D2DB;
    }

    #cardback .st5 {
        fill: #C4C4C4;
    }

    #cardback .st6 {
        font-family: 'Source Code Pro', monospace;
        font-weight: 400;
    }

    #cardback .st7 {
        font-size: 27px;
    }

    #cardback .st8 {
        opacity: 0.6;
    }

    #cardback .st9 {
        fill: #FFFFFF;
    }

    #cardback .st10 {
        font-size: 24px;
    }

    #cardback .st11 {
        fill: #EAEAEA;
    }

    #cardback .st12 {
        font-family: 'Rock Salt', cursive;
    }

    #cardback .st13 {
        font-size: 37.769px;
    }

    /* FLIP ANIMATION */
    /* .container {
        perspective: 1000px;
    } */

    .creditcard {
        width: 100%;
        max-width: 400px;
        -webkit-transform-style: preserve-3d;
        transform-style: preserve-3d;
        transition: -webkit-transform 0.6s;
        -webkit-transition: -webkit-transform 0.6s;
        transition: transform 0.6s;
        transition: transform 0.6s, -webkit-transform 0.6s;
        cursor: pointer;
    }

    .creditcard .front,
    .creditcard .back {
        position: absolute;
        width: 100%;
        max-width: 400px;
        -webkit-backface-visibility: hidden;
        backface-visibility: hidden;
        -webkit-font-smoothing: antialiased;
        color: #47525d;
    }

    .creditcard .back {
        -webkit-transform: rotateY(180deg);
        transform: rotateY(180deg);
    }

    .creditcard.flipped {
        -webkit-transform: rotateY(180deg);
        transform: rotateY(180deg);
    }
    button.btn.btn-primary.cardb{
    position: relative;
    top: 0px;
    right: -2px;
    }
</style>


</head>
<body class="background-white">
<%@ include file="/front-end/member/header.jsp"%>
<div class="container bc" >
<div class="row1">
<i class="fas fa-bone"></i>
<a href="<%=request.getContextPath()%>/front-end/donation/Adopt/Alladopt.jsp">動物認養</a><span> > 認養表單</span>

</div>
</div>
<div class="container bc" >
		<div class="row bac justify-content-start align-items-center" style="background-image:url('<%=request.getContextPath()%>/resources/images/box.png')">
	
	<div class="container bc" >
		<div class="row justify-content-center">
				<div class="bs-example ">

					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/DonationForm/donationform.do" name="form1">
						<div class="divforp">
							<div class="form-group">
          						<label>	${npo_infoVO.npo_name}</label>
          						<input type="TEXT" name="npo_id" value="${npo_infoVO.npo_id}" readonly="readonly" class="form-control" style="border-style: none">
        					</div>
						  <div class="form-group f1">
         					<label class="ladd">捐款人:</label>
         					<input type="text" class="form-control" name="donator_name"><span class="error">${errors.donator_name}</span>
       					  </div>
						  <div class="form-group f1">
         					<label class="ladd">聯絡電話:</label>
         					<input type="text" class="form-control" name="donator_phone_num"><span class="error">${errors.donator_phone_num}</span>
       					  </div>	
       					    <div class="form-group f1">
         					<label class="ladd">捐款金額:</label>
         					<input type="text" class="form-control" name="donation_money"><span class="error">${errors.donation_money}</span>
       					  </div>				
			
			<fieldset class="form-group">
          <legend class="col-form-label col-sm pt-0">付款方式</legend>
        <div class="col-sm-10">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="payment" id="gridRadios1" value="信用卡" checked>
            <label class="form-check-label" >
              信用卡
            </label>
          </div><body>
  <!-- Button trigger modal -->
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
    付款信用卡
  </button>

  <!-- Modal -->
  <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="payment-title">
             </div>
             <div class="containerxx preload">
                 <div class="creditcard">
                     <div class="front">
                         <div id="ccsingle"></div>
                         <svg version="1.1" id="cardfront" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                             x="0px" y="0px" viewBox="0 0 750 471" style="enable-background:new 0 0 750 471;" xml:space="preserve">
                             <g id="Front">
                                 <g id="CardBackground">
                                     <g id="Page-1_1_">
                                         <g id="amex_1_">
                                             <path id="Rectangle-1_1_" class="lightcolor grey" d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
                                     C0,17.9,17.9,0,40,0z" />
                                         </g>
                                     </g>
                                     <path class="darkcolor greydark" d="M750,431V193.2c-217.6-57.5-556.4-13.5-750,24.9V431c0,22.1,17.9,40,40,40h670C732.1,471,750,453.1,750,431z" />
                                 </g>
                                 <text transform="matrix(1 0 0 1 60.106 295.0121)" id="svgnumber" class="st2 st3 st4">0123 4567 8910 1112</text>
                                 <text transform="matrix(1 0 0 1 54.1064 428.1723)" id="svgname" class="st2 st5 st6">JOHN DOE</text>
                                 <text transform="matrix(1 0 0 1 54.1074 389.8793)" class="st7 st5 st8">cardholder name</text>
                                 <text transform="matrix(1 0 0 1 479.7754 388.8793)" class="st7 st5 st8">expiration</text>
                                 <text transform="matrix(1 0 0 1 65.1054 241.5)" class="st7 st5 st8">card number</text>
                                 <g>
                                     <text transform="matrix(1 0 0 1 574.4219 433.8095)" id="svgexpire" class="st2 st5 st9">01/23</text>
                                     <text transform="matrix(1 0 0 1 479.3848 417.0097)" class="st2 st10 st11">VALID</text>
                                     <text transform="matrix(1 0 0 1 479.3848 435.6762)" class="st2 st10 st11">THRU</text>
                                     <polygon class="st2" points="554.5,421 540.4,414.2 540.4,427.9 		" />
                                 </g>
                                 <g id="cchip">
                                     <g>
                                         <path class="st2" d="M168.1,143.6H82.9c-10.2,0-18.5-8.3-18.5-18.5V74.9c0-10.2,8.3-18.5,18.5-18.5h85.3
                                 c10.2,0,18.5,8.3,18.5,18.5v50.2C186.6,135.3,178.3,143.6,168.1,143.6z" />
                                     </g>
                                     <g>
                                         <g>
                                             <rect x="82" y="70" class="st12" width="1.5" height="60" />
                                         </g>
                                         <g>
                                             <rect x="167.4" y="70" class="st12" width="1.5" height="60" />
                                         </g>
                                         <g>
                                             <path class="st12" d="M125.5,130.8c-10.2,0-18.5-8.3-18.5-18.5c0-4.6,1.7-8.9,4.7-12.3c-3-3.4-4.7-7.7-4.7-12.3
                                     c0-10.2,8.3-18.5,18.5-18.5s18.5,8.3,18.5,18.5c0,4.6-1.7,8.9-4.7,12.3c3,3.4,4.7,7.7,4.7,12.3
                                     C143.9,122.5,135.7,130.8,125.5,130.8z M125.5,70.8c-9.3,0-16.9,7.6-16.9,16.9c0,4.4,1.7,8.6,4.8,11.8l0.5,0.5l-0.5,0.5
                                     c-3.1,3.2-4.8,7.4-4.8,11.8c0,9.3,7.6,16.9,16.9,16.9s16.9-7.6,16.9-16.9c0-4.4-1.7-8.6-4.8-11.8l-0.5-0.5l0.5-0.5
                                     c3.1-3.2,4.8-7.4,4.8-11.8C142.4,78.4,134.8,70.8,125.5,70.8z" />
                                         </g>
                                         <g>
                                             <rect x="82.8" y="82.1" class="st12" width="25.8" height="1.5" />
                                         </g>
                                         <g>
                                             <rect x="82.8" y="117.9" class="st12" width="26.1" height="1.5" />
                                         </g>
                                         <g>
                                             <rect x="142.4" y="82.1" class="st12" width="25.8" height="1.5" />
                                         </g>
                                         <g>
                                             <rect x="142" y="117.9" class="st12" width="26.2" height="1.5" />
                                         </g>
                                     </g>
                                 </g>
                             </g>
                             <g id="Back">
                             </g>
                         </svg>
                     </div>
                     <div class="back">
                         <svg version="1.1" id="cardback" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                             x="0px" y="0px" viewBox="0 0 750 471" style="enable-background:new 0 0 750 471;" xml:space="preserve">
                             <g id="Front">
                                 <line class="st0" x1="35.3" y1="10.4" x2="36.7" y2="11" />
                             </g>
                             <g id="Back">
                                 <g id="Page-1_2_">
                                     <g id="amex_2_">
                                         <path id="Rectangle-1_2_" class="darkcolor greydark" d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
                                 C0,17.9,17.9,0,40,0z" />
                                     </g>
                                 </g>
                                 <rect y="61.6" class="st2" width="750" height="78" />
                                 <g>
                                     <path class="st3" d="M701.1,249.1H48.9c-3.3,0-6-2.7-6-6v-52.5c0-3.3,2.7-6,6-6h652.1c3.3,0,6,2.7,6,6v52.5
                             C707.1,246.4,704.4,249.1,701.1,249.1z" />
                                     <rect x="42.9" y="198.6" class="st4" width="664.1" height="10.5" />
                                     <rect x="42.9" y="224.5" class="st4" width="664.1" height="10.5" />
                                     <path class="st5" d="M701.1,184.6H618h-8h-10v64.5h10h8h83.1c3.3,0,6-2.7,6-6v-52.5C707.1,187.3,704.4,184.6,701.1,184.6z" />
                                 </g>
                                 <text transform="matrix(1 0 0 1 621.999 227.2734)" id="svgsecurity" class="st6 st7">985</text>
                                 <g class="st8">
                                     <text transform="matrix(1 0 0 1 518.083 280.0879)" class="st9 st6 st10">security code</text>
                                 </g>
                                 <rect x="58.1" y="378.6" class="st11" width="375.5" height="13.5" />
                                 <rect x="58.1" y="405.6" class="st11" width="421.7" height="13.5" />
                                 <text transform="matrix(1 0 0 1 59.5073 228.6099)" id="svgnameback" class="st12 st13">John Doe</text>
                             </g>
                         </svg>
                     </div>
                 </div>
             </div>
             <div class="form-container">
                 <div class="field-container">
                     <label class="cardl" for="name">Name</label>
                     <input class="cardl"  id="name" maxlength="20" type="text">
                 </div>
                 <div class="field-container">
                     <label class="cardl"  for="cardnumber">Card Number</label><span id="generatecard">generate random</span>
                     <input class="cardl"  id="cardnumber" type="text" pattern="[0-9]*" inputmode="numeric">
                     <svg id="ccicon" class="ccicon" width="750" height="471" viewBox="0 0 750 471" version="1.1" xmlns="http://www.w3.org/2000/svg"
                         xmlns:xlink="http://www.w3.org/1999/xlink">

                     </svg>
                 </div>
                 <div class="field-container">
                     <label class="cardl"  for="expirationdate">Expiration (mm/yy)</label>
                     <input class="cardl"  id="expirationdate" type="text" pattern="[0-9]*" inputmode="numeric">
                 </div>
                 <div class="field-container">
                     <label class="cardl"  for="securitycode">Security Code</label>
                     <input class="cardl"  id="securitycode" type="text" pattern="[0-9]*" inputmode="numeric">
                 </div>
             </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
          <button type="button" class="btn btn-primary cardb" data-dismiss="modal">確定</button>
        </div>
      </div>
    </div>
  </div>
</body>
        <div class="form-check check2">
            <input class="form-check-input" type="radio" name="payment" id="gridRadios2" value="paypal">
            <label class="form-check-label" >
             paypal
            </label>
          </div>
		
		<div class="form-group">
								<p class="p5">收據領取付款方式:<input type="radio" name="onwhitch"  onclick="RadioCheck()" id="yes" value="電子郵件"size="45" style="margin-left:26px;" checked/>
								<label class="ce">電子郵件</label>
								<input type="radio" onclick="RadioCheck()" name="onwhitch" id="no" value="住家地址"size="45"
	      						style="margin-left:74px;"/><label class="ca">住家地址</label></p>
							</div>
							<div class="form-group">
								      <input class="p6" type ="text"id="tt" name="receipt_type"style="display:block;"/>
	      							  <input class="p6"type ="text" id="cb" name="receipt_type" style="display:none;"/>
							</div><span  class="errorwh">${errors.receipt_type}</span>
							
							
							  <button type="submit" name="action" value="insert" class="btn btn-primary yy">送出</button>
							</div>
						</div>
					</FORM>
				</div>
			</div>
		</div>
	</div>
</div>
	<%@ include file="/front-end/member/footer.jsp"%>
 <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/donation/JS/card.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js"></script>
  
  <script>
  function RadioCheck() {
      if (document.getElementById("yes").checked) {
          document.getElementById("tt").style.display = "block";
          document.getElementById("cb").style.display = "none";
          document.getElementById("tt").value="";

      }
      if (document.getElementById("no").checked) {
          document.getElementById("cb").style.display = "block";
          document.getElementById("tt").style.display = "none";
          document.getElementById("cb").value="";
      }

  }
 
  </script>
</body>
</html>