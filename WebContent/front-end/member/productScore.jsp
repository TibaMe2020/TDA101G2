<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.util.DistinctKey"%>
<%@page import="com.product.model.Product_VO"%>
<%@page import="com.product_version.model.Version_VO"%>
<%@page import="com.product_order_master.model.Order_master_VO"%>
<%@page import="com.product_order_detail.model.Order_detail_VO"%>
<%@page import="com.product.model.Product_Service"%>
<%@page import="com.product_version.model.Version_Service"%>
<%@page import="com.product_order_detail.model.Order_detail_Service"%>
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
<meta charset="UTF-8" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Product Score</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/productScore.css">
</head>

<body>
	<%@ include file="header.jsp"%>

	<%
		try {

			Order_master_Service omSvc = new Order_master_Service();
			Order_detail_Service odSvc = new Order_detail_Service();
			Version_Service vSvc = new Version_Service();
			Product_Service pSvc = new Product_Service();
			String order_id = request.getParameter("order_master_id");
			Order_master_VO order = omSvc.getOneMaster(order_id);
			List<Order_detail_VO> detail_list = odSvc.getByOmId(order_id);
			order.setDetail_list(detail_list);
			pageContext.setAttribute("order", order);
			pageContext.setAttribute("order_master_id", order_id);
			DistinctKey dk = new DistinctKey();
			
			for (Order_detail_VO detail : detail_list) {
				String vid = detail.getProduct_version_id();
				Version_VO versionVO = vSvc.getOneVersion(vid);
				detail.setVersionVO(versionVO);
				Product_VO prodVO = pSvc.getOneProduct(versionVO.getProduct_id());
				versionVO.setProductVO(prodVO);
			}
			Set<String> set = new HashSet<>(detail_list.size());
			detail_list.removeIf(p -> !set.add(p.getVersionVO().getProduct_id()));;
			boolean duplicateProduct = false;
		} catch (Exception e) {

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
				<div class="content-title">評價</div>
				<div class="content-container">

					<div class="tab-content" id="pills-tabContent">
						<div class="tab-pane fade show active" id="pills-1"
							role="tabpanel" aria-labelledby="pills-1-tab">



							<div class="card bg-white border-0">
								<div class="card-header bg-white d-flex justify-content-between">
									<div>
										<%--                     	賣家: ${order.getDetail_list().get(0).getVersionVO().getProductVO().getMember_id()}<br> --%>
										訂單編號: ${order.order_master_id}
									</div>

									<div>
										<fmt:formatDate value="${order.create_time}"
											pattern="yyyy-MM-dd" />
									</div>
								</div>
								<c:forEach items="${order.detail_list}" var="detail">
									<div class="card-body row product">
										<div class="product-image col-2">
											<img
												src="<%=request.getContextPath()%>/Product_Image?product_id=${detail.getVersionVO().getProductVO().getProduct_id()}&image=1"
												class="rounded">
										</div>
										<div class="col-8 text-left">
											<h5 class="card-title"
												data-pid="${detail.getVersionVO().getProductVO().getProduct_id()}">
												${detail.getVersionVO().getProductVO().getName()}(${detail.getVersionVO().getVersion_name()})
											</h5>
											<div class="star-holder" data-stars="">
												<span class="star" data-star="1"><i
													class="fas fa-star"></i></span> <span class="star" data-star="2"><i
													class="fas fa-star"></i></span> <span class="star" data-star="3"><i
													class="fas fa-star"></i></span> <span class="star" data-star="4"><i
													class="fas fa-star"></i></span> <span class="star" data-star="5"><i
													class="fas fa-star"></i></span>
											</div>
										</div>
										<div class="col-2 text-left align-self-center calculation">
											<h5 class="card-title">
												<fmt:formatNumber maxFractionDigits="0"
													value="${detail.getVersionVO().getPrice()}" type="currency" />
											</h5>
											<p class="card-text">
												<span class="d-none price">${detail.getVersionVO().getPrice()}</span>
												<%-- 													x<span class="quantity">${detail.quantity}</span> --%>
											</p>
										</div>
									</div>
								</c:forEach>
								<div class="card-body row">
									<div class="col-2"></div>
									<div class="col-8 text-center">
										<!-- 										<h5 class="card-title">總計:</h5> -->
										<input type="button" class="btn btn-primary text-white"
											id="feedback" value="送出評價">
									</div>
									<div class="col-2 text-left align-self-center">
<!-- 										<h5 class="card-title total"></h5> -->
									</div>
								</div>

							</div>

						</div>

					</div>
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp"%>
	</div>
	<script
		src="<%=request.getContextPath()%>/front-end/member/js/productScore.js"></script>
	<script>
		$(function() {
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
    		
    		$('#feedback').on('click', function() {
    			let products = $(this).closest('.card').find('.product');
    			let slist = [];
    			let empty = false;
    			for(let p of products) {
    				let stars = $(p).find('.clicked-star').last().attr('data-star');
    				let star = $(p).find('.clicked-star').last();
    				console.log($(star).length);
    				if($(star).length === 0) empty = true;
    				let pid = $(p).find('.card-title').attr('data-pid');
    				slist.push(
    					{
    						member_id :'${memberVO.member_id}',
    						score: parseInt(stars),
    						product_id: pid
    					}	
    				)
    			}
    			if(empty == false) {
	    			$.ajax({
	  				  url: '<%=request.getContextPath()%>/Score',           
	  				  type: "post",                 
	  				  data: {
	  					  action: "insert",
									order_master_id: '${order_master_id}',	
	  					  json : JSON.stringify(slist)
	  				  },      
	  				  dataType: "text",     
	  				  timeout: 0,
	  				  success: function(data){    
	  					  console.log(data);
	  					  if(data == "success") {
	  					    window.location.href = "<%=request.getContextPath()%>/front-end/member/shoppingList.jsp";
	  					  } else {
	  						  	window.location.href = "<%=request.getContextPath()%>/front-end/member/shoppingList.jsp";
	  					  }
	  				  },
	  				  error: function(xhr){      
	  					  console.log(xhr);
	           		window.location.href = "<%=request.getContextPath()%>/front-end/member/productList.jsp";
	  				  }
	  				});
    			} else {
    				alert("請填寫給予商品的評價");
    			}
    		})
    	})
		</script>
</body>

</html>