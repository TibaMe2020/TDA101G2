package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.store.service.model.ServiceService;

@WebServlet("/ServiceController_Ajax")
public class ServiceController_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String store_id = request.getParameter("storeId");
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
// gson
		Gson gson = new Gson();
		ServiceService serviceSvc = new ServiceService();
		String action = request.getParameter("action");
		if (action.equals("getSerivceList")) {
//			System.out.println(store_id);
			out.print(gson.toJson(serviceSvc.selectByStoreFilter(store_id)));
			out.flush();
			out.close();
		}
			
//		JSONObject json = new JSONObject();
//		ServiceService serviceSvc = new ServiceService();
//		try {
//			json.put("key", serviceSvc.selectByStore(store_id));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
