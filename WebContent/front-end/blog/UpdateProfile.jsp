<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.post.model.*"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>UpdateProfile</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/blog/css/UpdateProfile.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
	<script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
</head>
<body class="body">
<%@ include file="/front-end/member/header.jsp"%>
<%
	PostService postService = new PostService();
	List<PostVO> list = postService.getByMemberId(member_id);
	pageContext.setAttribute("list", list);
	
	List<PostVO> list1 = postService.getFifthCreateTimeMemberId(member_id);
	pageContext.setAttribute("list1", list1);
	List<PostVO> postContents1 = new ArrayList<PostVO>();
	for(PostVO postVO : list1){
		PostVO postContent = postService.getPostContent(postVO.getPost_id());
		postContents1.add(postContent);
	}
	pageContext.setAttribute("postContents1", postContents1);
	
	List<PostVO> list2 = postService.getFifthPostLikeMemberId(member_id);
	pageContext.setAttribute("list2", list2);
	List<PostVO> postContents2 = new ArrayList<PostVO>();
	for(PostVO postVO : list2){
		PostVO postContent = postService.getPostContent(postVO.getPost_id());
		postContents2.add(postContent);
	}
	pageContext.setAttribute("postContents2", postContents2);
%>
	<div class="container">
		<div class="row cover" style="background-image: url('<%=request.getContextPath()%>/member/coverImage?member_id=${memberVO.member_id}');">
			<div class="my_cover_title">${memberVO.blog_name}</div>
		</div>
		<div class="row">
			<!-- container左欄 -->
			<div class="col-2 padding_left">
				<div class="personal_profile">
					<figure class="profile_figure">
						<img class="profile_image" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">
					</figure>
					<div class="profile_info">
						<span class="profile_info"> 
							暱稱:<span class="nickname_span">${memberVO.nickname}</span>
						</span> 
						<span class="profile_info"> 
							寵物:<span class="class_span">${memberVO.pet_class}</span>
						</span>
					</div>
				</div>

				<div class="post_class">
					<h4 class="post_class_title">文章分類</h4>
						<ul class="categories">
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a href="<%=request.getContextPath()%>/front-end/blog/MyBlog.jsp">全部</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a href="<%=request.getContextPath()%>/front-end/blog/MyBlogLife.jsp">生活</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a href="<%=request.getContextPath()%>/front-end/blog/MyBlogShopping.jsp">購物</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a href="<%=request.getContextPath()%>/front-end/blog/MyBlogFood.jsp">美食</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a href="<%=request.getContextPath()%>/front-end/blog/MyBlogTravel.jsp">旅遊</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/front-end/blog/MyBlogOthers.jsp">其他</a>
							</li>
						</ul>
				</div>

				<div class="follow_blogger">
					<a href="<%=request.getContextPath()%>/front-end/blog/MyBack.jsp?member_id=<%=member_id%>">
						<h4 class="follow_blogger_title">我的關注</h4>
					</a>
				</div>

				<div class="saved_post">
					<a href="<%=request.getContextPath()%>/front-end/blog/MyBack.jsp?member_id=<%=member_id%>">
						<h4 class="saved_post_title">我的收藏</h4>
					</a>
				</div>

				<a href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp">
        	<input class="back_to_homepage" type="button" value="回部落格">
        </a>
        
			</div>
			<!-- container中間 -->
			<div id="middle" class="col-7 padding_middle">
				<div class="update">
					<form method="POST" class="updateform" action="<%=request.getContextPath()%>/member/controller" enctype="multipart/form-data">
						<table class="table table-borderless">
							<tbody>
								<tr>
									<td class="input-label">
										<div class="d-flex align-items-center justify-content-end">
											<span>暱稱 :</span>
										</div>
									</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" placeholder="${memberVO.nickname}" name="nickname">
										</div>
									</td>
								</tr>
								<tr>
									<td class="input-label">
										<div class="d-flex align-items-center justify-content-end">
											<span>寵物 :</span>
										</div>
									</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" placeholder="寵物" name="pet_class">
										</div>
									</td>
								</tr>
								<tr>
									<td class="input-label">
										<div class="d-flex align-items-center justify-content-end">
											<span>部落格名稱 :</span>
										</div>
									</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" placeholder="部落格名稱" name="blog_name">
										</div>
									</td>
								</tr>
								<tr>
									<td class="input-label">
										<div class="d-flex align-items-center justify-content-end">
											<span>圖片 :</span>
										</div>
									</td>
									<td>
										<div class="input-group mb-3">
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="inputGroupFile" name="blog_cover_image"> 
												<label class="custom-file-label" for="inputGroupFile">Choose file</label>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="input-label"></td>
									<td style="text-align: end;">
										<a href="<%=request.getContextPath()%>/front-end/blog/MyBlog.jsp?member_id=<%=member_id%>">
											<input type="button" class="btn btn-dark" value="取消修改">
										</a>	
										<input id="submit" type="submit" class="btn btn-outline-dark edit-blog" value="修改">
										<input type="hidden" name="action" value="update_blog_info">
									</td>
								</tr>
							</tbody>
						</table>
					</form>
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
</body>
</html>