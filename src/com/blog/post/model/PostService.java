package com.blog.post.model;

import java.sql.Timestamp;
import java.util.List;

public class PostService {
	
	private PostDAOInterface dao;
	
	public PostService() {
//		dao = new PostJDBCDAO();
		dao = new PostJNDIDAO();
	}
	
	public PostVO insertPost(String member_id, String post_class, byte[] post_image1, byte[] post_image2,
			byte[] post_image3, byte[] post_image4, byte[] post_image5, String post_content) {
		PostVO result = new PostVO();
		if(result != null) {
			result.setMember_id(member_id);
			result.setPost_class(post_class);
			result.setPost_image1(post_image1);
			result.setPost_image2(post_image2);
			result.setPost_image3(post_image3);
			result.setPost_image4(post_image4);
			result.setPost_image5(post_image5);
			result.setPost_content(post_content);
			dao.insert(result);
		}
		return result;
	}
	
//	public PostVO insertPost(PostVO postVO) {
//		PostVO result = new PostVO();
//		if(result != null) {
//			dao.insert(postVO);
//		}
//		return postVO;
//	}
	
	public PostVO updatePost(PostVO postVO) {
		PostVO result = new PostVO();
		if(result != null) {
			dao.update(postVO);
		}
		return postVO;
	}
		
	public String deletePost(String post_id) {
		String result = "delete false";
		if(post_id != null) {
			dao.delete(post_id);
			result = "delete success";
		}
		return result;
	}
	
	public PostVO getOnePost(String post_id) {
		PostVO result = null;
		if(post_id != null) {
			result = dao.findByPrimaryKey(post_id);
		}
		return result;
	}
	
	public List<PostVO> getAll() {
		List<PostVO> result = dao.getAll();
		return result;
	}
	
	public List<PostVO> getByMemberId(String member_id) {
		List<PostVO> result = null;
		if(member_id != null) {
			result = dao.getByMemberId(member_id);
		}
		return result;
	}
	
	public List<PostVO> getByPostClass(String post_class){
		List<PostVO> result = null;
		if(post_class != null) {
			result = dao.getByPostClass(post_class);
		}
		return result;
	}
//  全部的最新文章及熱門文章
	public List<PostVO> getFifthCreateTime() {
		List<PostVO> result = dao.getFifthCreateTime();
		return result;
	}
	
	public List<PostVO> getFifthPostLike() {
		List<PostVO> result = dao.getFifthPostLike();
		return result;
	}
//	某個分類的最新文章及熱門文章
	public List<PostVO> getFifthCreateTime(String post_class){
		List<PostVO> result = null;
		if(post_class != null) {
			result = dao.getFifthCreateTime(post_class);
		}
		return result;	
	}
	public List<PostVO> getFifthPostLike(String post_class){
		List<PostVO> result = null;
		if(post_class != null) {
			result = dao.getFifthPostLike(post_class);
		}
		return result;
	}
//  某個會員全部的最新文章及熱門文章
	public List<PostVO> getFifthCreateTimeMemberId(String member_id){
		List<PostVO> result = null;
		if(member_id != null) {
			result = dao.getFifthCreateTimeMemberId(member_id);
		}
		return result;
	}
	public List<PostVO> getFifthPostLikeMemberId(String member_id){
		List<PostVO> result = null;
		if(member_id != null) {
			result = dao.getFifthPostLikeMemberId(member_id);
		}
		return result;
	}
//	某個會員的最新文章及熱門文章
	public List<PostVO> getFifthCreateTime(String post_class, String member_id){
		List<PostVO> result = null;
		if(post_class != null && member_id != null) {
			result = dao.getFifthCreateTime(post_class, member_id);
		}
		return result;
	}
	public List<PostVO> getFifthPostLike(String post_class, String member_id){
		List<PostVO> result = null;
		if(post_class != null && member_id != null) {
			result = dao.getFifthPostLike(post_class, member_id);
		}
		return result;
	}
	
//	某個會員某個分類的文章
	public List<PostVO> getByMemberId(String member_id, String post_class){
		List<PostVO> result = null;
		if(member_id != null && post_class != null) {
			result = dao.getByMemberId(member_id, post_class);
		}
		return result;
	}
	
//	更新某篇文章的按讚數
	public PostVO updateByPostId(String post_id, Integer post_like) {
		PostVO result = new PostVO();
		if(post_id != null) {
			result.setPost_id(post_id);
			result.setPost_like(post_like);
			dao.updateByPostId(post_id, post_like);
		}
		return result;
	}
	
//	更新某篇文章的留言數
	public PostVO updateMessageCountByPostId(String post_id, Integer post_message_count) {
		PostVO result = new PostVO();
		if(post_id != null) {
			result.setPost_id(post_id);
			result.setPost_message_count(post_message_count);
			dao.updateMessageCountByPostId(post_id, post_message_count);
		}
		return result;
	}

//	更新某篇文章的分享數
	public PostVO updateShareCountByPostId(String post_id, Integer post_share) {
		PostVO result = new PostVO();
		if(post_id != null) {
			result.setPost_id(post_id);
			result.setPost_share(post_share);
			dao.updateShareCountByPostId(post_id, post_share);
		}
		return result;
	}
	
//  取得文章的總數
	public Integer getPostCount() {
		Integer result = dao.getPostCount();
		return result;
	}
	
//  取得文章內容的前20個字
	public PostVO getPostContent(String post_id) {
		PostVO result = new PostVO();
		if(post_id != null) {
			result = dao.getPostContent(post_id);
		}
		return result;
	}
	
	public List<PostVO> getPostLikeMost(){
		List<PostVO> result = dao.getPostLikeMost();
		return result;
	}
	
	public static void main(String[] args) {
		PostService test = new PostService();
        //insert
//		System.out.println(test.insertPost("MB00001", "拉拉", null, null, null, null, null, "This is PostService"));
		
		//update
//		PostVO postVO = new PostVO();
//		postVO.setPost_class("哈哈");
//		postVO.setPost_image1(null);
//		postVO.setPost_content("This is PostServiceUpdate");
//		postVO.setPost_id("PID00012");
//		System.out.println(test.updatePost(postVO));
		
		//delete
//		System.out.println(test.deletePost("PID00012"));
		
		//getOnePost
//		System.out.println(test.getOnePost("PID00010"));
		
		//getAll
//		System.out.println(test.getAll());
		
		//getByMemberId
//		System.out.println(test.getByMemberId("MB00001"));
		
		//getByPostClass
//		System.out.println(test.getByPostClass("生活"));
		
		//getFifthCreateTime
//		System.out.println(test.getFifthCreateTime());
		
		//getFifthPostLike
//		System.out.println(test.getFifthPostLike());
		
		//getFifthCreateTime(String post_class)
//		System.out.println(test.getFifthCreateTime("美食"));
		
		//getFifthPostLike(String post_class)
//		System.out.println(test.getFifthPostLike("美食"));
		
		//getFifthCreateTimeMemberId(String member_id)
//		System.out.println(test.getFifthCreateTimeMemberId("MB00001"));

		//getFifthPostLikeMemberId(String member_id)
//		System.out.println(test.getFifthPostLikeMemberId("MB00001"));
		
		//getFifthCreateTime(String post_class, String member_id)
//		System.out.println(test.getFifthCreateTime("美食", "MB00001"));
		
		//getFifthPostLike(String post_class, String member_id)
//		System.out.println(test.getFifthPostLike("美食", "MB00001"));
		
		//getByMemberId(String member_id, String post_class)
//		System.out.println(test.getByMemberId("MB00001", "生活"));
		
		//updateByPostId(String post_id, Integer post_like)
//		System.out.println(test.updateByPostId("PID00010", 6));
		
		//updateMessageCountByPostId
//		System.out.println(test.updateMessageCountByPostId("PID00010", 20));
		
		//updateMessageCountByPostId
//		System.out.println(test.updateShareCountByPostId("PID00010", 9));
	}
}
