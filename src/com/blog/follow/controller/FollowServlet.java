package com.blog.follow.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.follow.model.FollowService;
import com.blog.follow.model.FollowVO;

public class FollowServlet extends HttpServlet {
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
				
				String followed_member_id = req.getParameter("followed_member_id");
				if(followed_member_id == null || (followed_member_id.trim()).length() == 0) {
					errorMsgs.add("被關注的會員編號請勿空白");
				}
							
				FollowVO followVO = new FollowVO();
				followVO.setMember_id(member_id);
				followVO.setFollowed_member_id(followed_member_id);
				req.setAttribute("followVO", followVO);

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/addFollow.jsp");
					failureView.forward(req, res);
					return;
				}
				//新增資料
				FollowService service = new FollowService();
				followVO = service.insertFollow(member_id, followed_member_id);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/Follow/listOneFollow.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Follow/addFollow.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String follow_id = new String(req.getParameter("follow_id"));
				//刪除資料
				FollowService service = new FollowService();
				service.deleteFollow(follow_id);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/Follow/listAllFollow.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("資料刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if("getOneFollow".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("follow_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入關注id");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String follow_id = null;
				try {
					follow_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("關注id格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢資料
				FollowService service = new FollowService();
				FollowVO followVO = service.getOneFollow(follow_id);
				if(followVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交
				req.setAttribute("followVO", followVO);
				RequestDispatcher successView = req.getRequestDispatcher("/Follow/listOneFollow.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("FollowServlet: " + member_id);
				req.getSession().setAttribute("member_id", member_id);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/MyBack.jsp");
				successView.forward(req, res);
				
				//查詢資料
//				FollowService service = new FollowService();
//				List<FollowVO> list = null;
//				list = service.getByMemberId(member_id);
//				if(list == null) {
//					errorMsgs.add("查無資料");
//				}
//				
//				if(!errorMsgs.isEmpty()){
//					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				//轉交
//				req.setAttribute("list", list);
//				RequestDispatcher successView = req.getRequestDispatcher("/Project/MyBack.jsp");
//				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getAllFollowMe".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("member_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢資料
				FollowService service = new FollowService();
				List<FollowVO> list2 = null;
				list2 = service.getAllFollowMe(member_id);
				if(list2 == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交
				req.setAttribute("list2", list2);
				RequestDispatcher successView = req.getRequestDispatcher("/Project/MyBack.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Follow/select_page.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
		
	}
}
