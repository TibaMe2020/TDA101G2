package com.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.store.model.StoreService;
import com.store.model.StoreVO;
import com.store.service.model.ServiceService;
import com.store.service.model.ServiceVO;
import com.store_closed.model.Store_closedService;
import com.store_closed.model.Store_closedVO;
import com.store_order.model.Store_orderService;
import com.store_order.model.Store_orderVO;
import com.store_order_detail.model.Store_order_detailVO;

@WebServlet("/Store_frontController")
public class Store_frontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		if (action.equals("getStoreVO")) {
			String id = request.getParameter("storeId");
			
			GsonBuilder gsonBuilder = new GsonBuilder();
		    gsonBuilder.setDateFormat("yyyy/MM/dd");
		    Gson gson = gsonBuilder.create();
		    
			ss = new StoreService();
			
			Store_closedService closed = new Store_closedService(); 
			Set<Store_closedVO> closedlist = closed.selectByStore(id);
			
			String store = gson.toJson(ss.findByStoreId(id));
			String clo = gson.toJson(closedlist);
			
			List<String> obj = new ArrayList<String>();
			obj.add(store);
			obj.add(clo);
			out.print(gson.toJson(obj));
		}
		
		if (("getStoreData").equals(action)) {
			String member_id = request.getParameter("memberId");
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyy-MM-dd");
			Gson gson = gsonBuilder.create();
			StoreService storeSvc = new StoreService();
			StoreVO storeVO = storeSvc.findByMemberId(member_id);
			if(storeVO != null) {
				String store = gson.toJson(storeVO);
				Store_closedService closedSvc = new Store_closedService();
				
				Set<Store_closedVO> closed = closedSvc.selectByStore(storeVO.getStore_id()); 
//				List<Store_closedVO> closed2 = new ArrayList<Store_closedVO>();
//				closed2.addAll(closed);
//				for(Store_closedVO c:closed2) {
//					System.out.println(c.getStore_closed_day());
//				}
				
				String clo = gson.toJson(closed);
				List<String> obj = new ArrayList<String>();
				obj.add(store);
				obj.add(clo);
				out.print(gson.toJson(obj));
			}
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		
		String action = request.getParameter("action");
		if (("Booking").equals(action)) {
			Gson gson = new Gson();
			String json = request.getParameter("data");
			try {
				Map<String,String> datamap = gson.fromJson(json, Map.class);
				String store_id = datamap.get("store_id");
				
				StoreService storeSvc = new StoreService();
				String stclass = storeSvc.findByStoreId(store_id).getStore_class();
				
				String member_id = datamap.get("member_id");
				String store_order_name = datamap.get("store_order_name");
				String store_order_email = datamap.get("store_order_email");
				String store_order_phone_num = datamap.get("store_order_phone_num");
				Integer store_order_persons = null;
				if(datamap.get("store_order_persons")!=null) {
					store_order_persons = new Integer(datamap.get("store_order_persons"));
				}
				
	//			日期處理
				String timeStampReg = "^\\d{4}[\\-/\\.](0?[1-9]|1[012])[\\-/\\.](0?[1-9]|[12][0-9]|3[01])$";
				Timestamp store_order_date_time = null;
				if("".equals(datamap.get("store_order_date_time"))){
					out.print("預約失敗=預約日期不可空白");
					return;
				}else if(datamap.get("store_order_date_time").matches(timeStampReg)) {
					store_order_date_time = Timestamp.valueOf(datamap.get("store_order_date_time")+" 00:00:00");
				}else {
					store_order_date_time = Timestamp.valueOf(datamap.get("store_order_date_time")+":00");
				}
				
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				Date store_order_end_date = null;
				if(stclass.equals("旅館")) {
					if("".equals(datamap.get("store_order_end_date"))){
						out.print("預約失敗=結束日期不可空白");
						return;
					}else {
						store_order_end_date = new java.sql.Date(sdf2.parse(datamap.get("store_order_end_date")).getTime());
					}
				}
				
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
				store_orderVO.setStore_order_date_time(store_order_date_time);
				store_orderVO.setStore_order_end_date(store_order_end_date);
				store_orderVO.setStore_order_payment(store_order_payment);
				store_orderVO.setStore_order_note(store_order_note);
	//			orderService.newOrder(store_orderVO);
	// 新增訂單明細 1對多
				List<Store_order_detailVO> detail_list = new ArrayList<Store_order_detailVO>();
				
//				System.out.println(store_id);
//				System.out.println(stclass);
				if (!(stclass.equals("餐廳") || stclass.equals("醫院"))) {
					
					//gson
	//				List<Product> products = gson.fromJson(json, new TypeToken<List<Product>>(){}.getType());
	//			  	for(Store_order_detailVO list : products) {
	//			   		System.out.println(list);
	//			  	}
	//				for(Product p : products) {
//					   System.out.println(p);
//					   System.out.println(p.getName());
//					   System.out.println(p.getPid());
//					   System.out.println(p.getProduct_version());
//					   System.out.println(p.getProduct_version().get(0).getVid());
//					   for(ProductVersion pv : p.getProduct_version()) {
//					    System.out.println(pv.getVid());
//					    System.out.println(pv.getVersion());
//					   } 
	//				}
					
					//json
					JSONObject jObj = new JSONObject(json.toString());
					JSONArray jArray = jObj.getJSONArray("detail_list");
					int count = 0;
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject rest = jArray.getJSONObject(i);
						String service_id = rest.getString("service_id");
						Integer order_detail_pets = new Integer( rest.getString("order_detail_pets"));
						if(order_detail_pets <= 0 ) {
							order_detail_pets = null;
							continue;
						}
						count+=1;
						Store_order_detailVO detail = new Store_order_detailVO();
						detail.setService_id(service_id);
						detail.setOrder_detail_pets(order_detail_pets);
						detail_list.add(detail);
					}
					if (count==0) {
						out.print("預約失敗=寵物數量不可全部都是0");
						return;
					}
				}
				
				String store_order_id = orderService.insertWithDetail_return(store_orderVO, detail_list);
				out.print("預約成功="+store_order_id);
				System.out.println("預約成功");
			
			} catch (Exception e) {
				e.printStackTrace();
				out.print("Exception="+e.getMessage());
			}
			
		}
		
		if (("newStore").equals(action)) {
			
//			System.out.println("newStore");
//			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//			String json = "";
//			if (br != null) {
//				json = br.readLine();
//				System.out.println("original: " + json);
//			}
			
//			上傳圖片 直接存成VO
//			GsonBuilder builder = new GsonBuilder();
//			//D將給定的字串格式轉換成日期
//			builder.setDateFormat("yyyy-MM-dd");
//			//byte[] to base64
//			builder.registerTypeAdapter(byte[].class, 
//			(JsonSerializer<byte[]>) (src, typeOfSrc, context) -> 
//			new JsonPrimitive(Base64.getEncoder().encodeToString(src)));
			
			//D將給定的字串格式轉換成日期
			GsonBuilder gsonBuilder = new GsonBuilder();
		    gsonBuilder.setDateFormat("yyyy-MM-dd");
		    
		    //base64 to byte[]
			gsonBuilder.registerTypeAdapter(byte[].class,(JsonDeserializer<byte[]>) (json, typeOfT, context) ->
			Base64.getDecoder().decode(json.getAsString()));
			// 客製化設定後再create()創出gson物件
			Gson gson = gsonBuilder.create();
		    
			String jsondata = request.getParameter("data");
			StoreVO storeVO = gson.fromJson(jsondata,StoreVO.class);
			System.out.println(storeVO);
			
			//接收圖片				
//			String imagelist = request.getParameter("image");
//			System.out.println(imagelist);
			
			ss = new StoreService();
			
			List<Store_closedVO> cloesdList = storeVO.getStore_closed();
			if(cloesdList==null) {
				ss.newStore(storeVO);
				out.print("新增成功");
			}
			else {
				ss.insertWithClosed(storeVO, cloesdList);
				out.print("新增成功 (含其餘公休日)");
			}
		}
		
		if (("updateStore").equals(action)) {
			
			//D將給定的字串格式轉換成日期
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyy-MM-dd");
			
			//base64 to byte[]
			gsonBuilder.registerTypeAdapter(byte[].class,(JsonDeserializer<byte[]>) (json, typeOfT, context) ->
			Base64.getDecoder().decode(json.getAsString()));
			// 客製化設定後再create()創出gson物件
			Gson gson = gsonBuilder.create();
			
			String jsondata = request.getParameter("data");
			StoreVO storeVO = gson.fromJson(jsondata,StoreVO.class);
			
			
			//接收圖片				
//			String imagelist = request.getParameter("image");
//			System.out.println(imagelist);
			
			ss = new StoreService();
			Store_closedService closed = new Store_closedService(); 
			List<Store_closedVO> cloesdList = storeVO.getStore_closed();
			if(cloesdList==null) {
				ss.updateStore(storeVO);
				out.print("修改成功");
			}
			else {
				ss.updateStore(storeVO);
				Set<Store_closedVO> set = closed.selectByStore(storeVO.getStore_id());
				for(Store_closedVO s :set) {
					closed.deleteClosed(s.getStore_closed_id());
				}
				List<Store_closedVO> newclosed = storeVO.getStore_closed();
				for(Store_closedVO s :newclosed) {
					System.out.println(storeVO.getStore_id());
					s.setStore_id(storeVO.getStore_id());
					closed.newClosed(s);
				}
				out.print("修改成功 (含其餘公休日)");
			}
		}
		
		if (("getListByMember").equals(action)) {
			String member_id = request.getParameter("memberId");
			StoreService storeSvc = new StoreService();
			StoreVO store = storeSvc.findByMemberId(member_id);
			if(store!=null) {
				ServiceService serviceSvc = new ServiceService();
				Gson gson = new Gson();
				out.print(gson.toJson(serviceSvc.selectByStore(store.getStore_id())));
			}
		}
		
		if (("updateService").equals(action)) {
			String service = request.getParameter("service_vo");
			Gson gson = new Gson();
			ServiceVO serviceVO =gson.fromJson(service,ServiceVO.class);
			ServiceService serviceSvc = new ServiceService();
			serviceSvc.updateService(serviceVO);
			out.print("service修改成功");
		}
		
		if (("newService").equals(action)) {
			String service = request.getParameter("service_vo");
			Gson gson = new Gson();
			ServiceVO serviceVO =gson.fromJson(service,ServiceVO.class);
			ServiceService serviceSvc = new ServiceService();
			String serviceId = serviceSvc.newSerivce2(serviceVO);
			out.print("service新增成功="+serviceId);
		}
		
		if (("deleteService").equals(action)) {
			String serviceId = request.getParameter("serviceId");
			ServiceService serviceSvc = new ServiceService();
			serviceSvc.deleteService(serviceId);
			out.print("service刪除成功");
		}
		
	}

}
