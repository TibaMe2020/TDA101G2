<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>Insert title here</title>

</head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
<style>
      @import url(https://fonts.googleapis.com/css?family=Open+Sans:300);

      * {
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
      }

      body {
        height:100vh;
        font-family: 'Open Sans', Helvetica, Arial, sans-serif;
        font-weight: 300;
        letter-spacing: 0.05em;
        /* background-image: linear-gradient(135deg, rgba(155, 89, 182,0.8) 0%,rgba(211, 84, 0,0.8) 100%), url("https://i.epochtimes.com/assets/uploads/2019/12/pets-3715733_1280-600x400.jpg"); */
        /* background-image: linear-gradient(35deg,rgba(255, 227, 132,0.8) 0%,rgba(135, 206, 235, 0.7) 70%), url("https://i.epochtimes.com/assets/uploads/2019/12/pets-3715733_1280-600x400.jpg"); */
        background-image: linear-gradient(rgba(255, 192, 203,0.8) 0%,rgba(135, 206, 235, 0.7) 70%), url("https://i.epochtimes.com/assets/uploads/2019/12/pets-3715733_1280-600x400.jpg");

        background-repeat: no-repeat;
        background-position: center center;
        background-attachment: fixed;
        background-size: cover;
        margin:0;
      }

      h1 {
        font-size: 1.5em;
        padding: 0.5em;
        text-align: center;
        font-weight:300;
        color:#444;
        background:rgba(255,255,255,0.2);
        border-radius:5px;
        margin-bottom:40px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
      }

      .acc {
        .dl {
          box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
          &:after {
            content: "";
            display: block;
            height: 1em;
            width: 100%;
            background-color: #2980b9;

          }
        }

        dt > a {
          text-align: center;
          font-weight: 100;
          padding: 2em;
          display: block;
          text-decoration: none;
          color: #fff;
          -webkit-transition: background-color 0.5s ease-in-out;
        }

        dd {
          background-color: #fff;
          font-size: 1em;
          line-height: 1.5em;
          background-image: linear-gradient(to bottom, #444 0%, #fff 100%);
          -webkit-background-clip: text;
          -webkit-text-fill-color: transparent;
          > p {
            padding: 1em 2em 1em 2em;
          }
        }
        position: relative;
        background-color: #fff;
      }

      .container {
        max-width: 960px;
        margin: 0 auto;
        padding:40px 0 0 0;
      }

      .acc_title {
        /* background-color: #3498db; */
        /* color: aliceblue; */
        color: black;
        text-decoration:none;
        border-bottom: 1px solid #258cd1;
        &:before {
          content: "+";
          font-size: 1.5em;
          line-height: 0.5em;
          float: left;
          transition: transform 0.3s ease-in-out;
        }
        &:hover {
          background-color: #2980b9;
        }
      }

      .acc_title_active {
        background-color: #2980b9;
        color: white;
        &:before {
          transform: rotate(-225deg);

        }
      }

      .acc_panel {
        height: auto;
        overflow: hidden;
      }

      @media all {
        .acc_panel {
          max-height: 50em;
          transition: max-height 1s;
        }
      }

      @media screen and (min-width: 48em) {
        .acc_panel {
          max-height: 15em;
          transition: max-height 0.5s;
        }
      }

      .acc_panel_col {
        max-height: 0;
      }

      .anim_in {
        animation-name: acc_in;
        animation-duration: 0.65s;
        animation-iteration-count: 1;
        animation-direction: normal;
        animation-timing-function: ease-in-out;
        animation-fill-mode: both;
        animation-delay: 0s;
      }

      .anim_out {
        animation-name: acc_out;
        animation-duration: 0.75s;
        animation-iteration-count: 1;
        animation-direction: alternate;
        animation-timing-function: ease-in-out;
        animation-fill-mode: both;
        animation-delay: 0s;
      }


      @keyframes acc_in {
        0% {
          opacity: 0;
          transform: scale(0.8);
        }
        100% {
          opacity: 1;
          transform: scale(1);
        }
      }


      @keyframes acc_out {
        0% {
          opacity: 1;
          transform: scale(1);
        }
        100% {
          opacity: 0;
          transform: scale(0.8);
        }
      }
      /*改問題條顏色 先加class給他們所屬的標籤再一次加*/
      .dt1{
        background-color: #2980b9;
      }
         div.row1{ 
    position: relative; 
	top: 31px;
    left: -289px;
    }
    </style>
<body>
<div class="container" >
		<div class="row1" >
			<i class="fas fa-bone"></i>
			<a href="<%=request.getContextPath()%>/front-end/donation/myMain/donation.jsp">公益首頁</a><span> > Q&A問題專區</span>	
</div>
</div>
<div class="container">
          <h1>公益Q&A專區</h1>
          <div class="acc">
            <dl>
              <dt class="dt1"><a class="acc_title" href="#">🔍 Q1 如果沒有信用卡想捐贈怎麼辦 ? </a></dt>
              <dd class="acc_panel acc_panel_col">
                <p>那就先不要捐,等有信用卡的時候再捐
                </p>
              </dd>
              <dt class="dt1"><a href="#" class="acc_title">🔍 Q2 我捐款了,但我沒有會員,可以知道自己的捐款紀錄嗎 ?</a></dt>
              <dd class="acc_panel acc_panel_col">
                <p>點選公益首頁的選單,有個捐款查詢,輸入捐款人姓名與電話號碼,則可查詢自己的捐款紀錄</p>
              </dd>
              <dt class="dt1"><a href="#" class="acc_title">🔍 Q3 如果捐款資料填錯地址或是電話該怎麼辦 ?</a></dt>
              <dd class="acc_panel acc_panel_col">
                <p>可以點選右下角的客服人員中心,他們會解決你的問題 </p>
              </dd>
              <dt class="dt1"><a class="acc_title" href="#">🔍 Q4 會員在公益這方面有提供什麼功能 ? </a></dt>
              <dd class="acc_panel acc_panel_col">
                <p>會員在捐款的時候能紀錄資料在會員中心,所捐贈的公益團體發表募款成果時也會被通知
                </p>
              </dd>
            </dl>
          </div>
        </div>

  <script src="<%=request.getContextPath()%>/front-end/donation/JS/test3.js"></script>

</body>
</html>