package com.product_version.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product_version.model.Version_Service;
import com.product_version.model.Version_VO;

@WebServlet("/Version")
public class VersionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// One Display
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("product_version_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入規格編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/version_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String product_version_id = null;
				try {
					product_version_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("規格編號不對");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/version_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Version_Service version_search = new Version_Service();
				Version_VO version_VO = version_search.getOneVersion(product_version_id);
				if (version_VO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/version_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("version_VO", version_VO);
				String url = "/version/listOneVersion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/version_page.jsp");
				failureView.forward(req, res);
			}
		}
		// One for update
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String version_id = req.getParameter("product_version_id");
				/*************************** 2.開始查詢資料 ****************************************/
				Version_Service version_search = new Version_Service();
				Version_VO version_VO = version_search.getOneVersion(version_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("version_VO", version_VO); // 資料庫取出的empVO物件,存入req
				String url = "/version/updateVersion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/version/listAllVersion.jsp");
				failureView.forward(req, res);
			}
		}

		// update version
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String product_version_id = req.getParameter("product_version_id");
				String version_name = req.getParameter("version_name");

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,6}$";
				if (version_name == null || version_name.trim().length() == 0) {
					errorMsgs.add("規格名稱: 請勿空白");
				} else if (!version_name.trim().matches(enameReg)) {
					errorMsgs.add("規格名字: 只能是中、英文字母、數字和_ , 且長度必需在2到6之間");
				}

				Integer price = Integer.parseInt(req.getParameter("price"));
				if (price >= 999999 || price == 0) {
					errorMsgs.add("價錢: 請介於 0~99999 ");
				} else if (price == null) {
					errorMsgs.add("價錢: 請輸入價錢");
				}

				Integer inventory = Integer.parseInt(req.getParameter("inventory"));
				if (inventory >= 999 || inventory == 0) {
					errorMsgs.add("數量: 請介於 0~999 ");
				} else if (inventory == null) {
					errorMsgs.add("數量: 請輸入價錢");
				}

				Version_VO version_VO = new Version_VO();
				// 與Member串通之後才可使用
				version_VO.setProduct_version_id(product_version_id);

				version_VO.setVersion_name(version_name);
				version_VO.setPrice(price);
				version_VO.setInventory(inventory);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("version_VO", version_VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/version/updateVersion.jsp");
					failureView.forward(req, res);

					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Version_Service version_Svc = new Version_Service();
				version_VO = version_Svc.updateVersion(version_VO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("version_VO", version_VO);
				System.out.println(version_VO);
				String url = "/version/listOneVersion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/version/updateVersion.jsp");
				failureView.forward(req, res);
			}
		}

		// insert Version
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String product_id = req.getParameter("product_id");
				String version_name = req.getParameter("version_name");

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,6}$";
				if (version_name == null || version_name.trim().length() == 0) {
					errorMsgs.add("規格名稱: 請勿空白");
				} else if (!version_name.trim().matches(enameReg)) {
					errorMsgs.add("規格名字: 只能是中、英文字母、數字和_ , 且長度必需在2到6之間");
				}

				Integer price = Integer.parseInt(req.getParameter("price"));
				if (price >= 999999 || price < 0) {
					errorMsgs.add("價錢: 請介於 0~99999 ");
				} else if (price == null) {
					errorMsgs.add("價錢: 請輸入價錢");
				}

				Integer inventory = Integer.parseInt(req.getParameter("inventory"));
				if (inventory >= 999 || inventory < 0) {
					errorMsgs.add("數量: 請介於 0~999 ");
				} else if (inventory == null) {
					errorMsgs.add("數量: 請輸入價錢");
				}

				Version_VO version_VO = new Version_VO();
				// 與Member串通之後才可使用
				version_VO.setProduct_id(product_id);
				version_VO.setVersion_name(version_name);
				version_VO.setPrice(price);
				version_VO.setInventory(inventory);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("version_VO", version_VO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/version/addVersion.jsp");
					failureView.forward(req, res);

					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Version_Service version_Svc = new Version_Service();
				version_VO = version_Svc.addVersion(version_VO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("version_VO", version_VO);
				String url = "/version/listAllVersion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/version/addVersion.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs1 = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs1);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String product_version_id = new String(req.getParameter("product_version_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Version_Service version_Svc = new Version_Service();
				version_Svc.deletVersion(product_version_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/listAllVersion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs1.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/version/listAllVersion.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
