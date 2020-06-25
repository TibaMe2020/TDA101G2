package com.blog.saved.model;

import java.util.List;

public interface SavedDAOInterface {
	public void insert(SavedVO savedVO);
	public void delete(String saved_post_id);
	public SavedVO findByPrimaryKey(String saved_post_id);
	public List<SavedVO> getAll();
	public List<SavedVO> getByMemberId(String member_id);
	public List<String>  getPost_idByMemberId(String member_id);
	public void delete(String post_id, String member_id);
	public List<SavedVO> getByPostId(String post_id);
}
