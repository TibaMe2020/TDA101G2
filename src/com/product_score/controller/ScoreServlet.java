package com.product_score.controller;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.product_order_master.model.Order_master_Service;
import com.product_score.model.Score_Service;
import com.product_score.model.Score_VO;
import com.util.GsonGenerator;

@WebServlet("/Score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
//		res.addHeader("Access-Control-Allow-Origin", "*");
//		res.setContentType("application/json");
//		res.setCharacterEncoding("UTF-8");
//
//		PrintWriter out = res.getWriter();
//
//		String action = req.getParameter("action");
//		System.out.println(action);
//
//		if("HighScore".equals(action)) {
//			System.out.println("HighScore");
//			try {
//				res.getWriter().write(new JSONObject().put("score", new Score_Service().sortScore()).toString());
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		System.out.println(action);
		// One Display
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("product_score_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入評分編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/score_page.jsp");
					failureView.forward(req, res);
					return;
				}

				
				String product_score_id = null;
				try {
					product_score_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("評分編號不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/score_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Score_Service score_Svc = new Score_Service();
				Score_VO score_VO = score_Svc.getOneScore(product_score_id);
				if (score_VO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/score_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("score_VO", score_VO);
				String url = "/score/listAllScore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/score_page.jsp");
				failureView.forward(req, res);
			}
		}
		// insert Version
		if ("insert".equals(action)) {


			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String order_id = req.getParameter("order_master_id");
				String json = req.getParameter("json");
				GsonGenerator g = new GsonGenerator();
				Gson gson = g.getGson();
				List<Score_VO> scoreList = gson.fromJson(json, new TypeToken<List<Score_VO>>(){}.getType());
				/*************************** 2.開始修改資料 *****************************************/
				Score_Service score_Svc = new Score_Service();
				Order_master_Service omSvc = new Order_master_Service();
				
				for(Score_VO s : scoreList) {
					score_Svc.addScore(s);
				}
				
				omSvc.updateState(order_id, "6");

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}
	}

}
