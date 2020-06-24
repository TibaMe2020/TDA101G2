package com.blog.message.model;

import java.util.List;

public interface MessageDAOInterface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO);
	public void delete(String message_id);
	public MessageVO findByPrimaryKey(String message_id);
	public List<MessageVO> getAll();
	public List<MessageVO> getByMemberId(String member_id);
	public List<MessageVO> getByPostId(String post_id);
}
