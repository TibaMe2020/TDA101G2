package com.blog.saved.model;

import java.util.List;

public class SavedService {
	
	private SavedDAOInterface dao;
	
	public SavedService() {
//		dao = new SavedJDBCDAO();
		dao = new SavedJNDIDAO();
	}
	
	public SavedVO insertSaved(String member_id, String post_id) {
		SavedVO result = new SavedVO();
		if(result != null) {
			result.setMember_id(member_id);
			result.setPost_id(post_id);
			dao.insert(result);
		}
		return result;
	}
	
	public String deleteSaved(String saved_post_id) {
		String result = "delete false";
		if(saved_post_id != null) {
			dao.delete(saved_post_id);
			result = "delete success";
		}
		return result;
	}
	
	public SavedVO getOneSaved(String saved_post_id) {
		SavedVO result = null;
		if(saved_post_id != null) {
			result = dao.findByPrimaryKey(saved_post_id);
		}
		return result;
	}
	
	public List<SavedVO> getAll(){
		List<SavedVO> result = dao.getAll();
		return result;
	}
	
	public List<SavedVO> getByMemberId(String member_id){
		List<SavedVO> result = null;
		if(member_id != null) {
			result = dao.getByMemberId(member_id);
		}
		return result;
	}
	public List<String> getPost_idByMemberId(String member_id){
		List<String> result = null;
		if(member_id != null) {
			result = dao.getPost_idByMemberId(member_id);
		}
		return result;
	}
	
	public String delete(String post_id, String member_id) {
		String result = "delete false";
		if(post_id != null && member_id != null) {
			dao.delete(post_id, member_id);
			result = "delete success";
		}
		return result;
	}
	
	public static void main(String[] args) {
		SavedService test = new SavedService();
		
//		System.out.println(test.insertSaved("MB00001", "PID00010"));
//		System.out.println(test.deleteSaved("SID00011"));
//		System.out.println(test.getOneSaved("SID00010"));
//		System.out.println(test.getAll());
//		System.out.println(test.getByMemberId("MB00001"));
//		System.out.println(test.delete("PID00010", "MB00001"));
	}
}
