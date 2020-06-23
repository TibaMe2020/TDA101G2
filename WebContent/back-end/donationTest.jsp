<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@ page import="com.donation.npo_info.model.*"%>
<%
    Npo_infoService NpoSvc = new Npo_infoService(); //介面
    List<Npo_infoVO> list = NpoSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%
  Npo_infoVO npo_infoVO = (Npo_infoVO) request.getAttribute("npo_infoVO"); 
%>
<%@page
	import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>


<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Donation List</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
  <link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/productList.css">
  <link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/adminManagement.css">
</head>
<style>
div.modal-body{
text-align: -webkit-center;
}
 img.preview{
    width: 300px;
    height: 300px; 
}
td{
text-align: center;
}

span.error{
	color:red;
}

#table-title-text {
	color: white;
}
</style>
<body>
  
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
					<h3 class="title-tab">公益團體</h3>
				</div>
			</div>
		</div>

	</header>
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
                  <h2 id="table-title-text">公益團體</h2>
                </div>
                <div class="col-6 d-flex align-items-center justify-content-end">
                  <a href="#add-product" class="d-flex add-product-modal" data-toggle="modal">
                    <i class="fas fa-plus-circle add-product"></i>
                  </a>
                </div>
              </div>
            </div>
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a href="#" class="d-flex align-items-center">公益團體編號<i class="fas fa-sort"></i></a>
                    </div>
                  </th>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a href="#" class="d-flex align-items-center">公益團體名稱<i class="fas fa-sort"></i></a>
                    </div>
                  </th>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a href="#" class="d-flex align-items-center">創建日期<i class="fas fa-sort"></i></a>
                    </div>
                  </th>
               
                  <th>修改</th>
                  <th>移除</th>
                </tr>
              </thead>
              <tbody id="product-table">
              	<c:forEach var="npo_infoVO" items="${list}" >
              
                <tr>
                  <td>${npo_infoVO.npo_id}</td>
                  <td>${npo_infoVO.npo_name}</td>
                  <td>${npo_infoVO.create_time}</td>
<!--                   <td> -->
<!--                     <div class="d-flex justify-content-center"> -->
<!--                       <label class="switch d-flex align-self-center"> -->
<!--                         <input type="checkbox" name="product_state" checked> -->
<!--                         <span class="slider round"></span> -->
<!--                       </label> -->
<!--                     </div> -->
<!--                   </td> -->
                  <td>
<!--                     <div class="d-flex justify-content-center"> -->
<!--                       <a href="#update-product" data-toggle="modal" class="product-update"> -->
<!--                         <i class="fas fa-pen"></i> -->
<!--                       </a> -->
<!--                     </div> -->
 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Npo/npo.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="npo_id"  value="${npo_infoVO.npo_id}">
			     
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                  </td>
                  <td>
                    <a href="#remove-product" data-toggle="modal" class="product-remove">
                      <i class="fas fa-trash"></i>
                    </a>
                  </td>
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
                  <button type="button" class="close invisible" data-dismiss="modal" aria-hidden="true"
                    disabled>&times;</button>
                </div>
                <div class="d-flex flex-grow-1 justify-content-center">
                  <h4 class=" modal-title">移除商品</h4>
                </div>
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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
                      <button type="button" class="btn btn-secondary" id="cancel" data-dismiss="modal">取消</button>
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
              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Npo/npo.do" name="form1" enctype="multipart/form-data">
                
                <div class="modal-body">
<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Npo/npo.do" name="form1" enctype="multipart/form-data"> --%>
	<tr>
		<td>公益團體名稱:</td>
		<td><input type="TEXT" name="npo_name"size="45" ><span class="error">${errors.npo_name}</span></td>
	</tr><br><br>
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="npo_image" size="45" id="the_file"/></td>	
    	<ul class="picture_list" id="pc"></ul>
<%-- 		<td><img id="blah" alt="your image" src="<%=request.getContextPath()%>/Npo/DBGifReader2?npo_id=${npo_infoVO.npo_id}"></td>		 --%>
	</tr>

	<tr>
		<td>公益團體介紹:</td>
		<td><input type="TEXT" name="npo_description" size="45"
			 /><span class="error">${errors.npo_name}</span></td>
	</tr>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                  <input type="hidden" name="action" value="insert">
				  <input class="btn btn-primary" type="submit" value="送出新增"></div></FORM>

            </div>
          </div>
        </div>

        <!-- Update Product -->
        <div id="update-product" class="modal fade">
          <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content">
              <form action="" method="post" class="main-form" enctype="multipart/form-data" id="update-product-form">

                <div class="modal-header d-flex justify-content-center">
                  <div class="d-flex flex-grow-0">
                    <button type="button" class="close invisible" data-dismiss="modal" aria-hidden="true"
                      disabled>&times;</button>
                  </div>
                  <div class="d-flex flex-grow-1 justify-content-center">
                    <h4 class=" modal-title">更新商品</h4>
                  </div>
                  <div class="d-flex flex-grow-0">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  </div>
                </div>

                <div class="modal-body">

                  <div class="row justify-content-around">
                    <div class="col-lg-5 text-left">

                      <div class="form-group">
                        <label for="product_name_update">商品名稱</label>
                        <input type="text" name="name" id="product_name_update" class="form-control" required>
                      </div>

                      <div class="form-group">
                        <label for="product_type">商品類別</label>
                        <select name="product_type" id="product_type_update" class="form-control">
                          <option value="">選項...</option>
                          <option value="食品">食品</option>
                          <option value="服飾">服飾</option>
                          <option value="住家">住家</option>
                          <option value="用品">用品</option>
                          <option value="其他">其他</option>
                        </select>
                      </div>


                      <label for="description_update">商品介紹</label>
                      <input type="file" id="description_update">
                      <div id="new-description-update" class="d-flex justify-content-around"></div>

                      <label for="product_image">商品圖片</label>
                      <input type="file" id="product_image_update" multiple>
                      <div class="d-flex justify-content-around flex-wrap" id="new-product-image-update">
                      </div>

                    </div>
                    <div class="col-lg-5 text-left">
                      <div class="form-group">
                        <label for="product_version">規格</label>
                        <input type="text" name="product_version" id="product_version_update" class="form-control">
                      </div>
                      <div class="form-group">
                        <label for="quantity">數量</label>
                        <input type="text" name="quantity" id="quantity_update" class="form-control">
                      </div>
                      <div class="form-group">
                        <label for="price">單價</label>
                        <input type="text" name="price" id="price_update" class="form-control">
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
                        <button type="button" class="btn btn-primary" id="update-product-btn">更新</button>
                      </div>
                      <div class="col-4">
                        <button type="button" class="btn btn-secondary" id="cancel-update">取消</button>
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

<%-- 	<%@ include file="footer.file"%> --%>
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
	<script>
var the_file_element = document.getElementById("the_file");
var pc=document.getElementById("pc");
the_file_element.addEventListener("change", function(e){ //當事件發生改變
  console.log(this.files)
  console.log(this.files[0])
  pc.innerHTML="";  //清空
  for(let a=0;a<this.files.length; a++){
    let reader = new FileReader();
    reader.readAsDataURL(this.files[a]);


  reader.addEventListener("load", function () { //讀取資料後
      var linode=document.createElement("li"); //設一個節點
      linode.innerHTML = "<img class='preview' src='" + reader.result + "'>";
      pc.appendChild(linode); //把上面的linode加入倒ul標籤 類似創造完物件要找地方擺
       console.log(reader.result);
    });
  }


});


</script>
</body>

</html>