package com.blog.saved.controller;

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
import com.blog.saved.model.SavedService;
import com.blog.saved.model.SavedVO;

public class SavedServlet extends HttpServlet {
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
				//接收請求參數
				String member_id = req.getParameter("member_id");
				if(member_id == null || (member_id.trim()).length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String post_id = req.getParameter("post_id");
				if(post_id == null || (post_id.trim()).length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				SavedVO savedVO = new SavedVO();
				savedVO.setMember_id(member_id);
				savedVO.setPost_id(post_id);
				req.setAttribute("savedVO", savedVO);
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Saved/addSaved.jsp");
					failureView.forward(req, res);
					return;
				}
				//新增資料
				SavedService service = new SavedService();
				savedVO = service.insertSaved(member_id, post_id);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/Saved/listOneSaved.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Saved/addSaved.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}	
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				//接收請求參數
				String saved_post_id = req.getParameter("saved_post_id");
				//刪除資料
				SavedService service = new SavedService();
				service.deleteSaved(saved_post_id);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/Saved/listAllSaved.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("資料刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if("getOneSaved".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("saved_post_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("收藏文章編號請勿空白");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String saved_post_id = null;
				try {
					saved_post_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("收藏文章編號格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//查詢資料
				SavedService service = new SavedService();
				SavedVO savedVO = service.getOneSaved(saved_post_id);
				if(savedVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交
				req.setAttribute("savedVO", savedVO);
				RequestDispatcher successView = req.getRequestDispatcher("/Saved/listOneSaved.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getByMemberId".equals(action)) {
			System.out.println("進到SavedServlet getByMemberId");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("member_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員id");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String member_id = null;
				try {
					member_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員id格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("member_id", member_id);
				//查詢資料
//				SavedService service = new SavedService();
//				List<SavedVO> list = null;
//				list = service.getByMemberId(member_id);
//				if(list == null) {
//					errorMsgs.add("查無資料");
//				}
//				
//				if(!errorMsgs.isEmpty()){
//					RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				//轉交
//				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/Saved/listAllByMemberId.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Saved/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
