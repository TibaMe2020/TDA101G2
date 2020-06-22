package com.product_order_master.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_order_master.model.Order_master_Service;
import com.product_order_master.model.Order_master_VO;

@WebServlet("/Order_master")
public class Order_masterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// One Display
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("product_order_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單主檔編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String product_order_id = null;
				try {
					product_order_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單主檔編號不對");
				}

				String member_id = null;
				try {
					member_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

			
				/*************************** 2.開始查詢資料 *****************************************/

				Order_master_Service order_master_Svc = new Order_master_Service();
				Order_master_VO order_master_VO = order_master_Svc.getOneMaster(product_order_id);

				if (order_master_VO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("order_master_VO", order_master_VO);
				String url = "/order/listAllMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		// One (member) Display
//				if ("getOne_For_Display".equals(action)) {
//					List<String> errorMsgs = new LinkedList<String>();
//					req.setAttribute("errorMsgs", errorMsgs);
//					try {
//						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//						String str = req.getParameter("member_id");
//						if (str == null || (str.trim()).length() == 0) {
//							errorMsgs.add("請輸入會員編號");
//						}
//
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
//							failureView.forward(req, res);
//							return;
//						}
//
//						String product_order_id = null;
//						try {
//							product_order_id = new String(str);
//						} catch (Exception e) {
//							errorMsgs.add("訂單主檔編號不對");
//						}
//
//						
//						// Send the use back to the form, if there were errors
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
//							failureView.forward(req, res);
//							return;// 程式中斷
//						}
//
//					
//						/*************************** 2.開始查詢資料 *****************************************/
//
//						Order_master_Service order_master_Svc = new Order_master_Service();
//						Order_master_VO order_master_VO = order_master_Svc.getOneMaster(product_order_id);
//
//						if (order_master_VO == null) {
//							errorMsgs.add("查無資料");
//						}
//
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
//							failureView.forward(req, res);
//							return;
//						}
//						/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//						req.setAttribute("order_master_VO", order_master_VO);
//						String url = "/order/listAllMaster.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url);
//						successView.forward(req, res);
//						/*************************** 其他可能的錯誤處理 *************************************/
//					} catch (Exception e) {
//						errorMsgs.add("無法取得資料:" + e.getMessage());
//						RequestDispatcher failureView = req.getRequestDispatcher("/master_page.jsp");
//						failureView.forward(req, res);
//					}
//				}
		
		// insert Order_Master
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String member_id = req.getParameter("member_id");
				String product_order_state = req.getParameter("product_order_state");

				String payment = req.getParameter("payment");
				String delivery_location = req.getParameter("delivery_location");


				

				Order_master_VO order_master_VO = new Order_master_VO();
				// 與Member串通之後才可使用
				order_master_VO.setMember_id(member_id);
				order_master_VO.setProduct_order_state(product_order_state);
				order_master_VO.setPayment(payment);
				order_master_VO.setLocation(delivery_location);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_master_VO", order_master_VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/order_master/addMaster.jsp");
					failureView.forward(req, res);

					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Order_master_Service order_master_Svc = new Order_master_Service();
				order_master_VO = order_master_Svc.addOrder_master(order_master_VO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("order_master_VO", order_master_VO);
				String url = "/order_master/listAllMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/order_master/addMaster.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update_state".equals(action)) {
			
			String order_id = req.getParameter("product_order_id");
			String order_state = req.getParameter("order_state");
			PrintWriter out = res.getWriter();
			try {
				Order_master_Service orderSvc = new Order_master_Service();
				orderSvc.updateState(order_id, order_state);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("success");
			}
			
		}
		
		
	}

}
