<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<!-- <link rel="stylesheet" href="style.css"> -->
<link rel="stylesheet" href="https://unpkg.com/aos@2.3.1/dist/aos.css">
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap')
	;

:root { -
	-primary-color: #3a4052;
}

* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: "Open", sans-serif;
	line-height: 2.5;
	font-family: 'Noto Serif TC', serif;
}

a {
	text-decoration-color: none;
	color: var(- -primary-color);
}

h1 {
	font-size: 60px;
	font-weight: 300;
	line-height: 1.2;
	margin-bottom: 1.5px;
}

.video-container video {
	min-width: 100%;
	min-height: 100%;
	object-fit: cover;
	
}

.showcase {
	position: relative;
	height: 100vh;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	text-align: center;
	padding: 20px;
	color: white;
}

.video-container {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
	background: var(- -primary-color);
}

.content {
	z-index: 10;
}

.btn {
	display: inline-block;
	padding: 10px 30px;
	background: var(- -primary-color);
	color: #fff;
	border: 1px #fff solid;
	border-radius: 5px;
	margin-top: 25px;
	opacity: 0.7;
}

a.entrance:link, a.entrance:visited, a.entrance {
	text-decoration: none;
	color: white;
}

.btn:hover {
	transform: scale(0.95);
}

#about {
	padding: 40px;
	text-align: center;
}

#about p {
	font-size: 1.2rem;
	max-width: 600px;
	margin: auto;
}

#about h2 {
	margin: 30px 0;
	color: var(- -primary-color);
}

.social a {
	margin: 0 5px;
}

.social a i {
	color: black;
}

/* 宇宏覆寫 ===============================================================*/
video {
	width: 100%;
}

#social {
	margin-left: 850px;
}

h2.wordpage {
	margin-left: 875px;
}

i {
	color: rgb(250, 224, 107);
}
</style>
<title>Homepage</title>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<!-- <div class="col"></div> -->
			<div class="showcase col-12">
				<div class="video-container">
					<video src="<%=request.getContextPath()%>/video/dogInBeach.mp4" autoplay muted loop></video>
				</div>
				<div class="content">
					<a href="<%=request.getContextPath()%>/front-end/store/store.jsp" class="entrance">
						<h1>PETBOX</h1> <i class="fas fa-paw fa-3x" style="color: white;"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="container-fluid">
		<!-- 部落格 -->
		<div class="row">
			<div class="col-lg-6">
				<div data-aos="fade-right">
					<img src="<%=request.getContextPath()%>/photo/blog(1).png"
						style="width: 500px; margin-left: 230px;">
				</div>

			</div>
			<div class="col-lg-6">
				<p style="font-size: 30px; text-align: center;">部落格</p>
				<p style="background-color: orange;"></p>
				<p style="font-size: 25px; text-align: center;">
					志同道合的愛寵人，分享喜悅及日常的天地，最豐富最多樣內容，集合寵奴們嘔心瀝血之作，點閱率居高不下，偶像名人也紛紛加入這股痞客邦PIXNET
					風潮，你也可以創造一個個人專屬創作的天地，上傳圖片，並且能分享資訊。</p>
			</div>
		</div>
		<br>
	</div>
	<hr>
	<!-- 店家 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6">
				<p style="font-size: 30px; text-align: center;">店家</p>
				<p style="font-size: 25px; text-align: center;">日常所需的各類寵物實體店家<br>寵物餐廳、寵物旅館、寵物美容、寵物學校、寵物醫院
				</p>
			</div>
			<div class="col-lg-6">
				<div data-aos="fade-left">
					<img src="<%=request.getContextPath()%>/photo/store.png"
						style="width: 750px; margin-left: 85px;">
				</div>

			</div>
		</div>
	</div>


	<br>
	<hr>
	<!-- 公益 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6">
				<div data-aos="fade-down-right">
					<img src="<%=request.getContextPath()%>/photo/donation.jpg"
						style="width: 500px; margin-left: 200px;">
				</div>
			</div>
			<div class="col-lg-6">
				<p style="font-size: 30px; text-align: center;">公益</p>
				<p style="font-size: 25px; text-align: center;">
					因為堅持愛心不偏心，因此除了狗狗、貓咪、一般常見的寵物外，本網站關注寵物方面的問題，其他許多被關注和解決的寵物議題，這些需要被看見、被重視並且獲得幫助，本網站提供捐款跟認養功能。
				</p>
			</div>
		</div>
	</div>
	<br>
	<hr>
	<!-- 商家 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6">
				<p style="font-size: 30px; text-align: center;">商家</p>
				<p style="font-size: 25px; text-align: center;">各種商品應有盡有，寵物愛好者在網路上的最佳選擇，商品多元，讓你愛不釋手的購物商城。
				</p>
			</div>
			<div class="col-lg-6">
				<div data-aos="fade-down-left">
					<img src="<%=request.getContextPath()%>/photo/E-commerce.png"
						style="width: 760px; margin-left: 85px;">
				</div>

			</div>
		</div>
	</div>
	<hr>
	<h2 class="wordpage">平台社群連結</h2>
	<div class="social" id="social">
		<a href="https://twitter.com/home" target="_blank"><i
			class="fab fa-twitter fa-3x"></i></a> <a href="https://www.facebook.com/"
			target="_blank"><i class="fab fa-facebook fa-3x"></i></a> <a
			href="https://github.com/" target="_blank"><i
			class="fab fa-github fa-3x"></i></a> <a
			href="https://www.linkedin.com/feed/" target="_blank"><i
			class="fab fa-linkedin fa-3x"></i></a>
	</div>

	<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script>
		$(function () {
		    AOS.init({
		        duration: 400,
		    });
		})
		</script>
</body>

</html>