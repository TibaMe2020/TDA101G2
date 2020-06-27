package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.product.model.Product_DAO;
import com.product.model.Product_Service;
import com.product.model.Product_VO;
import com.product_version.model.Version_Service;
import com.product_version.model.Version_VO;
import com.util.GsonGenerator;

@MultipartConfig(maxRequestSize = 25 * 1024 * 1024)
@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Version_Service versionSvc;

	public void init() {
		versionSvc = new Version_Service();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.addHeader("Access-Control-Allow-Origin", "*");
//		res.setContentType("application/json");
//		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");

		PrintWriter out = res.getWriter();
		String id = req.getParameter("product_id");

		Enumeration<String> enm = req.getParameterNames();
		while (enm.hasMoreElements()) {
			String name = (String) enm.nextElement();
//			System.out.println(req.getParameter(name));
		}

		String action = req.getParameter("action");
		System.out.println(action);

		Gson gson = new Gson();
		Product_DAO product_DAO = new Product_DAO();
		switch (action) {
		case "NewDate":
			out.print(gson.toJson(product_DAO.newDate()));
			break;
		case "HighPrice":
			out.print(gson.toJson(new Product_Service().highPrice()));
			break;
		case "LowPrice":
			out.print(gson.toJson(new Product_Service().lowPrice()));
			break;
		case "HighScore":
			out.print(gson.toJson(new Product_Service().highScore()));
			break;
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("product_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String product_id = null;
				try {
					product_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號不對");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Product_Service product_Svc = new Product_Service();
				Product_VO product_VO = product_Svc.getOneProduct(product_id);
				if (product_VO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("product_VO", product_VO); // 資料庫取出的empVO物件,存入req
				String url = "/pro/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String product_id = req.getParameter("product_id");
				/*************************** 2.開始查詢資料 ****************************************/
				Product_Service product_Svc = new Product_Service();
				Product_VO product_VO = product_Svc.getOneProduct(product_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("product_VO", product_VO); // 資料庫取出的empVO物件,存入req
				String url = "/pro/updateProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/pro/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				GsonGenerator g =new GsonGenerator();
				Gson gs =g.getGson();
				String json =req.getParameter("data");
				
				Product_VO product_VO =gs.fromJson(json, Product_VO.class);
				String product_id =product_VO.getProduct_id();
				/*************************** 2.開始修改資料 *****************************************/
				Product_Service product_Svc = new Product_Service();
				product_Svc.updateProduct(product_VO);
				List<Version_VO> versions =product_VO.getVersions();
				for(Version_VO v: versions) {
					if("".equals(v.getProduct_version_id())) {
						v.setProduct_id(product_id);
						versionSvc.addVersion(v);
					}else {
						versionSvc.updateVersion(v);
					}
				} 
				product_VO =product_Svc.updateProduct(product_VO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				out.print("success");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				out.print("fail");
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				GsonGenerator g = new GsonGenerator();
				Gson gs = g.getGson();
				String json = req.getParameter("data");
				
				Product_VO productVO =gs.fromJson(json, Product_VO.class);
				
				List<Version_VO> list =productVO.getVersions();

				/*************************** 2.開始新增資料 ***************************************/
				Product_Service product_Svc = new Product_Service();
				product_Svc.insertWithVersion(productVO, list);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				
				out.write("success");
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				out.write("fail");
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			out = res.getWriter();

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String product_id = new String(req.getParameter("pid"));

				/*************************** 2.開始刪除資料 ***************************************/
				Product_Service product_Svc = new Product_Service();
				product_Svc.deleteProduct(product_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				out.print("success");

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				out.print("fail");
			}
		}

		// 萬用查詢
		if ("listProduct_ByName".equals(action)) { // 來自select_page.jsp的複合查詢請求
			System.out.println("複合查詢");
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				System.out.println(map);
				/*************************** 2.開始複合查詢 ***************************************/
				Product_Service product_Svc = new Product_Service();
				List<Product_VO> list = product_Svc.getAll(map);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listProduct_ByName", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/product/SearchProduct.jsp");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/Noproduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete_version".equals(action)) {
			try {
				String vid = req.getParameter("vid");
				versionSvc.deletVersion(vid);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}

		if ("update_state".equals(action)) {
			try {

				String pid = req.getParameter("pid");
				String ps = req.getParameter("product_state");
				Product_Service pSvc = new Product_Service();
				Product_VO pVO = pSvc.getOneProduct(pid);
				if (pVO.getProduct_state() == 1) {
					pVO.setProduct_state(2);
				} else {
					pVO.setProduct_state(1);
				}
				pSvc.updateProduct(pVO);

				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
