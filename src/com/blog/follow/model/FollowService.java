package com.blog.follow.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FollowService {
	
	private FollowDAOInterface dao;
	
	public FollowService() {
		dao = new FollowJDBCDAO();
//		dao = new FollowJNDIDAO();
	}
	
	private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) 
	  {
	    final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
	     
	    return t -> 
	    {
	      final List<?> keys = Arrays.stream(keyExtractors)
	                  .map(ke -> ke.apply(t))
	                  .collect(Collectors.toList());
	       
	      return seen.putIfAbsent(keys, Boolean.TRUE) == null;
	    };
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
	
	@SuppressWarnings("unchecked")
	public List<FollowVO> getByMemberId(String member_id){
		List<FollowVO> result = null;
		if(member_id != null) {
			result = dao.getByMemberId(member_id);
			result = result.stream()
					.filter(distinctByKeys(FollowVO::getMember_id,FollowVO::getFollowed_member_id))
					.collect(Collectors.toList());
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
