<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Footer -->
  <footer>
    <div class="container-fluid">
      <div class="row footer">
        <div class="col-sm-12 col-md-3 align-self-center">
          <div>
            <span class="logo-text" id="footer-logo">PETBOX</span>
          </div>
        </div>
        <div class="col-sm-12 col-md-2 footer-col">
          <ul class="list-group footer-info">
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/">主頁</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/product/product.jsp">商城</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/store/store.html">店家</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp">部落格</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/donation/donation.jsp">公益</a>
            </li>
          </ul>
        </div>
        <div class="col-sm-12 col-md-2 footer-col">
          <ul class="list-group footer-info">
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/store/instruction.html?type=hospital">寵物醫院</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/store/instruction.html?type=school">寵物學校</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/store/instruction.html?type=restaurant">寵物餐廳</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/store/instruction.html?type=hostel">寵物旅館</a>
            </li>
            <li class="list-group-item">
              <a href="<%=request.getContextPath()%>/front-end/store/instruction.html?type=grooming">寵物美容</a>
            </li>
          </ul>
        </div>
        <div class="col-sm-12 col-md-2 footer-col">
          <ul class="list-group footer-info">
            <li class="list-group-item">張琳琳</li>
            <li class="list-group-item">楊紫茵</li>
            <li class="list-group-item">黃宇宏</li>
            <li class="list-group-item">陳惟揚</li>
            <li class="list-group-item">廖豐民</li>
          </ul>
        </div>

        <div class="col-sm-12 col-md-3 footer-col footer-col-blue">
          <ul class="list-group footer-info">
            <li class="list-group-item text-left">聯絡資訊</li>
            <li class="list-group-item text-left">服務專線：(03)425-8183</li>
            <li class="list-group-item text-left">寵物盒子股份有限公司</li>
            <li class="list-group-item text-left">地址：新北市汐止區新台五路一段100號</li>
            <li class="list-group-item padding-bottom">
              <div class="d-flex">
                <a class="flex-fill d-flex" href="https://twitter.com/home" target="_blank">
                  <i class="fab fa-twitter social-media-icon"></i></a>
                <a class="flex-fill d-flex" href="https://www.facebook.com/" target="_blank">
                  <i class="fab fa-facebook social-media-icon"></i></a>
                <a class="flex-fill d-flex" href="https://github.com/" target="_blank">
                  <i class="fab fa-github social-media-icon"></i></a>
                <a class="flex-fill d-flex" href="https://www.linkedin.com/feed/" target="_blank">
                  <i class="fab fa-linkedin social-media-icon"></i></a>
                <a class="flex-fill d-flex" href="https://www.google.com.tw/" target="_blanl">
                  <i class=" fab fa-google social-media-icon"></i></a>
                <a class="flex-fill d-flex" href="https://www.instagram.com/?hl=zh-tw">
                  <i class="fab fa-instagram social-media-icon"></i></a>
              </div>
            </li>
          </ul>
        </div>
        <div class="col-12 footer-copyright text-center py-3">© 2020 Copyright:
          <a href="#">Petbox.com</a>
        </div>
      </div>
    </div>
  </footer>
  
  <!-- bootstrap4, jquery -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous">
	</script>
	
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous">
	</script>
	<script
		src="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.zh.js"></script>
		
	<script>
	<% if (memberVO == null) { %>
		$(function() {
			if($('input#birthday').length !== 0){
				$('input#birthday').datepicker({
					dateFormat : 'yyyy-mm-dd',
					todayHighlight : true,
					language : 'zh',
					maxDate : new Date(),
					autoClose : true
			})
			if(${not empty new_member.birthday ? 'true' : 'false'}) {
				$('input#birthday').data('datepicker').selectDate(new Date("${new_member.birthday}"));
			} else if(${not empty memberVO.birthday ? 'true' : 'false'}) {
				$('input#birthday').data('datepicker').selectDate(new Date("${memberVO.birthday}"));
			}
		  $('div.datepicker').css('z-index', 1100);
		  }
		})
	<%} else { %>
		let notibell = $('.fa-bell');
		let notis = $('.notifications');
		
		function notiRead() {
			$.ajax({
				  url: "<%=request.getContextPath()%>/notification/controller",           
				  type: "post",
				  data: {
					  action : "update",
					  member_id : "${memberVO.member_id}"
				  },               
				  dataType: "text",             
				  timeout: 0,  
				  success: function(data){   
					  console.log(data)
				  },
				  error: function(xhr){    
				    console.log(xhr);
				  }
			})
		}
		
		if($(notibell).length !== 0) {
			$(notibell).on("click", function() {
				notiRead();				
				$('#noti-dot').text('');
			})
		}
		
		if($(notis).length !== 0) {
			$(notis).on("click", function() {
				notiRead();				
			})
		}
		
		if(${not empty errorMsgs.loginEmail ? 'true' : 'false'} || 
			${not empty errorMsgs.loginPassword ? 'true' : 'false'} ||
			${not empty errorMsgs.loginAccount ? 'true' : 'false'}) {
			$('#login-modal').click();
		} else if (${not empty errorMsgs.loginEmail ? 'true' : 'false'} ||
			${not empty errorMsgs.signupName ? 'true' : 'false'} ||
			${not empty errorMsgs.signupEmail ? 'true' : 'false'} ||
			${not empty errorMsgs.signupPhone_num ? 'true' : 'false'} ||
			${not empty errorMsgs.signupPassword ? 'true' : 'false'} ||
			${not empty errorMsgs.signupAddress ? 'true' : 'false'}
		) {
			$('#login-modal').click();
			$('#signup-link').click();
		}
	<%}%>
	</script>