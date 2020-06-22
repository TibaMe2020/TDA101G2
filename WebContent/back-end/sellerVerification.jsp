<%@page import="com.google.gson.JsonPrimitive"%>
<%@page import="com.google.gson.JsonDeserializer"%>
<%@page import="com.google.gson.JsonSerializer"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page
	import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>
<%@page import="com.admin.model.AdminVO"%>

<%@page import="com.admin.model.AdminService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Seller Verification</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link href="https://unpkg.com/filepond/dist/filepond.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/sellerVerification.css">
</head>

<body>

	<%
		MemberService mbSvc = new MemberService();
		List<MemberVO> applicants = mbSvc.getApplicants();
		pageContext.setAttribute("applicants", applicants);

		Type listType = new TypeToken<List<MemberVO>>(){}.getType();
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd");
		//byte[] to base64
		builder.registerTypeAdapter(byte[].class, 
				(JsonSerializer<byte[]>) (src, t, ctx) -> 
		new JsonPrimitive(Base64.getEncoder().encodeToString(src)));
		
		//base64 to byte[]
		builder.registerTypeAdapter(byte[].class, 
				(JsonDeserializer<byte[]>) (json, t, ctx) ->
		Base64.getDecoder().decode(json.getAsString()));
		Gson gson = builder.create();

		String json = gson.toJson(applicants, listType); 
		pageContext.setAttribute("json", json);
		
	%>

	<header class="bg-white">
		<div class="container-fluid">
			<div class="row header">
				<div
					class="col-2 d-flex align-items-center justify-content-center border-right border-bottom">
					<img src="<%=request.getContextPath()%>/resources/images/admin.svg"
						alt="admin"> &nbsp;
					<h5 class="text-center admin-tab">Admin</h5>
				</div>
				<div
					class="col-10 d-flex align-items-center justify-content-center border-bottom">
					<h3 class="title-tab">賣家審核</h3>
				</div>
			</div>
		</div>

	</header>

	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-2">
				<%@ include file="sidebar.jsp"%>
			</div>
			<div class="table-container col-9">
				<table class="table table-fluid" id="myTable">
					<thead>
						<tr>
							<th>會員編號</th>
							<th>會員信箱</th>
							<th>加入時間</th>
							<th>申請時間</th>
							<th>審核</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${applicants}">
							<tr>
							<td class="member_id">${member.member_id}</td>
							<td>${member.email}</td>
							<td><fmt:formatDate value="${member.create_time}" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${member.application_time}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><a href="#verify" data-toggle="modal"
								class="btn btn-dark modal-link verify">審核</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div id="verify" class="modal fade">
				<div class="modal-dialog modal-lg modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header d-flex justify-content-center">
							<div class="d-flex flex-grow-0">
								<button type="button" class="close invisible"
									data-dismiss="modal" aria-hidden="true">&times;</button>
							</div>
							<div class="d-flex flex-grow-1 justify-content-center">
								<h4 class=" modal-title">審核資料</h4>
							</div>
							<div class="d-flex flex-grow-0">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
						</div>

						<div class="modal-body">
							<div class="container-fluid">
								<div class="row justify-content-center">
									<div class="col-6">
										<ul class="list-group d-flex justify-content-around">
											<li class="list-group-item order-detail-info">姓名: <span
												id="name"></span></li>
											<br>
											<li class="list-group-item order-detail-info">電話: <span
												id="phone_number"></span></li>
											<br>
											<li class="list-group-item order-detail-info">地址: <span
												id="address"></span></li>
											<br>
											<li class="list-group-item order-detail-info">電子郵件: <span
												id="email"></span>
											</li>
											<br>
											<li class="list-group-item order-detail-info">身分證字號: <span
												id="id_number"></span></li>
											<br>
											<li class="list-group-item order-detail-info">銀行帳戶: <span
												id="bank_account"></span>
											</li>
										</ul>
									</div>
									<div class="col-6">
										<ul class="list-group d-flex justify-content-around">
											<li class="list-group-item order-detail-info">文件照片:
												<div id="document-image-holder">
													<img src="" id="document_image">
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<div class="modal-footer border-0">
							<div class="container-fluid">
								<div class="row justify-content-center">
									<div class="col-3 d-flex justify-content-center">
										<form action="<%=request.getContextPath()%>/member/controller"
											method="POST">
											<input type="hidden" name="member_id" value="" class="member_id"> 
												<input type="hidden" name="pass" value="succeed"> 
												<input type="hidden" name="action" value="seller_verification"> 
												<input type="submit" class="btn btn-dark" value="通過">
										</form>
									</div>
									<div class="col-3 d-flex justify-content-center">
										<form action="<%=request.getContextPath()%>/member/controller"
											method="POST">
											<input type="hidden" name="member_id" value="" class="member_id"> 
											<input type="hidden" name="pass" value="fail"> 
											<input type="hidden" name="action" value="seller_verification"> 
											<input type="submit" class="btn btn-light" value="未通過">
										</form>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>


		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous">
  </script>
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
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js">
  </script>

	<script>
		
    $(document).ready(function () {
      $('#myTable').DataTable({
        searching: false,
        lengthChange: false,
        info: false,
        "language": {
          "paginate": {
            "previous": "上一頁",
            "next": "下一頁"
          }
        }
      });

      let host = window.location.host;
      let path = window.location.pathname;
      let webCtx = path.substring(0, path.indexOf('/', 1));
      
      let applicants = JSON.parse('${json}');
		
		
      
      $('.verify').on('click', function () {
        let member_id = $(this).closest('tr').find('.member_id').text();
        for (let a of applicants) {
          if (a.member_id === member_id) {
        	  $('input.member_id').val(a.member_id);
            $('#name').text(a.name);
            $('#address').text(a.address);
            $('#email').text(a.email);
            $('#phone_number').text(a.phone_num);
            $('#id_number').text(a.id_number);
            $('#bank_account').text(a.bank_account);
            if(typeof a.document_image === 'undefined') {
            	$('#document-image-holder img').attr("alt", "未申請成為實體店家");
            	break;
            }
            $('#document-image-holder img').attr("src", "data:image/png;base64,"+a.document_image);
            break;
          }
        }
      })

    })
  </script>

</body>

</html>