<%@page import="com.blog.message.model.MessageService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.follow.model.*" %>
<%@ page import="com.blog.saved.model.*" %>
<%@ page import="com.blog.post.model.*" %>

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
<%@ include file="/front-end/member/header.jsp"%>
<%		

	FollowService followService = new FollowService();
	List<FollowVO> list = followService.getByMemberId(member_id);
	pageContext.setAttribute("list", list);
	List<MemberVO> memberList1 = new ArrayList<MemberVO>();
	for(FollowVO followVO : list){
		MemberVO member = mbSvc.getOne(followVO.getFollowed_member_id());
		memberList1.add(member);
	}
	pageContext.setAttribute("memberList1", memberList1);
	
	List<FollowVO> list1 = followService.getAllFollowMe(member_id);
	pageContext.setAttribute("list1", list1);
	List<MemberVO> memberList2 = new ArrayList<MemberVO>();
	for(FollowVO followVO : list1){
		MemberVO member = mbSvc.getOne(followVO.getMember_id());
		memberList2.add(member);
	}
	pageContext.setAttribute("memberList2", memberList2);
// 	System.out.println(memberList2);
	
	SavedService savedService = new SavedService();
	List<SavedVO> list2 = savedService.getByMemberId(member_id);
	pageContext.setAttribute("list2", list2);	
	
	PostService postService = new PostService();
	List<PostVO> list3 = new ArrayList<PostVO>();
	for(SavedVO savedVO : list2){
		PostVO postVO = postService.getOnePost(savedVO.getPost_id());
		list3.add(postVO);		
	}
	pageContext.setAttribute("list3", list3);
	
	List<PostVO> postContents = new ArrayList<PostVO>();
	for(PostVO postVO : list3){
		PostVO postContent = postService.getPostContent(postVO.getPost_id());
		postContents.add(postContent);
	}
	pageContext.setAttribute("postContents", postContents);
	
	List<MemberVO> memberList = mbSvc.getAllBlogerInfo();
	pageContext.setAttribute("memberList", memberList);
%>
	<div class="container" id="mybackcontainer">
  	<div class="row">
    	<div class="col-3 padding_left">
      	<div class="follow_blogger">
        	<h4 class="follow_blogger_title">我關注的部落客</h4>
          <c:forEach var="followVO" items="${list}">
          	<div class="each_follow_blogger">
	          	<div>
	          		<a href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=${followVO.followed_member_id}">
			          	<figure class="follow_figure">
			            	<img class="follow_blogger_picture" src="<%=request.getContextPath()%>/member/profileImage?member_id=${followVO.followed_member_id}">
			            </figure>
			            <c:forEach var="member" items="${memberList1}">
			            	<c:if test="${followVO.followed_member_id == member.member_id}">
			            		<span class="nickname">${member.nickname}</span>
			            	</c:if>
			            </c:forEach>
			          </a>
		            <button class="follow_button" value="followed" id="${followVO.follow_id}"　style="outline: none;">
		            	<span class="follow_icon">
		              	<i id="like" class="fas fa-heart"></i>
		            	</span>
		          	</button> 
	          	</div>
	          </div>
          </c:forEach>
        </div>
      </div>
      
      <div class="col-3 padding_middle">
      	<div class="follow_me_blogger">
          <h4 class="follow_me_blogger_title">關注我的部落客</h4>
          <c:forEach var="followVO" items="${list1}">
          	<div>
	          	<a href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=${followVO.member_id}">      	
		          	<div class="each_follow_me_blogger">
			          	<figure class="follow_me_figure">
			            	<img class="follow_me_blogger_picture" src="<%=request.getContextPath()%>/member/profileImage?member_id=${followVO.member_id}">
			            </figure>
			            <c:forEach var="member" items="${memberList2}">
				            <c:if test="${followVO.member_id == member.member_id}">
				            	<span class="nickname">${member.nickname}</span>
				            </c:if>
			            </c:forEach>
			          </div>
		          </a>	
	          </div> 
          </c:forEach>
        </div>
      </div>

      <div class="col-6 padding_right">
      	<div class="saved_post">
        	<h4 class="saved_post_title">收藏文章</h4>
        	<c:forEach var="savedVO" items="${list2}">
          	<div class="each_saved_post" value="${savedVO.saved_post_id}">
	          	<div class="saved_post_blogger">
	          	<c:forEach var="postVO" items="${list3}">
		          	<c:if test="${(savedVO.post_id) == (postVO.post_id)}">
		          		<a href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postVO.post_id}">
			            	<figure class="saved_post_figure">
			              	<img class="saved_post_blogger_picture" src="<%=request.getContextPath()%>/member/profileImage?member_id=${postVO.member_id}">
			            	</figure>
			            </a>
			          </c:if>
		          </c:forEach>
		            <c:forEach var="postVO" items="${list3}">
		            	<c:if test="${(savedVO.post_id) == (postVO.post_id)}">
		            		<c:forEach var="member" items="${memberList}">
		            			<c:if test="${(postVO.member_id) == (member.member_id)}">
				            		<span class="nickname">${member.nickname}</span>
				            	</c:if>
		            		</c:forEach>     		
		            	</c:if>
	            	</c:forEach>
	            	<button class="saved_button">
	              	<span class="saved_post_icon">
	                	<i id="saved" class="fas fa-bookmark"></i>
	              	</span>
	            	</button>
	         		</div>
	         		<c:forEach var="postContent" items="${postContents}">
		         		<c:if test="${savedVO.post_id == postContent.post_id}">
		         			<div class="saved_post_content">
			            	<span style="width: 100%;">${postContent.post_content}</span>
			          	</div>
		         		</c:if>
	         		</c:forEach>
	          </div>
          </c:forEach>
        </div>
      </div>
   	</div>
	</div>
	<%@ include file="/front-end/member/footer.jsp"%>
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