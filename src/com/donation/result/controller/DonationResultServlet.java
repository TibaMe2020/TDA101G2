package com.donation.result.controller;

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

import com.donation.donation_result.model.Donation_resultService;
import com.donation.donation_result.model.Donation_resultVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)//上傳三要素
public class DonationResultServlet extends HttpServlet {

	public void doGet (HttpServletRequest req, HttpServletResponse res)
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
						String str = req.getParameter("result_id");
						if(str == null || (str.trim()).length() == 0){
							errorMsgs.add("請輸入成果編號");
							
						}
						if(!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/donation/Result/select_page.jsp");			
							failureView.forward(req, res);
							return;
						}			
		
					String result_id = null;
					try {
						result_id = new String(str);
					}catch(Exception e) {
						errorMsgs.add("成果編號格式不正確");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/donation/Result/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
		
					Donation_resultService resultSvc = new Donation_resultService();
					Donation_resultVO donation_resultVO = resultSvc.getOneDonation_result(result_id);
					System.out.println(resultSvc);
					if(donation_resultVO == null) {
						errorMsgs.add("查無此成果文章");
						}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/donation/Result/select_page.jsp");
						failureView.forward(req, res);
						return;
					}
		
					req.setAttribute("donation_resultVO",donation_resultVO );
					String url = "/front-end/donation/Result/listOneResult.jsp";
					RequestDispatcher successView =req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					}catch (Exception e ) {
						errorMsgs.add("無法取得資料" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/donation/Result/select_page.jsp");
						failureView.forward(req, res);
					}
				}
		
		if("insert".equals(action)) {
			 Map<String,String> errors = new HashMap<String,String>();
			 req.setAttribute("errors", errors);
			 try {
				 String npo_id = new String(req.getParameter("npo_id").trim());

				 String result_content = req.getParameter("result_content").trim();
				 String result_contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_,。，！？、)]{2,400}$";
				 if(result_content == null ||(result_content.trim()).length() == 0) {
					 errors.put("result_content","內文請勿空白");
				 }
				 else if(!result_content.trim().matches(result_contentReg)) {
					 errors.put("result_content","長度必須在2~400之間");
				 }
				
				 Integer result_month = Integer.valueOf(req.getParameter("result_month").trim());
//				 Integer result_month = null;
//					String result_monthR = req.getParameter("result_month").trim();
//					
//					if(result_monthR == "") {
//						errors.put("result_month","請輸入月份");
//						System.out.println("HO");
//					}						
//					else{
//						result_month = Integer.parseInt(result_monthR);
//						System.out.println(result_month);
//
//					}
//				 Integer result_month = 0;
//					String result_monthR = req.getParameter("result_month").trim();
//
//					if(result_monthR == "" ) {
//						errors.put("result_month","請輸入發表月份");
//					}						
//					else {
//							result_month = Integer.parseInt(result_monthR);
//						System.out.println("沒有輸入" + result_month);
//					}
//					if(result_month == 0 || result_month >= 12) {
//						errors.put("result_month","月份格式不正確");
//						System.out.println("不可以等於" + result_month);
//					}
 
				Part part = req.getPart("result_image");
					InputStream in = part.getInputStream();
					byte[] result_image = new byte[in.available()];
					in.read(result_image);
					in.close();
					

						Donation_resultVO donation_resultVO = new Donation_resultVO();
						donation_resultVO.setNpo_id(npo_id);
						donation_resultVO.setResult_month(result_month);
						donation_resultVO.setResult_image(result_image);
						donation_resultVO.setResult_content(result_content);
						if(!errors.isEmpty()) {
							req.setAttribute("donation_resultVO", donation_resultVO);
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/resultTest.jsp");
							failureView.forward(req, res);
							return;
						}
						Donation_resultService resultSvc = new Donation_resultService();
						donation_resultVO =resultSvc.addDonation_result(result_month, result_image, result_content, npo_id);
						req.setAttribute("donation_resultVO", donation_resultVO);

						String url = "/back-end/resultTest.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
				 			}catch(Exception e) {
				 				errors.put("errors",e.getMessage());
				 				RequestDispatcher failureView = req
				 						.getRequestDispatcher("/back-end/resultTest.jsp");
				 				failureView.forward(req, res);
				 			}
			 			}
		if("delete".equals(action)) {
			 List<String> errorMsgs = new LinkedList<String>();
			 
			 req.setAttribute("errorMsgs", errorMsgs);
			 
			 try {
				 
				 String result_id = new String(req.getParameter("result_id"));
				 
				 Donation_resultService resultSvc = new Donation_resultService();
				 resultSvc.deleteDonation_result(result_id);
				 
				 String url = "/front-end/member/resultTest.jsp";
				 RequestDispatcher successView = req.getRequestDispatcher(url);
				 successView.forward(req, res);
			 }catch (Exception e ) {
				 errorMsgs.add("刪除資料失敗" +e.getMessage());
				 RequestDispatcher failureView = req
						 .getRequestDispatcher("/front-end/member/resultTest.jsp");
				 failureView.forward(req, res);
			 }
		 }
	
	}
}
