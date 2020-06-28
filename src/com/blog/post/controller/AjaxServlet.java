package com.blog.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.follow.model.FollowService;
import com.blog.message.model.MessageService;
import com.blog.message.model.MessageVO;
import com.blog.post.model.PostService;
import com.blog.saved.model.SavedService;
import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		MessageService messageService = new MessageService();
		PostService postService = new PostService();
		SavedService savedService = new SavedService();
		FollowService followService = new FollowService();
		MemberService memberService = new MemberService();
		
		if("getPostId".equals(action)) {
			String post_id = request.getParameter("post_id");
			out.print(gson.toJson(messageService.getByPostId(post_id)));		
		}
		
		if("addMessage".equals(action)) {
			String post_id = request.getParameter("post_id");
			String member_id = request.getParameter("member_id");
			String message_content = request.getParameter("message_content");
//			System.out.println(post_id + "|" + member_id + "|" + message_content);
			
			out.print(gson.toJson(messageService.insertMessage(member_id, post_id, message_content)));
			
		}
		
		if("postLikeChange".equals(action)){
			String post_id = request.getParameter("post_id");
			Integer value = Integer.parseInt(request.getParameter("value"));
			Integer post_like = Integer.parseInt(request.getParameter("post_like"));
//			System.out.println(post_like);
			if(value == 1) {
				post_like ++;
//				System.out.println(post_like);
//				System.out.println(postService.updateByPostId(post_id, post_like));
				out.print(gson.toJson(postService.updateByPostId(post_id, post_like)));
			} else {
				post_like --;
//				System.out.println(post_like);
//				System.out.println(postService.updateByPostId(post_id, post_like));
				out.print(gson.toJson(postService.updateByPostId(post_id, post_like)));
			}	
		}
		
		if("postMessageCountChange".equals(action)) {
			String post_id = request.getParameter("post_id");
			System.out.println(post_id);
			Integer post_message_count = Integer.parseInt(request.getParameter("post_message_count"));
			System.out.println(post_message_count);
			post_message_count ++;
			System.out.println(post_message_count);
			out.print(gson.toJson(postService.updateMessageCountByPostId(post_id, post_message_count)));
		}
		
		if("postShareChange".equals(action)) {
			String post_id = request.getParameter("post_id");
			Integer post_share = Integer.parseInt(request.getParameter("post_share"));
			post_share ++;
			out.print(gson.toJson(postService.updateShareCountByPostId(post_id, post_share)));
		}
		
		if("savedPost".equals(action)) {
			Integer value = Integer.parseInt(request.getParameter("value"));
			System.out.println(value);
			String post_id = request.getParameter("post_id");
			String member_id = request.getParameter("member_id");
			if(value == 1) {
				System.out.println("文章收藏成功");
				System.out.println(post_id + "|" + member_id);
				out.print(gson.toJson(savedService.insertSaved(member_id, post_id)));
			}else {
				System.out.println("取消收藏文章");
				System.out.println(post_id + "|" + member_id);
				out.print(gson.toJson(savedService.delete(post_id, member_id)));
			}
			
		}
		
		if("changeFollow".equals(action)) {
			System.out.println("進入Ajax changeFollow");
			String status = request.getParameter("status");
			String follow_id = request.getParameter("follow_id");
			String member_id = request.getParameter("member_id");
			String followed_member_id = request.getParameter("followed_member_id");
			System.out.println("我要關注的對象" + followed_member_id);
			if("unfollow".equals(status)) {
				System.out.println(follow_id);
				out.print(gson.toJson(followService.deleteFollow(follow_id)));
			}else {
				out.print(gson.toJson(followService.insertFollow(member_id, followed_member_id)));
			}
		}
		
		if("cancelSaved".equals(action)) {
			String saved_post_id = request.getParameter("saved_post_id");
			out.print(gson.toJson(savedService.deleteSaved(saved_post_id)));
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
