<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
  @import url('https://netdna.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.css'); /*icon的網址*/
  @import url(https://fonts.googleapis.com/css?family=Lato:400,300,700);

    html {
       height: 100%;
    }

    body {
    /* Remember to use the other versions for IE 10 and older browsers! */
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100%;
    font-family: 'lato', sans-serif;
    color: #fff;
    /* background: #FFFFCE; /*黑*/ */ */
    /* background: #16222A; /* fallback for old browsers */ */
    /* background: -webkit-linear-gradient(to top, #16222A , #3A6073); /* Chrome 10-25, Safari 5.1-6 */ */
    /* background: linear-gradient(to top, #16222A , #3A6073); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */ */
    }

    .container {
    	/* background:rgba(58,63,68,0.5); */
      background:rgba(37,84,115,0.4); /*透明框顏色*/
      border-radius: 5px;
      box-shadow: 0 1.5px 0 0 rgba(0,0,0,0.1);
      width:450px;
      display: flex;
      flex-direction: column;
    }

    .logo{
      font-family: "museo-slab";
      font-size:20px;
      text-align: center;
      padding: 20px 20px 0;
      margin:0;
    }

    .login-item {
    	color: #ffff;
    	padding:25px 25px 0;
    	margin: 20px 20px 0;
    	border-radius: 3px;
    }

    input {
      border: 0;
      color: inherit;
      font: inherit;
      margin: 0;
      outline: 0;
      padding: 0;
      -webkit-transition: background-color .3s;
      transition: background-color .3s;
    }

    .user:before {
      content: '\f007';
      font: 14px fontawesome;
    	color: #FFFFFF;
    }

    .lock:before {
      content: '\F095';
      font: 14px fontawesome;
    	color: #FFFFFF;
    }

    .form input[type="password"], .form input[type="text"], .form input[type="submit"] {
      width: 100%;
    }

    .form-login label,
    .form-login input[type="text"],
    .form-login input[type="password"],
    .form-login input[type="submit"] {
      border-radius: 0.25rem;
      padding: 1rem;
      color: #3A3F44;
    }

    .form-login label {
      background-color: #5BC0EF; /*#222222*/ logo底色
      border-bottom-right-radius: 0;
      border-top-right-radius: 0;
      padding-left: 1.25rem;
      padding-right: 1.25rem;
    }

    .form-login input[type="text"], .form-login input[type="password"] {
      background-color: #ffffff;
      border-bottom-left-radius: 0;
      border-top-left-radius: 0;
    }
    .form-login input[type="text"]:focus, .form-login input[type="text"]:hover, .form-login input[type="password"]:focus, .form-login input[type="password"]:hover {
      background-color: #eeeeee;
    }
    .form-login input[type="submit"] {
      background-color: #5BC0EF;
      color: #eee;
      font-weight: bold;
      text-transform: uppercase;
    }
    .form-login input[type="submit"]:focus, .form-login input[type="submit"]:hover {
      background-color: #128BC3;
    }
    .form-field {
      display: -webkit-box;
      display: -webkit-flex;
      display: -ms-flexbox;
      display: flex;
      margin-bottom: 2rem;
    }


    .hidden {
      border: 0;
      clip: rect(0 0 0 0);
      height: 1px;
      margin: -1px;
      overflow: hidden;
      padding: 0;
      position: absolute;
      width: 1px;
    }

    .text--center {
      text-align: center;
    }
    span.error{
    color:red;
    }
   
  
</style>

</head>


<body>

    <script src="https://use.typekit.net/rjb4unc.js"></script>
    <script>try{Typekit.load({ async: true });}catch(e){}</script>


    <div class="container">
        <div class="logo">查 詢 捐 款 紀 錄</div>
        <div class="login-item">
        
        
          <form action="<%=request.getContextPath()%>/DonationForm/donationform.do" method="post" class="form form-login">
            <div class="form-field">
              <label class="user" for="login-username"><span class="hidden">Username</span></label>
<!--               <input id="login-username" type="text" class="form-input" name="donator_name" placeholder="請輸入捐款姓名" required> -->
                   <input id="login-username" type="text" class="form-input" name="donator_name" placeholder="請輸入捐款姓名">
                   <span class="error">${errors.donator_name}</span>
                   <input type="hidden" name="action" value="getOne_For_Display">
           
            </div>

            <div class="form-field">
              <label class="lock" for="login-password"><span class="hidden">Password</span></label>
              <input id="login-password" type="password" class="form-input" name="donation_phone_num" placeholder="請輸入電話號碼">
              <span class="error">${errors.donation_phone_num}</span>
                  <input type="hidden" name="action" value="getOne_For_Display">
          
            </div>

            <div class="form-field">
<!--               <input type="hidden" name="action" value="getOne_For_Display"> -->
              <input type="submit" value="查詢" >
            </div>
          </form>
            <div class="form form-login">
            <div class="form-field">
              <a href="<%=request.getContextPath()%>/front-end/donation/myMain/donation.jsp"><input type="submit" value="返回" ></a>
            </div>
            </div>
        </div>
    </div>
  </body>
</body>
</html>