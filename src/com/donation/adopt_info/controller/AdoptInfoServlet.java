package com.donation.adopt_info.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.donation.adopt_info.model.Adopt_infoService;
import com.donation.adopt_info.model.Adopt_infoVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)//上傳三要素
public class AdoptInfoServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		   throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			Map<String,String> errors = new HashMap<String,String>();
			req.setAttribute("errorMsgs", errors);
			
			try {
				String str = req.getParameter("adopt_id");
				if(str == null || (str.trim()).length() == 0) {
					errors.put("adopt_id","請輸入認養動物編號");
				}
				if(!errors.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/donation/Adopt/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
			String adopt_id = null;
			try {
				adopt_id = new String(str);
			}catch(Exception e) {
				errors.put("adopt_id","認養編號格是不正確");
			}
			if(!errors.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Adopt/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Adopt_infoService adoptSvc = new Adopt_infoService();
			Adopt_infoVO adopt_infoVO = adoptSvc.getOneAdopt_info(adopt_id);
			System.out.println(adoptSvc);
			if(adopt_infoVO == null) {
				errors.put("errors","查無此認養動物");
				}
			if(!errors.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Adopt/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			req.setAttribute("adopt_infoVO", adopt_infoVO);
			String url = "/front-end/donation/Adopt/listOneAdopt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			}catch (Exception e ) {
				errors.put("errors","無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Adopt/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String adopt_id =  new String(req.getParameter("adopt_id"));
				
				Adopt_infoService adoptSvc = new Adopt_infoService();
				Adopt_infoVO adopt_infoVO = adoptSvc.getOneAdopt_info(adopt_id);
				req.setAttribute("adopt_infoVO", adopt_infoVO);
				String url = "/front-end/donation/Adopt/adoptUpdate.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Npo/listAllNpo.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			
			Map<String,String> errors = new HashMap<String,String>();
			req.setAttribute("errors", errors);
			
			try {
				String adopt_id = new String(req.getParameter("adopt_id").trim());
				
				String adopt_name = req.getParameter("adopt_name");
				String adopt_nameReg = "^[(\u4e00-\u9fa5)]{2,20}$";
				if (adopt_name == null || adopt_name.trim().length() == 0) {
					errors.put("adopt_name","認養動物名稱:請勿空白");
				}else if(!adopt_name.trim().matches(adopt_nameReg)) {
					errors.put("adopt_name","只能是中文,且長度必須在2到20之間");
				}
				
				String adopt_description = req.getParameter("adopt_description").trim();
				String adopt_descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,。，！？、)]{2,400}$";
				if(adopt_description == null || adopt_description.trim().length() ==0) {
					errors.put("adopt_description","內文請勿空白");
				}
				else if(!adopt_description.trim().matches(adopt_descriptionReg)) {
					errors.put("adopt_description","長度必須在2~400");
				}
				

//				Integer adopt_money = Integer.valueOf(req.getParameter("adopt_money").trim());
//				Integer adopt_moneyReg =Integer.valueOf("^[(a-zA-Z0-9_,。，！？、)]{2,100000}$");
//				if(adopt_money == null) {
//					errors.put("adopt_money","請輸入捐款金額");
//				}
				
				Part part = req.getPart("adopt_image");
				InputStream in = part.getInputStream();
				byte[] adopt_image = new byte[in.available()];
				in.read(adopt_image);
				in.close();
				
				
				Adopt_infoVO adopt_infoVO = new Adopt_infoVO();
				adopt_infoVO.setAdopt_id(adopt_id);
				adopt_infoVO.setAdopt_name(adopt_name);
				adopt_infoVO.setAdopt_image(adopt_image);//
				adopt_infoVO.setAdopt_description(adopt_description);
//				adopt_infoVO.setAdopt_money(adopt_money);

				
				if(!errors.isEmpty()) {
					req.setAttribute("adopt_infoVO", adopt_infoVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/donation/Adopt/adoptUpdate.jsp");
					failureView.forward(req, res);
					return;
				}
			Adopt_infoService adoptSvc = new Adopt_infoService();
			adopt_infoVO = adoptSvc.updateAdopt_info(adopt_id, adopt_name, adopt_image, adopt_description);
			
			req.setAttribute("adopt_infoVO", adopt_infoVO);
			String url = "/back-end/adoptTest.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		 }catch(Exception e) {
			 errors.put("errors","修改資料失敗"+e.getMessage());
			 RequestDispatcher failureView = req
					 .getRequestDispatcher("/front-end/donation/Adopt/adoptUpdate.jsp");
			 System.out.println(failureView);
			 failureView.forward(req, res);
		 }
	  }
		 if("insert".equals(action)) {
			 Map<String,String> errors = new HashMap<String,String>();
			 req.setAttribute("errors", errors);
			 try {
				 String adopt_name = req.getParameter("adopt_name");
				 String adopt_nameReg ="^[(\u4e00-\u9fa5)]{2,20}$";
				 if(adopt_name == null || adopt_name.trim().length() == 0) {
					 errors.put("adopt_name","認養動物名稱請勿空白");
					 
				 }else if(!adopt_name.trim().matches(adopt_nameReg)) {
					 errors.put("adopt_name","只能輸入中文,且長度必須在2到20之間");
				 }
				 // System.out.println(adopt_name);
				 String adopt_description = req.getParameter("adopt_description").trim();
				 String adopt_descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,。，！？、)]{2,400}$";
				 if(adopt_description == null ||(adopt_description.trim()).length() == 0) {
					 errors.put("adopt_description","認養動物介紹請勿空白");
				 }
				 else if(!adopt_description.trim().matches(adopt_descriptionReg)) {
					 errors.put("adopt_description","格式不正確");
				 }
//				Integer adopt_money = Integer.valueOf(req.getParameter("adopt_money").trim());
				
				Integer adopt_money = 0;
				String adopt_moneyR = req.getParameter("adopt_money").trim();
				if(adopt_moneyR == "" ) {
					errors.put("adopt_money","請規定認養金額");
				}						
				else {
						adopt_money = Integer.parseInt(adopt_moneyR);
						System.out.println("沒有輸入" + adopt_money);
				}
				if(adopt_money == 0) {
					errors.put("adopt_money","規定金額請大於0");
				}
			
				
				 Part part = req.getPart("adopt_image");
					InputStream in = part.getInputStream();
					byte[] adopt_image = new byte[in.available()];
					in.read(adopt_image);
					in.close();
					

						Adopt_infoVO adopt_infoVO = new Adopt_infoVO();
						adopt_infoVO.setAdopt_name(adopt_name);
						adopt_infoVO.setAdopt_image(adopt_image);
						adopt_infoVO.setAdopt_description(adopt_description);
						adopt_infoVO.setAdopt_money(adopt_money);
						
						if(!errors.isEmpty()) {
							req.setAttribute("adopt_infoVO", adopt_infoVO);
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/adoptTest.jsp");
							failureView.forward(req, res);
							return;
						}
						Adopt_infoService adopt_infoSvc = new Adopt_infoService();
						adopt_infoVO =adopt_infoSvc.addAdopt_info(adopt_name, adopt_image, adopt_description, adopt_money);
						req.setAttribute("adopt_infoVO", adopt_infoVO);

						String url = "/back-end/adoptTest.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
				 			}catch(Exception e) {
				 				errors.put("errors",e.getMessage());
				 				RequestDispatcher failureView = req
				 						.getRequestDispatcher("/back-end/adoptTest.jsp");
				 				failureView.forward(req, res);
				 			}
			 			}
			 if("delete".equals(action)) {
				 List<String> errorMsgs = new LinkedList<String>();
				 
				 req.setAttribute("errorMsgs", errorMsgs);
				 
				 try {
					 
					 String adopt_id = new String(req.getParameter("adopt_id"));
					 
					 Adopt_infoService adoptSvc = new Adopt_infoService();
					 adoptSvc.deleteAdopt_info(adopt_id);
					 
					 String url = "/front-end/donation/Adopt/listAllAdopt.jsp";
					 RequestDispatcher successView = req.getRequestDispatcher(url);
					 successView.forward(req, res);
				 }catch (Exception e ) {
					 errorMsgs.add("刪除資料失敗" +e.getMessage());
					 RequestDispatcher failureView = req
							 .getRequestDispatcher("/front-end/donation/Adopt/addAdopt.jsp");
					 failureView.forward(req, res);
				 }
			 }
	
	}
		 }
