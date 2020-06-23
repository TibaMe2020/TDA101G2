<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blog.post.model.*"%>

<%
	PostService postService = new PostService();
	List<PostVO> list = postService.getByPostClass("購物");
	pageContext.setAttribute("list", list);
	
	List<PostVO> list1 = postService.getFifthCreateTime("購物");
    pageContext.setAttribute("list1", list1);
    List<PostVO> postContents1 = new ArrayList<PostVO>();
	for (PostVO postVO : list1) {
		PostVO postContent = postService.getPostContent(postVO.getPost_id());
		postContents1.add(postContent);
	}
    pageContext.setAttribute("postContents1", postContents1);
    
    List<PostVO> list2 = postService.getFifthPostLike("購物");
    pageContext.setAttribute("list2", list2);
    List<PostVO> postContents2 = new ArrayList<PostVO>();
	for (PostVO postVO : list2) {
		PostVO postContent = postService.getPostContent(postVO.getPost_id());
		postContents2.add(postContent);
	}
    pageContext.setAttribute("postContents2", postContents2); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shopping</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/blog/css/HomePage.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
</head>
<body class="body">
    <div class="container">

        <div class="row">
            <!-- container左欄 -->
            <div class="col-2 padding_left">

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
                
                <div class="recommend_blogger">
                    <h4 class="recommend_blogger_title">推薦部落客</h4>
                    <div class="each_recommend_blogger">
                        <figure class="recommend_figure">
                            <img class="recommend_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
                        </figure>
                        <span class="nickname">
                            <a class="a_tag" href="#" style="text-decoration: none; color:#13406A;">噢!兔子</a>
                        </span>
                    </div>
                    <div class="each_recommend_blogger">
                        <figure class="recommend_figure">
                            <img class="recommend_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
                        </figure>
                        <span class="nickname">
                            <a class="a_tag" href="#" style="text-decoration: none; color:#13406A;">我愛馬卡龍</a>
                        </span>
                    </div>
                    <div class="each_recommend_blogger">
                        <figure class="recommend_figure">
                            <img class="recommend_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
                        </figure>
                        <span class="nickname">
                            <a class="a_tag" href="#" style="text-decoration: none; color:#13406A;">蘇太太</a>
                        </span>
                    </div>
                            
                </div>
                
                <div class="button" style="margin-top: 10px; text-align: center;">
                    <input class="to_my_blog" type="button" value="我的部落格">
                    <a href="http://localhost:8081/TDA101G2-0604/front-end/blog/HomePage.jsp"><input class="back_to_homepage" type="button" value="回部落格"></a>
                </div>
            </div>
            <!-- container中間 -->
            <div class="col-7 padding_middle">
            	<div class="write_a_post">
                	<div class="post">
                    	<figure class="post_figure">
                        	<img class="post_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
                        </figure>
                        <span class="nickname">Tom</span>
                    </div>
                    <div class="wanted_post">
                        <textarea class="my_text" placeholder="我也想發文..." style="width: 585px; height: 50px"></textarea>
                    </div>
                    <div>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" style="float: right; margin: 5px 10px;">
                            	我要發文
                        </button>
                    </div>
                            
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    	<span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="" class="post" method="POST" enctype="multipart/form-data">
                                    	<div class="write_a_post">
                                        	<div class="post">
                                                <figure class="post_figure">
                                                    <img class="post_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
                                                </figure>
                                                <span class="nickname">Tom</span>
                                            </div>
                                            <div class="wanted_post">
                                                <textarea class="my_text" placeholder="我也想發文..." style="width: 100%; max-height: 500px; outline: none; border: 1px solid lightgray; border-radius: 3px;"></textarea>  
                                            </div>
                                            <div class="button">
                                                <div class="" style="margin: 10px 0;">
                                                    <div style="margin-top: 5px; width: 100%;">圖片1 : <input type="file" name="post_image1"></div>
                                                    <div style="margin-top: 5px; width: 100%;">圖片2 : <input type="file" name="post_image2"></div>
                                                    <div style="margin-top: 5px; width: 100%;">圖片3 : <input type="file" name="post_image3"></div>
                                                    <div style="margin-top: 5px; width: 100%;">圖片4 : <input type="file" name="post_image4"></div>
                                                    <div style="margin-top: 5px; width: 100%;">圖片5 : <input type="file" name="post_image5"></div>
                                                </div>
                                                <select class="choose_class" name="your_class">
                                                    <option value="life">生活</option>
                                                    <option value="shopping">購物</option>
                                                    <option value="food">美食</option>
                                                    <option value="travel">旅遊</option>
                                                    <option value="others">其他</option>
                                                </select>
                                                <input class="confirm" type="button" value="確認發文">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                	<button type="button" class="btn btn-secondary" data-dismiss="modal">取消發文</button>
                                    <a href="HomePage.jsp"><button type="button" id="send_post" class="btn btn-primary">我要發文</button></a>
                                    <!-- <button type="button" id="send_post" class="btn btn-primary">我要發文</button> -->
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

				<c:forEach var="postVO" items="${list}">
					<div class="each_post" id="${postVO.post_id}">
	                    <div class="post">
	                        <figure class="post_figure">
	                            <img class="post_blogger_picture" src="https://images.669pic.com/element_banner/41/83/83/73/c95ce96fa9002df8623201c605601bef.jpg">
	                        </figure>
	                        <span class="nickname">${postVO.member_id}</span>
	                        <button class="saved_button">
	                        	<span class="saved_post_icon">
		                            <i class="far fa-bookmark"></i>
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
                                <span class="post_share_icon">
                                    <i class="fas fa-share-square"></i>
                                </span>
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
                                    <span class="message_nickname">XXXXXX</span>
                                    <br>
                                    <div class="message_content">
                                        <div style="display: inline;">
                                            <input id="content" type="text" style="width: 90%; background-color: lightgray; border: 1px solid lightgray;">
                                        </div>
                                        <div style="display: inline; margin-left: 15px;">
                                            <button class="send_button">
                                                <span class="send_icon" style="margin-top: 5px; font-size: 20px; color: #13406A;">
                                                    <i class="fas fa-paper-plane"></i>
                                                    <!-- <i class="far fa-paper-plane"></i> -->
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
    <script src="<%=request.getContextPath()%>/front-end/blog/js/HomePage.js"></script>
</body>
</html>