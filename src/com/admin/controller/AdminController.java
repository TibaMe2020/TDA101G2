package com.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;

@WebServlet("/admin/controller")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adSvc;

	public void init() {
		adSvc = new AdminService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		AdminVO adminVO = null;
		
		if("login".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<>();			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String admin_account = req.getParameter("admin_account").trim();
				String admin_password = req.getParameter("admin_password").trim();
				
				if("".equals(admin_account)) {
					errorMsgs.put("admin_account", "帳號不能為空白");
				}
				
				if("".equals(admin_password)) {
					errorMsgs.put("admin_password", "密碼不能為空白");
				}
				
				if(!errorMsgs.isEmpty() ) {
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//call service
				adminVO = adSvc.login(admin_account, admin_password);
				
				if(adminVO == null) {
					errorMsgs.put("account", "帳號密碼有誤");
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				HttpSession session = req.getSession();
				session.setAttribute("adminVO", adminVO);
				
//				RequestDispatcher view = req.getRequestDispatcher("/back-end/adminStatistics.jsp");
//				view.forward(req, res);
//				return;
				//D成功的話避免重新送出表單
//				res.sendRedirect(req.getContextPath() + "/back-end/adminStatistics.jsp");
//				res.sendRedirect((String) session.getAttribute("location"));
				res.sendRedirect(req.getContextPath() + "/back-end/adminStatistics.jsp");
				return;
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", "Error occurred");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminLogin.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("signup".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<>();			
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String admin_account = req.getParameter("admin_account").trim();
				String admin_password = req.getParameter("admin_password").trim();
				adminVO = new AdminVO();
				req.setAttribute("newAdminVO", adminVO);
				
				adminVO.setAdmin_account(admin_account);
				adminVO.setAdmin_password(admin_password);
				if("".equals(admin_account)) {
					errorMsgs.put("admin_account", "帳號不能為空白");
					adminVO.setAdmin_account("");
				}
				
				
				if("".equals(admin_password)) {
					errorMsgs.put("admin_password", "密碼不能為空白");
				}
				
				if(!errorMsgs.isEmpty() ) {
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminManagement.jsp");
					failureView.forward(req, res);
					return;
				}
				
				boolean state = adSvc.signup(adminVO);
				
				if(state == false) {
					errorMsgs.put("admin_password", "此帳號已被註冊");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminManagement.jsp");
					failureView.forward(req, res);
					return;
				}
				
//				RequestDispatcher view = req.getRequestDispatcher("/back-end/adminManagement.jsp");
//				view.forward(req, res);
//				return;
			//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + "/back-end/adminManagement.jsp");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminManagement.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("adminVO");
			session.invalidate();
			res.sendRedirect(req.getContextPath()+"/back-end/adminLogin.jsp");
		}
		
		if("suspend".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<>();			
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String admin_id = req.getParameter("admin_id");
				
				adminVO = adSvc.suspend(admin_id);
				
				if(adminVO == null) {
					errorMsgs.put("error", "Please try again");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminManagement.jsp");
					failureView.forward(req, res);
					return;
				}
				
				errorMsgs.put("error", "Success");
// 				RequestDispatcher view = req.getRequestDispatcher("/back-end/adminManagement.jsp");
//				view.forward(req, res);
//				return;
			//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + "/back-end/adminManagement.jsp");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", "Please try again");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminManagement.jsp");
				failureView.forward(req, res);
				return;
			}
		}	
		
		if("delete".equals(action)) {
			try {
				String admin_id = req.getParameter("admin_id");
				boolean deleted = adSvc.delete(admin_id);
				if(deleted == false) System.out.println("something wrong!");
//				RequestDispatcher view = req.getRequestDispatcher("/back-end/adminManagement.jsp");
//				view.forward(req, res);
//				return;
			//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + "/back-end/adminManagement.jsp");
				return;
			} catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher view = req.getRequestDispatcher("/back-end/adminManagement.jsp");
				view.forward(req, res);
				return;
			}
		}
	}
}
