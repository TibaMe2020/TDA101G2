<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.post.model.*"%>

<%
//     MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
//     String member_id = memberVO.getMember_id();
    
    String member_id = request.getParameter("member_id");
	PostService postService = new PostService();
	
	List<PostVO> list = postService.getByMemberId(member_id, "購物");
	pageContext.setAttribute("list", list);

	List<PostVO> list1 = postService.getFifthCreateTime("購物", member_id);
	pageContext.setAttribute("list1", list1);
	List<PostVO> postContents1 = new ArrayList<PostVO>();
    for(PostVO postVO : list1){
    	PostVO postContent = postService.getPostContent(postVO.getPost_id());
    	postContents1.add(postContent);
    }
    pageContext.setAttribute("postContents1", postContents1);

	List<PostVO> list2 = postService.getFifthPostLike("購物", member_id);
	pageContext.setAttribute("list2", list2);
	List<PostVO> postContents2 = new ArrayList<PostVO>();
    for(PostVO postVO : list2){
    	PostVO postContent = postService.getPostContent(postVO.getPost_id());
    	postContents2.add(postContent);
    }
    pageContext.setAttribute("postContents2", postContents2); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyBlogShopping</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/blog/css/MyBlog.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
	<script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
</head>
<body class="body">
	<div class="container">
		<div class="row cover">
			<input class="my_cover_title" type="text"
				value="櫻桃小丸子的懶惰人生 || 想冬眠一輩子">
		</div>
		<div class="row">
			<!-- container左欄 -->
			<div class="col-2 padding_left">
				<div class="personal_profile">
					<button class="edit_button" style="outline: none;">
						<span class="edit_my_profile"> 	
							<a class="edit">
								<i id="edit" class="fas fa-edit"></i>
							</a>
						</span>
					</button>
					<figure class="profile_figure">
						<img class="profile_image" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">
					</figure>
					<div class="profile_info">
						<span class="profile_info"> 
							暱稱:<span class="nickname_span"><%=member_id%></span>
						</span> 
						<span class="profile_info"> 
							寵物:<span class="class_span">柴犬</span>
						</span>
					</div>
				</div>

				<div class="post_class">
					<h4 class="post_class_title">文章分類</h4>
						<ul class="categories">
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/MyBlog.jsp?member_id=<%=member_id%>" style="text-decoration: none; color: #13406A;">全部</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/MyBlogLife.jsp?member_id=<%=member_id%>" style="text-decoration: none; color: #13406A;">生活</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/MyBlogShopping.jsp?member_id=<%=member_id%>" style="text-decoration: none; color: #13406A;">購物</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/MyBlogFood.jsp?member_id=<%=member_id%>" style="text-decoration: none; color: #13406A;">美食</a>
							</li>
							<li class="categories_li" style="border-bottom: 1px solid #13406A;">
								<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/MyBlogTravel.jsp?member_id=<%=member_id%>" style="text-decoration: none; color: #13406A;">旅遊</a>
							</li>
							<li>
								<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/MyBlogOthers.jsp?member_id=<%=member_id%>" style="text-decoration: none; color: #13406A;">其他</a>
							</li>
						</ul>
				</div>

				<div class="follow_blogger">
					<a href="#" style="text-decoration: none; color: #13406A;">
						<h4 class="follow_blogger_title">關注部落客</h4>
					</a>
				</div>

				<div class="saved_post">
					<a href="#" style="text-decoration: none; color: #13406A;">
						<h4 class="saved_post_title">收藏文章</h4>
					</a>
				</div>

				<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/HomePage.jsp" style="text-decoration: none; color:#13406A;">
                    <input class="back_to_homepage" type="button" value="回部落格">
                </a>
			</div>
			<!-- container中間 -->
			<div id="middle" class="col-7 padding_middle">
				<div class="write_a_post">
					<div class="post">
						<figure class="post_figure">
							<img class="post_blogger_picture" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">
						</figure>
						<span class="nickname"><%=member_id%></span>
					</div>
					<div class="wanted_post">
						<textarea class="my_text" placeholder="我也想發文..." style="width: 585px; height: 50px"></textarea>
					</div>
					<div>
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" style="float: right; margin: 5px 20px;">我要發文</button>
					</div>
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="outline:none;">
							        	<span aria-hidden="true">&times;</span>
							        </button>
							    </div>
								<div class="modal-body">
									<form action="<%=request.getContextPath()%>/front-end/blog/MyBlog.jsp" class="post" method="POST" enctype="multipart/form-data">
										<div class="add_a_post">
											<div class="post">
												<figure class="post_figure">
													<img class="post_blogger_picture"
														src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">
												</figure>
												<span class="nickname">櫻桃小丸子</span>
											</div>
											<table class="table table-borderless">
												<tbody>
													<tr>
														<td class="input">
															<div class="d-flex align-items-center justify-content-end">
																<span>文章分類 : </span>
															</div>
														</td>
														<td>
															<div>
																<select class="custom-select">
																	<option selected value="生活">生活</option>
																	<option value="購物">購物</option>
																	<option value="美食">美食</option>
																	<option value="旅遊">旅遊</option>
																	<option value="其他">其他</option>
																</select>
															</div>
														</td>
													</tr>
													<tr>
														<td class="input">
															<div class="d-flex align-items-center justify-content-end">
																<span>圖片1 : </span>
															</div>
														</td>
														<td>
															<div class="input-group mb-3">
																<div class="custom-file">
																	<input type="file" class="custom-file-input" id="inputGroupFile01"> 
																	<label class="custom-file-label" for="inputGroupFile01">Choose file</label>
																</div>
															</div>
														</td>
													</tr>
													<tr>
														<td class="input">
															<div class="d-flex align-items-center justify-content-end">
																<span>圖片2 : </span>
															</div>
														</td>
														<td>
															<div class="input-group mb-3">
																<div class="custom-file">
																	<input type="file" class="custom-file-input" id="inputGroupFile02"> 
																	<label class="custom-file-label" for="inputGroupFile02">Choose file</label>
																</div>
															</div>
														</td>
													</tr>
													<tr>
														<td class="input">
															<div class="d-flex align-items-center justify-content-end">
																<span>圖片3 : </span>
															</div>
														</td>
														<td>
															<div class="input-group mb-3">
																<div class="custom-file">
																	<input type="file" class="custom-file-input" id="inputGroupFile03"> 
																	<label class="custom-file-label" for="inputGroupFile03">Choose file</label>
																</div>
															</div>
														</td>
													</tr>
													<tr>
														<td class="input">
															<div class="d-flex align-items-center justify-content-end">
																<span>圖片4 : </span>
															</div>
														</td>
														<td>
															<div class="input-group mb-3">
																<div class="custom-file">
																	<input type="file" class="custom-file-input" id="inputGroupFile04"> 
																	<label class="custom-file-label" for="inputGroupFile04">Choose file</label>
																</div>
															</div>
														</td>
													</tr>
													<tr>
														<td class="input">
															<div class="d-flex align-items-center justify-content-end">
																<span>圖片5 : </span>
															</div>
														</td>
														<td>
															<div class="input-group mb-3">
																<div class="custom-file">
																	<input type="file" class="custom-file-input" id="inputGroupFile05"> 
																	<label class="custom-file-label" for="inputGroupFile05">Choose file</label>
																</div>
															</div>
														</td>
													</tr>
													<tr>
														<td class="input">
															<div
																class="d-flex align-items-center justify-content-end">
																<span>文章內容 : </span>
															</div>
														</td>
														<td>
															<div class="input-group">
																<textarea class="form-control"
																	aria-label="With textarea"></textarea>
															</div>
														</td>
													</tr>
													<tr>
														<td class="input"></td>
														<td style="text-align: end;">
															<button type="button" class="btn btn-secondary" data-dismiss="modal">取消發文</button>
															<input id="submit" type="submit" class="btn btn-outline-dark edit-blog" value="確認發文">
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>

				</div>

				<c:forEach var="postVO" items="${list}">

					<div id="${postVO.post_id}" class="each_post">
						<div class="post">
							<figure class="post_figure">
								<img class="post_blogger_picture"
									src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">
							</figure>
							<span class="nickname">${postVO.member_id}</span>
							<button class="delete_button" style="outline: none;">
								<span class="delete_post_icon"> <i
									class="fas fa-trash-alt"></i>
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
								<% for (int i = 1; i <= 5; i++) { %>
									<% if (i == 1) { %>
										<div class="carousel-item active">
											<img class="post_image" src="<%=request.getContextPath()%>/Post/DBGifReader2?post_id=${postVO.post_id}&count=<%=i%>">
										</div>
									<% } else { %>
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
							<p class="post_content">${postVO.post_content}</p>
						</div>

						<div class="post_functions">
							<div class="post_like">
								<button class="post_like_button">
									<span class="post_like_icon"> 
										<i id="unlike" class="fas fa-thumbs-up"></i>
									</span>
								</button>
								<br> 
								<span class="post_like_count">${postVO.post_like}</span>
							</div>

							<div class="post_message">
								<button class="post_message_button">
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

							<div class="each_message">
								<figure class="message_figure">
									<img class="message_blogger_picture" src="https://stickershop.line-scdn.net/stickershop/v1/product/583/LINEStorePC/main.png;compress=true">
								</figure>
								<div class="message_person">
									<span class="message_nickname">XXXXX</span> 
									<br>
									<div class="message_content">
										<div style="display: inline;">
											<input id="content" type="text" style="width: 90%; background-color: lightgray; border: 1px solid lightgray;">
										</div>
										<div style="display: inline; margin-left: 15px;">
											<button class="send_button">
												<span class="send_icon" style="margin-top: 5px; font-size: 20px; color: #13406A;">
													<i class="fas fa-paper-plane"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>

						</div>

					</div>
				</c:forEach>

			</div>
			<!-- container右欄 -->
			<div class="col-3 padding_right">
				<div class="new_post">
					<h4 class="new_post_title">最新文章</h4>
					<c:forEach var="postContent" items="${postContents1}">
						<div class="each_new_post">
							<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postContent.post_id}" style="text-decoration: none; color: #13406A;">
								${postContent.post_content} 
							</a>
						</div>
					</c:forEach>
				</div>

				<div class="hot_post">
					<h4 class="hot_post_title">熱門文章</h4>
					<c:forEach var="postContent" items="${postContents2}">
						<div class="each_hot_post">
							<a class="a_tag" href="<%=request.getContextPath()%>/front-end/blog/SinglePost.jsp?post_id=${postContent.post_id}" style="text-decoration: none; color: #13406A;">
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