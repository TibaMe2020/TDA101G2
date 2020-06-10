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
		String action = request.getParameter("action");

//		查店家分類
		String type = request.getParameter("type");
		if (action.equals("store_type")) {
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
					out.print(gson.toJson(ss.findByClass(type)));
					break;
				case "school":
					type = "學校";
					out.print(gson.toJson(ss.findByClass(type)));
					break;
				case "hospital":
					type = "醫院";
					out.print(gson.toJson(ss.findByClass(type)));
					break;
			}
		}
		
//			查單一店家
		String id = request.getParameter("storeId");
		if (action.equals("getStoreVO")) {
			System.out.println(id);
			Gson gson = new Gson();
			ss = new StoreService();
			out.print(gson.toJson(ss.findByStoreId(id)));
		}
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
