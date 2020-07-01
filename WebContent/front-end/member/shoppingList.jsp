<%@page import="com.product_score.model.Score_VO"%>
<%@page import="com.product.model.Product_VO"%>
<%@page import="com.product_version.model.Version_VO"%>
<%@page import="com.product_score.model.Score_Service"%>
<%@page import="com.product.model.Product_Service"%>
<%@page import="com.product_version.model.Version_Service"%>
<%@page import="com.product_order_detail.model.Order_detail_VO"%>
<%@page import="com.product_order_detail.model.Order_detail_Service"%>
<%@page import="com.product_order_master.model.Order_master_VO"%>
<%@page import="com.product_order_master.model.Order_master_Service"%>
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
  <title>Shopping List</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/shoppingList.css">
</head>

<body>
  <%@ include file="header.jsp"%>
	<% 
		Map<String, String> order_states = new HashMap<>();
		order_states.put("1", "待付款");
		order_states.put("2", "待出貨");
		order_states.put("3", "已出貨");
		order_states.put("4", "待取貨");
		order_states.put("5", "尚未評價");
		order_states.put("6", "已完成");
		pageContext.setAttribute("order_states", order_states);
		
		List<Order_master_VO> orders = null;
		try{
			Order_master_Service omSvc = new Order_master_Service();
			Order_detail_Service odSvc = new Order_detail_Service();
			Version_Service vSvc = new Version_Service();
			Product_Service pSvc = new Product_Service();
			Score_Service scSvc = new Score_Service();
			orders = omSvc.getAllmember(memberVO.getMember_id());
			pageContext.setAttribute("orderList", orders);
			for(Order_master_VO order : orders) {
				String omId = order.getOrder_master_id();
				List<Order_detail_VO> detail_list = odSvc.getByOmId(omId);
				order.setDetail_list(detail_list);
				
				for(Order_detail_VO detail : detail_list) {
					String vid = detail.getProduct_version_id();
					Version_VO versionVO = vSvc.getOneVersion(vid);
					detail.setVersionVO(versionVO);
					Product_VO prodVO = pSvc.getOneProduct(versionVO.getProduct_id());
					versionVO.setProductVO(prodVO);
					Score_VO scoreVO = scSvc.getByMIdPId(memberVO.getMember_id(), versionVO.getProduct_id());
					if(scoreVO != null) prodVO.setMemberScore(scoreVO.getScore());
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
	%>
	
	

  <div class="container-fluid">
    <div class="row content-height">
      <div class="col-2">
        <%@ include file="sidebar.jsp"%>
      </div>
      <!-- Main content -->
      <div class="col-9">
        <div class="content-title">購買清單</div>
        <div class="content-container">
          <ul class="nav nav-pills mb-3 bg-white d-flex justify-content-center" id="pills-tab" role="tablist">
          	<li class="nav-item flex-fill">
              <a class="nav-link active" id="show-all" data-toggle="pill" href="#" role="tab" aria-controls="pills-7"
                aria-selected="false">所有訂單</a>
            </li>
            <li class="nav-item flex-fill dropdown">
              <a class="nav-link dropdown-toggle" id="pills" data-toggle="dropdown" href="#" role="button"
                aria-haspopup="true" aria-expanded="false">已完成</a>
              <div class="dropdown-menu">
                <a class="dropdown-item order-state-pill" id="pills-5" href="#" data-toggle="pill" data-order-state="5">未評價</a>
                <a class="dropdown-item order-state-pill" id="pills-6" href="#" data-toggle="pill" data-order-state="6">已評價</a>
              </div>
            </li>
            <li class="nav-item flex-fill">
              <a class="nav-link order-state-pill" id="pills-1" data-toggle="pill" href="#pills-7" role="tab" aria-controls="pills-7"
                aria-selected="false" data-order-state="1">待付款</a>
            </li>
            <li class="nav-item flex-fill">
              <a class="nav-link order-state-pill" id="pills-2" data-toggle="pill" href="#pills-8" role="tab" aria-controls="pills-8"
                aria-selected="false" data-order-state="2">待出貨</a>
            </li>
            <li class="nav-item flex-fill">
              <a class="nav-link order-state-pill" id="pills-3" data-toggle="pill" href="#pills-9" role="tab" aria-controls="pills-9"
                aria-selected="false" data-order-state="3">已出貨</a>
            </li>
            <li class="nav-item flex-fill">
              <a class="nav-link order-state-pill" id="pills-4" data-toggle="pill" href="#pills-10" role="tab" aria-controls="pills-10"
                aria-selected="false" data-order-state="4">待取貨</a>
            </li>
          </ul>

          <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active order-holder" id="pills-1" role="tabpanel" aria-labelledby="pills-1-tab">
            
			<c:forEach items="${orderList}" var="order">
				
              <div class="card bg-white border-0 product_order" data-orderState="${order.product_order_state}">
                <div class="card-header bg-white d-flex justify-content-between">
                  <div>
<%--                     	賣家: ${order.getDetail_list().get(0).getVersionVO().getProductVO().getMember_id()}<br> --%>
<%--                     	訂單編號: ${order.order_master_id}&nbsp&nbsp&nbsp&nbsp --%>
						<c:if test="${order.product_order_state == 4}">
							商品已送達 ${order.location}
						</c:if>
						<c:if test="${order.product_order_state != 4}">
							訂單編號:  ${order.order_master_id }
						</c:if>
<%--                     	取貨地點: ${order.location}  --%>
                  </div>

                  <div>
                  <fmt:formatDate value="${order.create_time}" pattern="yyyy-MM-dd"/>
                  </div>
                </div>
                <c:forEach items="${order.detail_list}" var="detail">
                <a href="<%=request.getContextPath()%>/front-end/product/Product.jsp?product_id=${detail.getVersionVO().getProductVO().getProduct_id()}">
                <div class="card-body row">
                  <div class="product-image col-2">
                    <img src="<%=request.getContextPath()%>/Product_Image?product_id=${detail.getVersionVO().getProductVO().getProduct_id()}&image=1" class="rounded">
                  </div>
                  <div class="col-8 text-left">
                    <h5 class="card-title">${detail.getVersionVO().getProductVO().getName()}(${detail.getVersionVO().getVersion_name()})</h5>
                    <c:if test="${order.product_order_state == 6}">
                    <div class="star-holder" data-stars="${detail.getVersionVO().getProductVO().getMemberScore()}">
                      <span class="star" data-star="1"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="2"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="3"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="4"><i class="fas fa-star"></i></span>
                      <span class="star" data-star="5"><i class="fas fa-star"></i></span>
                    </div>
                    </c:if>
                  </div>
                  <div class="col-2 text-left align-self-center calculation">
                    <h5 class="card-title"><fmt:formatNumber maxFractionDigits = "0" value="${detail.getVersionVO().getPrice()}" type="currency"/></h5>
                    <p class="card-text">
                    	<span class="d-none price">${detail.getVersionVO().getPrice()}</span>
                    	x<span class="quantity">${detail.quantity}</span>
                    </p>
                  </div>
                </div>
                </a>
                </c:forEach>
                <div class="card-body row">
                  <div class="col-2">
                  <c:if test="${order.product_order_state == 5}">
										<a href="<%=request.getContextPath()%>/front-end/member/productScore.jsp?order_master_id=${order.order_master_id}" 
										class="btn btn-primary text-white" >
											前往評價
										</a>
									</c:if>
                  </div>
                  <div class="col-8 text-right">
                    <h5 class="card-title">總計: </h5>
                  </div>
                  <div class="col-2 text-left align-self-center">
                    <h5 class="card-title total"></h5>
                  </div>
                </div>

              </div>
              </c:forEach>

              
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
    <%@ include file="footer.jsp"%>
    <script>
    
    	//check if order exists
   		function checkIfEmpty() {
   			let cards = $('.card'); 
   			if($(cards).length === 0) {
   				$('.order-holder').append('<div class="text-center">您還沒有購買任何商品請點選此' + 
   						'<a href="<%=request.getContextPath()%>/front-end/product/ProductHomepage.jsp"' +
   						'style="color:blue; font-weight:900;">連結</a>去購物</div>'
   				);
   			}
   		}
   		
    
    	$(function() {
    		
    		checkIfEmpty();
    		let starHolders = $('.star-holder');
    		$.each(starHolders, function(i, item) {
    			let stars = $(item).data("stars");
    			let starIcons = $(item).find(".star");
    			$.each(starIcons, function(index, s) {
    				if(parseInt(stars) >= parseInt($(s).data("star"))) $(s).addClass("orange");
    			})
    		})
    		
    		let totalPriceList = $('.total');
    		$.each(totalPriceList, function(i, t) {
	    		let total = 0;
    			let cals = $(t).closest('.card').find('.calculation');
    			$.each(cals, function(index, c) {
    				let price = $(c).find('.price').text();
        		let quantity = $(c).find('.quantity').text();
	    			total += price*quantity;  				
    			})
    			total = new Intl.NumberFormat().format(total)
    			$(t).text("$" +total);
    		}) 
    		
    		$('.order-state-pill').on('click', function() {
	    		let self = this;
	    		let order_state = $(this).attr('data-order-state');
// 	    		console.log(order_state);
	    		let orders = $('.product_order');
	    		
	    		$.each(orders, function(i, o) {
	    			$(o).removeClass('d-none');
	    			if($(o).attr('data-orderState') !== order_state) {
	    				$(o).addClass('d-none');
	    			}
	    		})
    		})
    		
    		$('#show-all').on('click', function() {
    			let orders = $('.product_order');
    			$.each(orders, function(i, o) {
	    			$(o).removeClass('d-none');
	    		})
    		})
    		
    	})
    
    </script>

</body>

</html>