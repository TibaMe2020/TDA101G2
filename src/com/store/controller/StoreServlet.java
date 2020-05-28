package com.store.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/store/Controller")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		StoreVO storeVO = null;
// 單一查詢
		if("getOneForDisplay".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數，格式錯誤處理
				String store_id = req.getParameter("storeId").trim();
				if("".equals(store_id)) {
					errorMsgs.put("error","店家編號格式不正確");
				}
								
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/select_page.jsp");
					failureView.forward(req,res);
					return;
				}
				
				// 開始查詢資料
				StoreService storeService = new StoreService();
				storeVO = storeService.findStoreId(store_id);
				if (storeVO == null) {
					errorMsgs.put("error","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				// 查詢完成,準備轉交(Send the Success view)
				req.setAttribute("storeVO", storeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				// 其他可能的錯誤處理
			} catch (Exception e) {
				errorMsgs.put("error","無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
// 新增
		if("insert".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String sname = req.getParameter("sname");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (sname == null || sname.trim().length() == 0) {
				errorMsgs.put("errorMsgs","店家名稱: 請勿空白");
			} else if(!sname.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("errorMsgs","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String adress = req.getParameter("adress").trim();
			if (adress == null || adress.trim().length() == 0) {
				errorMsgs.put("errorMsgs","地址請勿空白");
			}
			String phone = req.getParameter("phone").trim();
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.put("errorMsgs","電話請勿空白");
			}
			
		}
	}

}
