package com.blog.message.model;

import java.sql.Timestamp;
import java.util.List;

public class MessageService {

	private MessageDAOInterface dao;
	
	public MessageService() {
//		dao = new MessageJDBCDAO();
		dao = new MessageJNDIDAO();
	}
	
	public MessageVO insertMessage(String member_id, String post_id, String message_content) {
		MessageVO result = new MessageVO();
		if(result != null) {
			result.setMember_id(member_id);
			result.setPost_id(post_id);
			result.setMessage_content(message_content);
			dao.insert(result);
		}
		return result;
	}
	
	public MessageVO updateMessage(String message_id, String member_id, String post_id, String message_content) {
		MessageVO result = new MessageVO();
		if(result != null) {
			result.setMessage_id(message_id);
			result.setMember_id(member_id);
			result.setPost_id(post_id);
			result.setMessage_content(message_content);
//			result.setCreate_time(create_time);
//			result.setUpdate_time(update_time);
			dao.update(result);
		}
		return result;
	}
	
	public String deleteMessage(String message_id) {
		String result = "delete false";
		if(message_id != null) {
			dao.delete(message_id);
			result = "delete success";
		}
		return result;
	}
	
	public MessageVO getOneMessage(String message_id) {
		MessageVO result = null;
		if(message_id != null) {
			result = dao.findByPrimaryKey(message_id);
		}
		return result;
	}
	
	public List<MessageVO> getAll(){
		List<MessageVO> result = dao.getAll();
		return result;
	}
	
	public List<MessageVO> getByMemberId(String member_id) {
		List<MessageVO> result = null;
		if(member_id != null) {
			result = dao.getByMemberId(member_id);
		}
		return result;
	}
	
	public List<MessageVO> getByPostId(String post_id){
		List<MessageVO> result = null;
		if(post_id != null) {
			result = dao.getByPostId(post_id);
		}
		return result;
	}
	
	public static void main(String[] args) {
		MessageService test = new MessageService();
		
//		System.out.println(test.insertMessage("MB00001", "PID00010", "This is MessageService"));
//		System.out.println(test.updateMessage("MID00021", "MB00001", "PID00010", "This is update MessageService"));
//		System.out.println(test.deleteMessage("MID00021"));
//		System.out.println(test.getOneMessage("MID00020"));
//		System.out.println(test.getAll());
//		System.out.println(test.getByMemberId("MB00010"));
		System.out.println(test.getByPostId("PID00001"));
	}
}
