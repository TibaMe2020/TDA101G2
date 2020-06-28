<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.donation.donation_result.model.*"%>  
<%
	String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Donation_resultService ResultSvca = new Donation_resultService(); //介面
    List<Donation_resultVO> resultlist = ResultSvca.getAll();
    pageContext.setAttribute("list",resultlist);
%>


<html>
<head>
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
  background-color:	#F0F0F0;
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
  </style>
<body>

	<jsp:useBean id="ResultSvc" scope="page"
		class="com.donation.donation_result.model.Donation_resultService" />


		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Result/result.do">
		<label class="monthlabel">選擇成果月份:</label><select class="monthselect" size="1" name="result_id" onchange="ss(event);">
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
			  <div class="all">
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
  			</div>
			
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
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
              			  $('div.all').html("");

              		  		let a = "";
              			  $.each(data, function(index, item){
              			  console.log(item.result_id);
          					a +=`
          					<div class="timeline"> 
    							<div class="container left">
    		      					<div class="content">
    		       						<img class="group list-group-image" src="<%=request.getContextPath()%>/Result/DBGifReader4?result_id=`+ item.result_id +`">
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
             	</script>
              			  
   
</body>
</html>