<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.follow.model.*" %>
<%@ page import="com.blog.saved.model.*" %>
<%@ page import="com.blog.post.model.*" %>

<%	
	String member_id = request.getParameter("member_id");
	System.out.println(member_id);
	
	FollowService followService = new FollowService();
	List<FollowVO> list = followService.getByMemberId(member_id);
	pageContext.setAttribute("list", list);
	
	List<FollowVO> list1 = followService.getAllFollowMe(member_id);
	pageContext.setAttribute("list1", list1);
	
	SavedService savedService = new SavedService();
	List<SavedVO> list2 = savedService.getByMemberId(member_id);
	pageContext.setAttribute("list2", list2);
		
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyBack</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/blog/css/MyBack.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
</head>
<body class="body">
	<div class="container">
        <div class="row">
            <div class="col-3 padding_left">

                <div class="follow_blogger">
                    <h4 class="follow_blogger_title">我關注的部落客</h4>
                    <c:forEach var="followVO" items="${list}">
                    	<div class="each_follow_blogger">
	                        <figure class="follow_figure">
	                            <img class="follow_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
	                        </figure>
	                        <span class="nickname">
	                            <a class="a_tag" href="#" style="text-decoration: none; color:#13406A;">${followVO.followed_member_id}</a>
	                        </span>
	                        <button class="follow_button" value="followed" id="${followVO.follow_id}"　style="outline: none;">
	                            <span class="follow_icon">
	                                <i id="like" class="fas fa-heart"></i>
	                            </span>
	                        </button> 
	                    </div>
                    </c:forEach>
                </div>
            </div>
            
            <div class="col-5 padding_middle">
                <div class="follow_me_blogger">
                    <h4 class="follow_me_blogger_title">關注我的部落客</h4>
                    <c:forEach var="followVO" items="${list1}">
                    	<div class="each_follow_me_blogger">
	                        <figure class="follow_me_figure">
	                            <img class="follow_me_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
	                        </figure>
	                        <span class="nickname">
	                            <a class="a_tag" href="#" style="text-decoration: none; color:#13406A;">${followVO.member_id}</a>
	                        </span>
	                    </div>
                    </c:forEach>
                </div>
            </div>

            <div class="col-4 padding_right">
                <div class="saved_post">
                    <h4 class="saved_post_title">收藏文章</h4>
                    <c:forEach var="savedVO" items="${list2}">
                    	<div class="each_saved_post" value="${savedVO.saved_post_id}">
	                        <div class="saved_post_blogger">
	                            <figure class="saved_post_figure">
	                                <img class="saved_post_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
	                            </figure>
	                            <span class="nickname">
	                                <a class="a_tag" href="#" style="text-decoration: none; color:#13406A;">發${savedVO.post_id}的人</a>
	                            </span>
	                            <button class="saved_button">
	                                <span class="saved_post_icon">
	                                    <i id="saved" class="fas fa-bookmark"></i>
	                                </span>
	                            </button>
	                        </div>
	                        <div class="saved_post_content">
	                            <span style="width: 100%;">${savedVO.post_id}</span>
	                        </div>
	                    </div>
                    </c:forEach>
            	</div>
        	</div>
    	</div>
	</div>
	<script src="<%=request.getContextPath()%>/resources/vendors/jquery/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/resources/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script>
		window.addEventListener("load", function(){
			//取消關注
			$("button.follow_button").on("click", function(){
				let follow_id = $(this).attr("id");		
				let status = $(this).attr("value");
				let it = $(this);
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
    	            	it.find("span.follow_icon").attr("style", "color: lightgray");
    	            	it.parents("div.each_follow_blogger").remove();
    	            }
				});
			});
			
			//取消收藏文章
			$("button.saved_button").on("click", function(){
				$(this).find("span.saved_post_icon").attr("style", "color: lightgray");
				let saved_post_id = $(this).parents("div.each_saved_post").attr("value");				
				let it = $(this);
				$.ajax({
					url:"<%=request.getContextPath()%>/Post/AjaxServlet",
					type:"GET",
					data:{
						"action": "cancelSaved",
						"saved_post_id": saved_post_id,
					},
					dataType:"json",
					error: function (xhr) {         
    	                console.log("錯誤");
    	            },
    	            success: function(data){
    	            	console.log("成功取消收藏");
    	            	it.parents("div.each_saved_post").remove();
    	            }
				});
			});
		});
	</script>
</body>
</html>