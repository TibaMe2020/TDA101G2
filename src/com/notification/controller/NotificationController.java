package com.notification.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.notification.model.NotiService;
import com.notification.model.NotiVO;


@WebServlet("/notification/controller")
public class NotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NotiService notiSvc;
	public void init() {
		notiSvc = new NotiService();
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		NotiVO notiVO = null;
		
		
		if("delete".equals(action)) {
			try {
				String notification_id = req.getParameter("notification_id");
				notiSvc.delete(notification_id);
				RequestDispatcher view = req.getRequestDispatcher("/front-end/notification/notifications.jsp");
				view.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher view = req.getRequestDispatcher("/front-end/notification/notifications.jsp");
				view.forward(req, res);
				return;
			}
		}
		
		if("add_notification".equals(action)) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
			  String json = "";
			  if (br != null) {
			   json = br.readLine();
			  }
			  
			  Gson gson = new Gson();
			  notiVO = gson.fromJson(json, NotiVO.class);
			  notiSvc.insert(notiVO);
			  RequestDispatcher view = req.getRequestDispatcher("/front-end/notification/notifications.jsp");
				view.forward(req, res);
				return;
			  
			} catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher view = req.getRequestDispatcher("/front-end/notification/notifications.jsp");
				view.forward(req, res);
				return;
			}
		}
		
		if("update".equals(action)) {
			try {
				String member_id = req.getParameter("member_id");
				List<NotiVO> notis = notiSvc.findByMembId(member_id);
				for(NotiVO n : notis) {
					Integer n_class = n.getNotification_class();
					if(n_class < 6) {
						n.setNotification_class(n_class + 5);	
						notiSvc.update(n);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
