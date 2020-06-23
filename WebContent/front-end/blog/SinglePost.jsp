<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.post.model.*"%>

<%
// 	PostVO postVO = (PostVO) session.getAttribute("postVO");
// 	String member_id = postVO.getMember_id();
	
	String post_id = request.getParameter("post_id");
	
	PostService postService = new PostService();
	PostVO postVO = postService.getOnePost(post_id);
	pageContext.setAttribute("postVO", postVO);
	
	String member_id = postVO.getMember_id();
	
	List<PostVO> list1 = postService.getFifthCreateTimeMemberId(member_id);
	pageContext.setAttribute("list1", list1);
	List<PostVO> postContents1 = new ArrayList<PostVO>();
	for(PostVO mypostVO : list1){
		PostVO postContent = postService.getPostContent(mypostVO.getPost_id());
		postContents1.add(postContent);
	}
	pageContext.setAttribute("postContents1", postContents1);
	
	List<PostVO> list2 = postService.getFifthPostLikeMemberId(member_id);
	pageContext.setAttribute("list2", list2);
	List<PostVO> postContents2 = new ArrayList<PostVO>();
	for(PostVO mypostVO : list2){
		PostVO postContent = postService.getPostContent(mypostVO.getPost_id());
		postContents2.add(postContent);
	}
	pageContext.setAttribute("postContents2", postContents2);
%>

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
	<div class="container">
        <div class="row cover">
            <input class="my_cover_title" type="text" value="柴犬先生的平凡生活||下輩子想當狗">
        </div>
        <div class="row">
            <!-- container左欄 -->
            <div class="col-2 padding_left">
                <div class="personal_profile">
                    <button class="follow_button" style="outline: none;">
                        <span class="follow_blogger"> 	
                            <i id="like" class="fas fa-heart" style="color: lightgray;"></i>
                        </span>
                    </button>
                    <a href="<%=request.getContextPath()%>/front-end/blog/OtherPeopleBlog.jsp?member_id=<%=member_id%>">
                    	<figure class="profile_figure">
	                        <img class="profile_image" src="<%=request.getContextPath()%>/resources/images/img06.jpg">
	                    </figure>
                    </a>
                    <div class="profile_info">
                        <span class="profile_info"> 
                                                                          暱稱:<span class="nickname_span">${postVO.member_id}</span>
                        </span> 
                        <span class="profile_info"> 
                           	 寵物:<span class="class_span">柴犬</span>
                        </span>
                    </div>
                </div>

                <div class="post_class">
                    <h4 class="post_class_title">文章分類</h4>
                    <ul class="categories">
                        <li style="border-bottom: 1px solid #13406A;"><a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp" style="text-decoration: none; color:#13406A;">全部</a></li>
                        <li style="border-bottom: 1px solid #13406A;"><a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/Life.jsp" style="text-decoration: none; color:#13406A;">生活</a></li>
                        <li style="border-bottom: 1px solid #13406A;"><a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/Shopping.jsp" style="text-decoration: none; color:#13406A;">購物</a></li>
                        <li style="border-bottom: 1px solid #13406A;"><a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/Food.jsp" style="text-decoration: none; color:#13406A;">美食</a></li>
                        <li style="border-bottom: 1px solid #13406A;"><a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/Travel.jsp" style="text-decoration: none; color:#13406A;">旅遊</a></li>
                        <li><a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/Others.jsp" style="text-decoration: none; color:#13406A;">其他</a></li>
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
	                    <span class="nickname">${postVO.member_id}</span>
	                    <button class="saved_button" style="outline: none;" value="0">
	                    	<span class="saved_post_icon" style="color: lightgray">
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
	<script src="<%=request.getContextPath()%>/resources/vendors/jquery/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>