<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.post.model.*"%>
<%@ page import="com.blog.follow.model.*"%>
<%@ page import="com.blog.saved.model.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SinglePost</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/blog/css/SinglePost.css">
  <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
</head>
<body class="body">
<%@ include file="/front-end/member/header.jsp"%>
<%
	String post_id = request.getParameter("post_id");	
	pageContext.setAttribute("post_id", post_id);

	PostService postService = new PostService();
	PostVO postVO = postService.getOnePost(post_id);
	pageContext.setAttribute("postVO", postVO);
	
	MemberVO postOwner = mbSvc.getOne(postVO.getMember_id());
	pageContext.setAttribute("postOwner", postOwner);
	String OwnerMemberId = postOwner.getMember_id();
	pageContext.setAttribute("OwnerMemberId", OwnerMemberId);
	
	FollowService followService = new FollowService();
	List<String> followList = followService.getFollowedMemberIdByMemberId(member_id);
	pageContext.setAttribute("followList", followList);
	
	SavedService savedService = new SavedService();
	List<String> savedList = savedService.getPost_idByMemberId(member_id);
	pageContext.setAttribute("savedList", savedList);
	
	List<PostVO> list1 = postService.getFifthCreateTimeMemberId(postVO.getMember_id());
	pageContext.setAttribute("list1", list1);
	List<PostVO> postContents1 = new ArrayList<PostVO>();
	for(PostVO mypostVO : list1){
		PostVO postContent = postService.getPostContent(mypostVO.getPost_id());
		postContents1.add(postContent);
	}
	pageContext.setAttribute("postContents1", postContents1);
	
	List<PostVO> list2 = postService.getFifthPostLikeMemberId(postVO.getMember_id());
	pageContext.setAttribute("list2", list2);
	List<PostVO> postContents2 = new ArrayList<PostVO>();
	for(PostVO mypostVO : list2){
		PostVO postContent = postService.getPostContent(mypostVO.getPost_id());
		postContents2.add(postContent);
	}
	pageContext.setAttribute("postContents2", postContents2);
%>
	<div class="container">
  	<div class="row cover" style="background-image: url('<%=request.getContextPath()%>/member/coverImage?member_id=${postOwner.member_id}');">
    	<input class="my_cover_title" type="text" value="${postOwner.blog_name}">
    </div>
    <div class="row">
    	<!-- container左欄 -->
     	<div class="col-2 padding_left">
      	<div class="personal_profile">
        	<button class="follow_button">
          	<span class="follow_blogger">
            	<i id="like" class="fas fa-heart" style="color: ${followList.contains(OwnerMemberId)?'#EE6464':'lightgray'}"></i>
            </span>
          </button>
          <a href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=<%=member_id%>">
          	<figure class="profile_figure">
	          	<img class="profile_image" src="<%=request.getContextPath()%>/resources/images/img06.jpg">
	        	</figure>
          </a>
          <div class="profile_info">
          	<span class="profile_info"> 
                                     暱稱:<span class="nickname_span">${postOwner.nickname}</span>
            </span>
            <span class="profile_info"> 
           		 寵物:<span class="class_span">${postOwner.pet_class}</span>
            </span> 
          </div>
        </div>
        
				<div class="post_class">
        	<h4 class="post_class_title">文章分類</h4>
          <ul class="categories">
          	<li style="border-bottom: 1px solid #13406A;">
          		<a href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=<%=postVO.getMember_id()%>">全部</a>
          	</li>
            <li style="border-bottom: 1px solid #13406A;">
            	<a href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlogLife.jsp?other_member_id=<%=postVO.getMember_id()%>">生活</a>
            </li>
          	<li style="border-bottom: 1px solid #13406A;">
          		<a href="<%=request.getContextPath()%>/front-end/blog/Shopping.jsp?other_member_id=<%=postVO.getMember_id()%>">購物</a>
          	</li>
          	<li style="border-bottom: 1px solid #13406A;">
          		<a href="<%=request.getContextPath()%>/front-end/blog/Food.jsp?other_member_id=<%=postVO.getMember_id()%>">美食</a>
          	</li>
         		<li style="border-bottom: 1px solid #13406A;">
         			<a href="<%=request.getContextPath()%>/front-end/blog/Travel.jsp?other_member_id=<%=postVO.getMember_id()%>">旅遊</a>
         		</li>
         		<li>
         			<a href="<%=request.getContextPath()%>/front-end/blog/Others.jsp?other_member_id=<%=postVO.getMember_id()%>">其他</a>
         		</li>
         	</ul>    
       	</div>
        
        <div class="button" style="margin-top: 10px; text-align: center;">
        	<a href="<%=request.getContextPath()%>/front-end/blog/MyBlog.jsp?member_id=<%=member_id%>">
          	<input class="to_my_blog" type="button" value="我的部落格">
          </a>
          <a href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp">
          	<input class="back_to_homepage" type="button" value="回部落格">
          </a>
        </div>
                
      </div>
      <!-- container中間 -->
      <div class="col-7 padding_middle">

      	<div class="each_post" id="${post_id}" value="${postVO.member_id}">
        	<div class="post">
	        	<figure class="post_figure">
	          	<img class="post_blogger_picture" src="<%=request.getContextPath()%>/resources/images/img06.jpg">
	        	</figure>
	        	<span class="nickname">${postOwner.nickname}</span>
	        	<button class="saved_button" value="0">
	          	<span class="saved_post_icon" style="color:${savedList.contains(post_id)?'black':'lightgray'}">
		          	<i class="fas fa-bookmark"></i>
		         	</span>
	          </button>
	     		</div>

          <div class="carousel slide" data-ride="carousel" id="carousel-demo">
          	<ol class="carousel-indicators">
            	<li data-target="#carousel-demo" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-demo" data-slide-to="1"></li>
              <li data-target="#carousel-demo" data-slide-to="2"></li>
           		<li data-target="#carousel-demo" data-slide-to="3"></li>
            	<li data-target="#carousel-demo" data-slide-to="4"></li>
          	</ol>
         		<div class="carousel-inner">
            <% for(int i = 1; i <= 5; i++){ %>
		        <% if(i == 1){ %>
				    	<div class="carousel-item active">
			        	<img class="post_image" src="<%=request.getContextPath()%>/Post/DBGifReader2?post_id=${postVO.post_id}&count=<%=i%>">
			       	</div>
						<% }else{%>	
							<div class="carousel-item">
								<img class="post_image" src="<%=request.getContextPath()%>/Post/DBGifReader2?post_id=${postVO.post_id}&count=<%=i%>">
							</div>
						<% }; %>
						<% }; %>
							<a href="#carousel-demo" class="carousel-control-prev" data-slide="prev">
             		<span class="carousel-control-prev-icon"></span>
            	</a>       
	            <a href="#carousel-demo" class="carousel-control-next" data-slide="next">
              	<span class="carousel-control-next-icon"></span>
            	</a>
            </div>
          </div>

          <div class="post_content">
          	<p class="post_content">${postVO.post_content}</p>
          </div>
          
          <div class="post_functions">
          	<div class="post_like">
            	<button class="post_like_button" value="0">
              	<span class="post_like_icon">
                	<i id="unlike" class="fas fa-thumbs-up"></i>
               	</span>
           		</button>
            	<br>
            	<span class="post_like_count">${postVO.post_like}</span>
          	</div>
    
            <div class="post_message">
            	<button class="post_message_button" style="outline: none;">
		          	<span class="post_message_icon">
		            	<i class="fas fa-comment-dots"></i>
		          	</span>
		        	</button> 
		         	<br>	
             	<span class="post_message_count">${postVO.post_message_count}</span>
           	</div>     	   
    
            <div class="post_share">
            	<button class="post_share_button" data-toggle="tooltip" title="copy" data-placement="right" data-src="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postVO.post_id}">
									<span class="post_share_icon"> 
										<i class="fas fa-share-square"></i>
									</span>
								</button>
             	<br>
            	<span class="post_share_count">${postVO.post_share}</span>
         		</div>
          </div>
          
          <div class="message">
          	<!-- 動態生成留言 -->
          </div>
         
        </div>
      </div>
      <!-- container右欄 -->
      <div class="col-3 padding_right">
      	<div class="new_post">
        	<h4 class="new_post_title">最新文章</h4>
          <c:forEach var="postContent" items="${postContents1}">
          	<div class="each_new_post">
	          	<a href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postContent.post_id}">
	            	${postContent.post_content}                                              
	            </a>
	          </div>
          </c:forEach>
      	</div>

        <div class="hot_post">
        	<h4 class="hot_post_title">熱門文章</h4>
          <c:forEach var="postContent" items="${postContents2}">
          	<div class="each_hot_post">
	          	<a href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postContent.post_id}">
	            	${postContent.post_content}
	           	</a>
	          </div>
         	</c:forEach>
        </div>
      </div>
            
    </div>
  </div>
  <%@ include file="/front-end/member/footer.jsp"%>
  <script>
  	window.addEventListener("load", function(){
	  	// 點擊留言,留言才顯示
			$(document).on("click", "button.post_message_button", function(){    	    	
		  	let post_id = $(this).closest("div.each_post").attr("id");
		    let it = $(this);
		    	$.ajax({
		    			url: "<%=request.getContextPath()%>/Post/AjaxServlet",
		          type: "GET",   
		          data: {                       
		              "action" : "getPostId", 
		              "post_id": post_id
		          },                              
		          dataType: "json",
		          error: function (xhr) {         
		              console.log("錯誤");
		          },
		          success: function(datas){    
		     	    	console.log($(datas));
								it.parents("div.post_functions").next().empty();
								$.each(datas, function(index, data){
									let messagecontent = '<div class="each_message">' + 
	             	  	'<figure class="message_figure">' +
		                '<img class="message_blogger_picture" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">' +
		              	'</figure>' +
		              	'<div class="message_person">' +
		                '<span class="message_nickname">'+ $(data).attr("member_id") +'</span>' +
		                '<br>' +
		                '<div class="message_content">' +
		                '<span>' + $(data).attr("message_content") +'</span>' +
		                '</div>' +
		                '</div>' +
		                '</div>';
								it.parents("div.post_functions").next().prepend(messagecontent);
								});
								let leavemessage = '<div class="each_message">'+
									'<figure class="message_figure">'+
								  '<img class="message_blogger_picture" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">'+     
									'</figure>'+      
									'<div class="message_person">'+     
									'<span class="message_nickname">' + $("div.message").attr("id") + '</span>'+     
									'<br>'+        
									'<div class="message_content">'+        
									'<div style="display: inline;">'+         
									'<input id="content" type="text" name="message_content" style="width: 90%; background-color: lightgray; border: 1px solid lightgray;">'+             
									'</div>'+                 
									'<div style="display: inline; margin-left: 15px;">'+             
									'<button class="send_button" value="' + post_id + '">'+             
									'<span class="send_icon" style="margin-top: 5px; font-size: 20px; color: #13406A;">'+                 
									'<i class="fas fa-paper-plane"></i>'+                     
									'</span>'+                         
									'</button>'+                     
									'</div>'+                 
									'</div>'+             
									'</div>'+         
									'</div>';                     
	        			it.parents("div.post_functions").next().append(leavemessage);
	        			it.parents("div.post_functions").next().slideToggle(1000);
		      		}
				});
			});
	  		
	  	// 留言送出 
			$(document).on("click", "button.send_button", function(e){
	    	let post_id = $(this).closest("div.each_post").attr("id");
	    	let message_content = $(this).parents("div.message_content").find("#content").val();
	    	let it = $(this);
	    	if(message_content.trim() != ""){
	    		$.ajax({	
	        		url:"<%=request.getContextPath()%>/Post/AjaxServlet",
	        		type:"GET",
	        		data: {                       
	        	  	"action": "addMessage", 
	        	    "post_id": post_id,
	        	    "member_id": "MB00001",
	        	    "message_content": message_content, 
	        	  },
	        	  dataType: "json",
	        	  error: function (xhr) {         
	        	  	console.log("錯誤");
	        	  },
	        	  success: function(data){
	        	  	it.attr("value", "slide");
	        	    let messagecontent = '<div class="each_message">' + 
	    						'<figure class="message_figure">' +
	    					  '<img class="message_blogger_picture" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">' +
	    					  '</figure>' +
	    					  '<div class="message_person">' +
	    					  '<span class="message_nickname">'+ $(data).attr("member_id") +'</span>' +
	    					  '<br>' +
	    					  '<div class="message_content">' +
	    					  '<span>' + $(data).attr("message_content") +'</span>' +
	    					  '</div>' +
	    					  '</div>' +
	    					  '</div>';
	    					it.parents("div.post_functions").next().prepend(messagecontent);
	    					it.parents("div.message_content").find("#content").val("");
	//     					it.parents("div.message").prev().find("button.post_message_button").click().click();
	        	  }
					});
	    	}
	    });
	    		
	    // 留言數送出
	 		$(document).on("click", "button.send_button", function(){
	    	let message_count = $(this).parents("div.message").prev().find("span.post_message_count").text();
	    	let message_content = $(this).parents("div.message_content").find("#content").val();
	    	let post_id = $(this).attr("value");
	    	let it = $(this);
	    	if(message_content.trim() != ""){
	    		$.ajax({
	        		url:"<%=request.getContextPath()%>/Post/AjaxServlet",
	        		type:"GET",
	        		data: {                       
	        	  	"action": "postMessageCountChange", 
	        	    "post_id": post_id,
								"post_message_count": message_count
	        	  },
	        	  dataType: "json",
	        	  error: function (xhr) {         
	        	  	console.log("錯誤");
	        	 	},
	        	  success: function(data){
	        	  	let messagecount = '<span class="post_message_count">' + $(data).attr("post_message_count") + '</span>';
	    					it.parents("div.message").prev().find("span.post_message_count").replaceWith(messagecount);
	        	  }
	        });
	    	}
	    });
	    		
	    // 按讚數送出               
	    $(document).on("click", "button.post_like_button", function(){
				let post_like = $(this).parents("div.post_like").find("span.post_like_count").text();
				let post_id = $(this).closest("div.each_post").attr("id");
				let it = $(this);
				if($(this).attr("value") == 0){
	    		it.attr("value", "1");
	    		it.find("span.post_like_icon").attr("style", "color: #13406A");
	    		$.ajax({
	    				url: "<%=request.getContextPath()%>/Post/AjaxServlet",
	   					type: "GET",
	   					data: {
	    					"action": "postLikeChange",
	  						"post_id": post_id,
	    					"value": "1",
	    					"post_like": post_like
	    				},
	    			dataType: "json",
	   				error: function(xhr){
	   					console.log("錯誤");
	   				},
	    			success: function(data){
	    				let like_count = '<span class="post_like_count">' + $(data).attr("post_like") + '</span>';
	    				it.parents("div.post_like").find("span.post_like_count").replaceWith(like_count);
	    			}    					
	    	  });
	   		} else{
	   			$(this).attr("value", "0");
	    		$(this).find("span.post_like_icon").attr("style", "color:lightgray");
	    		$.ajax({
	    				url: "<%=request.getContextPath()%>/Post/AjaxServlet",
	    				type: "GET",
	   					data: {
	   						"action": "postLikeChange",
	    					"post_id": post_id,
	    					"value": "0",
	    					"post_like": post_like
	    				},
	    				dataType: "json",
	   					error: function(xhr){
	   						console.log("錯誤");
	   					},
	    				success: function(data){
	    					let like_count = '<span class="post_like_count">' + $(data).attr("post_like") + '</span>';
	    					it.parents("div.post_like").find("span.post_like_count").replaceWith(like_count);
	    				}    					
	    		 });
	   		}
			});
	  
		 	// 分享數送出
			$(document).on("click", "button.post_share_button", function(){
				let post_share = $(this).parents("div.post_share").find("span.post_share_count").text();
				let post_id = $(this).closest("div.each_post").attr("id");
				let it = $(this);
				$.ajax({
						url:"<%=request.getContextPath()%>/Post/AjaxServlet",
						type:"GET",
						data: {                       
		      		"action": "postShareChange", 
		          "post_id": post_id,
								"post_share": post_share
		        },
		        dataType: "json",
		        error: function (xhr) {         
			        console.log("錯誤");
	         },
		       success: function(data){
					   	let sharecount = '<span class="post_share_count">' + $(data).attr("post_share") + '</span>';
							it.parents("div.post_share").find("span.post_share_count").replaceWith(sharecount);
		       }
				});
			});
			// 收藏文章         
			$(document).on("click", "button.saved_button", function(event){
				event.stopPropagation();
				let issaved = $(this).attr("value");
				console.log(issaved);
				let post_id = $(this).closest("div.each_post").attr("id");
				let it = $(this);
				if(issaved == 0){
					it.find("span.saved_post_icon").attr("style", "color: black");
					it.attr("value", "1");
					$.ajax({
	    				url:"<%=request.getContextPath()%>/Post/AjaxServlet",
	    				type:"GET",
	    				data: {                       
	    	      	"action": "savedPost", 
	    	        "value": "1",
	    	        "post_id": post_id,
								"member_id": "MB00001" 
	    	      },
	    	      dataType: "json",
	    	      error: function (xhr) {         
	    	      	console.log("錯誤");
	    	      },
	    	      success: function(data){
	    	      	console.log(data);
							}
	    		});
				}else{
					it.find("span.saved_post_icon").attr("style", "color: lightgray");
					it.attr("value", "0");
					$.ajax({
	    				url:"<%=request.getContextPath()%>/Post/AjaxServlet",
							type : "GET",
							data : {
								"action" : "savedPost",
								"value" : "0",
								"post_id" : post_id,
								"member_id" : "MB00001"
							},
							dataType : "json",
							error : function(xhr) {
								console.log("錯誤");
							},
							success : function(data) {
								console.log(data);
							}										
					});
				}		
			});	
  	});
  </script>
</body>
</html>