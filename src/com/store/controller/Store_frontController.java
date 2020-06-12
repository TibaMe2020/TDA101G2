package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.store.model.StoreService;
import com.store_order.model.Store_orderService;
import com.store_order.model.Store_orderVO;

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
		
		
//		System.out.println("有進入Controller");
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
					out.print(gson.toJson(ss.findByClass(type)));
					break;
				case "hostel":
					type = "旅館";
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
//			System.out.println(id);
			Gson gson = new Gson();
			ss = new StoreService();
			out.print(gson.toJson(ss.findByStoreId(id)));
		}
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		String action = request.getParameter("action");
		if (("Booking").equals(action)) {
			String json = request.getParameter("data");
			try {
			Map<String,String> datamap = gson.fromJson(json, Map.class);
			String store_id = datamap.get("store_id");
			String member_id = datamap.get("member_id");
			String store_order_name = datamap.get("store_order_name");
			String store_order_email = datamap.get("store_order_email");
			String store_order_phone_num = datamap.get("store_order_phone_num");
			Integer store_order_persons = new Integer(datamap.get("store_order_persons"));
//			Timestamp store_order_date_time = Timestamp.valueOf(datamap.get("store_order_date_time"));
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			Date store_order_end_date = new java.sql.Date(sdf2.parse(datamap.get("store_order_end_date")).getTime());
			String store_order_payment = datamap.get("store_order_payment");
			String store_order_note = datamap.get("store_order_note");
			
			
			
			Store_orderService orderService = new Store_orderService();
			Store_orderVO store_orderVO = new Store_orderVO();
			store_orderVO.setStore_id(store_id);
			store_orderVO.setMember_id(member_id);
			store_orderVO.setStore_order_name(store_order_name);
			store_orderVO.setStore_order_email(store_order_email);
			store_orderVO.setStore_order_phone_num(store_order_phone_num);
			store_orderVO.setStore_order_persons(store_order_persons);
//			store_orderVO.setStore_order_date_time(store_order_date_time);
//			store_orderVO.setStore_order_end_date(store_order_end_date);
			store_orderVO.setStore_order_payment(store_order_payment);
			store_orderVO.setStore_order_note(store_order_note);
			orderService.newOrder(store_orderVO);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
