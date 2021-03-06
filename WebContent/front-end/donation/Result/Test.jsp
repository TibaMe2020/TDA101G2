<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%--     pageEncoding="utf-8"%> --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.donation.donation_result.model.*"%>

<!DOCTYPE html>
<%
  	String path = request.getContextPath();  
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Donation_resultService ResultSvca = new Donation_resultService(); //介面
    List<Donation_resultVO> list = ResultSvca.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta charset="utf-8">
<title>donation result</title>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/vendors/air_datapicker/bootstrap.min.css"> --%>
<!-- <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" /> -->






<style>
.glyphicon {
	margin-right: 5px;
}

.thumbnail {
	margin-bottom: 20px;
	padding: 0px;
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
}

.item.list-group-item {
	float: none;
	width: 100%;
	background-color: #eeeeee;
	margin-bottom: 10px;
}

.item.list-group-item:nth-of-type(odd):hover, .item.list-group-item:hover
	{
	background: #FFCBB3;
}

.item.list-group-item .list-group-image {
	margin-right: 10px;
}

.item.list-group-item .thumbnail {
	margin-bottom: 0px;
}

.item.list-group-item .caption {
	padding: 9px 9px 0px 9px;
}

.item.list-group-item:nth-of-type(odd) {
	background: #eeeeee;
}

.item.list-group-item:before, .item.list-group-item:after {
	display: table;
	content: " ";
}

.item.list-group-item img {
	float: left;
}

.item.list-group-item:after {
	clear: both;
}

.list-group-item-text {
	margin: 0 0 11px;
}

</style>
</head>
<body>
	<jsp:useBean id="ResultSvc" scope="page"
		class="com.donation.donation_result.model.Donation_resultService" />

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do">
		<select size="1" name="result_id" onchange="ss(event);">
			<%--  		<c:forEach var="donation_resultVO" items="${ResultSvc.all}" >  --%>
			<%--  			<option value="${donation_resultVO.result_month}">${donation_resultVO.result_month} --%>
			<%--  		</c:forEach>    --%>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>

		</select>
	</FORM>
	<div class="container">
		<div class="well well-sm" style="background-color: #FFCBB3;">
			<strong>瀏覽方式</strong>
			<div class="btn-group">
				<a href="#" id="list" class="btn btn-default btn-sm"><span
					class="glyphicon glyphicon-th-list"> </span>橫式</a> <a href="#"
					id="grid" class="btn btn-default btn-sm"><span
					class="glyphicon glyphicon-th"></span>直式</a>
			</div>
		</div>
		<%@ include file="page1.file"%>
		<div id="products" class="row list-group">
			<c:forEach var="donation_resultVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="item  col-xs-4 col-lg-4">
					<div class="thumbnail">
						<img class="group list-group-image"
							src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=${donation_resultVO.result_id}">
						<div class="caption">
							<h4 class="group inner list-group-item-heading">
								${npoSvc.getOneNpo_info(donation_resultVO.npo_id).npo_name}</h4>
							<p class="group inner list-group-item-text">
								${donation_resultVO.result_content}</p>
							<div class="row">
								<div class="col-xs-12 col-md-6">
									<p class="lead">${donation_resultVO.result_month}月</p>
								</div>
<!-- 								<div class="col-xs-12 col-md-6"> -->
<!-- 									<a class="btn btn-success" href="">我要捐款</a> -->
<!-- 								</div> -->
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<%@ include file="page2.file"%>
	</div>
	<script>
         	function ss(event){
             	 console.log(event.target.value);
              	$.ajax({
              		  url:"<%=request.getContextPath()%>/Result/AjaxresultServelet",           // 資料請求的網址
              		  type: "GET",                  // GET | POST | PUT | DELETE | PATCH
              		  data: {
              			  "action":"getMonth",
              			  "month":event.target.value},                  // 傳送資料到指定的 url
              		  dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
              		  beforeSend: function(event){       // 在 request 發送之前執行
//               		  console.log("yes")
              		  },
              		  statusCode: {                 // 狀態碼
              		    200: function (res) {
              		    },
              		    404: function (res) {
              		    },
              		    500: function (res) {
              		    }
              		  },

              		  success: function(data){      // request 成功取得回應後執行
              			  console.log(data);
              			  $('div.list-group').html("");

              		  		let a = "";
              			  $.each(data, function(index, item){           				  
              				a +=` <div class="item  col-xs-4 col-lg-4">`
                        	<div class="thumbnail">
                        		<img class="group list-group-image" src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=`+item.result_id +`">
                            <div class="caption">
                                <h4 class="group inner list-group-item-heading">
                                   ${npoSvc.getOneNpo_info(item.npo_id).npo_name}</h4>
                                <p class="group inner list-group-item-text">                                
                                `+ item.result_content +`</p>
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <p class="lead">
                                          `+item.result_month +`月</p>
                                    </div>
                                    <div class="col-xs-12 col-md-6">
                                        <!-- <a class="btn btn-success" href="">捐款金額</a> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div> `;
              		});
              			$('div.list-group').html(a);  
              			alert(data[0].result_content);
                    },
              		  error: function(xhr){         // request 發生錯誤的話執行
              		    console.log(xhr);
              		  },
              		  complete: function(xhr){      // request 完成之後執行(在 success / error 事件之後執行)
//               		    console.log("OK");
              		  }
              		});
         	}
        
         	</script>
<!-- 					<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script> -->
<!-- 					<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script> -->
<!-- 					<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script> -->
<%-- 					<script src="<%=request.getContextPath()%>/front-end/donation/JS/nine.js"></script> --%>
<%-- 					 <script src="<%=request.getContextPath()%>/resources/vendors/air_datepicker/bootstrap.min.css"></script> --%>
					
					
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<%--   <script src="<%=request.getContextPath()%>/front-end/donation/JS/nine.js"></script> --%>
    
  
</body>
</html>

