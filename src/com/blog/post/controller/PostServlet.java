package com.blog.post.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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

import com.blog.message.model.MessageService;
import com.blog.message.model.MessageVO;
import com.blog.post.model.PostService;
import com.blog.post.model.PostVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PostServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("PostServlet");
		
		if("insert".equals(action)) {
			System.out.println("進入PostServlet insert!");
			Map errorMessage = new HashMap();
			req.setAttribute("errorMessage", errorMessage);
			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String member_id = req.getParameter("member_id");
				if(member_id == null || (member_id.trim()).length() == 0) {
					errorMessage.put("noMemberId", "會員編號請勿空白");
//					errorMsgs.add("會員編號請勿空白");
				}
	
				String post_class = req.getParameter("post_class");
				
				//把圖片讀進水管,轉成陣列
				Part post_image1 = req.getPart("post_image1");
				InputStream in1 = post_image1.getInputStream();
				byte[] image1 = new byte[in1.available()];
				in1.read(image1);
				in1.close();
				Part post_image2 = req.getPart("post_image2");
				InputStream in2 = post_image2.getInputStream();
				byte[] image2 = new byte[in2.available()];
				in2.read(image2);
				in2.close();
				Part post_image3 = req.getPart("post_image3");
				InputStream in3 = post_image3.getInputStream();
				byte[] image3 = new byte[in3.available()];
				in3.read(image3);
				in3.close();
				Part post_image4 = req.getPart("post_image4");
				InputStream in4 = post_image4.getInputStream();
				byte[] image4 = new byte[in4.available()];
				in4.read(image4);
				in4.close();
				Part post_image5 = req.getPart("post_image5");
				InputStream in5 = post_image5.getInputStream();
				byte[] image5 = new byte[in5.available()];
				in5.read(image5);
				in5.close();
								
				String post_content = req.getParameter("post_content");
				if(post_content == null || (post_content.trim()).length() == 0) {
					errorMessage.put("noPostContent", "文章內容請勿空白");
//					errorMsgs.add("文章內容請勿空白");
				} else if((post_content.trim()).length() < 20) {
					errorMessage.put("noEnoughWords", "文章內容請勿低於20個字");
//					errorMsgs.add("文章內容請勿低於20個字");
				}
				
				PostVO postVO = new PostVO();
				postVO.setMember_id(member_id);
				postVO.setPost_class(post_class);
				postVO.setPost_image1(image1);
				postVO.setPost_image2(image2);
				postVO.setPost_image3(image3);
				postVO.setPost_image4(image4);
				postVO.setPost_image5(image5);
				postVO.setPost_content(post_content);
				req.setAttribute("postVO", postVO);
				if(!errorMessage.isEmpty()) {
//				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/HomePage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//新增資料
				PostService service = new PostService();
				postVO = service.insertPost(member_id, post_class, image1, image2, image3, image4, image5, post_content);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/HomePage.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMessage.put("InsertFail", "新增資料失敗");
//				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/HomePage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("memberInsert".equals(action)) {
			Map errorMessage = new HashMap();
			req.setAttribute("errorMessage", errorMessage);
			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String member_id = req.getParameter("member_id");
				if(member_id == null || (member_id.trim()).length() == 0) {
					errorMessage.put("noMemberId", "會員編號請勿空白");
//					errorMsgs.add("會員編號請勿空白");
				}
	
				String post_class = req.getParameter("post_class");
				
				//把圖片讀進水管,轉成陣列
				Part post_image1 = req.getPart("post_image1");
				InputStream in1 = post_image1.getInputStream();
				byte[] image1 = new byte[in1.available()];
				in1.read(image1);
				in1.close();
				Part post_image2 = req.getPart("post_image2");
				InputStream in2 = post_image2.getInputStream();
				byte[] image2 = new byte[in2.available()];
				in2.read(image2);
				in2.close();
				Part post_image3 = req.getPart("post_image3");
				InputStream in3 = post_image3.getInputStream();
				byte[] image3 = new byte[in3.available()];
				in3.read(image3);
				in3.close();
				Part post_image4 = req.getPart("post_image4");
				InputStream in4 = post_image4.getInputStream();
				byte[] image4 = new byte[in4.available()];
				in4.read(image4);
				in4.close();
				Part post_image5 = req.getPart("post_image5");
				InputStream in5 = post_image5.getInputStream();
				byte[] image5 = new byte[in5.available()];
				in5.read(image5);
				in5.close();
								
				String post_content = req.getParameter("post_content");
				if(post_content == null || (post_content.trim()).length() == 0) {
					errorMessage.put("noPostContent", "文章內容請勿空白");
//					errorMsgs.add("文章內容請勿空白");
				} else if((post_content.trim()).length() < 20) {
					errorMessage.put("noEnoughWords", "文章內容請勿低於20個字");
//					errorMsgs.add("文章內容請勿低於20個字");
				}
				
				PostVO postVO = new PostVO();
				postVO.setMember_id(member_id);
				postVO.setPost_class(post_class);
				postVO.setPost_image1(image1);
				postVO.setPost_image2(image2);
				postVO.setPost_image3(image3);
				postVO.setPost_image4(image4);
				postVO.setPost_image5(image5);
				postVO.setPost_content(post_content);
				req.setAttribute("postVO", postVO);
				if(!errorMessage.isEmpty()) {
//				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//新增資料
				PostService service = new PostService();
				postVO = service.insertPost(member_id, post_class, image1, image2, image3, image4, image5, post_content);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/MyBlog.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMessage.put("InsertFail", "新增資料失敗");
//				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			try {
				//接收請求參數	
				PostService service = new PostService();
				String post_id = req.getParameter("post_id");
				PostVO postVO = service.getOnePost(post_id);
				String post_class = req.getParameter("post_class");
				//把圖片讀進水管,轉成陣列
				Part post_image1 = req.getPart("post_image1");
				InputStream in1 = post_image1.getInputStream();
				byte[] image1 = new byte[in1.available()];
				in1.read(image1);
				in1.close();
				Part post_image2 = req.getPart("post_image2");
				InputStream in2 = post_image2.getInputStream();
				byte[] image2 = new byte[in2.available()];
				in2.read(image2);
				in2.close();
				Part post_image3 = req.getPart("post_image3");
				InputStream in3 = post_image3.getInputStream();
				byte[] image3 = new byte[in3.available()];
				in3.read(image3);
				in3.close();
				Part post_image4 = req.getPart("post_image4");
				InputStream in4 = post_image4.getInputStream();
				byte[] image4 = new byte[in4.available()];
				in4.read(image4);
				in4.close();
				Part post_image5 = req.getPart("post_image5");
				InputStream in5 = post_image5.getInputStream();
				byte[] image5 = new byte[in5.available()];
				in5.read(image5);
				in5.close();
								
				String post_content = req.getParameter("post_content");
				if(post_content == null || (post_content.trim()).length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				} else if((post_content.trim()).length() < 20) {
					errorMsgs.add("文章內容請勿低於20個字");
				}
										
				postVO.setPost_id(post_id);
				postVO.setPost_class(post_class);
				postVO.setPost_image1(image1);
				postVO.setPost_image2(image2);
				postVO.setPost_image3(image3);
				postVO.setPost_image4(image4);
				postVO.setPost_image5(image5);
				postVO.setPost_content(post_content);
//				postVO.setMember_id(member_id);
//				postVO.setPost_like(post_like);
//				postVO.setPost_message_count(post_message_count);
//				postVO.setPost_share(post_share);
//				postVO.setCreate_time(create_time);
//				postVO.setUpdate_time(update_time);

				if(!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/updatePost.jsp");
					failureView.forward(req, res);
					return;
				}
				//修改資料
				PostService service1 = new PostService();
				postVO = service1.updatePost(postVO);						
				//轉交
				req.setAttribute("postVO", postVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/HomePage.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("修改失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/updatePost.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("memberUpdate".equals(action)) {
			System.out.println("進入PostServlet memberUpdate");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			try {
				//接收請求參數	
				PostService service = new PostService();
				String post_id = req.getParameter("post_id");
				PostVO postVO = service.getOnePost(post_id);
				String post_class = req.getParameter("post_class");
				//把圖片讀進水管,轉成陣列
				Part post_image1 = req.getPart("post_image1");
				InputStream in1 = post_image1.getInputStream();
				byte[] image1 = new byte[in1.available()];
				in1.read(image1);
				in1.close();
				Part post_image2 = req.getPart("post_image2");
				InputStream in2 = post_image2.getInputStream();
				byte[] image2 = new byte[in2.available()];
				in2.read(image2);
				in2.close();
				Part post_image3 = req.getPart("post_image3");
				InputStream in3 = post_image3.getInputStream();
				byte[] image3 = new byte[in3.available()];
				in3.read(image3);
				in3.close();
				Part post_image4 = req.getPart("post_image4");
				InputStream in4 = post_image4.getInputStream();
				byte[] image4 = new byte[in4.available()];
				in4.read(image4);
				in4.close();
				Part post_image5 = req.getPart("post_image5");
				InputStream in5 = post_image5.getInputStream();
				byte[] image5 = new byte[in5.available()];
				in5.read(image5);
				in5.close();
								
				String post_content = req.getParameter("post_content");
				if(post_content == null || (post_content.trim()).length() == 0) {
					errorMsgs.add("文章內容請勿空白");
				} else if((post_content.trim()).length() < 20) {
					errorMsgs.add("文章內容請勿低於20個字");
				}
										
				postVO.setPost_id(post_id);
				postVO.setPost_class(post_class);
				postVO.setPost_image1(image1);
				postVO.setPost_image2(image2);
				postVO.setPost_image3(image3);
				postVO.setPost_image4(image4);
				postVO.setPost_image5(image5);
				postVO.setPost_content(post_content);
//				postVO.setMember_id(member_id);
//				postVO.setPost_like(post_like);
//				postVO.setPost_message_count(post_message_count);
//				postVO.setPost_share(post_share);
//				postVO.setCreate_time(create_time);
//				postVO.setUpdate_time(update_time);

				if(!errorMsgs.isEmpty()) {
					req.setAttribute("postVO", postVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/updatePost.jsp");
					failureView.forward(req, res);
					return;
				}
				//修改資料
				PostService service1 = new PostService();
				postVO = service1.updatePost(postVO);						
				//轉交
				req.setAttribute("postVO", postVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/MyBlog.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("修改失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/updatePost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOneForUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String post_id = new String();
				post_id = req.getParameter("post_id");
				//查詢資料
				PostService service = new PostService();
				PostVO postVO = service.getOnePost(post_id);
				//轉交
				req.setAttribute("postVO", postVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Post/updatePost.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/listAllPost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String post_id = req.getParameter("post_id");
				String member_id = req.getParameter("member_id");
//				System.out.println(post_id);
//				System.out.println(member_id);
//				System.out.println("delete");
//				req.setAttribute("member_id", member_id);

				
				
				//刪除資料
				PostService service = new PostService();
				MessageService messageService = new MessageService();
				
				List<MessageVO> list = messageService.getByPostId(post_id);
				if(list != null) {
					for(MessageVO messageVO : list) {
						messageService.deleteMessage(messageVO.getMessage_id());
						System.out.println("delete one row");
					}
				}
				service.deletePost(post_id);
				//轉交
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/MyBlog.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("資料刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/MyBlog.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		if("getOnePost".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("post_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String post_id = null;
				try {
					post_id = new String(str);
				} catch(Exception e) {
					errorMsgs.add("文章編號格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.getSession().setAttribute("post_id", post_id);
				System.out.println(post_id);
				//查詢資料
//				PostService service = new PostService();
//				PostVO postVO = service.getOnePost(post_id);
//				if(postVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				//轉交
//				req.setAttribute("postVO", postVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/SinglePost.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getByMemberId".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("member_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String member_id = null;
				try {
					member_id = new String(str);
				} catch(Exception e) {
					errorMsgs.add("會員編號格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.getSession().setAttribute("member_id", member_id);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/MyBlog.jsp");
				successView.forward(req, res);
				
//				String mb1 = "MB00001";
//				if(member_id.equals(mb1)) {
//					req.getSession().setAttribute("member_id", member_id);
//					RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/MyBlog.jsp");
//					successView.forward(req, res);
//				}else {
//					req.getSession().setAttribute("member_id", member_id);
//					RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/OtherPeopleBlog.jsp");
//					successView.forward(req, res);
//				}
				
				
				//查詢資料
//				PostService service = new PostService();
//				List<PostVO> list = null;
//				list = service.getByMemberId(member_id);
//				if(list == null || list.isEmpty()) {
//					errorMsgs.add("查無資料");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				//轉交
//				req.setAttribute("list", list);
//				System.out.println(list);
//				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/MyBlog.jsp");
//				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Post/listAllByMemberId.jsp");
//				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getByPostClass".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接受請求參數
				String str = req.getParameter("post_class");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("分類名稱請勿空白");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String post_class = null;
				try {
					post_class = new String(str);
				} catch(Exception e) {
					errorMsgs.add("分類名稱格式不正確");
					e.printStackTrace();
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//查詢資料
				PostService service = new PostService();
				List<PostVO> list = null;
				list = service.getByPostClass(post_class);
				System.out.println(list);
				if(list == null || list.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				//轉交
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/blog/Post/listAllByClass.jsp");
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/blog/Post/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
