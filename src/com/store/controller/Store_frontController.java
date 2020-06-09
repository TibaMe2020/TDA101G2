package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/Store_frontController")
public class Store_frontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "http://127.0.0.1:5500/Introduction.html";
	StoreService ss = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		System.out.println("有進入Controller");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");

		String action = request.getParameter("action");
		if (action.equals("store_type")) {
			List<StoreVO> storelist = new ArrayList<StoreVO>();
			Gson gson = new Gson();
			ss = new StoreService();
			switch (type) {
				case "restaurant":
					type = "餐廳";
					System.out.println("回傳餐廳全部");
					out.print(gson.toJson(ss.findByClass(type)));
					break;
				case "hostel":
					type = "旅館";
					System.out.println("回旅館全部");
					out.print(gson.toJson(ss.findByClass(type)));
					break;
				case "grooming":
					type = "美容";
					url=url+"?store_type=grooming";
					break;
				case "school":
					type = "學校";
					url=url+"?store_type=school";
					break;
				case "hospital":
					type = "醫院";
					url=url+"?store_type=hospital";
					break;
			}
//			response.sendRedirect(url);
			System.out.println(type);
		}
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
