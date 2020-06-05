package com.store.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store_order.model.Store_orderService;
import com.store_order.model.Store_orderVO;
import com.store_order_detail.model.Store_order_detailService;
import com.store_order_detail.model.Store_order_detailVO;


@WebServlet("/store/OrderController")
public class StoreOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Store_orderVO store_orderVO = null;
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
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				// 查詢完成,準備轉交(Send the Success view)
				req.setAttribute("store_orderVO", store_id); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/store/listOrder.jsp";
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
		
		
// 查訂單-訂單明細
		if("getDetailList".equals(action)) {
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
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// 查該筆訂單明細
				Store_orderService orderSvc = new Store_orderService();
				Store_order_detailService detailSvc = new Store_order_detailService();
				List<Store_orderVO> orderList = orderSvc.selectByStore(store_id);
				
				List<Store_order_detailVO> detailList = detailSvc.selectByOrderId(req.getParameter("storeId"));
				
				
				// 查詢完成,準備轉交(Send the Success view)
				req.setAttribute("store_id", store_id); // 資料庫取出的empVO物件,存入req
				req.setAttribute("detailList", detailList);
				String url = "/back-end/store/listAllOrder.jsp";
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
				String store_id = req.getParameter("storeId");
				String member_id = req.getParameter("memberId");
				String member_idReg = "^[MB]{2}[0-9]{5}$";
				if (member_id == null || member_id.trim().length() == 0) {
				} else if(member_id.trim().matches(member_idReg)) { //以下練習正則(規)表示式(regular-expression)
					// 跟member table 做比對
				} else{
					errorMsgs.put("error_member","會員ID: 只能是已註冊之現有會員。非會員請空白");
				}
				String store_order_name = req.getParameter("bookerName");
				String store_nameReg = "^[(\u4e00-\u9fa5)]{2,5}$";
				if (store_order_name == null || store_order_name.trim().length() == 0) {
					errorMsgs.put("error_name","預約者姓名: 請勿空白");
				} else if(!store_order_name.trim().matches(store_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("error_name","預約者姓名: 請輸入中文姓名, 且長度必需在2到5之間");
				}
				String store_order_email = req.getParameter("bookerEmail");
				String emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if (store_order_email == null || store_order_email.trim().length() == 0) {
					errorMsgs.put("error_email","email: 請勿空白");
				} else if(!store_order_email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("error_email","email: 請輸入正確的email格式");
				}
				String store_order_phone_num = req.getParameter("bookerPhoneNumber").trim();
				String phoneReg = "^[0-9]{9,10}$";
				if (store_order_phone_num == null || store_order_phone_num.trim().length() == 0) {
					errorMsgs.put("error_phone","電話：請勿空白");
				}else if(!store_order_phone_num.matches(phoneReg))
					errorMsgs.put("error_phone","電話：請輸入數字且是9-10位數");
				

//				String bookingDateReg="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$";
				String bookingDate = req.getParameter("bookingDate").trim();
				Timestamp store_order_date_time=null;
				if(bookingDate!=null) {
//					if(!bookingDate.matches(bookingDateReg))
					try {
						store_order_date_time = Timestamp.valueOf(bookingDate);
					} catch (Exception e) {
						errorMsgs.put("error_date","請填選正確格式:yyyy-mm-dd hh:mm:ss");
						bookingDate="";
						e.printStackTrace();
					}
				}
				
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				String checkOutDate = req.getParameter("checkOutDate").trim();
				Date store_order_end_date=null;
				if(!"".equals(checkOutDate)){
					store_order_end_date = new java.sql.Date(sdf2.parse(checkOutDate).getTime());
				}
				
				Integer store_order_persons = null;
				String persons = req.getParameter("orderPersons").trim();
				try {
					if("".equals(persons)) {
//						store_order_persons=0;
						System.out.println("persons = null");
					}else {
						store_order_persons = new Integer(req.getParameter("orderPersons").trim());
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("error_persons", "僅能輸入數字");
				}
				
				String store_order_payment = req.getParameter("orderPayment").trim();
				String store_order_note = req.getParameter("orderNote").trim();
				Integer store_order_state = new Integer(req.getParameter("orderState").trim());
				
			
				
				store_orderVO = new Store_orderVO();
				store_orderVO.setStore_id(store_id);
				store_orderVO.setMember_id(member_id);
				store_orderVO.setStore_order_name(store_order_name);
				store_orderVO.setStore_order_email(store_order_email);
				store_orderVO.setStore_order_phone_num(store_order_phone_num);
				store_orderVO.setStore_order_date_time(store_order_date_time);
				store_orderVO.setStore_order_end_date(store_order_end_date);
				store_orderVO.setStore_order_persons(store_order_persons);
				store_orderVO.setStore_order_payment(store_order_payment);
				store_orderVO.setStore_order_note(store_order_note);
				store_orderVO.setStore_order_state(store_order_state);
				
// 新增訂單明細 1對多
				String[] service_id = req.getParameterValues("service_id");
				String[] pets = req.getParameterValues("pets");
System.out.println("-------------------------------------------");				
System.out.println("service_id[0]="+service_id[0]);				
System.out.println("pets[0]="+pets[0]);				
System.out.println("-------------------------------------------");
				List<Store_order_detailVO> detailList = new ArrayList<Store_order_detailVO>();
				for(int i=0; i<service_id.length; i++) {
					Store_order_detailVO detail = new Store_order_detailVO();
					detail.setService_id(service_id[i]);
					if("".equals(pets[i])) {
						continue;
					}
					detail.setOrder_detail_pets(new Integer(pets[i]));
					detailList.add(detail);
				};
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("store_orderVO", store_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/addOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				Store_orderService orderSvc = new Store_orderService();
//				orderSvc.newOrder(store_orderVO);
				orderSvc.insertWithDetail(store_orderVO, detailList);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("store_orderVO", store_id);
				String url = "/back-end/store/listOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} 	catch (Exception e) {
				errorMsgs.put("error","新增錯誤:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/addOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
// 刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String store_order_id = req.getParameter("storeOrderId");
				
				/***************************2.開始刪除資料***************************************/
				Store_orderService orderSvc = new Store_orderService();
				orderSvc.deleteOrder(store_order_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				errorMsgs.put("error",store_order_id+"刪除成功");
				String url = "/back-end/store/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("error","刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
