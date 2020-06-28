<%@page import="com.google.gson.Gson"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.util.GsonGenerator"%>
<%@page import="com.product_version.model.Version_Service"%>
<%@page import="com.product_version.model.Version_VO"%>
<%@page import="com.product.model.Product_VO"%>
<%@page import="com.product.model.Product_Service"%>
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
<title>Product List</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/productList.css">
</head>

<body>

	<%@ include file="header.jsp"%>
	<%
		if (!"".equals(member_id)) {
			Product_Service pSvc = new Product_Service();
			List<Product_VO> pList = pSvc.getByMId(member_id);
			Version_Service vSvc = new Version_Service();
			for (Product_VO pVO : pList) {
				List<Version_VO> vList = vSvc.getbyProductID(pVO.getProduct_id());
				pVO.setVersions(vList);
			}
			pageContext.setAttribute("pList", pList);
			Type listType = new TypeToken<List<Product_VO>>() {
			}.getType();
			GsonGenerator g = new GsonGenerator();
			Gson gson = g.getGson();
			String json = gson.toJson(pList, listType);
			pageContext.setAttribute("json", json);
		}
	%>


	<div class="container-fluid">
		<div class="row content-height">
			<div class="col-2">
				<%@ include file="sidebar.jsp"%>
			</div>
			<div class="col-9">
				<div class="container-fluid">

					<div class="table-wrapper">
						<div class="table-title">
							<div class="row">
								<div class="col-6 text-left d-flex align-items-center">
									<h2 id="table-title-text">商品列表</h2>
								</div>
								<div class="col-6 d-flex align-items-center justify-content-end">
									<a href="#add-product" class="d-flex add-product-modal"
										data-toggle="modal"> <i
										class="fas fa-plus-circle add-product"></i>
									</a>
								</div>
							</div>
						</div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>
										<div class="d-flex justify-content-center">
											<a href="#" class="d-flex align-items-center">商品編號<i
												class="fas fa-sort"></i></a>
										</div>
									</th>
									<th>
										<div class="d-flex justify-content-center">
											<a href="#" class="d-flex align-items-center">商品名稱<i
												class="fas fa-sort"></i></a>
										</div>
									</th>
									<th>
										<div class="d-flex justify-content-center">
											<a href="#" class="d-flex align-items-center">創建日期<i
												class="fas fa-sort"></i></a>
										</div>
									</th>
									<th>
										<div class="d-flex justify-content-center">
											<a href="#" class="d-flex align-items-center">上/下架<i
												class="fas fa-sort"></i></a>
										</div>
									</th>
									<th>修改</th>
									<th>移除</th>
								</tr>
							</thead>
							<tbody id="product-table">
								<c:forEach items="${pList}" var="p">
									<tr>
										<td class="pid">${p.product_id}</td>
										<td>${p.name}</td>
										<td>${p.create_time}</td>
										<td>
											<div class="d-flex justify-content-center">
												<label class="switch d-flex align-self-center"> <input
													type="checkbox" name="product_state"
													${(p.product_state == 1) ? 'checked' : ''}> <span
													class="slider round" data-pid="${p.product_id}" data-state="${p.product_state}"></span>
												</label>
											</div>
										</td>
										<td>
											<div class="d-flex justify-content-center">
												<a href="#update-product" data-toggle="modal"
													class="product-update"> <i class="fas fa-pen"></i>
												</a>
											</div>
										</td>
										<td><a href="#remove-product" data-toggle="modal"
											class="product-remove product-id-remove"> <i class="fas fa-trash"></i>
										</a></td>
									</tr>

								</c:forEach>

							</tbody>
						</table>
					</div>

				</div>
				<!-- Remove Product-->
				<div id="remove-product" class="modal fade">
					<div class="modal-dialog modal-md modal-dialog-centered">
						<div class="modal-content">

							<div class="modal-header d-flex justify-content-center">
								<div class="d-flex flex-grow-0">
									<button type="button" class="close invisible"
										data-dismiss="modal" aria-hidden="true" disabled>&times;</button>
								</div>
								<div class="d-flex flex-grow-1 justify-content-center">
									<h4 class=" modal-title">移除商品</h4>
								</div>
								<div class="d-flex flex-grow-0">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
								</div>
							</div>

							<div class="modal-body">
								<div class="container-fluid">
									<div class="row">
										<div class="col-12">
											<p class="mb-0">
												<b><i>確定要移除此項商品?</i></b>
											</p>
										</div>
									</div>
								</div>
							</div>

							<div class="modal-footer border-0">
								<div class="container-fluid">
									<div class="row">
										<div class="col-6">
											<button type="button" class="btn btn-danger" id="remove">刪除</button>
										</div>
										<div class="col-6">
											<button type="button" class="btn btn-secondary" id="cancel"
												data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<!-- Add New Product  -->
				<div id="add-product" class="modal fade">
					<div class="modal-dialog modal-xl modal-dialog-centered">
						<div class="modal-content">
							<form action="" method="post" class="main-form"
								enctype="multipart/form-data" id="add-product-form">

								<div class="modal-header d-flex justify-content-center">
									<div class="d-flex flex-grow-0">
										<button type="button" class="close invisible"
											data-dismiss="modal" aria-hidden="true" disabled>&times;</button>
									</div>
									<div class="d-flex flex-grow-1 justify-content-center">
										<h4 class=" modal-title">新增商品</h4>
									</div>
									<div class="d-flex flex-grow-0">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
								</div>

								<div class="modal-body">

									<div class="row justify-content-around">
										<div class="col-lg-5 text-left">

											<div class="form-group">
												<label for="product_name">商品名稱</label> <input type="text"
													name="name" id="product_name" class="form-control" required>
											</div>

											<div class="form-group">
												<label for="product_type">商品類別</label> <select
													name="product_type" id="product_type" class="form-control"
													required>
													<option value="">選項...</option>
													<option value="食品">食品</option>
													<option value="服飾">服飾</option>
													<option value="住家">住家</option>
													<option value="用品">用品</option>
													<option value="玩具">玩具</option>
													<option value="其他">其他</option>
												</select>
											</div>

											<!-- <div class="form-group">
                        <label for="product_price">商品價格</label>
                        <input type="text" name="product_price" id="product_price" class="form-control">
                      </div> -->

											<label for="description">商品介紹</label> <input type="file"
												id="description">
											<div id="new-description"
												class="d-flex justify-content-around"></div>

											<label for="product_image">商品圖片</label> <input type="file"
												id="product_image" multiple>
											<div class="d-flex justify-content-around flex-wrap"
												id="new-product-image"></div>

										</div>
										<div class="col-lg-5 text-left">
											<div class="form-group">
												<label for="product_version">規格</label> <input type="text"
													name="product_version" id="product_version"
													class="form-control">
											</div>
											<div class="form-group">
												<label for="price">單價</label> <input type="text"
													name="price" id="price" class="form-control">
											</div>
											<div class="form-group">
												<label for="quantity">庫存</label> <input type="text"
													name="quantity" id="quantity" class="form-control">
											</div>
											<div class="form-group">
												<button type="button" class="btn btn-outline-success"
													id="add-product-version">新增</button>
											</div>

											<div>
												<table class="table">
													<thead>
														<tr>
															<th scope="col">規格</th>
															<th scope="col">單價</th>
															<th scope="col">庫存</th>
															<th scope="col">移除</th>
														</tr>
													</thead>
													<tbody id="product-version-table">

													</tbody>
												</table>

											</div>
											<input type="hidden" name="action" value="updateProduct">

										</div>
									</div>
								</div>
								<div class="modal-footer border-0">
									<div class="container-fluid">
										<div class="row justify-content-center">
											<div class="col-4">
												<!-- 正式版 -->
												<button type="button" class="btn btn-primary"
													id="add-product-btn">新增</button>
												<!-- 測試版 -->
												<!-- <button type="button" class="btn btn-primary" id="add-product-btn">新增</button> -->
											</div>
											<div class="col-4">
												<button type="button" class="btn btn-secondary"
													id="cancel-product">取消</button>
											</div>

										</div>
									</div>

								</div>
							</form>

						</div>
					</div>
				</div>

				<!-- Update Product -->
				<div id="update-product" class="modal fade">
					<div class="modal-dialog modal-xl modal-dialog-centered">
						<div class="modal-content">
							<form action="" method="post" class="main-form"
								enctype="multipart/form-data" id="update-product-form">

								<div class="modal-header d-flex justify-content-center">
									<div class="d-flex flex-grow-0">
										<button type="button" class="close invisible"
											data-dismiss="modal" aria-hidden="true" disabled>&times;</button>
									</div>
									<div class="d-flex flex-grow-1 justify-content-center">
										<h4 class=" modal-title">更新商品</h4>
									</div>
									<div class="d-flex flex-grow-0">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
									</div>
								</div>

								<div class="modal-body">

									<div class="row justify-content-around">
										<div class="col-lg-5 text-left">

											<div class="form-group">
												<label for="product_name_update">商品名稱</label> <input
													type="text" name="name" id="product_name_update"
													class="form-control" required>
											</div>

											<div class="form-group">
												<label for="product_type">商品類別</label> <select
													name="product_type" id="product_type_update"
													class="form-control" required>
													<option value="">選項...</option>
													<option value="食品">食品</option>
													<option value="服飾">服飾</option>
													<option value="住家">住家</option>
													<option value="用品">用品</option>
													<option value="玩具">玩具</option>
													<option value="其他">其他</option>
												</select>
											</div>


											<label for="description_update">商品介紹</label> <input
												type="file" id="description_update">
											<div id="new-description-update"
												class="d-flex justify-content-around"></div>

											<label for="product_image">商品圖片</label> <input type="file"
												id="product_image_update" multiple>
											<div class="d-flex justify-content-around flex-wrap"
												id="new-product-image-update"></div>

										</div>
										<div class="col-lg-5 text-left">
											<div class="form-group">
												<label for="product_version">規格</label> <input type="text"
													name="product_version" id="product_version_update"
													class="form-control">
											</div>
											<div class="form-group">
												<label for="price">單價</label> <input type="text"
													name="price" id="price_update" class="form-control">
											</div>
											<div class="form-group">
												<label for="quantity">庫存</label> <input type="text"
													name="quantity" id="quantity_update" class="form-control">
											</div>
											<div class="form-group">
												<button type="button" class="btn btn-outline-success"
													id="add-product-version-update">新增</button>
											</div>

											<div>
												<table class="table">
													<thead>
														<tr>
															<th scope="col">規格</th>
															<th scope="col">單價</th>
															<th scope="col">庫存</th>
															<th scope="col">移除</th>
														</tr>
													</thead>
													<tbody id="product-version-table-update">

													</tbody>
												</table>

											</div>
											<input type="hidden" name="action" value="updateProduct">

										</div>
									</div>
								</div>
								<div class="modal-footer border-0">
									<div class="container-fluid">
										<div class="row justify-content-center">
											<div class="col-4">
												<!-- 正式版 -->
												<!-- <button type="submit" class="btn btn-primary" id="update-product">更新</button> -->
												<!-- 測試版 -->
												<button type="button" class="btn btn-primary"
													id="update-product-btn">更新</button>
											</div>
											<div class="col-4">
												<button type="button" class="btn btn-secondary"
													id="cancel-update">取消</button>
											</div>

										</div>
									</div>

								</div>
							</form>

						</div>
					</div>
				</div>



			</div>

		</div>
	</div>

	<%@ include file="footer.jsp"%>
	<script src="https://unpkg.com/filepond/dist/filepond.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-file-encode/dist/filepond-plugin-file-encode.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-image-resize/dist/filepond-plugin-image-resize.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-image-transform/dist/filepond-plugin-image-transform.js"></script>
	<script
		src="https://unpkg.com/filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.js">
  </script>
  <script src="https://unpkg.com/filepond-plugin-file-validate-size/dist/filepond-plugin-file-validate-size.js"></script>
  
	<script>
	
	
		
		
// 	判斷是否為正整數
	function isPositiveInteger(str) {
		let n; 
		if(typeof str == 'number') {
			n = Math.floor(str);
			return n !== Infinity && n >= 0;
		} else {
		  n = Math.floor(Number(str));			
	  	return n !== Infinity && String(n) === str && n >= 0;
		}
	}
	
	

	function getImageBlob(url) {
	  return $.ajax({
	    url: url,
	    cache: false,
	    xhrFields: {
	      responseType: 'blob'
	    },
	    success: function (data) {
	      console.log(data);
	    },
	    error: function (xhr) {
	      console.log(xhr);
	    }
	  })
	}

	function getDataUrl(url) {
	  var canvas = document.createElement('canvas');
	  var ctx = canvas.getContext('2d');

	  canvas.width = img.width;
	  canvas.height = img.height;
	  ctx.drawImage(img, 0, 0);
	  return canvas.toDataURL();
	}

	function toDataURL(url) {
	  return $.ajax({
	    url: url,
	    cache: false,
	    xhrFields: {
	      responseType: 'blob'
	    },
	    success: function (data) {
	      let reader = new FileReader();
	      reader.readAsDataURL(data);
	      reader.onloadend = function () {
	        base64 = reader.result;
	      }
	    },
	    error: function (xhr) {}
	  })
	}


	$(function () {
		let list;
		if(${json != null ? 'true' : 'false'}) {
			list = ${json};
		}
		let newDescription;
		let newImages = {};
		let description_id;
		let firstTimeUpdate = false;
// 	上傳圖片設定 ========================================================================================
		FilePond.registerPlugin(
				  FilePondPluginImagePreview,
				  FilePondPluginImageResize,
				  FilePondPluginImageTransform,
				  FilePondPluginFileValidateType,
				  FilePondPluginFileEncode,
				  FilePondPluginFileValidateSize
		);
	  const inputElement = document.querySelector('input[id="description"]');
	  const pond = FilePond.create(inputElement, {
	    allowFileEncode: true,
	    labelIdle: '上傳圖片',
	    allowImagePreview: false,
	    acceptedFileTypes: ['image/*'],
	    maxFiles: 1,
	    imageResizeTargetWidth: 290,
	    name: 'description',
	    onpreparefile: (file, output) => {
	      // console.log("this is file id: " + file.id);
	      const holder = $('div#new-description');
	      let url = URL.createObjectURL(output);
	      description_id = file.id;
//		 	以下程式碼會把file轉成base64字串
	      newDescription = file.getFileEncodeBase64String();
	      $(holder).append('<div class="description_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded"' + 
					'id="' + file.id + '"><img src="' + url +　'"></div>');
	      
	    },
	    onremovefile: (error, file) => {
//		 	          console.log("this is file id: " + file.id);
	      const img = $("div#"+file.id);
	      $(img).remove();
	      newDescription = "";
	    }
	  });

	  const inputElement1 = document.querySelector('input[id="product_image"]');
	  
	  const pond1 = FilePond.create(inputElement1, {
	    labelIdle: '上傳圖片',
	    allowImagePreview: false,
	    acceptedFileTypes: ['image/*'],
	    maxFiles: 4,
	    name: 'product_image',
	    imageResizeTargetWidth: 270,
	    onpreparefile: (file, output) => {
// 	      console.log("this is file id: " + file.id);
	      const holder = $('div#new-product-image');
	      let url = URL.createObjectURL(output);
	      $(holder).append('<div class="product_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded"' + 
					'id="' + file.id + '"><img src="' + url +　'"></div>');
	      newImages[file.id] = file.getFileEncodeBase64String();
	    },
	    onremovefile: (error, file) => {
// 	      console.log("this is file id: " + file.id);
	      const img = $("div#"+file.id);
	      $(img).remove();
	      delete newImages[file.id];
	    }
	  });

	  
	  
	  let updateDescription;
	  let updateImages = {};
	  const inputElement2 = document.querySelector('input[id="description_update"]');

	  const pond2 = FilePond.create(inputElement2, {
	    labelIdle: '上傳圖片',
	    allowImagePreview: false,
	    acceptedFileTypes: ['image/*'],
	    maxFiles: 1,
	    name: 'description',
	    imageResizeTargetWidth: 290,
	    onpreparefile: (file, output) => {
	      const holder = $('div#new-description-update');
	      let url = URL.createObjectURL(output);
	      $(holder).append('<div class="description_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded"' + 
					'id="' + file.id + '"><img src="' + url +　'"></div>');
	      updateDescription = file.getFileEncodeBase64String();
	    },
	    onremovefile: (error, file) => {
	      const img = $("div#"+file.id);
	      $(img).remove();
	      updateDescription = "";
	    }
	  });

	  const inputElement3 = document.querySelector('input[id="product_image_update"]');

	  const pond3 = FilePond.create(inputElement3, {
	    labelIdle: '上傳圖片',
	    allowImagePreview: false,
	    acceptedFileTypes: ['image/*'],
	    maxFiles: 4,
	    name: 'product_image',
	    imageResizeTargetWidth: 270,
	    onpreparefile: (file, output) => {
	      firstTimeUpdate = true;
	      const holder = $('div#new-product-image-update');
	      let url = URL.createObjectURL(output);
	      if (firstTimeUpdate === false) $(holder).html('');
	      $(holder).append('<div class="product_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded"' + 
					'id="' + file.id + '"><img src="' + url +　'"></div>');
	      updateImages[file.id] = file.getFileEncodeBase64String();
	    },
	    onremovefile: (error, file) => {
	      const img = $("div#"+file.id);
	      $(img).remove();
	      delete updateImages[file.id];
	      console.log(updateImages);
	    }
	  });
	  
	  //D更新狀態
	  $('.slider').on('click', function() {
		  let self = this;
		  $.ajax({
			  url: "<%=request.getContextPath()%>/Product",           
			  type: "post",                 
			  data: {
				  action: "update_state",
				  pid: $(self).attr("data-pid"),
				  product_state: $(self).attr("data-product_state")
			  },          
			  dataType: "text",     
			  timeout: 0,       
			  headers: {                    
			  },
			  success: function(data){   
				  if(data == "success") alert("狀態更新成功");
				  console.log(data);
			  },
			  error: function(xhr){      
			    console.log(xhr);
			    if(data == "success") alert("狀態更新成功");
			  }
			});
	  })
	  
	  
	  //D點選移除
	  $('.product-id-remove').on("click", function(){
			let pid = $(this).closest('tr').find('.pid').text();
			$('#remove').attr("data-pid", pid);
		})
		
	    // 	刪除某項商品=======================================================================
			$('#remove').on("click", function () {
				let self = this;
		// 	  這邊透過ajax到後端進行移除
				$.ajax({
				  url: '<%=request.getContextPath()%>/Product',           
				  type: "post",                 
				  data: {
					  "action": "delete",
					  "pid" : $(this).attr("data-pid")
				  },      
				  dataType: "text",     
				  timeout: 0,
				  success: function(data){    
					  console.log(data);
					  if(data == "success") {
						  alert("成功刪除");							  
					    window.location.href = "<%=request.getContextPath()%>/front-end/member/productList.jsp";
					  } else {
						  
						  $(self).closest('.modal-content').find('button.close')[1].click();
						  alert("還存在商品規格，所以無法進行刪除");
					  }
				  },
				  error: function(xhr){      
					  console.log(xhr);
					  $(self).closest('.modal-content').find('button.close')[1].click();
				    alert("還存在商品規格，所以無法進行刪除");
				  }
				});
// 			  $(remove).off('click').on("click", function () {
// 			    $(self).closest('tr').remove();
// 			    $(this).closest('.modal-content').find('button.close').click();
// 			  })
			})
		
		// 	新增商品內容=======================================================================
		
		// 	新增商品
			$('#add-product-btn').on("click", function () {
			  let modal = $(this).closest('.modal-content');
			  let name = $('#product_name').val().trim();
			  let type = $('#product_type').val().trim();
			  // let price = $('#product_price').val().trim();
			  let form = $(this).closest('form');
			  let trs = $(modal).find('tbody tr');
			  let product = {} 
			  let versions = [];
			  $.each(trs, function() {
				  let vname = $(this).find('td.name').text().trim();
				  let price = parseInt($(this).find('td.price').text().trim());
				  let inventory = parseInt($(this).find('td.inventory').text().trim());
				  versions.push({
					  "version_name": vname,
					  "price": price,
					  "inventory": inventory
				  });
			  })
			  let member_id = '${memberVO.member_id}';
			  product.member_id = member_id;
			  product.name = name;
			  product.product_class = type;
			  product.description = newDescription;
			  product.product_state = 1;
			  product.versions = versions;
			  let images = Object.values(newImages);
			  
			  $.each(images, function(i, image) {
					product["image" + (i + 1)] = image;
			  })
			  
		// 	  判斷不能為空值
			  if (name !== "" && type !== "" && trs.length !== 0) {
				  $.ajax({
					  url: "<%=request.getContextPath()%>/Product",           
					  type: "post",                 
					  data: {
						  action: "insert",
						  data: JSON.stringify(product)
					  },          
					  dataType: "text",     
					  timeout: 0,       
					  headers: {                    
					  },
					  success: function(data){   
						  if(data == "success") alert("新增成功");
						  console.log(data);
					    window.location.href = "<%=request.getContextPath()%>/front-end/member/productList.jsp";
					  },
					  error: function(xhr){      
					    console.log(xhr);
					    alert("新增失敗");
					  }
					});
			  } else {
				  alert("請填寫完整再送出");
			  }
			})
		
		// 	新增商品規格(新增商品頁面上)
			$('#add-product-version').on("click", function () {
			  let name = $('#product_version').val().trim();
			  let quantity = $('#quantity').val().trim();
			  let price = $('#price').val().trim();
			  let trs = $(this).closest('.modal-body').find('tr');
				let duplicate = false;
			  
			  $.each(trs, function(i, t) {
				  if($(t).find('.name').text() === name) duplicate=true;
			  })
			  if (duplicate === false && name !== "" && quantity !== "" && price !== "" &&
			    isPositiveInteger(quantity) && isPositiveInteger(price) && parseInt(quantity) < 1000 && parseInt(price) < 1000000) {
				  $('#product-version-table').append('<tr><td class="name">' +name +
						  '</td><td class="price">' + price + '</td><td class="inventory">' + quantity + 
				    		'</td><td><a href="#" data-toggle="modal" class="product-remove"><i class="fas fa-trash remove-product-version"></i></a></td></tr>'
				  );
			    $('#product_version').val("");
			    $('#quantity').val("");
			    $('#price').val("");
			  }
			})
		
			
			
		// 	移除商品規格
			$('#product-version-table').on("click", "a.product-remove", function () {
			  $(this).closest('tr').remove();
			})
		
		
		
		// 	取消新增商品
			$('#cancel-product').on("click", function () {
			  let modal = $(this).closest('.modal-content');
			  if (confirm("確定要取消新增此項商品? 此次填寫的內容將會遺失")) {
			    $(modal).find('input').val('');
			    $(modal).find('select option').first().prop("selected", true);
			    $(modal).find('tbody').html('');
			    pond.removeFiles();
			    pond1.removeFiles();
			    $(modal).find('button.close').click();
			  }
			})
		
		
		// 	更新商品內容=======================================================================
			
			let b64 = "data:image/jpg;base64,";
			let pidClicked;
			console.log(list);
		  $('.product-update').on('click', function() {
			  let modal = $('#update-product').closest('.modal-content');
			  console.log(modal);
		    $('#product-version-table-update').html('');
		    
			  pond2.removeFiles();
			  pond3.removeFiles();
			  updateImages = {};
			  let pid = $(this).closest('tr').find('.pid').text();
			  pidClicked = pid;
			  $("#product_type_update").find("option[selected]").removeAttr("selected");
			  $.each(list, function (i, p) {
				  if(p.product_id === pid) {
					  if(typeof p.description !== 'undefined') pond2.addFile(b64 + p.description);			  
						if(typeof p.image1 !== 'undefined') pond3.addFile(b64 + p.image1);
						if(typeof p.image2 !== 'undefined') pond3.addFile(b64 + p.image2);
						if(typeof p.image3 !== 'undefined') pond3.addFile(b64 + p.image3);
						if(typeof p.image4 !== 'undefined') pond3.addFile(b64 + p.image4);
					  $('#product_name_update').val(p.name);
					  $('#product_type_update > option').each(function() {
						  if(p.product_class === $(this).val()) {
							  $(this).attr('selected', 'selected');
							  return false;
						  }
					  })
					  $.each(p.versions, function(i, v) {
						  let vid = v.product_version_id;
						  let name = v.version_name;
						  let price = v.price;
						  let inventory = v.inventory;
						  if (isPositiveInteger(inventory) && isPositiveInteger(price)) {
						    $('#product-version-table-update').append('<tr><td class="name">' +name +
						    		'</td><td class="price">' + price + '</td><td class="inventory">' + inventory +
						    		'</td><td><a href="#" data-toggle="modal" data-vid="' + vid + '" class="product-remove"><i class="fas fa-trash remove-product-version"></i></a></td></tr>'
						    );
						  }
					  })
				  }
			  }) 
		  })	
		
		// 	更改商品資訊
			$('#update-product-btn').on("click", function () {
			  let modal = $(this).closest('.modal-content');
			  let name = $('#product_name_update').val().trim();
			  let type = $('#product_type_update').val().trim();
			  let trs = $(modal).find('tbody tr');
			  let product = {};
			  let versions = [];
			  $.each(list, function(i, p) {
				  if(p.product_id === pidClicked) {
					  product = p;
					  versions = p.versions; 
				  }
			  })
			  
			  $.each(trs, function() {
				  let vname = $(this).find('td.name').text().trim();
				  let price = parseInt($(this).find('td.price').text().trim());
				  let inventory = parseInt($(this).find('td.inventory').text().trim());
				  let duplicate = false;
				  for(let v of versions) {
					  if(v.version_name === vname) {
						  duplicate = true; 
						  break;
					  } 
				  }
					  
					if(duplicate === false) {
					  versions.push({
						  "product_version_id": "",
						  "version_name": vname,
						  "price": price,
						  "inventory": inventory
					  });						
					}  
			  })
			  console.log(versions);
			  
			  let member_id = list[0].member_id;
			  product.member_id = member_id;
			  product.name = name;
			  product.product_class = type;
			  product.description = updateDescription;
			  product.versions = versions;
			  let images = Object.values(updateImages);
			  product.image1="";
			  product.image2="";
			  product.image3="";
			  product.image4="";			  
			  $.each(images, function(i, image) {
					product["image" + (i + 1)] = image;
			  })
			  console.log(product);
			  
		// 	  判斷不能為空值
			  if (name !== "" && type !== "" && trs.length !== 0) {
				  $.ajax({
					  url: "<%=request.getContextPath()%>/Product",           
					  type: "post",
					  data: {
						  action : "update",
						  data : JSON.stringify(product)
					  },               
					  dataType: "text",             
					  timeout: 0,  
					  success: function(data){   
						  alert("成功更新");
					    window.location.href = "<%=request.getContextPath()%>/front-end/member/productList.jsp";
					  },
					  error: function(xhr){    
						  alert("更新失敗");
					    console.log(xhr);
					  }
					});
			  } else {
				  alert("請填寫完整再送出");
			  }
			  $(modal).find('button.close').click();
			})
	
	
	// 	新增商品規格(更新商品頁面上)
			$('#add-product-version-update').on("click", function () {
			  let name = $('#product_version_update').val().trim();
			  let quantity = $('#quantity_update').val().trim();
			  let price = $('#price_update').val().trim();
			  let trs = $(this).closest('.modal-body').find('tr');
			  let duplicate = false;
			  
			  $.each(trs, function(i, t) {
				  if($(t).find('.name').text() === name) duplicate=true;
			  })
			  if (duplicate === false && name !== "" && quantity !== "" && price !== "" &&
			    isPositiveInteger(quantity) && isPositiveInteger(price) && parseInt(quantity) < 1000 && parseInt(price) < 1000000) {
			    $('#product-version-table-update').append('<tr><td class="name">' +name +
			    		 '</td><td  class="price">' + price + '</td><td class="inventory">' + quantity +
			    		'</td><td><a href="#" data-toggle="modal" class="product-remove"><i class="fas fa-trash remove-product-version"></i></a></td></tr>'
			    		);
		
			    $('#product_version_update').val("");
			    $('#quantity_update').val("");
			    $('#price_update').val("");
			  }
			})
	
	// 	移除商品規格
			$('#product-version-table-update').on("click", "a.product-remove", function () {
			  //D這邊發起一個ajax請求刪除商品規格，判斷如果該商品規格已經被參照到的話則會跳出不能刪除
			  let self = this;
			  console.log($(self).data("vid"))
			  $.ajax({
					  url: "<%=request.getContextPath()%>/Product",           
					  type: "post",   
					  dataType: "text",     
					  data: {
						  action: "delete_version",
						  vid: $(self).data("vid")
					  },                       
					  timeout: 0,
					  success: function(data){      // request 成功取得回應後執行
						  console.log(data);
						  if(data =="success") {
							  alert("刪除成功");							  
						    $(this).closest('tr').remove();
						    window.location.href = "<%=request.getContextPath()%>/front-end/member/productList.jsp";
						  } else {
							  alert("該商品規格已被人購買，所以無法進行刪除");
						  }
						  $(self).closest('.modal-content').find('button.close')[1].click();
					  },
					  error: function(xhr){         // request 發生錯誤的話執行
					    alert("該商品規格已被人購買，所以無法進行刪除");
					    $(self).closest('.modal-content').find('button.close')[1].click();
					  }
					});
			  
			})
	
	// 	取消更新商品
			$('#cancel-update').on("click", function () {
			  let modal = $(this).closest('.modal-content');
			  console.log(modal);
			  if (confirm("確定要取消更新此項商品?" + "\n" + "此次填寫的內容將會遺失回到上次更新的狀態")) {
			    $(modal).find('button.close').click();
			  }
			})
	})
	


// // 	修改上傳圖片預設文字，及預覽圖片
// 	$(window).on("load", function () {
// // 	  修改上傳圖片預設文字
// // 	  此標籤為實際上傳圖片之input
// 	  $("input[name='description']").on("change", function () {
// 	     for (let i = 0; i < this.files.length; i++) {
// 	       let reader = new FileReader();
// 	       reader.readAsDataURL(this.files[i]);
// 	       reader.addEventListener("load", function () {
// 	        // console.log(reader.result);

// // 	         此處處理圖片，可以把圖片轉成blob
// // 	         之後透過ajax把圖片傳到後端處理
// 	       });
// 	     }
// 	  })
// 	});


	</script>
</body>

</html>