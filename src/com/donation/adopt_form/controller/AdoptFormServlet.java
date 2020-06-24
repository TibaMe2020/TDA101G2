package com.donation.adopt_form.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donation.adopt_form_info.model.Adopt_form_infoService;
import com.donation.adopt_form_info.model.Adopt_form_infoVO;
import com.donation.donation_result.model.Donation_resultService;



public class AdoptFormServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
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
				String str = req.getParameter("adopt_form_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入認養表單編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/donation/AdoptForm/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
			String adopt_form_id = null;
			try {
				adopt_form_id = new String(str);
			}catch(Exception e) {
				errorMsgs.add("表單編號格是不正確");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/AdoptForm/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Adopt_form_infoService adoptformSvc = new Adopt_form_infoService();
			Adopt_form_infoVO adopt_form_infoVO = adoptformSvc.getOneAdopt_form_info(adopt_form_id);

			System.out.println(adoptformSvc);
			if(adopt_form_infoVO == null) {
				errorMsgs.add("查無此認養動物");
				}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/AdoptForm/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			req.setAttribute("adopt_form_infoVO", adopt_form_infoVO);
			String url = "/front-end/donation/AdoptForm/listOneAdoptForm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			}catch (Exception e ) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/AdoptForm/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {
	
			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);
			 try {
				
				 String adopt_id = new String(req.getParameter("adopt_id").trim());

				 
				 String adopt_person = req.getParameter("adopt_person");
				 String adopt_personReg ="^[(\u4e00-\u9fa5)]{2,20}$";
				 if(adopt_person == null || adopt_person.trim().length() == 0) {
						errors.put("adopt_person", "請填寫認養人姓名");
					 
				 }else if(!adopt_person.trim().matches(adopt_personReg)) {
					 errors.put("adopt_person","只能是中文,且長度必須在2到20之間");
				 }
				 String adopt_talk = req.getParameter("adopt_talk").trim();
				 String adopt_talkReg = "^[(\u4e00-\u9fa5)]{2,20}$";
				
				 if(adopt_talk ==null || adopt_talk.trim().length()==0) {
						errors.put("adopt_talk","請填寫要對寵物說的話");
				 }
				 String payadopt_person = req.getParameter("payadopt_person");
				 String payadopt_personReg ="^[(\u4e00-\u9fa5)]{2,20}$";
				 if(payadopt_person == null || adopt_person.trim().length() == 0) {
						errors.put("payadopt_person","請填寫付款人姓名");
					 
				 }else if(!payadopt_person.trim().matches(payadopt_personReg)) {
					 errors.put("payadopt_person","只能是中文,且長度必須在2到20之間");
				 }

				 String adopt_phone_num = req.getParameter("adopt_phone_num").trim();
				 String adopt_phone_numReg = "[0-9]{10}";
				 if(adopt_phone_num == null ||adopt_phone_num.trim().length() == 0) {
					 errors.put("adopt_phone_num","請填寫電話號碼");
				 }
				 else if(!adopt_phone_num.trim().matches(adopt_phone_numReg)) {
					 errors.put("adopt_phone_num","長度必須在10之間");
				 }
				 

				 String adopt_payment;
				 String [] parameterValues = req.getParameterValues("adopt_payment");
				 if(parameterValues != null) {
					 adopt_payment = parameterValues[0].toString();
				 }else {
					 errors.put("adopt_payment", "請選擇一種付款方式");
					 adopt_payment = "";
				 }
				 
				 String adopt_certificate;
				 String[] parameterValue = req.getParameterValues("adopt_certificate");
				if(parameterValue!=null) {
					 adopt_certificate = parameterValue[0].toString();
					 System.out.println(adopt_certificate);
				 }else {
					 errors.put("adopt_certificate","請選擇是否需要實體證書");
					 adopt_certificate = "";
				 }
				 

			 
				 
				 String adopt_email = req.getParameter("adopt_email").trim();
				 String adopt_emailReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,�C�A�I�H�B@.)]{2,400}$";
				 if(adopt_email == null ||adopt_email.trim().length() == 0) {
					 errors.put("adopt_email","信箱請勿空白");
				 }
				 else if(!adopt_email.trim().matches(adopt_emailReg)) {
					 errors.put("adopt_email","信箱長度必須在2~400之間");
				 }
				 
				 String address = req.getParameter("address").trim();
				 String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,。，！？、)]{2,400}$";
				 
				 if(address == null ||address.trim().length() == 0) {
					 errors.put("address","地址請勿空白");
				 }
				 else if(!address.trim().matches(addressReg)) {
					 errors.put("address","長度必須在2~400之間");
				 }
				 
						Adopt_form_infoVO adopt_form_infoVO = new Adopt_form_infoVO();
						adopt_form_infoVO.setAdopt_id(adopt_id);
						adopt_form_infoVO.setAdopt_person(adopt_person);
						adopt_form_infoVO.setAdopt_talk(adopt_talk);
						adopt_form_infoVO.setPayadopt_person(payadopt_person);
						adopt_form_infoVO.setAdopt_phone_num(adopt_phone_num);
						adopt_form_infoVO.setAdopt_payment(adopt_payment);
						adopt_form_infoVO.setAdopt_certificate(adopt_certificate);
						adopt_form_infoVO.setAdopt_email(adopt_email);
						adopt_form_infoVO.setAddress(address);

						
						if(!errors.isEmpty()) {
							req.setAttribute("adopt_form_infoVO", adopt_form_infoVO);
							System.out.println("adopt_form_infoVO");

							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/donation/AdoptForm/addTest.jsp");
							failureView.forward(req, res);
							return;
						}
						Adopt_form_infoService adoptFormSvc = new Adopt_form_infoService();
						adopt_form_infoVO =adoptFormSvc.addAdopt_form(adopt_id,adopt_person,adopt_talk, payadopt_person, adopt_phone_num, adopt_payment, adopt_certificate, adopt_email, address);
						req.setAttribute("adopt_form_infoVO", adopt_form_infoVO);
						System.out.println("adopt_form_infoVO");
						String url = "/front-end/donation/myMain/successpage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
				 			}catch(Exception e) {
				 				errors.put("errors", e.getMessage());
				 				RequestDispatcher failureView = req
				 						.getRequestDispatcher("/front-end/donation/AdoptForm/addTest.jsp");
				 				failureView.forward(req, res);
				 			}
			 			}
		

	}
		
}

