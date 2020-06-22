<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page
	import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Update info</title>
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
<!-- air datepicker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/updateInfo.css">
</head>

<body>
	<%@ include file="header.jsp"%>

	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-2">
				<%@ include file="sidebar.jsp"%>
			</div>
			<!-- Main content -->
			<div class="col-9">
				<div class="content-title">修改資料</div>
				<div class="content-container">
					<form action="<%=request.getContextPath()%>/member/controller" method="post" class="main-form"
						enctype="multipart/form-data">

						<div class="row justify-content-around">
							<div class="col-lg-4 text-left">
								<div class="form-group">
									<label for="name">姓名</label> <input type="text" name="name"
										id="name" value="${memberVO.name}"
										class="form-control ${not empty errorMsgs.updateName ? 'is-invalid' : ''} ">
									<div
										class="${not empty errorMsgs.updateName ? 'invalid-feedback' : ''}">
										${empty errorMsgs.updateName ? '' : errorMsgs.updateName}</div>
									<div class="invalid-feedback">無效</div>
									<div class="valid-feedback">有效</div>
								</div>
								<div class="form-group">
									<label for="phone_num">電話號碼</label> <input type="text"
										name="phone_num" id="phone_num" value="${memberVO.phone_num}"
										class="form-control ${not empty errorMsgs.updatePhone_num ? 'is-invalid' : ''}">
									<div
										class="${not empty errorMsgs.updatePhone_num ? 'invalid-feedback' : ''}">
										${empty errorMsgs.updatePhone_num ? '' : errorMsgs.updatePhone_num}</div>
								</div>

								<div class="form-group">
									<label for="address">地址</label> <input type="text"
										name="address" id="address" value="${memberVO.address}"
										class="form-control ${not empty errorMsgs.updateAddress ? 'is-invalid' : ''}">
									<div
										class="${not empty errorMsgs.updateAddress ? 'invalid-feedback' : ''}">
										${empty errorMsgs.updateAddress ? '' : errorMsgs.updateAddress}</div>
								</div>

								<div class="form-group">
									<label for="birthday">出生日期</label> <input type="text"
										placeholder="yyyy-mm-dd" name="birthday" id="birthday"
										class="form-control datepicker-here" data-position="top right" />
								</div>

								<div class="form-group">
									<label for="sex">性別</label> 
									<select name="sex" id="sex" class="form-control">
										<option value="">選項...</option>
										<option value="男" ${(memberVO.sex=="男" ) ? 'selected' :'' }>男性</option>
										<option value="女" ${(memberVO.sex=="女" ) ? 'selected' :'' }>女性</option>
										<option value="其他" ${(memberVO.sex=="其他" ) ? 'selected' :'' }>其他</option>
									</select>
								</div>

								<label for="profile_image">頭像照片</label> <input type="file"
									id="profile_image">
									<input name="profile_image" type="hidden" id="hidden-image">
								<div class="d-flex justify-content-around flex-wrap"
									id="profile_image_container"></div>
							</div>
							<div class="col-lg-4 text-left">



								<div class="form-group">
									<label for="password">舊密碼</label>
									 
									<input type="password" name="password" id="password"
										class="form-control ${not empty errorMsgs.password ? 'is-invalid' : ''}">
									<div class="${not empty errorMsgs.password ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.password ? '' : errorMsgs.password}</div>
									<div class="invalid-feedback">無效</div>
									<div class="valid-feedback">有效</div>
								</div>
								<div class="form-group">
									<label for="new_password">新密碼</label>
									
									<input type="password" name="new_password" id="new_password"
										class="form-control ${not empty errorMsgs.new_password ? 'is-invalid' : ''}">
									<div class="${not empty errorMsgs.new_password ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.new_password ? '' : errorMsgs.new_password}</div>
								</div>
								<div class="form-group">
									<label for="confirm">確認密碼</label>
									<input type="password" name="confirm" id="confirm"
										class="form-control ${not empty errorMsgs.new_password ? 'is-invalid' : ''}">
									<div class="${not empty errorMsgs.new_password ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.new_password ? '' : errorMsgs.new_password}</div>
								</div>

								<input type="hidden" name="action" value="update_info">
								<button type="submit" class="btn btn-primary">更新</button>
							</div>
						</div>

					</form>



				</div>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
	<script src="https://unpkg.com/filepond/dist/filepond.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-image-exif-orientation/dist/filepond-plugin-image-exif-orientation.js">
  </script>
	<script
		src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-image-resize/dist/filepond-plugin-image-resize.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-image-transform/dist/filepond-plugin-image-transform.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-image-crop/dist/filepond-plugin-image-crop.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.js">
  </script>
  <script src="https://unpkg.com/filepond-plugin-file-encode/dist/filepond-plugin-file-encode.js"></script>
	<script>

	let host = window.location.host;
	let path = window.location.pathname;
	let webCtx = path.substring(0, path.indexOf('/', 1));
	let projectPath = "http://" + host + webCtx;
	console.log(projectPath);


	FilePond.registerPlugin(
	  FilePondPluginImagePreview,
	  FilePondPluginImageResize,
	  FilePondPluginImageTransform,
	  FilePondPluginFileValidateType,
	  FilePondPluginImageCrop,
	  FilePondPluginImageExifOrientation,
	  FilePondPluginFileEncode
	)
	
	const inputElement = document.querySelector('input[id="profile_image"]');
	let profile_image_id;
	const pond = FilePond.create(inputElement, {
	  labelIdle: '上傳圖片',
	  allowImagePreview: false,
	  acceptedFileTypes: ['image/*'],
	  imageResizeTargetWidth: 190,
	  imageResizeMode: 'contain',
	  maxFiles: 1,
	  onpreparefile: (file, output) => {
	    const holder = $('div#profile_image_container');
	    let url = URL.createObjectURL(output);
	    profile_image_id = file.id;
	    $(holder).html(
	    '<div class="profile_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white" id="' + file.id +'"><img src="'+ url + 
	    '"></div>');
	    let base64 = file.getFileEncodeBase64String();
	    $('#hidden-image').val(base64);
	  },
	  onremovefile: (error, file) => {
// 	    console.log("this is file id: " + file.id);
	    let id = file.id;
	    const img = $("div#"+id);
	    $('#hidden-image').val('');
	    $(img).remove();
	  },
	  oninit : () => {
		  const holder = $('div#profile_image_container');
		   $(holder).html('<div class="profile_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded""><img src="' + projectPath  + '/member/profileImage?member_id=${memberVO.member_id}" style="width:190px;"></div>');
	  }
	});
	</script>
</body>

</html>