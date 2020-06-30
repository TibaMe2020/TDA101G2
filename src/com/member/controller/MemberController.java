	package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.notification.model.NotiService;
import com.notification.model.NotiVO;
import com.util.CheckFormat;
import com.util.ForwardView;
import com.util.ProcessBase64;
import com.util.SendEmail;

@MultipartConfig()
@WebServlet("/member/controller")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService mbSvc;
	private NotiService notiSvc;
//	private CheckFormat format = new CheckFormat();

	public void init() {
		mbSvc = new MemberService();
		notiSvc = new NotiService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		MemberVO memberVO = null;
		CheckFormat format = new CheckFormat();
		ForwardView fw = new ForwardView(req, res);
		ProcessBase64 b64 = new ProcessBase64();
		if ("login".equals(action)) {
			String failurePath ="/front-end/store/store.jsp";
			String successPath = "/front-end/member/updateInfo.jsp";
			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			String url = (String) session.getAttribute("location");
			System.out.println("location: " + url);
			if(!"".equals(url) && url != null) {
				url = url.substring(url.indexOf('/', 1));				
			} else {
				url = "/front-end/store/store.jsp";
			}
//			url = (!"".equals(url)) ? url.substring(url.indexOf('/', 0)) : successPath;		
			System.out.println("login: " + url);
			try {
				String email = req.getParameter("email").trim();
				String password = req.getParameter("password").trim();
				
				
				if(format.isEmptyOrNull.test(email)) {
					errorMsgs.put("loginEmail", "Please enter your email");
				} else if (!format.checkEmailFormat.test(email)) {
					errorMsgs.put("loginEmail", "Invalid email format");
				}

				if (format.isEmptyOrNull.test(password)) {
					errorMsgs.put("loginPassword", "Please enter your password");
				}

//				if (!format.isErrorMsgsEmpty.test(errorMsgs)) {
//					fw.forward(failurePath);
//					return;
//				}
				
				if(!errorMsgs.isEmpty()) {
					fw.forward(failurePath);
					return;
				}

				memberVO = mbSvc.login(email, password);

				if (memberVO == null) {
					System.out.println(memberVO);
					errorMsgs.put("loginPassword", "It might be three following problems <br>"
							+ "1. Email or Password incorrect <br>"
							+ "2. Account has yet been activated, go to your email to activate your account <br>"
							+ "3. Account has been suspended");
					fw.forward(failurePath);
					return;
				}
				

				
				session.setAttribute("memberVO", memberVO);
				// Redirect to where user came from
//				String location = (String) session.getAttribute("location");
//				req.getSession().setAttribute("email", email);
				//D成功的話避免重新送出表單
//				res.sendRedirect(req.getContextPath() + successPath);
				res.sendRedirect(req.getContextPath() + url);
				session.removeAttribute("location");
//				fw.forward(successPath);
				return;

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", "Error occurred");
				fw.forward(failurePath);
				return;
			}
		}

		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("memberVO");
			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}

		if ("signup".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String failurePath ="/front-end/store/store.jsp";
			String successPath = "/front-end/store/store.jsp";
			HttpSession session = req.getSession();
			String url = (String) session.getAttribute("location");
			if(!"".equals(url) && url != null) {
				url = url.substring(url.indexOf('/', 0));				
			} else {
				url = "/front-end/store/store.jsp";
			}
			System.out.println("signup: " + url);
			try {
				String name = req.getParameter("name").trim();
				String email = req.getParameter("email").trim();
				String phone_num = req.getParameter("phone_num").trim();
				String password = req.getParameter("password").trim();
				String confirm = req.getParameter("confirm").trim();
				String address = req.getParameter("address").trim();
				String sex = req.getParameter("sex");
				String birthday = req.getParameter("birthday");

				if (format.isEmptyOrNull.test(name)) { // check if name field is empty
					errorMsgs.put("signupName", "You must enter a name");
				} else if (!format.checkNameFormat.test(name)) {
					errorMsgs.put("signupName", "Name field does not allow special characters");
					name = "";
				} else if (format.checkNameLength.test(name)) {
					errorMsgs.put("signupName", "Chinese name must less than 8 words, english name must less than 20 words");
				}

				if (format.isEmptyOrNull.test(email)) { // Check if email field is empty
					errorMsgs.put("signupEmail", "You must enter an email address");
					email = "";
				} else if (!format.checkEmailFormat.test(email)) { // check if email format is incorrect(50 chars)
					errorMsgs.put("signupEmail", "Please enter a valid email address");
					email = "";
				} else if (format.checkEmailLength.test(email)) { // check if email length > 50
					errorMsgs.put("signupEmail", "Email field only allows 50 characters");
					email = "";
				}

				// check if phone_num field is valid, it can be left empty(10 chars)
				if (!format.checkPhoneFormat.test(phone_num) && !format.isEmptyOrNull.test(phone_num)) {
					errorMsgs.put("signupPhone_num", "Please enter a valid phone number");
					phone_num = "";
				}

				if (format.isEmptyOrNull.test(password)) { // check if password field is empty
					errorMsgs.put("signupPassword", "You must enter a password");
					password = "";
				} else if (format.isEmptyOrNull.test(confirm)) { // check if confirm password field is empty
					errorMsgs.put("signupPassword", "You must confirm your password");
					confirm = "";
				} else if (!password.equals(confirm)) {
					errorMsgs.put("signupPassword", "The password and confirmation password do not match");
					password = "";
				}

				

				if (!format.checkAddressFormat.test(address) && address.length() != 0) { // check if address is invalid
					errorMsgs.put("signupAddress", "Invalid address");
					address = "";
				}

				memberVO = new MemberVO();
				java.sql.Date bday = null;

				try {
					bday = java.sql.Date.valueOf(birthday);
				} catch (Exception e) {
					bday = null;
				}

				memberVO.setName(name);
				memberVO.setEmail(email);
				memberVO.setAddress(address);
				memberVO.setPhone_num(phone_num);
				memberVO.setSex(sex);
				memberVO.setBirthday(bday);
				memberVO.setPassword(password);

				req.setAttribute("new_member", memberVO);

				if (!errorMsgs.isEmpty()) {
					fw.forward(failurePath);
					return;
				}

				memberVO = mbSvc.signUp(memberVO);

				if ("".equals(memberVO.getEmail())) {
					errorMsgs.put("signupEmail", "This email has been registered");
					fw.forward(failurePath);
					return;
				}
				
				String activate = 
						req.getScheme()+ "://" + req.getServerName() + ":" +
						req.getServerPort()+ "/" + req.getContextPath() + "/member/controller?member_id=" 
								+ memberVO.getMember_id() + "&action=activate_account";
				
				SendEmail se = new SendEmail();
				String content = "<h1>Welcome to Petbox</h1><br><p style='font-size:16px;'>Click the button below to activate your account</p><a href='"+ activate + "' style='background-color:#13406A; padding:10px 25px; "+
						"border-radius: 25px; color: #ffffff; text-decoration: none; display: inline-block; font-size: 18px; " + 
						"font-weight: bold;'>Activate</a>";
				se.sendEmail(email, content);
				session.removeAttribute("new_member");
				session.invalidate();
				//D成功的話避免重新送出表單
//				res.sendRedirect(req.getContextPath() + successPath);
				res.sendRedirect(req.getContextPath() + "/index.jsp");
//				fw.forward(successPath);
				return;

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				fw.forward(failurePath);
				return;
			}
		}
		
		if("activate_account".equals(action)) {
			String failurePath ="/index.jsp";
			String successPath = "/index.jsp";
//			���董����擐��
//			String failurePath ="/front-end/index.jsp";
//			String successPath = "/front-end/index.jsp";
			
			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_id = req.getParameter("member_id");
				memberVO = mbSvc.updateState(member_id, 1);
				if(memberVO == null) {
					System.out.println("Activate failed");
				}
				fw.forward(failurePath);
				return;
			} catch(Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", "Error occurred");
				fw.forward(successPath);
				return;
			}
		}

		if ("update_info".equals(action)) {
			String failurePath ="/front-end/member/updateInfo.jsp";
			String successPath = "/front-end/member/updateInfo.jsp";
			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				String name = req.getParameter("name").trim();
				String address = req.getParameter("address").trim();
				String phone_num = req.getParameter("phone_num").trim();
				String birthday = req.getParameter("birthday").trim();
				String sex = req.getParameter("sex").trim();
				String password = req.getParameter("password").trim();
				String new_password = req.getParameter("new_password").trim();
				String confirm = req.getParameter("confirm").trim();
				byte[] profile_image = null;
				String base64 = req.getParameter("profile_image");
				
				memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
				
				if("".equals(base64)) {
					profile_image = memberVO.getProfile_image();
				}
				
				profile_image = b64.base64ToBytes(base64);
				
				if (profile_image.length == 0) {
					profile_image = memberVO.getProfile_image();
				}

				
//				try {
//					Part part = req.getPart("profile_image");
//					InputStream in = part.getInputStream();
//					profile_image = new byte[in.available()];
//					in.read(profile_image);
//					in.close();
//					System.out.println(profile_image.length);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//					profile_image = null;
//				}
				
				
				
				
				java.sql.Date bday = null;

				// if member has become a seller, you can't change your phone_num, name, address

				if (format.isEmptyOrNull.test(name)) { // check if name field is empty
					name = memberVO.getName();
				} else if (!format.checkNameFormat.test(name)) {
					name = memberVO.getName();
				} else if (format.checkNameLength.test(name)) {
					
					errorMsgs.put("updateName", "Chinese name must less than 8 words, english name must less than 20 words");
					name = memberVO.getName();
				}

				if (format.isEmptyOrNull.test(phone_num)) {
					phone_num = memberVO.getPhone_num();
				} else if (!format.checkPhoneFormat.test(phone_num)) {
					errorMsgs.put("updatePhone_num", "Please enter a valid phone number");
					phone_num = memberVO.getPhone_num();
				}

				if (format.isEmptyOrNull.test(address)) {
					address = memberVO.getAddress();
				} else if (!format.checkAddressFormat.test(address)) { // check if address is invalid
					errorMsgs.put("updateAddress", "Invalid address");
					address = memberVO.getAddress();
				}

				try {
					bday = java.sql.Date.valueOf(birthday);
				} catch (Exception e) {
					bday = null;
				}

				if (format.isEmptyOrNull.test(password) || format.isEmptyOrNull.test(new_password) 
						|| format.isEmptyOrNull.test(confirm)) {
					password = memberVO.getPassword();
				}

				if (!new_password.equals(confirm)) {
					errorMsgs.put("new_password", "The new password and confirmation password do not match");
				}

				

				if (!errorMsgs.isEmpty()) {
					System.out.println("error");
					fw.forward(failurePath);
					return;
				}
				
				
				memberVO.setName(name);
				memberVO.setAddress(address);
				memberVO.setPhone_num(phone_num);
				memberVO.setSex(sex);
				memberVO.setBirthday(bday);
				memberVO.setProfile_image(profile_image);
				memberVO.setPassword("");
				if (!"".equals(password)) {
					memberVO.setPassword(password);
				}

				memberVO = mbSvc.updateInfo(memberVO, new_password);

				// error
				if (format.isEmptyOrNull.test(memberVO.getPassword())) {
					errorMsgs.put("password", "Your current password is incorrect");
				}

				if (!errorMsgs.isEmpty()) {
					fw.forward(failurePath);
					return;
				}

				//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + successPath);
//				fw.forward(successPath);
				return;

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", e.getMessage());
				fw.forward(failurePath);
				return;
			}
		}

		if ("update_blog_info".equals(action)) {
			String failurePath ="/front-end/blog/MyBlog.jsp";
			String successPath = "/front-end/blog/MyBlog.jsp";
			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String nickname = req.getParameter("nickname").trim();
				String blog_name = req.getParameter("blog_name").trim();
				String pet_class = req.getParameter("pet_class").trim();
				byte[] blog_cover_image = null;

				try {
					Part part = req.getPart("blog_cover_image");
					InputStream in = part.getInputStream();
					blog_cover_image = new byte[in.available()];
					in.read(blog_cover_image);
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
					blog_cover_image = null;
				}

				memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
				
				if (format.checkNameLength.test(nickname)) {
					errorMsgs.put("nickname", "Nickname only allows 6 chinese characters or 20 english letters");
					nickname="";
				}

				if (format.checkBlogNameLength.test(blog_name)) {
					errorMsgs.put("blog_name", "Blog name only allows 16 chinese characters or 50 english letters");
					blog_name="";
				}
				
				if (blog_cover_image.length == 0) {
					blog_cover_image = memberVO.getBlog_cover_image();
				}
				
				memberVO.setNickname(nickname);
				memberVO.setBlog_name(blog_name);
				memberVO.setPet_class(pet_class);
				memberVO.setBlog_cover_image(blog_cover_image);

				if (!errorMsgs.isEmpty()) {
					fw.forward(failurePath);
					return;
				}

				
				memberVO = mbSvc.updateBlogInfo(memberVO);

				if (memberVO == null) {
					errorMsgs.put("database", "Something went wrong");
				}

				//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + successPath);
//				fw.forward(successPath);
				return;

			} catch (Exception e) {
				errorMsgs.put("error", e.getMessage());
				e.printStackTrace();
				fw.forward(failurePath);
				return;
			}
		}

		if ("submit_application".equals(action)) {
			String failurePath ="/front-end/member/submitApplication.jsp";
			String successPath = "/front-end/member/updateInfo.jsp";
			Map<String, String> errorMsgs = new HashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String name = req.getParameter("name").trim();
				String address = req.getParameter("address").trim();
				String phone_num = req.getParameter("phone_num").trim();
				String id_number = req.getParameter("id_number").trim();
				String bank_account = req.getParameter("bank_account").trim();
				String confirm = req.getParameter("confirm").trim();
				byte[] document_image = null;
				String base64 = req.getParameter("document_image");
				
				
				memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
				if(!"".equals(base64)) {
					document_image = b64.base64ToBytes(base64);					
				}

				if (format.isEmptyOrNull.test(name)) { // check if name field is empty
					name = memberVO.getName();
				} else if (!format.checkNameFormat.test(name)) {
					errorMsgs.put("name", "Name field does not allow special characters");
					name = memberVO.getName();
				} else if (format.checkNameLength.test(name)) {
					errorMsgs.put("name", "Chinese name must less than 8 words, english name must less than 20 words");
					name = memberVO.getName();
				}

				if (format.isEmptyOrNull.test(phone_num)) {
					phone_num = memberVO.getPhone_num();
				} else if (!format.checkPhoneFormat.test(phone_num) && !format.isEmptyOrNull.test(phone_num)) {
					errorMsgs.put("phone_num", "Please enter a valid phone number");
					phone_num = memberVO.getPhone_num();
				}

				if (format.isEmptyOrNull.test(address)) {
					address = memberVO.getAddress();
				} else if (!format.checkAddressFormat.test(address) && !format.isEmptyOrNull.test(address)) { // check if address is invalid
					errorMsgs.put("address", "Invalid address");
					address = memberVO.getAddress();
				}

				if (format.isEmptyOrNull.test(id_number)) {
					errorMsgs.put("id_number", "You must enter your ID number");
					id_number = "";
				} else if (!format.checkIdNumFormat.test(id_number)) {
					errorMsgs.put("id_number", "Invalid ID number");
					id_number = "";
				}

				if (format.isEmptyOrNull.test(bank_account)) {
					errorMsgs.put("bank_account", "You must enter your bank account");
					bank_account = "";
				} else if (!format.checkBankAccFormat.test(bank_account)) {
					errorMsgs.put("bank_account", "Invalid bank account");
					bank_account = "";
				}

				if (format.isEmptyOrNull.test(confirm)) {
					errorMsgs.put("confirm", "You must enter your current password to proceed");
				}
				HttpSession session = req.getSession();
				session.setAttribute("memberVO", memberVO);

				memberVO.setName(name);
				memberVO.setAddress(address);
				memberVO.setPhone_num(phone_num);
				memberVO.setId_number(id_number);
				memberVO.setBank_account(bank_account);
				memberVO.setDocument_image(document_image);
				
				if (!errorMsgs.isEmpty()) {
					fw.forward(failurePath);
					return;
				}

				boolean success = false;
				success = mbSvc.submitApplication(memberVO, confirm);
				memberVO.setMember_state(2);
				if (success == false) {
					errorMsgs.put("confirm", "Please make sure your password is correct");
				}

				if (!errorMsgs.isEmpty()) {
					fw.forward(failurePath);
					return;
				}

				//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + successPath);
//				fw.forward(successPath);
				return;

			} catch (Exception e) {
				errorMsgs.put("error", e.getMessage());
				e.printStackTrace();
				fw.forward(failurePath);
				return;
			}
		}

		if ("update_state".equals(action)) {
			String failurePath ="/back-end/userManagement.jsp";
			String successPath = "/back-end/userManagement.jsp";
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_id = req.getParameter("member_id");
				Integer member_state = new Integer(req.getParameter("member_state"));
				mbSvc.updateState(member_id, member_state);
				//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + successPath);
//				fw.forward(successPath);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("error", "Error occurred");
				fw.forward(failurePath);
				return;
			}
		}


		if ("forgot_password".equals(action)) {
			String failurePath ="/front-end/member/forgotPassword.jsp";
			String successPath = "/index.jsp";
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String email = req.getParameter("email").trim();
				
				if (format.isEmptyOrNull.test(email)) {
					errorMsgs.put("forgotEmail", "Enter an email to retrieve your account");
				} else if (!format.checkEmailFormat.test(email)) {
					errorMsgs.put("forgotEmail", "Invalid email address");
				}

				if (!format.isErrorMsgsEmpty.test(errorMsgs)) {
					fw.forward(failurePath);
					return;
				}
				// database
				memberVO = mbSvc.forgotPassword(email);
				
				if(memberVO == null) {
					errorMsgs.put("email", "Please make sure your email address is correct");	
					fw.forward(failurePath);
					return;
				}
				String absoluteURL = req.getScheme()+ "://" + req.getServerName() + ":" +
				req.getServerPort();
				String content = "<h1>Welcome to Petbox</h1><br><p style='font-size:16px;'>Please enter this link to change your password.</p>" + 
							"<a href='" + absoluteURL +req.getContextPath()+"/front-end/member/changePassword.jsp?member_id=" +
							memberVO.getMember_id() + "' style='background-color:#13406A; padding:10px 25px; " + 
									"border-radius: 25px; color: #ffffff; text-decoration: none; display: inline-block; font-size: 18px; " + 
									"font-weight: bold;'>Change Password</a>";
				SendEmail se = new SendEmail();
				se.sendEmail(email, content);
				//D成功的話避免重新送出表單
				res.sendRedirect(req.getScheme()+ "://" + req.getServerName() + ":" +
						req.getServerPort() + req.getContextPath() + successPath);
//				fw.forward(successPath);
				return;

			} catch (Exception e) {
				errorMsgs.put("error", "Please try again");
				e.printStackTrace();
				fw.forward(failurePath);
				return;
			}
		}
		
		if("change_password".equals(action)) {
			String failurePath ="/front-end/member/changePassword.jsp";
			String successPath = "/index.jsp";
			Map<String, String> errorMsgs = new HashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_id = req.getParameter("member_id");
				memberVO = mbSvc.getOne(member_id);
				String password = req.getParameter("password").trim();
				String confirm = req.getParameter("confirm").trim();
				if(format.isEmptyOrNull.test(password)) {
					errorMsgs.put("changePassword", "Please enter a new password");
				} else if (!password.equals(confirm)) {
					errorMsgs.put("changeConfirm", "New password and confirmation password do not match");
				}
				
				if (!errorMsgs.isEmpty()) {
					fw.forward(failurePath);
					return;
				}
				
				memberVO.setPassword(password);
				memberVO = mbSvc.changePassword(memberVO);
				
				if(memberVO == null) {
					errorMsgs.put("error", "Please enter a new password again");
					fw.forward(failurePath);
					return;
				}
				
				//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + successPath);
//				fw.forward(successPath);
				return;
			
			} catch (Exception e) {
				errorMsgs.put("error", "Please enter a new password again");
				e.printStackTrace();
				fw.forward(failurePath);
				return;
			}
		}
		
		if("seller_verification".equals(action)) {
			String failurePath ="/back-end/userManagement.jsp";
			String successPath = "/back-end/userManagement.jsp";
			try {
				String member_id = req.getParameter("member_id");
				String pass = req.getParameter("pass");
				memberVO = mbSvc.getOne(member_id);
				NotiVO notiVO = new NotiVO();
				notiVO.setMember_id(member_id);
				System.out.println(pass);
				if("succeed".equals(pass)) {
					if(memberVO.getDocument_image() != null) {
						System.out.println("document image exists");
						mbSvc.updateState(member_id, 4);
						notiVO.setNotification_class(5);
					} else {
						System.out.println("document image not exist");
						mbSvc.updateState(member_id, 3);
						notiVO.setNotification_class(4);
//						System.out.println(notiVO.getNotification_class());
					}					
					notiSvc.insert(notiVO);
				} else if("fail".equals(pass)) {
					if(!"".equals(memberVO.getBank_account())) {
						mbSvc.updateState(member_id, 3);	
					} else {
						mbSvc.updateState(member_id, 1);	
					}
				}
				//D成功的話避免重新送出表單
				res.sendRedirect(req.getContextPath() + successPath);
//				fw.forward(successPath);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				fw.forward(failurePath);
				return;
			}
		}
	}
}
