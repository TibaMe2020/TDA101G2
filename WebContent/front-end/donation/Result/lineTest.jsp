<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.donation.donation_result.model.*"%>  
<%@ page import="com.donation.npo_info.model.*"%>

<%
	String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Donation_resultService ResultSvca = new Donation_resultService(); //介面
    List<Donation_resultVO> resultlist = ResultSvca.getAll();
    pageContext.setAttribute("list",resultlist);
%>
<%Npo_infoVO npo_infoVO = (Npo_infoVO) request.getAttribute("npo_infoVO");  %>

<jsp:useBean id ="npoSvc" scope="page" class="com.donation.npo_info.model.Npo_infoService"/>



<html>
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
<title></title>

</head>

  <style>
  select.monthselect{
  right: -880px;
    position: relative;
  }
  label.monthlabel{
  right: -880px;
    position: relative;
  }
  * {
  box-sizing: border-box;
}

/* Set a background color */
body {
  background-color:	#e5ccdc;
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
  left: 0;
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
label.monthlabel{
color:white;
}
span.spanc{
color:white;
}
a.aahref{
color:white;
}
  </style>
<style>
      #button {
        display: inline-block;
        background-color: #9FC4EF;
        width: 50px;
        height: 30px;
        text-align: center;
        border-radius: 4px;
        position: fixed;
        bottom: 30px;
        right: 30px;
        transition: background-color .3s,
          opacity .5s, visibility .5s;
        opacity: 0;
        visibility: hidden;
        z-index: 1000;
      }
      #button::after {
        font-weight: normal;
        font-style: normal;
        font-size: 2em;
        line-height: 50px;
        color: #fff;
      }
      #button:hover {
        cursor: pointer;
        background-color: #C8E5FF;
      }
      #button:active {
        background-color: #C8E5FF;
      }
      #button.show {
        opacity: 1;
        visibility: visible;
      }
/*       @media (min-width: 500px) { */
/*         .content { */
/*           width: 43%; */
/*         } */
        #button {
          margin: 30px;
        }
      }
</style>
<body>

	<jsp:useBean id="ResultSvc" scope="page"
		class="com.donation.donation_result.model.Donation_resultService" />

        
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do">
		<label class="monthlabel">選擇成果月份:</label><select class="monthselect" size="1" name="result_id" onchange="ss(event);">
			<option value="0">查全部月份</option>
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
	 <i class="fas fa-bone"></i>
<a class="aahref" href="<%=request.getContextPath()%>/front-end/donation/myMain/donation.jsp">公益首頁</a><span class="spanc"> > 成果發表</span>
	    <a id="button">TOP</a>
	
			  <div class="all">
 <c:forEach var="donation_resultVO" items="${list}">
			  <div class="timeline">
    		<div class="container left">
      					<div class="content">
       					 <img class="group list-group-image"
							src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=${donation_resultVO.result_id}" style="width:320px; height:200px">
     				 </div>
    		</div>
    		<div class="container right">
     		 <div class="content">
     		 <c:forEach var="npo_infoVO" items="${npoSvc.all}">
     		 	<h1>
     		 	
     		 	<c:if test="${donation_resultVO.npo_id==npo_infoVO.npo_id}">
     		 	${npo_infoVO.npo_name} 	
     			</c:if>	
     		 	</h1>
     		 	
     		 </c:forEach>
       			 <h2>${donation_resultVO.result_month}月</h2>
        		<p>${donation_resultVO.result_content}</p>
      		</div>
   			 </div>
  			</div>
</c:forEach>
  			</div>
			
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
			<script>
         	function ss(event){
             	 console.log(event.target.value);
             	 if(event.target.value == 0){
             		 window.location.reload();
             			 
             	 }
             	 else{
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
              			  $('div.all').html("");

              		  		let a = "";
              			  $.each(data, function(index, item){
              			  console.log(item.result_id);
          					a +=`
          					<div class="timeline"> 
    							<div class="container left">
    		      					<div class="content">
    		       						<img class="group list-group-image" src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=`+ item.result_id +`" style="width:320px; height:200px">
   									 <h1>

   									`+ item.npo_id +`
   									 </h1> 
    		       						<h2>`+ item.result_month +`月</h2>
    						        	 <p>`+ item.result_content +`</p>
    								</div>
    							</div>
    						</div> `;
                  		});
                  			$('div.all').html(a);  
//                   			alert(data[0].result_content);
                        },
                  		  error: function(xhr){         // request 發生錯誤的話執行
                  		    console.log(xhr);
                  		  },
                  		  complete: function(xhr){      // request 完成之後執行(在 success / error 事件之後執行)
//                   		    console.log("OK");
                  		  }
                  		});
             	}        
             	 }  
             	</script>
              			  
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/donation/JS/Top.js"></script>

</body>
</html>