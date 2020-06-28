<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%--     pageEncoding="utf-8"%> --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">



  <style>
  * {
  box-sizing: border-box;
}

/* Set a background color */
body {
  background-color: #474e5d;
  font-family: Helvetica, sans-serif;
}

/* The actual timeline (the vertical ruler) */
.timeline {
  position: relative;
  max-width: 1200px;
  margin: 0 auto;
}

/* The actual timeline (the vertical ruler) */
.timeline::after {
  content: '';
  position: absolute;
  width: 6px;
  background-color: white;
  top: 0;
  bottom: 0;
  left: 50%;
  margin-left: -3px;
}

/* Container around content */
.container {
  padding: 10px 40px;
  position: relative;
  background-color: inherit;
  width: 50%;
}

/* The circles on the timeline */
.container::after {
  content: '';
  position: absolute;
  width: 25px;
  height: 25px;
  right: -17px;
  background-color: white;
  border: 4px solid #FF9F55;
  top: 15px;
  border-radius: 50%;
  z-index: 1;
}

/* Place the container to the left */
.left {
  left: -289;
}

/* Place the container to the right */
.right {
  left: 50%;
}

/* Add arrows to the left container (pointing right) */
.left::before {
  content: " ";
  height: 0;
  position: absolute;
  top: 22px;
  width: 0;
  z-index: 1;
  right: 30px;
  border: medium solid white;
  border-width: 10px 0 10px 10px;
  border-color: transparent transparent transparent white;
}

/* Add arrows to the right container (pointing left) */
.right::before {
  content: " ";
  height: 0;
  position: absolute;
  top: 22px;
  width: 0;
  z-index: 1;
  left: 30px;
  border: medium solid white;
  border-width: 10px 10px 10px 0;
  border-color: transparent white transparent transparent;
}

/* Fix the circle for containers on the right side */
.right::after {
  left: -16px;
}

/* The actual content */
.content {
  padding: 20px 30px;
  background-color: white;
  position: relative;
  border-radius: 6px;
}

/* Media queries - Responsive timeline on screens less than 600px wide */
@media screen and (max-width: 600px) {
/* Place the timelime to the left */
  .timeline::after {
    left: 31px;
  }

/* Full-width containers */
  .container {
    width: 100%;
    padding-left: 70px;
    padding-right: 25px;
  }

/* Make sure that all arrows are pointing leftwards */
  .container::before {
    left: 60px;
    border: medium solid white;
    border-width: 10px 10px 10px 0;
    border-color: transparent white transparent transparent;
  }

/* Make sure all circles are at the same spot */
  .left::after, .right::after {
    left: 15px;
  }

/* Make all right containers behave like the left ones */
  .right {
    left: 0%;
  }
}
  </style>
</head>
<body>
	<jsp:useBean id="ResultSvc" scope="page"
		class="com.donation.donation_result.model.Donation_resultService" />


		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do">
		選擇成果月份:<select size="1" name="result_id" onchange="ss(event);">
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
			<c:forEach var="donation_resultVO" items="${list}">
			  <div class="timeline">
    		<div class="container left">
      					<div class="content">
       					 <img class="group list-group-image"
							src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=${donation_resultVO.result_id}">
     				 </div>
    		</div>
    		<div class="container right">
     		 <div class="content">
       			 <h2>${donation_resultVO.result_month}月</h2>
        		<p>${donation_resultVO.result_content}</p>
      		</div>
   			 </div>
  			</div>
			</c:forEach>
<%-- 		<%@ include file="page2.file"%> --%>
<a href="<%=request.getContextPath()%>/front-end/donation/myMain/donation.jsp">回到主頁</a>


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
//               				a +=` <div class="item  col-xs-4 col-lg-4">
//                         	<div class="thumbnail">
<%--                         		<img class="group list-group-image" src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=`+item.result_id +`"> --%>
//                             <div class="caption">
//                                 <h4 class="group inner list-group-item-heading">
//                                    ${npoSvc.getOneNpo_info(item.npo_id).npo_name}</h4>
//                                 <p class="group inner list-group-item-text">                                
//                                 `+ item.result_content +`</p>
//                                 <div class="row">
//                                     <div class="col-xs-12 col-md-6">
//                                         <p class="lead">
//                                           `+item.result_month +`月</p>
//                                     </div>
//                                     <div class="col-xs-12 col-md-6">
//                                         <!-- <a class="btn btn-success" href="">捐款金額</a> -->
//                                     </div>
//                                 </div>
//                             </div>
//                         </div>
//                     </div>
//                     </div> `;
							a+=` <div class="timeline">
							<div class="container left">
		      					<div class="content">
		       					 <img class="group list-group-image"
									src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=`+item.result_id +`">
									 <h2>`+item.result_month +`月</h2>
						        	 <p>`+ item.result_content +`</p>
									</div>
									</div> `;
              		});
              			$('div.list-group').html(a);  
//               			alert(data[0].result_content);
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
       	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"></script>
    <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>
