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
  <title>Submit Application</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
  <!-- air datepicker -->
  <link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet">
  <link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/submitApplication.css">
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
        <div class="content-title">賣家申請表單</div>
        <div class="content-container">
          <form action="<%=request.getContextPath()%>/member/controller" method="post" 
          class="main-form" enctype="multipart/form-data">
            <div class="row justify-content-around">
              <div class="col-lg-4 text-left">
                <div class="form-group">
                  <label for="name">姓名</label>
                  <input type="text" name="name" id="name" value="${memberVO.name}"
                  class="form-control ${not empty errorMsgs.name ? 'is-invalid' : ''}" required>
                  <div class="${not empty errorMsgs.name ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.name ? '' : errorMsgs.name}</div>
                </div>
                <div class="form-group">
                  <label for="phone_num">電話號碼</label>
                  <input type="text" name="phone_num" id="phone_num" value="${memberVO.phone_num}"
                  class="form-control ${not empty errorMsgs.phone_num ? 'is-invalid' : ''}">
                  <div class="${not empty errorMsgs.phone_num ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.phone_num ? '' : errorMsgs.phone_num}</div>
                  <div class="invalid-feedback">無效</div>
                </div>

                <div class="form-group">
                  <label for="address">地址</label>
                  
                  <input type="text" name="address" id="address" value="${memberVO.address}"
                  class="form-control ${not empty errorMsgs.address ? 'is-invalid' : ''}">
                  <div class="${not empty errorMsgs.address ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.address ? '' : errorMsgs.address}</div>
                </div>

                <div class="form-group">
                  <label for="id_number">身分證字號</label>
                  
                  <input type="text" name="id_number" id="id_number" value="${memberVO.id_number}"
                  class="form-control ${not empty errorMsgs.id_number ? 'is-invalid' : ''} " required>
                  <div class="${not empty errorMsgs.id_number ? 'invalid-feedback' : 'valid-feedback'}">
                        ${empty errorMsgs.id_number ? '' : errorMsgs.id_number}</div>
                </div>
                <div class="form-group">
                  <label for="bank_account">銀行帳戶</label>
                  
                  <input type="text" name="bank_account" id="bank_account" value="${memberVO.bank_account}"
                  class="form-control ${not empty errorMsgs.bank_account ? 'is-invalid' : ''} " required>
                  <div class="${not empty errorMsgs.bank_account ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.bank_account ? '' : errorMsgs.bank_account}</div>
                </div>

                <div class="form-group">
                  <label for="password">密碼</label>
                  
                  <input type="password" name="confirm" id="password" 
                  class="form-control ${not empty errorMsgs.confirm ? 'is-invalid' : ''} " required>
                  <div class="${not empty errorMsgs.confirm ? 'invalid-feedback' : ''}">
                        ${empty errorMsgs.confirm ? '' : errorMsgs.confirm}</div>
                </div>

              </div>
              <div class="col-lg-4 text-left">

                <label for="document_image">文件照片</label>
                <input type="file" id="document_image">
                <input name="document_image" type="hidden" id="hidden-image">
                <div class="d-flex justify-content-around flex-wrap" id="document_image_container">
                </div>
                <p>

                  <b><i>如果要成為店家，提供實體服務的話才需要上傳文件照片</i></b>
                </p>
                <input type="hidden" name="action" value="submit_application">

                <button type="submit" class="btn btn-primary">送出</button>
              </div>
            </div>

          </form>



        </div>
      </div>
    </div>
  </div>

  <%@ include file="footer.jsp"%>

  <script src="https://unpkg.com/filepond/dist/filepond.js"></script>
  <script src="https://unpkg.com/filepond-plugin-image-exif-orientation/dist/filepond-plugin-image-exif-orientation.js">
  </script>
  <script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
  <script src="https://unpkg.com/filepond-plugin-image-resize/dist/filepond-plugin-image-resize.js"></script>
  <script src="https://unpkg.com/filepond-plugin-image-transform/dist/filepond-plugin-image-transform.js"></script>
  <script src="https://unpkg.com/filepond-plugin-image-crop/dist/filepond-plugin-image-crop.js"></script>
  <script src="https://unpkg.com/filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.js">
  </script>
  <script src="https://unpkg.com/filepond-plugin-file-encode/dist/filepond-plugin-file-encode.js"></script>
	<script>

	FilePond.registerPlugin(
	  FilePondPluginImagePreview,
	  FilePondPluginImageResize,
	  FilePondPluginImageTransform,
	  FilePondPluginFileValidateType,
	  FilePondPluginImageCrop,
	  FilePondPluginImageExifOrientation,
	  FilePondPluginFileEncode
	)

	const inputElement = document.querySelector('input[id="document_image"]');
	let profile_image_id;
	const pond = FilePond.create(inputElement, {
	  labelIdle: '上傳圖片',
	  allowImagePreview: false,
	  acceptedFileTypes: ['image/*'],
	  imageResizeTargetWidth: 290,
	  imageResizeMode: 'contain',
	  maxFiles: 1,
	  onpreparefile: (file, output) => {
	    // console.log("this is file id: " + file.id);
	    const holder = $('div#document_image_container');
	    let url = URL.createObjectURL(output);
	    profile_image_id = file.id;
	    $(holder).html('<div class="document_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white" id="'+ file.id +
	    '"><img src="'+ url +'"></div>');
	    let base64 = file.getFileEncodeBase64String();
	    $('#hidden-image').val(base64);
	  },
	  onremovefile: (error, file) => {
// 	    console.log("this is file id: " + file.id);
	    const img = $("div#" + file.id );
	    $('#hidden-image').val('');
	    $(img).remove();
	  }
	});
	</script>
</body>

</html>