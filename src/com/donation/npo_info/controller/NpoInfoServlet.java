package com.donation.npo_info.controller;

import java.io.FileInputStream;
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

import com.donation.npo_info.model.Npo_infoService;
import com.donation.npo_info.model.Npo_infoVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)//上傳三要素
public class NpoInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res )
throws ServletException, IOException{
		   	doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("npo_id");
				if(str == null || (str.trim()).length() == 0){
					errorMsgs.add("請輸入公益團體編號");
					
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/donation/Npo/select_page.jsp");			
					failureView.forward(req, res);
					return;
				}			

			String npo_id = null;
			try {
				npo_id = new String(str);
			}catch(Exception e) {
				errorMsgs.add("公益編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Npo/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Npo_infoService npoSvc = new Npo_infoService();
			Npo_infoVO npo_infoVO = npoSvc.getOneNpo_info(npo_id);
			System.out.println("npoSvc");
			if(npo_infoVO == null) {
				errorMsgs.add("查無此公益團體");
				}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Npo/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("npo_infoVO",npo_infoVO );
			String url = "/front-end/donation/Npo/listOneNpo.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			}catch (Exception e ) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Npo/select_page.jsp");
				failureView.forward(req, res);
			}
			
//			String npo_id = null;
//			Npo_infoService npoSvc = new Npo_infoService();
//			Npo_infoVO npo_infoVO = npoSvc.findByDonationMoney(npo_id);
//			req.setAttribute("npo_infoVO",npo_infoVO);

		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String npo_id = new String(req.getParameter("npo_id"));
				
				Npo_infoService npoSvc = new Npo_infoService();
				Npo_infoVO npo_infoVO = npoSvc.getOneNpo_info(npo_id);
				
				req.setAttribute("npo_infoVO", npo_infoVO);
				String url = "/front-end/donation/Npo/update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch (Exception e) {
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
				String npo_id = new String(req.getParameter("npo_id").trim());
				
				String npo_name = req.getParameter("npo_name");
				String npo_nameReg = "^[(\u4e00-\u9fa5)]{2,20}$";
				if (npo_name == null || npo_name.trim().length() == 0) {
					errors.put("npo_name","公益團體名稱:請勿空白");
				}else if(!npo_name.trim().matches(npo_nameReg)) {
					errors.put("npo_name","只能是中文,且長度必須在2到20之間");
				}
				
				String npo_description = req.getParameter("npo_description").trim();
				String npo_descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,。，！？、)]{2,400}$";
				if(npo_description == null || npo_description.trim().length() == 0) {
					errors.put("npo_description","內文請勿空白");
				}
				else if(!npo_description.trim().matches(npo_descriptionReg)) {
					errors.put("npo_description","長度必須在2~400之間");
				}

				Part part = req.getPart("npo_image");
				InputStream in = part.getInputStream();
				byte[] npo_image = new byte[in.available()];
				in.read(npo_image);
				in.close();


			Npo_infoVO npo_infoVO = new Npo_infoVO();
			npo_infoVO.setNpo_id(npo_id);
			npo_infoVO.setNpo_name(npo_name);
			npo_infoVO.setNpo_image(npo_image);
			npo_infoVO.setNpo_description(npo_description);
			
			if (!errors.isEmpty()) {
				req.setAttribute("npo_infoVO", npo_infoVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Npo/update.jsp");
				failureView.forward(req, res);
				return;
			}
			
			//修改資料
			Npo_infoService npoSvc = new Npo_infoService();
			npo_infoVO = npoSvc.updateNpo_info(npo_id, npo_name, npo_image, npo_description);
			
			req.setAttribute("npo_infoVO", npo_infoVO);
			String url = "/back-end/donationTest.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			System.out.println("success!!!!!!!!!!");
			successView.forward(req, res);
			return;
			
		}catch(Exception e) {
			errors.put("errors","修改資料失敗"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/donation/Npo/update.jsp");
			failureView.forward(req, res);
			return;
		}	
	}
		if("insert".equals(action)) {
			Map<String,String> errors = new HashMap<String,String>();
			req.setAttribute("errors", errors);
			try {
				String npo_name = req.getParameter("npo_name");
				String npo_nameReg = "^[(\u4e00-\u9fa5)]{2,20}$";
				if (npo_name == null || npo_name.trim().length() == 0) {
					errors.put("npo_name","公益團體名稱:請勿空白");
				}else if(!npo_name.trim().matches(npo_nameReg)) {
					errors.put("npo_name","只能是中文,且長度必須在2到20之間");
				}
				String npo_description = req.getParameter("npo_description").trim();
				String npo_descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,。，！？、)]{2,400}$";
				if(npo_description == null || (npo_description.trim()).length() == 0) {
					errors.put("npo_description","內文請勿空白");
				}
				else if(!npo_description.trim().matches(npo_descriptionReg)) {
					errors.put("npo_description","長度必須在2~400之間");
				}
				
				Part part = req.getPart("npo_image");
				InputStream in = part.getInputStream();
				byte[] npo_image = new byte[in.available()];
				in.read(npo_image);
				in.close();
				

				
//				String npo_id =new String(req.getParameter("npo_id").trim());
				
				Npo_infoVO npo_infoVO = new Npo_infoVO();
//				npo_infoVO.setNpo_id(npo_id);
				npo_infoVO.setNpo_name(npo_name);
				npo_infoVO.setNpo_image(npo_image);
				npo_infoVO.setNpo_description(npo_description);
				
				if(!errors.isEmpty()) {
					req.setAttribute("npo_infoVO", npo_infoVO);
					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/donation/Npo/testB.jsp");
							.getRequestDispatcher("/back-end/donationTest.jsp");

							failureView.forward(req, res);
					return;
				}
				Npo_infoService npo_infoSvc = new Npo_infoService();
				npo_infoVO = npo_infoSvc.addNpo_info(npo_name, npo_image ,npo_description);
//				

				String url = "/back-end/donationTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
					}catch(Exception e) {
						errors.put("errors",e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/donationTest.jsp");
						failureView.forward(req, res);
					}
				}
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String npo_id = new String(req.getParameter("npo_id"));

				Npo_infoService npoSvc = new Npo_infoService();
				npoSvc.deleteNpo_info(npo_id);
				
				String url = "/front-end/donation/Npo/listAllNpo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e ) {
				errorMsgs.add("刪除資料失敗" +e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/Npo/listAllNpo.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
