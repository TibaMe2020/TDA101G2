package com.blog.follow.model;

import java.util.List;

public interface FollowDAOInterface {
	public void insert(FollowVO followVO);
	public void delete(String follow_id);
	public FollowVO findByPrimaryKey(String follow_id);
	public List<FollowVO> getAll();
	public List<FollowVO> getByMemberId(String member_id);
	public List<String> getFollowedMemberIdByMemberId(String member_id);
	public List<FollowVO> getAllFollowMe(String member_id);
}
