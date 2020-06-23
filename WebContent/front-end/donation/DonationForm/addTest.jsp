<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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
<title>Insert title here</title>
<style>

body{
overflow-x: hidden;
}
div.bs-example {
	margin: 100px;
}

div.bac {
 	height: 1000px;  
	background-repeat: no-repeat;
 	background-position: center; 
	background-size: contain
}

div.container{
}
  
    span.errorwh , span.errorpay , span.espan{
    margin-left: 10px;
        color:red;
    
    }
    div.row1{
    position: relative;
    top: 100px;
    right: 300px;
    }
</style>


</head>
<body>
<div class="container" >
<div class="row1">
<i class="fas fa-bone"></i>
<a href="<%=request.getContextPath()%>/front-end/donation/Npo/Allnpo.jsp">公益團體捐款</a><span class="nored"> > 捐款表單</span>
</div>
</div>
<div class="container">
		<div class="row bac justify-content-start align-items-center" style="background-image:url('<%=request.getContextPath()%>/resources/images/box.png')">
	
	<div class="container" >
		<div class="row justify-content-center">
				<div class="bs-example ">

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/DonationForm/donationform.do" name="form1">
						<div class="divforp">
							<div class="form-group">
			      <p class="p0">${npo_infoVO.npo_name}:	<input class="i0"type="TEXT" name="npo_id" size="45" value="${npo_infoVO.npo_id}" readonly="readonly" style="border-style:none"/></p>
							</div>
							<div class="form-group">
				      <p class="p1">捐款人:<input class="i1" type="TEXT" name="donator_name" size="45"/><span class="espan">${errors.donator_name}</span></p>
							</div>
							<div class="form-group">
								 <p class="p2">聯絡電話:<input class="i2" type="TEXT" name="donator_phone_num" size="45"/><span class="espan">${errors.donator_phone_num}</span></p>
							</div>
							<div class="form-group">
						      <p class="p3">付款金額:<input class="i3" type="TEXT" name="donation_money" size="45" /><span class="espan">${errors.donation_money}</span></p>
							</div>
							<div class="form-group">
			      <p class="p4">付款方式:<input type="radio" name="payment" value="信用卡"size="45"style="margin-left:90px;"/>信用卡<input type="radio" name="payment" value="第三方支付"size="45" style="margin-left:90px"/>第三方支付<span class="errorpay">${errors.payment}</span></p>
							</div>
							<div class="form-group">
								<p class="p5">收據領取付款方式:<input type="radio" name="onwhitch"  onclick="RadioCheck()" id="yes" value="電子郵件"size="45" style="margin-left:26px;"/>電子郵件<input type="radio" onclick="RadioCheck()" name="onwhitch" id="no" value="住家地址"size="45"
	      						style="margin-left:74px;"/>住家地址<span  class="errorwh">${errors.onwhitch}</span></p>
							</div>
							<div class="form-group">
								      <input class="p6" type ="text"id="tt" name="receipt_type" style="display:none;"/>
	      							  <input class="p6"type ="text" id="cb" name="receipt_type" style="display:none;"/>
							</div>
						      <input type="hidden" name="action" value="insert">
	      					  <input class="p7" type="submit"  value="送出新增">
						</div>
					</FORM>
				</div>
			</div>
		</div>
	</div>
</div>
	

<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/DonationForm/donationform.do" name="form1"> --%>
<%-- 	<div style="background-image:url('<%=request.getContextPath()%>/resources/images/box.png')"> --%>
<%-- 	      <p class="p0">${npo_infoVO.npo_name}:	<input class="i0"type="TEXT" name="npo_id" size="45" value="${npo_infoVO.npo_id}" readonly="readonly" style="border-style:none"/></p> --%>
	
<%-- 	      <p class="p1">捐款人:<input class="i1" type="TEXT" name="donator_name" size="45"/><span class="error">${errors.donator_name}</span></p> --%>

<%-- 	      <p class="p2">聯絡電話:<input class="i2" type="TEXT" name="donator_phone_num" size="45"/><span class="error">${errors.donator_phone_num}</span></p> --%>
	
<%-- 	      <p class="p3">付款金額:<input class="i3" type="TEXT" name="donation_money" size="45" /><span class="error">${errors.donation_money}</span></p> --%>
	
<%-- 	      <p class="p4">付款方式:<input type="radio" name="payment" value="信用卡"size="45"style="margin-left:90px;"/>信用卡<input type="radio" name="payment" value="第三方支付"size="45" style="margin-left:90px"/>第三方支付<span class="errorpay">${errors.payment}</span></p> --%>
	
<!-- 	      <p class="p5">收據領取付款方式:<input type="radio" name="onwhitch"  onclick="RadioCheck()" id="yes" value="電子郵件"size="45" style="margin-left:26px;"/>電子郵件<input type="radio" onclick="RadioCheck()" name="onwhitch" id="no" value="住家地址"size="45" -->
<%-- 	      style="margin-left:74px;"/>住家地址<span  class="errorwh">${errors.onwhitch}</span> --%>
<!-- 	      </p> -->
<!-- 	      <input class="p6" type ="text"id="tt" name="receipt_type" style="display:none;"/> -->
<!-- 	      <input class="p6"type ="text" id="cb" name="receipt_type" style="display:none;"/> -->
	
<!-- 	      <input type="hidden" name="action" value="insert"> -->
<!-- 	      <input class="p7" type="submit"  value="送出新增"> -->
	    
<!-- 	</div> -->
<!-- </FORM> -->
  <script src="https://smtpjs.com/v3/smtp.js"></script>   
  <script src="./vendors/jquery/jquery-3.4.1.min.js"></script>
  
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