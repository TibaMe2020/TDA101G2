package com.blog.follow.model;

import java.util.List;

public class FollowService {
	
	private FollowDAOInterface dao;
	
	public FollowService() {
		dao = new FollowJDBCDAO();
//		dao = new FollowJNDIDAO();
	}
	
	public FollowVO insertFollow(String member_id, String followed_member_id) {
		FollowVO result = new FollowVO();
		if(result != null) {
			result.setMember_id(member_id);
			result.setFollowed_member_id(followed_member_id);
			dao.insert(result);
		}
		return result;
	}
	
	public String deleteFollow(String follow_id) {
		String result = "delete false";
		if(follow_id != null) {
			dao.delete(follow_id);
			result = "delete success";
		}
		return result;
	}
	
	public FollowVO getOneFollow(String follow_id) {
		FollowVO result = null;
		if(follow_id != null) {
			result = dao.findByPrimaryKey(follow_id);
		}
		return result;
	}
	
	public List<FollowVO> getAll(){
		List<FollowVO> result = dao.getAll();
		return result;
	}
	
	public List<FollowVO> getByMemberId(String member_id){
		List<FollowVO> result = null;
		if(member_id != null) {
			result = dao.getByMemberId(member_id);
		}
		return result;
	}
	
	public List<String> getFollowedMemberIdByMemberId(String member_id){
		List<String> result = null;
		if(member_id != null) {
			result = dao.getFollowedMemberIdByMemberId(member_id);
		}
		return result;
	}
	
	public List<FollowVO> getAllFollowMe(String member_id){
		List<FollowVO> result = null;
		if(member_id != null) {
			result = dao.getAllFollowMe(member_id);
		}
		return result;
	}
	
	public static void main(String[] args) {
		FollowService test = new FollowService();
		
//		System.out.println(test.insertFollow("MB00001", "MB00010"));
//		System.out.println(test.deleteFollow("FID00011"));
//		System.out.println(test.getOneFollow("FID00010"));
//		System.out.println(test.getAll());
//		System.out.println(test.getByMemberId("MB00001"));
		System.out.println(test.getFollowedMemberIdByMemberId("MB00001"));
//		System.out.println(test.getAllFollowMe("MB00001"));
	}
}
