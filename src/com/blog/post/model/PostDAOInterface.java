package com.blog.post.model;

import java.sql.Timestamp;
import java.util.List;

public interface PostDAOInterface {
	public void insert(PostVO postVO);
	public void update(PostVO postVO);
	public void delete(String post_id);
	public PostVO findByPrimaryKey(String post_id);
//	全部的文章
	public List<PostVO> getAll();
//	某個會員全部的文章
	public List<PostVO> getByMemberId(String member_id);
//  某個分類全部的文章
	public List<PostVO> getByPostClass(String post_class);
//	全部的最新文章及熱門文章
	public List<PostVO> getFifthCreateTime();
	public List<PostVO> getFifthPostLike();
//	某個分類的最新文章及熱門文章
	public List<PostVO> getFifthCreateTime(String post_class);
	public List<PostVO> getFifthPostLike(String post_class);
//	某個會員全部的最新文章及熱門文章
	public List<PostVO> getFifthCreateTimeMemberId(String member_id);
	public List<PostVO> getFifthPostLikeMemberId(String member_id);
//	某個會員的某個分類最新文章及熱門文章
	public List<PostVO> getFifthCreateTime(String post_class, String member_id);
	public List<PostVO> getFifthPostLike(String post_class, String member_id);
//	某個會員某個分類的文章
	public List<PostVO> getByMemberId(String member_id, String post_class);
//  更新某篇文章的按讚數
	public void updateByPostId(String post_id, Integer post_like);
//  更新某篇文章的留言數
	public void updateMessageCountByPostId(String post_id, Integer post_message_count);
//	更新某篇文章的分享數
	public void updateShareCountByPostId(String post_id, Integer post_share);
//  取得文章總數
	public Integer getPostCount();
// 取得文章內容前20個字
	public PostVO getPostContent(String post_id);

}
