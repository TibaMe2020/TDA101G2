package com.donation.donation_form.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.env.IModule.IService;

import com.donation.donation_form_info.model.Donation_form_infoService;
import com.donation.donation_form_info.model.Donation_form_infoVO;
import com.member.model.MemberVO;

public class DonationFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("getOne_For_Display".equals(action)) {
			
			Map <String,String> errors = new HashMap<String,String>(); 
			req.setAttribute("errors", errors);

			try {
				String donator_name = req.getParameter("donator_name");
				String donator_nameR ="^[(\u4e00-\u9fa5)]{2,20}$";
				if(donator_name ==null || (donator_name.trim()).length() == 0) {
					errors.put("donator_name","捐款姓名不得為空白");
				}
				else if(!donator_name.trim().matches(donator_nameR)) {
					errors.put("donator_name","捐款姓名格式不正確");
				}
				String donation_phone_num = req.getParameter("donation_phone_num");
				String donation_phone_numR = "[0-9]{10}";
				if(donation_phone_num == null || (donation_phone_num.trim()).length() == 0){
					errors.put("donation_phone_num","電話號碼格式不得為空白");	
				}
				else if(!donation_phone_num.trim().matches(donation_phone_numR)) {
					errors.put("donation_phone_num","電話號碼格式不正確");
				}
				
				if(!errors.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/donation/DonationForm/selectdata.jsp");			
					failureView.forward(req, res);
					return;
				}								
		
//			try {
//				donator_name = new String(dname);
//			}catch(Exception e) {
//				errors.add("捐款編號格式不正確");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/donation/DonationForm/selectdata.jsp");
//				failureView.forward(req, res);
//			
//				return;
//			}
//			try {
//				donation_phone_num = new String(dphone);
//			}catch(Exception e) {
//				errorMsgs.add("電話格式不正確");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/donation/DonationForm/selectdata.jsp");
//				failureView.forward(req, res);
//			
//				return;
//			}
			Donation_form_infoService donationFormSvc = new Donation_form_infoService();
			Donation_form_infoVO donation_form_infoVO = donationFormSvc.getOneDonation_form_info(donator_name,donation_phone_num);
			System.out.println(donationFormSvc);
			if(donation_form_infoVO == null) {
				errors.put("donation_form_infoVO","查無此捐款編號");
				}
			if (!errors.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/DonationForm/selectdata.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("donation_form_infoVO",donation_form_infoVO );
			String url = "/front-end/donation/DonationForm/selectdatain.jsp";
			RequestDispatcher successView =req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			}catch (Exception e ) {
				errors.put("errors","無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/donation/DonationForm/selectdata.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if("getOne_For_Display".equals(action)) {
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String str = req.getParameter("donator_name");
//				if(str == null || (str.trim()).length() == 0){
//		errorMsgs.add("請輸入捐款姓名");
//					
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/DonationForm/selectdata.jsp");			
//					failureView.forward(req, res);
//					return;
//				}			
//
//			String donator_name = null;
//			try {
//				donator_name = new String(str);
//			}catch(Exception e) {
//		errorMsgs.add("捐款姓名格式不正確");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/DonationForm/selectdata.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			Donation_form_infoService donationFormSvc = new Donation_form_infoService();
//			Donation_form_infoVO donation_form_infoVO = donationFormSvc.getOneDonation_form_info(donator_name);
//			System.out.println(donationFormSvc);
//			if(donation_form_infoVO == null) {
//		errorMsgs.add("查無此捐款姓名");
//				}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/DonationForm/select_page.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			req.setAttribute("donation_form_infoVO",donation_form_infoVO );
//			String url = "/DonationForm/listOneDonationForm.jsp";
//			RequestDispatcher successView =req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			
//			}catch (Exception e ) {
//		errorMsgs.add("無法取得資料" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/DonationForm/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		if("insert".equals(action)) {
			
			Map<String,String> errors = new HashMap<String,String>();
			req.setAttribute("errors", errors);
			try {
				
				String npo_id = req.getParameter("npo_id");
				
				String donator_name = req.getParameter("donator_name");
				String donator_nameReg = "^[(\u4e00-\u9fa5)]{2,20}$";
				if (donator_name == null || donator_name.trim().length() == 0) {
					errors.put("donator_name","請輸入捐款人姓名");
				}else if(!donator_name.trim().matches(donator_nameReg)) {
					errors.put("donator_name","只能是中文,且長度必須在2到20之間");
				}
				
				String donator_phone_num = req.getParameter("donator_phone_num").trim();
				String donator_phone_numReg = "^[0-9]{10}";
				if(donator_phone_num == null || (donator_phone_num.trim()).length() == 0) {
					errors.put("donator_phone_num","請輸入電話號碼");
				}
				else if(!donator_phone_num.trim().matches(donator_phone_numReg)) {
					errors.put("donator_phone_num","只能輸入數字");
				}
				
//				Integer donation_money = Integer.valueOf(req.getParameter("donation_money").trim());

				Integer donation_money = 0;
				String donation_moneyR = req.getParameter("donation_money").trim();

				if(donation_moneyR == "" ) {
					errors.put("donation_money","請輸入捐款金額");
				}						
				else {
						donation_money = Integer.parseInt(donation_moneyR);
						System.out.println("沒有輸入" + donation_money);
				}
				if(donation_money == 0) {
					errors.put("donation_money","捐款金額請大於0");
				}
//				else {
//					donation_money = Integer.parseInt(donation_moneyR);
//					System.out.println("��J������0" + donation_money);	
//				}
			
				String payment;
				 String [] parameterValues = req.getParameterValues("payment");
				 if(parameterValues != null) {
					 payment = parameterValues[0].toString();
				 }else {
					 errors.put("payment", "請選擇一種付款方式");
					 payment = "";
				 }
					
				 String onwhitch;
				 
				 String [] parameterValue = req.getParameterValues("onwhitch");
				 String receipt_type = req.getParameter("receipt_type").trim();

				 if(parameterValue != null) {
					 onwhitch = parameterValue[0].toString();
					 if(receipt_type == null ||(receipt_type.trim()).length() == 0) {
						  errors.put("onwhitch","請勿空白");
					 }
					 else {
						 System.out.println(receipt_type);
					 }
				 }else {
					 errors.put("onwhitch", "請選擇一種付款方式");
					 onwhitch = "";
				 }
				HttpSession session = req.getSession();
				MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
				String member_id = memberVO.getMember_id();
				System.out.println("memberID : " + member_id);
				Donation_form_infoVO donation_form_infoVO = new Donation_form_infoVO();
				donation_form_infoVO.setNpo_id(npo_id);
				donation_form_infoVO.setDonator_name(donator_name);
				donation_form_infoVO.setDonator_phone_num(donator_phone_num);
				donation_form_infoVO.setDonation_money(donation_money);
				donation_form_infoVO.setPayment(payment);
				donation_form_infoVO.setReceipt_type(receipt_type);
				donation_form_infoVO.setMember_id(member_id);

				if(!errors.isEmpty()) {
					req.setAttribute("donation_form_infoVO", donation_form_infoVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/donation/DonationForm/addTest.jsp");
					failureView.forward(req, res);
					return;
				}
				Donation_form_infoService donation_form_infoSvc = new Donation_form_infoService();
				donation_form_infoVO = donation_form_infoSvc.addDonation_form(donator_name, donator_phone_num, donation_money, payment, receipt_type,npo_id, member_id);//���ǭn���k�@��		

				String url = "/front-end/donation/myMain/successpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) { //console的錯誤可顯示到畫面上
						errors.put("errors",e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/donation/DonationForm/addTest.jsp");
						failureView.forward(req, res);
					}
				}
	}
}
