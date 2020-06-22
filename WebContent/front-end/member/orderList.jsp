<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.member.model.*, com.notification.model.*"%>
<%@page
	import="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap,java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Order List</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/orderList.css">
</head>

<body>
  <%@ include file="header.jsp"%>


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
                <div class="col-sm-6  text-left d-flex align-items-center">
                  <h2 id="table-title-text">訂單列表</h2>
                </div>
              </div>
            </div>
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a class="d-flex align-items-center">訂單編號</a>
                    </div>
                  </th>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a class="d-flex align-items-center">會員編號</a>
                    </div>
                  </th>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a class="d-flex align-items-center">開立時間</a>
                    </div>
                  </th>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a class="d-flex align-items-center">金額</a>
                    </div>
                  </th>
                  <th>
                    <div class="d-flex justify-content-center">
                      <a class="d-flex align-items-center">訂單狀態</a>
                    </div>
                  </th>
                  <th>詳細資訊</th>
                </tr>
              </thead>
              <tbody>
              
								<jsp:useBean id="ProductSvc" scope="page" class="com.util.ProductSvc" />
								<c:forEach var="order" items="${ProductSvc.getOrders(memberVO.getMember_id())}">
								
									<tr>
                  <td class="order-id">${order.product_order_id}</td>
                  <td class="buyer-id">${order.buyer_id}</td>
                  <td class="create-time"><fmt:formatDate value="${order.create_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                  <td class="total"></td>
                  <td class="d-flex justify-content-center">
                  	
                    <div class="form-group order-state">
                      <select name="order_state" class="form-control order_state">
                        <option value="1" ${order.product_order_state == 1 ? 'selected' : ''}>待付款</option>
                        <option value="2" ${order.product_order_state == 2 ? 'selected' : ''}>待出貨</option>
                        <option value="3" ${order.product_order_state == 3 ? 'selected' : ''}>已出貨</option>
                        <option value="4" ${order.product_order_state == 4 ? 'selected' : ''}>待取貨</option>
                        <option value="5" ${(order.product_order_state >= 5 ) ? 'selected' : ''}>已完成</option>
                      </select>
                    </div>
                  </td>
                  <td>
                  <a href="#order-detail" data-toggle="modal" class="order-detail">
                  	<c:forEach var="spec" items="${order.product_list}">
                  		<span data-product="${spec.product_name}(${spec.version_name})" data-quantity="${spec.quantity}" data-price="${spec.price}" class="d-none"></span>
                  	</c:forEach>
                  	<i class="fas fa-info-circle fa-4x"></i>
                  </a>
                  </td>
                </tr>
								</c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        <!-- Order Modal HTML -->
        <div id="order-detail" class="modal fade">
          <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header d-flex justify-content-center">
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close invisible" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="d-flex flex-grow-1 justify-content-center">
                  <h4 class=" modal-title">訂單明細</h4>
                </div>
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
              </div>

              <div class="modal-body">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-12">
                      <ul class="list-group list-group-horizontal d-flex justify-content-around">
                        <li class="list-group-item order-detail-info">訂單編號: <b class="modal-order-id">OM00001</b></li>
                        <li class="list-group-item order-detail-info">買家編號: <b class="modal-buyer-id">MB00001</b></li>
                        <li class="list-group-item order-detail-info modal-create-time">2020-05-06</li>
                      </ul>
                    </div>
                    <div class="col-12">
                      <table class="table table-borderless">
                        <thead>
                          <tr>
                            <th scope="col">商品名稱</th>
                            <th scope="col">數量</th>
                            <th scope="col">單價</th>
                            <th scope="col">金額</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>

              <!-- <div class="modal-footer">
                <span>2020-05-06</span>
              </div> -->

            </div>
          </div>
        </div>

      </div>

    </div>
  </div>

  <%@ include file="footer.jsp"%>
  
  <script>
  $(window).on('load', function() {
	  let states = $('.order_state');
	  $.each(states, function(i, s) {
	  	let selected = $(s).find("option:selected");
		  $(selected).prevAll().addClass('d-none');
	  })
	  
	  $(".order_state").on('change', function() {
		  let order_id = $(this).closest("tr").find('.order-id').text();
		  let order_state = $(this).find("option:selected").val();
		  $.ajax({
			  url: "<%=request.getContextPath()%>/Order_master",           
			  type: "post",                 
			  data: {
				  action: "update_state",
				  product_order_id: order_id,
				  order_state: order_state
			  },          
			  dataType: "text",     
			  timeout: 0,    
			  success: function(data){   
				  if(data == "success") {
					  alert("狀態更新成功");
					  window.location.href = "<%=request.getContextPath()%>/front-end/member/orderList.jsp";
				  } else {
					  alert("狀態更新失敗");
				  }
			  },
			  error: function(xhr){      
			    console.log(xhr);
			    if(data == "success") alert("狀態更新失敗");
			  }
			});  
	  });
	  
	  
	  
	  let totalList = $('td.total');
	  $.each(totalList, function(i, t) {
		  let total = 0;
		  let specList = $(t).closest('tr').find('span');
		  $.each(specList, function(index, s) {
			  let price = parseInt($(s).data("price"));
			  let quantity = parseInt($(s).data("quantity"));
			  total += price*quantity;
		  }) 
		  total = new Intl.NumberFormat().format(total);
		  $(t).text("$" + total);
	  })
	});
	
  $('a.order-detail').on('click', function() {
	  $('#order-detail tbody').html('');
		let list = $(this).find('span');
		let total =0;
		let orderId = $(this).closest('tr').find('.order-id').text();
		let buyerId = $(this).closest('tr').find('.buyer-id').text();
		let createTime = $(this).closest('tr').find('.create-time').text();
		$('.modal').find('.modal-order-id').text(orderId);
		$('.modal').find('.modal-buyer-id').text(buyerId);
		$('.modal').find('.modal-create-time').text(createTime);
		$.each(list, function(i, t) {
			let product = $(t).data("product");
			let q = parseInt($(t).data("quantity"));
			let p = parseInt($(t).data("price"));
			total += p*q;
			$('#order-detail tbody').append(
			'<tr><td>' +　product　+'</td><td>' +  q + '</td><td>' + p + '</td><td>' + p*q + '</td></tr>');
		})
		$('#order-detail tbody').append(
				'<tr><th scope="row"></th><td></td><td><b>總金額</b></td><td><b>'+ total +'</b></td></tr>'
		);
	});
  </script>

</body>

</html>