package com.product_order_detail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_order_detail.model.Order_detail_Service;
import com.product_order_detail.model.Order_detail_VO;
import com.product_score.model.Score_Service;
import com.product_score.model.Score_VO;

@WebServlet("/Order_detail")
public class Order_detailServlet extends HttpServlet {
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
				String str = req.getParameter("product_order_detail_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單明細編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/detail_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String product_order_detail_id = null;
				try {
					product_order_detail_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號不對");
				}

				String product_version_id = null;
				try {
					product_version_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品規格編號不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/detail_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/

				Order_detail_Service order_detail_Svc = new Order_detail_Service();
				Order_detail_VO order_detail_VO = order_detail_Svc.getOneDetail(product_order_detail_id);

				if (order_detail_VO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/detail_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("order_detail_VO", order_detail_VO);
				String url = "/order/listAllDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/deatil_page.jsp");
				failureView.forward(req, res);
			}
		}
//insert
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String product_order_detail_id = req.getParameter("product_order_detail_id");

				String product_order_id = new String(req.getParameter("product_order_id").trim());
				String product_version_id = new String(req.getParameter("product_version_id").trim());
				Integer quantity = Integer.parseInt(req.getParameter("quantity"));
				if (quantity >= 99 || quantity < 0) {
					errorMsgs.add("數量: 請介於 0~99 ");
				}

				Order_detail_VO order_detail_VO = new Order_detail_VO();
				order_detail_VO.setProduct_order_detail_id(product_order_id);
				order_detail_VO.setProduct_version_id(product_version_id);
				order_detail_VO.setQuantity(quantity);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("order_detail_VO", order_detail_VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/order_detail/addDetail.jsp");
					failureView.forward(req, res);

					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Order_detail_Service order_detail_Svc = new Order_detail_Service();
				order_detail_VO = order_detail_Svc.addOrder_detail(order_detail_VO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("order_detail_VO", order_detail_VO);
				String url = "/order_detail/listAllDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/order_detail/addDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}

}
