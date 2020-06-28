<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_version.model.*"%>
<%@ page import="com.product_score.model.*"%>
<%@ page import="java.util.*"%>

<%
	Product_VO product_VO = new Product_Service().getOneProduct(request.getParameter("product_id"));
	pageContext.setAttribute("product_VO", product_VO);
%>
<%
	Version_Service versionSvc = new Version_Service();
	List<Version_VO> version_VOList = versionSvc.getbyProductID(request.getParameter("product_id"));
	pageContext.setAttribute("version_VOList", version_VOList);
%>
<%
	Score_Service scoreSvc = new Score_Service();
	List<Score_VO> score_VOList = scoreSvc.getByProductID(request.getParameter("product_id"));
	pageContext.setAttribute("score_VOList", score_VOList);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>購物車</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap"
	rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/css/Product.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/css/LightBoxShow.css">
	
</head>
<body>
	<%@ include file="/front-end/member/header.jsp"%>
	<div id="myModal" class="modal">
		<span class="close">&times;</span> <img class="modal-content"
			id="img01">
		<div id="caption"></div>
	</div>
	<br>

	<!-- 商品圖片 -->
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-6" id="product_photo">
				<br>
				<div class="container" id="product_photo1">
					<img
						src="<%=request.getContextPath()%>/Product_Image?product_id=${product_VO.product_id}&image=1"
						alt="" id="FirstProductImage">
				</div>

				<br>

				<div id="product_photo2">
					<div class="container">
						<div class="row" id="product_photo2_1" >
							<div class="col-sm-12 col-md-4" style="border:solid 1px black">
								<img
									src="<%=request.getContextPath()%>/Product_Image?product_id=${product_VO.product_id}&image=2"
									alt="" id="FirstProductImage">
							</div>
							<div class="col-sm-12 col-md-4" style="border:solid 1px black">
								<img
									src="<%=request.getContextPath()%>/Product_Image?product_id=${product_VO.product_id}&image=3"
									alt="" id="FirstProductImage">
							</div>
							<div class="col-sm-12 col-md-4" style="border:solid 1px black">
								<img
									src="<%=request.getContextPath()%>/Product_Image?product_id=${product_VO.product_id}&image=4"
									alt="" id="FirstProductImage">
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 中間空格 -->
			<div class="col-sm-12 col-md-1"></div>

			<!-- 商品數量、版本、一般介紹 -->
			<div class="col-sm-12 col-md-5" id="product_select">
				<br>
				<div id="product_select1">

					<p style="text-align: center; font-size: 30px;" id="product_name" data-product_ID="${product_VO.product_id}">${product_VO.name}</p>

					<br>
                 <!--價錢 -->
					<div class="col-sm-12 col-md-12">
						<div class="row justify-content-center"
							style="height: 30px; font-size:25px">
							<p class="col-sm-12 col-md-4"style="
    font-size: 24px;">直購價:$</p>
							<p class="col-sm-12 col-md-5" style="text-align: center; color:red; font-weight:800; background-color: #E4E4E4; font-size: 24px;"
								id="FirstProductPrice">
								 ${version_VOList.get(0).price}
							</p>
						</div>
					</div>
					<br>
				 <!--數量 -->
					<div class="container">
						<div class="row">
							<div class="col-sm-12 col-md-4">
								<h3 style="margin-top: 8px;">數量:</h3>
							</div>
							<div class="col-sm-12 col-md-4">
								<div class='ctrl'>
									<div class='ctrl__button ctrl__button--decrement'  style="font-size:30px">&ndash;</div>
									<div class='ctrl__counter'>
										<input class='ctrl__counter-input' maxlength='10' type='text'
											value='1' max="99" id="account">
										<div class='ctrl__counter-num'>1</div>
									</div>
									<div class='ctrl__button ctrl__button--increment' style="font-size:30px">+</div>
								</div>
							</div>
						</div>
					</div>
					<br>
					<!--版本 -->
					<div id="product_select2">
						<div class="row justify-content-center" id="version">
							<c:forEach var="version_VO" items="${version_VOList}"  varStatus="loop">


								<div class="col-sm-12 col-md-3"
									style="line-height: 60px; ">
									<button class="btn btn-outline-secondary version1 btn_version ${loop.index == 0? '-on': ''}" 
									data-price="${version_VO.price}" 
									data-version-id="${version_VO.product_version_id}">${version_VO.version_name}
									</button>
								</div>

							</c:forEach>
						</div>
						<div class="row justify-content-center">
							 <div class="col-sm-12 col-md-4" style="margin-top: 20px; ">
                                <a href="<%=request.getContextPath()%>/front-end/product/ProductHomepage.jsp" class="btn btn-primary stretched-link" id="stillbuy"> 繼續購買 </a>
                            </div>
<c:choose>
	<c:when test="${memberVO.member_id!=product_VO.member_id}">                            
                            <div class="col-sm-12 col-md-4" style="margin-top: 20px; ">
                                <a href="#" class="btn btn-primary stretched-link" id="shoppingcart" data-memberId = ${memberVO.member_id}> 購物車 </a>
                            </div>
                            
                            <div class="col-sm-12 col-md-4" style="margin-top: 20px; ">

                                <a href="<%=request.getContextPath()%>/front-end/product/ShoppingCart.jsp" class="btn btn-primary stretched-link" id="buy" 
                                data-memberId = "${memberVO.member_id}">直接購買</a>
	</c:when>
	<c:otherwise>
	
	  <div class="col-sm-12 col-md-4" style="margin-top: 20px; ">
                                <a href="#" class="btn btn-primary stretched-link disabled" id="shoppingcart" data-memberId = ${memberVO.member_id} disabled> 購物車 </a>
                            </div>
                            
                            <div class="col-sm-12 col-md-4" style="margin-top: 20px; ">

                                <a href="<%=request.getContextPath()%>/front-end/product/ShoppingCart.jsp" class="btn btn-primary stretched-link disabled" id="buy" 
                                data-memberId = "${memberVO.member_id}" >直接購買</a>


	</c:otherwise>
</c:choose>                             
                            </div>

						</div>


						<p style="text-align: left; line-height: 30px; margin-top: 20px; font-size: 25px;">★
							全台獨一無二</p>

						<p style="text-align: left;font-size: 25px;">★ 寵奴的最佳選擇</p>

					</div>

				</div>
				<br>

			</div>
		</div>
	</div>

	<!-- 商品介紹、規格、其他說明、商品評價 -->
	<br>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-12">

				<div class="warpper">
					<input class="radio" id="one" name="group" type="radio" checked>
					<input class="radio" id="two" name="group" type="radio"> <input
						class="radio" id="three" name="group" type="radio"> <input
						class="radio" id="four" name="group" type="radio">
					<div class="tabs">
						<label class="tab" id="one-tab" for="one">商品介紹</label> <label
							class="tab" id="two-tab" for="two">商品規格</label> <label
							class="tab" id="three-tab" for="three">其他說明</label> <label
							class="tab" id="four-tab" for="four">商品評價</label>
					</div>
					<div class="panels">
						<div class="panel" id="one-panel">
							<img
								src="<%=request.getContextPath()%>/Product_Image?product_id=${product_VO.product_id}&image=0"
								id="one-panel" style="width:750px ;hight:100%; margin:auto;">
						</div>
						<div class="panel" id="two-panel">
							<p style="text-align: left;">
								本商品規格 產品名稱：${product_VO.name}<br> 產地：台灣<br>
								製造日期：標示於商品底部<br> (鑑賞期並非試用期，本商品屬於個人商品，<br>
								如拆封使用後商品有任何疑慮及狀況，請連繫平台協助換貨，<br> 恕不接受個人因素退貨，建議您訂購前請先仔細評估喔!)<br>
							</p>
						</div>
						<div class="panel" id="three-panel">
							<p style="text-align: left;">
								運送及其他說明配送到府<br> 寄送時間：<br> 台北巿6h到貨試營運
								全台灣24h到貨，遲到提供100元現金積點。全年無休，週末假日照常出貨。<br> 例外說明 送貨方式：<br>
								透過宅配送達。除網頁另有特別標示外，均為常溫配送。<br>
								消費者訂購之商品若經配送兩次無法送達，再經本公司以電話與Email均無法聯繫逾三天者，本公司將取消該筆訂單，並且全額退款。<br>
								送貨範圍：<br>
								限台灣本島與離島地區註，部分離島地區包括連江馬祖、綠島、蘭嶼、琉球鄉…等貨件，將送至到岸船公司碼頭，需請收貨人自行至碼頭取貨。<br>
								注意！收件地址請勿為郵政信箱。<br> 註：離島地區不配送安裝商品、手機門號商品、超大材商品及四機商品。<br>
								售後服務：<br> ● 產品保固一年 ( 外觀、外殼、包裝材料，恕不在保固範圍內 ) <br> ●
								產品責任險：<br> 本產品已投保新光產物產品責任保險$250,000,000元。<br>
								(保險證號：130008AKP0000930)<br> 執照證號＆登錄字號：<br>
								本公司食品業者登錄字號A-116606102-00000-0。
							</p>
						</div>
						<div class="panel" id="four-panel" style>
							<p style="text-align: left;">
								<c:forEach var="score_VO" items="${score_VOList}">
									<div class="row">
										<div class="col-sm-12 col-md-7" style="margin-top: 50px;">
											<p style="text-align: left;">${product_VO.member_id}</p>
											<div class="row">
												<div class="col-sm-12 col-md-5" style="right: 65px;">
													<span class="star"><i class="fas fa-star"
														style=" ${(score_VO.score >=1)? ' color: #EF8216 ' :'' }"></i></span>
													<span class="star"><i class="fas fa-star"
														style=" ${(score_VO.score >=2)? ' color: #EF8216 ' :'' }"></i></span>
													<span class="star"><i class="fas fa-star"
														style=" ${(score_VO.score >=3)? ' color: #EF8216 ' :'' }"></i></span>
													<span class="star"><i class="fas fa-star"
														style=" ${(score_VO.score >=4)? ' color: #EF8216 ' :'' }"></i></span>
													<span class="star"><i class="fas fa-star"
														style=" ${(score_VO.score ==5)? ' color: #EF8216 ' :'' }"></i></span>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<br>
	<%@ include file="/front-end/member/footer.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/product/js/Product.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/product/js/LightBoxShow.js"></script>
	<script>
	
	<%
		String curPage = request.getRequestURI()+"?"+request.getQueryString();
	%>
	$("#shoppingcart").on("click", function () {
		<% 
			session.setAttribute("location", curPage);
 		%> 
	});
	
	
	</script>
</body>
</html>