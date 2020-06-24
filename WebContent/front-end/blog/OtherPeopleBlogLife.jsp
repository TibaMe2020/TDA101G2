<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.blog.post.model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>OtherPeopleBlogLife</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/blog/css/OtherPeopleBlog.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
</head>
<body class="body">
<%@ include file="/front-end/member/header.jsp"%>
<%
	System.out.println("我的member_id:" + member_id);
	
	String other_member_id = request.getParameter("other_member_id");
	pageContext.setAttribute("other_member_id", other_member_id);
	System.out.println("別人的other_member_id:" + other_member_id);
	
	PostService postService = new PostService();
	List<PostVO> list = postService.getByMemberId(other_member_id, "生活");
	pageContext.setAttribute("list", list);
	System.out.println(other_member_id + "生活分類的文章" + list);
	
	List<PostVO> list1 = postService.getFifthCreateTime("生活", other_member_id);
	pageContext.setAttribute("list1", list1);
	System.out.println(list1);
	List<PostVO> postContents1 = new ArrayList<PostVO>();
    for(PostVO postVO : list1){
    	PostVO postContent = postService.getPostContent(postVO.getPost_id());
    	postContents1.add(postContent);
    }
    pageContext.setAttribute("postContents1", postContents1);
	
	List<PostVO> list2 = postService.getFifthPostLike("生活", other_member_id);
	pageContext.setAttribute("list2", list2);
	System.out.println(list2);
	List<PostVO> postContents2 = new ArrayList<PostVO>();
    for(PostVO postVO : list2){
    	PostVO postContent = postService.getPostContent(postVO.getPost_id());
    	postContents2.add(postContent);
    }
    pageContext.setAttribute("postContents2", postContents2);

%>
	<div class="container">
        <div class="row cover">
            <input class="my_cover_title" type="text" value="柴犬先生的平凡生活||下輩子想當狗">
        </div>
        <div class="row">
            <!-- container左欄 -->
            <div class="col-2 padding_left">
                <div class="personal_profile">
                	
		                <button class="follow_button" 
               			<c:forEach items="${list4}" var="followed" >
			                <c:if test="${(followed.followed_member_id == other_member_id)}">
			                	data-follow-id="${followed.follow_id}" 
			                </c:if>
			     		</c:forEach>   	
		                value="${list3.contains(other_member_id)?'followed':'unfollow'}" id="<%=other_member_id%>" style="outline: none;">
				        	<span class="follow_blogger" style="color: ${list3.contains(other_member_id)?'#EE6464':'lightgray'}"> 	
				            	<i id="like" class="fas fa-heart"></i>
				            </span>
				        </button>
			        
                    <figure class="profile_figure">
                        <img class="profile_image" src="https://img.ltn.com.tw/Upload/partner/page/2019/12/19/191219-6235-01-TwNaw.jpg">
                    </figure>
                    <div class="profile_info">
						<span class="profile_info"> 
							暱稱:<span class="nickname_span">${other_member_id}</span>
						</span> 
						<span class="profile_info"> 
							寵物:<span class="class_span"></span>
						</span>
					</div>
                </div>

                <div class="post_class">
                    <h4 class="post_class_title">文章分類</h4>
                    <ul class="categories">
                        <li class="category">
                        	<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=<%=other_member_id%>" style="text-decoration: none; color:#13406A;">全部</a>
                        </li>
                        <li class="category">
                        	<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlogLife.jsp?other_member_id=<%=other_member_id%>" style="text-decoration: none; color:#13406A;">生活</a>
                        </li>
                        <li class="category">
                        	<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlogShopping.jsp?other_member_id=<%=other_member_id%>" style="text-decoration: none; color:#13406A;">購物</a>
                        </li>
                        <li class="category">
                        	<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlogFood.jsp?other_member_id=<%=other_member_id%>" style="text-decoration: none; color:#13406A;">美食</a>
                        </li>
                        <li class="category">
                        	<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlogTravel.jsp?other_member_id=<%=other_member_id%>" style="text-decoration: none; color:#13406A;">旅遊</a>
                        </li>
                        <li>
                        	<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlogOthers.jsp?other_member_id=<%=other_member_id%>" style="text-decoration: none; color:#13406A;">其他</a>
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

                <c:forEach var="postVO" items="${list}">
<%--                 <a href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postVO.post_id}" style="text-decoration: none; color:#13406A;"> --%>
					<div class="each_post" id="${postVO.post_id}" value="${postVO.member_id}">
		            	<div class="post">
		                    <figure class="post_figure">
		                        <img class="post_blogger_picture" src="https://img.ltn.com.tw/Upload/partner/page/2019/12/19/191219-6235-01-TwNaw.jpg">
		                    </figure>
		                    <span class="nickname">${postVO.member_id}</span>
		                    <button class="saved_button" style="outline: none;" value="${savedlist2.contains(postVO.post_id)?'1':'0'}">
									<span class="saved_post_icon" style="color: ${savedlist2.contains(postVO.post_id)?'black':'lightgray'}"> 
										<i class="fas fa-bookmark"></i>
									</span>
							</button>	
		                </div>
				
		                <div class="carousel slide" id="carousel-demo${postVO.post_id}">
		                	<ol class="carousel-indicators">
		                        <li data-target="#carousel-demo${postVO.post_id}" data-slide-to="0" class="active"></li>
		                        <li data-target="#carousel-demo${postVO.post_id}" data-slide-to="1"></li>
		                        <li data-target="#carousel-demo${postVO.post_id}" data-slide-to="2"></li>
		                        <li data-target="#carousel-demo${postVO.post_id}" data-slide-to="3"></li>
		                        <li data-target="#carousel-demo${postVO.post_id}" data-slide-to="4"></li>
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
		                        <a href="#carousel-demo${postVO.post_id}" class="carousel-control-prev" data-slide="prev">
		                            <span class="carousel-control-prev-icon"></span>
		                        </a>
		                        <a href="#carousel-demo${postVO.post_id}" class="carousel-control-next" data-slide="next">
		                            <span class="carousel-control-next-icon"></span>
		                        </a>
		                   	</div>
		                </div>    
		                    
						<div class="post_content">
		                	<p class="post_content">
		                    	${postVO.post_content}
		                    </p>
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
	                            <button class="post_share_button">
									<span class="post_share_icon"> 
										<i class="fas fa-share-square"></i>
									</span>
								</button>
	                            <br>
	                            <span class="post_share_count">${postVO.post_share}</span>
	                        </div>
	                    </div>
   
	                   	<div class="message">
						</div>
	                  
		            </div>	
<!-- 		        </a> -->
				</c:forEach>
                    
            </div> 
            <!-- container右欄 -->
            <div class="col-3 padding_right">
                <div class="new_post">
                    <h4 class="new_post_title">最新文章</h4>
                    <c:forEach var="postContent" items="${postContents1}">
                    	<div class="each_new_post">
	                        <a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postContent.post_id}" style="text-decoration: none; color:#13406A;">
	                       		${postContent.post_content}                                             
	                        </a>
	                    </div>
                    </c:forEach>
                </div>

                <div class="hot_post">
                    <h4 class="hot_post_title">熱門文章</h4>
                    <c:forEach var="postContent" items="${postContents2}">
                    	<div class="each_hot_post">
	                        <a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postContent.post_id}" style="text-decoration: none; color:#13406A;">
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
			//關注或是取消關注
			$(document).on("click", "button.follow_button", function(){	
				let status = $(this).attr("value");
				let it = $(this);
				if(status == "followed"){
					let follow_id = it.attr("data-follow-id");
					$.ajax({
						url:"<%=request.getContextPath()%>/Post/AjaxServlet",
						type:"GET",
						data:{
							"action": "changeFollow",
							"status": "unfollow",
							"follow_id": follow_id,
						},
						dataType:"json",
						error: function (xhr) {         
	    	                console.log("錯誤");
	    	            },
	    	            success: function(data){
	    	            	console.log("成功取消關注");
	    	            	it.find("span.follow_blogger").attr("style", "color: lightgray");
	    	            	it.attr("value", "unfollow");
	    	            	window.location.href = "<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=${other_member_id}";
	    	            }
					});
				}else {
					let member_id = "MB00001";
					let followed_member_id = it.attr("id");
					$.ajax({
						url:"<%=request.getContextPath()%>/Post/AjaxServlet",
						type:"GET",
						data:{
							"action": "changeFollow",
							"status": "followed",
							"member_id": member_id,
							"followed_member_id": followed_member_id
						},
						dataType:"json",
						error: function (xhr) {         
	    	                console.log("錯誤");
	    	            },
	    	            success: function(data){
	    	            	console.log("成功關注");
	    	            	it.find("span.follow_blogger").attr("style", "color: #EE6464");
	    	            	it.attr("value", "followed");
	    	            	window.location.href = "<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=${other_member_id}";
	    	            }
					});
				}
				
			});
			
			// 點擊留言,留言才顯示
			$(document).on("click", "button.post_message_button", function(){    	    	
		    	let post_id = $(this).closest("div.each_post").attr("id");
		    	let it = $(this);
		    	console.log(post_id);
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
						
						console.log(post_id);
						let leavemessage = '<div class="each_message">'+
										   '<figure class="message_figure">'+
					                       '<img class="message_blogger_picture" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">'+     
						                   '</figure>'+      
						                   '<div class="message_person">'+     
						                   '<span class="message_nickname">xxxxxxxxxx</span>'+     
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
	// 						it.parents("div.message").prev().find("button.post_message_button").click().click();
	    	            }
	    			});
				}
			});
			
			// 留言數送出
			$(document).on("click", "button.send_button", function(){
				let message_count = $(this).parents("div.message").prev().find("span.post_message_count").text();
				let message_content = $(this).parents("div.message_content").find("#content").val();
				let post_id = $(this).attr("value");
	// 			console.log(post_id);
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
	    	            	console.log(data);
						  	let messagecount = '<span class="post_message_count">' + $(data).attr("post_message_count") + '</span>';
						  	console.log(messagecount);
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
		            	console.log(data);
					  	let sharecount = '<span class="post_share_count">' + $(data).attr("post_share") + '</span>';
					  	console.log(sharecount);
					  	it.parents("div.post_share").find("span.post_share_count").replaceWith(sharecount);
		            }
				});
			});
			
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
						error : function(
								xhr) {
							console.log("錯誤");
						},
						success : function(
								data) {
							console.log(data);
						}										
																
					});
				}
				
			});
			
			//錯誤驗證
			$(document).on("click", "#confirm_send", function(){
				let member_id = $("#memberid").val().trim();
				let post_content = $("#post_content").val().trim();
	
				if(member_id == ""){
					$("#memberid").removeClass("is-valid");
					$("#memberid").addClass("is-invalid");
				}else{
					$("#memberid").removeClass("is-invalid");
					$("#memberid").addClass("is-valid");
				}
				
				if(post_content == ""){
					$("#post_content").removeClass("is-valid");
					$("#post_content").addClass("is-invalid");
				}else{
					$("#post_content").removeClass("is-invalid");
					$("#post_content").addClass("is-valid");
				}
				
				if(post_content.length < 20){
					$("#post_content").removeClass("is-valid");
					$("#post_content").addClass("is-invalid");
				}else{
					$("#post_content").removeClass("is-invalid");
					$("#post_content").addClass("is-valid");
				}
				
			});
			//驗證過才可以送出
			$("#confirm_send").click(function(){
				let member_id = $("#memberid").val();
				let post_content = $("#post_content").val();
				
		        if(member_id != "" && post_content != "" && post_content.length >= 20){
		            $("#insertform").submit();
		        }
		    });
		});
    </script>

</body>
</html>