package com.store.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.model.StoreDAO_JDBC;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/store/Controller")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
					errorMsgs.put("error","請輸入店家編號");
				}
								
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store/select_page.jsp");
					failureView.forward(req,res);
					return;
				}
				
				// 開始查詢資料
				StoreService storeService = new StoreService();
				storeVO = storeService.findByStoreId(store_id);
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
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String member_id = req.getParameter("memberId");
				
				String store_name = req.getParameter("storeName");
				String store_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.put("error_name","店家名稱: 請勿空白");
				} else if(!store_name.trim().matches(store_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("error_name","店家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				String store_class = req.getParameter("storeClass");
				
				String store_adress = req.getParameter("storeAdress").trim();
				if (store_adress == null || store_adress.trim().length() == 0) {
					errorMsgs.put("error_adress","地址請勿空白");
				}
				
				String store_phone_number = req.getParameter("storePhoneNumber").trim();
				String phoneReg = "^[0-9]{9,10}$";
				if (store_phone_number == null || store_phone_number.trim().length() == 0) {
					errorMsgs.put("error_phone","電話請勿空白");
				}else if(!store_phone_number.matches(phoneReg))
					errorMsgs.put("error_phone","請輸入數字且是9-10位數");
					
				String store_introduction = req.getParameter("storeIntroduction");
				
				Integer store_clicks = null;
				try {
					store_clicks = new Integer(req.getParameter("storeClicks").trim());
				} catch (NumberFormatException e) {
					store_clicks = 0;
					errorMsgs.put("error_clicks","點閱請勿空白");
				}
				
				Integer store_firstbreak = Integer.parseInt(req.getParameter("storeFirstbreak").trim());
				if(store_firstbreak==0) {
					store_firstbreak=null;
				}
				Integer store_secondbreak = Integer.parseInt(req.getParameter("storeSecondbreak").trim());
				if(store_secondbreak==0) {
					store_secondbreak=null;
				}
				
				
				// 無檢查格式的欄位
				String store_openhours1 = req.getParameter("storeOpenhours1");
				String store_openhours2 = req.getParameter("storeOpenhours2");
				String store_openhours3 = req.getParameter("storeOpenhours3");
				Integer store_timelimit = new Integer(req.getParameter("storeTimelimit").trim());
				Integer store_maxcapacity = new Integer(req.getParameter("storeMaxcapacity").trim());
				byte[] store_image1 = null;
				byte[] store_image2 = null;
				byte[] store_image3 = null;
				byte[] store_image4 = null;
				byte[] store_image5 = null;
				byte[] store_image6 = null;
				byte[] store_menu1 = null;
				byte[] store_menu2 = null;
				byte[] store_menu3 = null;
				Integer store_on = new Integer(req.getParameter("storeOn").trim());
				
				
				storeVO = new StoreVO();
				storeVO.setMember_id(member_id);
				storeVO.setStore_name(store_name);
				storeVO.setStore_class(store_class);
				storeVO.setStore_adress(store_adress);
				storeVO.setStore_phone_number(store_phone_number);
				storeVO.setStore_introduction(store_introduction);
				storeVO.setStore_clicks(store_clicks);
				storeVO.setStore_firstbreak(store_firstbreak);
				storeVO.setStore_secondbreak(store_secondbreak);
				storeVO.setStore_openhours1(store_openhours1);
				storeVO.setStore_timelimit(store_timelimit);
				storeVO.setStore_maxcapacity(store_maxcapacity);
				storeVO.setStore_on(store_on);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/addStore.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				StoreService storeSvc = new StoreService();
				storeSvc.newStore(storeVO);
//				storeVO = storeSvc.newStore(member_id, store_class, store_name, store_adress, store_phone_number,
//						store_introduction, store_clicks, store_firstbreak, store_secondbreak,
//						store_openhours1, store_openhours2, store_openhours3, store_timelimit,
//						store_maxcapacity, store_image1, store_image2, store_image3, store_image4,
//						store_image5, store_image6, store_menu1, store_menu2, store_menu3, store_on);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("error","新增錯誤:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/addStore.jsp");
				failureView.forward(req, res);
			}
		}
//修改
		if("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String store_id = req.getParameter("storeId");
				
				/***************************2.開始查詢資料****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.findByStoreId(store_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("storeVO", storeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/store/update_store_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("error","無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String store_id = req.getParameter("storeId");
				
				String member_id = req.getParameter("memberId");
				
				String store_name = req.getParameter("storeName");
				String store_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.put("error_name","店家名稱: 請勿空白");
				} else if(!store_name.trim().matches(store_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("error_name","店家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				
				String store_class = req.getParameter("storeClass");
				
				String store_adress = req.getParameter("storeAdress").trim();
				if (store_adress == null || store_adress.trim().length() == 0) {
					errorMsgs.put("error_adress","地址請勿空白");
				}
				
				String store_phone_number = req.getParameter("storePhoneNumber").trim();
				String phoneReg = "^[0-9]{9,10}$";
				if (store_phone_number == null || store_phone_number.trim().length() == 0) {
					errorMsgs.put("error_phone","電話請勿空白");
				}else if(!store_phone_number.matches(phoneReg))
					errorMsgs.put("error_phone","請輸入數字且是9-10位數");
				
				String store_introduction = req.getParameter("storeIntroduction");
				
				Integer store_clicks = null;
				try {
					store_clicks = new Integer(req.getParameter("storeClicks").trim());
				} catch (NumberFormatException e) {
					store_clicks = 0;
					errorMsgs.put("error_clicks", "僅能輸入數字且不能空白");
				}
				
				Integer store_firstbreak = Integer.parseInt(req.getParameter("storeFirstbreak").trim());
				if(store_firstbreak==0) {
					store_firstbreak=null;
				}
				Integer store_secondbreak = Integer.parseInt(req.getParameter("storeSecondbreak").trim());
				if(store_secondbreak==0) {
					store_secondbreak=null;
				}
				
				String store_openhours1 = req.getParameter("storeOpenhours1");
				Integer store_timelimit = new Integer(req.getParameter("storeTimelimit").trim());
				Integer store_maxcapacity = new Integer(req.getParameter("storeMaxcapacity").trim());
				Integer store_on = new Integer(req.getParameter("storeOn").trim());
				
				storeVO = new StoreVO();
				storeVO.setStore_id(store_id);
				storeVO.setMember_id(member_id);
				storeVO.setStore_name(store_name);
				storeVO.setStore_class(store_class);
				storeVO.setStore_adress(store_adress);
				storeVO.setStore_phone_number(store_phone_number);
				storeVO.setStore_introduction(store_introduction);
				storeVO.setStore_clicks(store_clicks);
				storeVO.setStore_firstbreak(store_firstbreak);
				storeVO.setStore_secondbreak(store_secondbreak);
				storeVO.setStore_openhours1(store_openhours1);
				storeVO.setStore_timelimit(store_timelimit);
				storeVO.setStore_maxcapacity(store_maxcapacity);
				storeVO.setStore_on(store_on);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/update_store_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.updateStore(storeVO);
								
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("storeVO", storeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("error","修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/update_store_input.jsp");
				failureView.forward(req, res);
			}
		}
// 刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String store_id = req.getParameter("storeId");
				
				/***************************2.開始刪除資料***************************************/
				StoreService storeSvc = new StoreService();
				storeSvc.deleteStore(store_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				errorMsgs.put("error",store_id+"刪除成功");
				String url = "/back-end/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("error","刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	

}
