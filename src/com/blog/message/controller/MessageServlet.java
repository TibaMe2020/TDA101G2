package com.blog.message.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.message.model.MessageService;
import com.blog.message.model.MessageVO;

public class MessageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String member_id = req.getParameter("member_id");
				if(member_id == null || (member_id.trim()).length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				String post_id = req.getParameter("post_id");
				if(post_id == null || (post_id.trim()).length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				
				String message_content = req.getParameter("message_content");
				if(message_content == null || (message_content.trim()).length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}
				
				MessageVO messageVO = new MessageVO();
				messageVO.setMember_id(member_id);
				messageVO.setPost_id(post_id);
				messageVO.setMessage_content(message_content);
				req.setAttribute("messageVO", messageVO);

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/addMessage.jsp");
					failureView.forward(req, res);
					return;
				}
				//新增資料
				MessageService service = new MessageService();
				messageVO = service.insertMessage(member_id, post_id, message_content);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Message/listAllMessage.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/addMessage.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if("getOneForUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				//接受請求參數
				String message_id = new String(req.getParameter("message_id"));
				//查詢資料
				MessageService service = new MessageService();
				MessageVO messageVO = service.getOneMessage(message_id);			
				//轉交
				req.setAttribute("messageVO", messageVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Message/updateMessage.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數	
				String message_id = req.getParameter("message_id");
				String member_id = req.getParameter("member_id");
				String post_id = req.getParameter("post_id");

				String message_content = req.getParameter("message_content");
				if(message_content == null || (message_content.trim()).length() == 0) {
					errorMsgs.add("留言內容請勿空白");
				}				
				MessageVO messageVO = new MessageVO();
				messageVO.setMessage_id(message_id);
				messageVO.setMember_id(member_id);
				messageVO.setPost_id(post_id);
				messageVO.setMessage_content(message_content);
				req.setAttribute("messageVO", messageVO);

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/updateMessage.jsp");
					failureView.forward(req, res);
					return;
				}
				//更新資料
				MessageService service = new MessageService();
				messageVO = service.updateMessage(message_id, member_id, post_id, message_content);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Message/listOneMessage.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/updateMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接收請求參數
				String message_id = req.getParameter("message_id");
				//刪除資料
				MessageService service = new MessageService();
				service.deleteMessage(message_id);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Message/listAllMessage.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("資料刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if("getOneMessage".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("message_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("留言編號請勿空白");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String message_id = null;
				try {
					message_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢資料
				MessageService service = new MessageService();
				MessageVO messageVO = service.getOneMessage(message_id);
				if(messageVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交
				req.setAttribute("messageVO", messageVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Message/listOneMessage.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getByMemberId".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("member_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String member_id = null;
				try {
					member_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢資料
				MessageService service = new MessageService();
				List<MessageVO> list = null;
				list = service.getByMemberId(member_id);
				if(list == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交
				req.setAttribute("list", list);
				System.out.println(list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Message/listAllByMemberId.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getByPostId".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("post_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String post_id = null;
				try {
					post_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				

				req.getSession().setAttribute("post_id", post_id);
				System.out.println(post_id);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/HomePage.jsp");
				successView.forward(req, res);
				
				//查詢資料
//				MessageService service = new MessageService();
//				List<MessageVO> list = null;
//				list = service.getByPostId(post_id);
//				if(list == null) {
//					errorMsgs.add("查無資料");
//				}
//				
//				if(!errorMsgs.isEmpty()){
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				//轉交
//				req.setAttribute("list", list);
//				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Message/listAllByPostId.jsp");
//				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Message/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
	}
}
