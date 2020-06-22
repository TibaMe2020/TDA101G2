package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

class Product_score {
	
	String score;
	String member_id;
	String product_id;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "Product_score [score=" + score + ", member_id=" + member_id + ", product_id=" + product_id + "]";
	}
}


	class Product {
	String pid;
	String name;
	byte[] image;
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	List<ProductVersion> product_version;
	
	public String getPid() {
		return pid;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", image=" + image.length + ", product_version="
				+ product_version + "]";
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<ProductVersion> getProduct_version() {
		return product_version;
	}
	public void setProduct_version(List<ProductVersion> product_version) {
		this.product_version = product_version;
	}
	class ProductVersion {
		String vid;
		public String getVid() {
			return vid;
		}
		public void setVid(String vid) {
			this.vid = vid;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		@Override
		public String toString() {
			return "ProductVersion [vid=" + vid + ", version=" + version + ", price=" + price + ", quantity=" + quantity
					+ "]";
		}
		String version;
		Integer price;
		Integer quantity;
	}
}



@WebServlet("/score")
public class Score extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		PrintWriter out = res.getWriter();
		
		
		JsonSerializer<byte[]> js = (src, t, ctx) -> {
			return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
		};
		
		JsonDeserializer<byte[]> jd = (json, t, ctx) -> {
			return Base64.getDecoder().decode(json.getAsString());
		};
		
		Type listType = new TypeToken<List<Product>>(){}.getType();
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd");
		//byte[] to base64
		builder.registerTypeAdapter(byte[].class, 
				(JsonSerializer<byte[]>) (src, t, ctx) -> 
		new JsonPrimitive(Base64.getEncoder().encodeToString(src)));
		
		//base64 to byte[]
		builder.registerTypeAdapter(byte[].class, 
				(JsonDeserializer<byte[]>) (json, t, ctx) ->
		Base64.getDecoder().decode(json.getAsString()));
		
		Gson gson = builder.create();
		
		
		String json = req.getParameter("data");
//		System.out.println(json);
		
		Product p = gson.fromJson(json, Product.class);
		System.out.println(p);
//		System.out.println(p.getProduct_version().get(0).getVid());
		System.out.println(p.getImage().getClass());
		
//		String dejson = gson.toJson(p, Product.class);
//		System.out.println("dejson: " + dejson);
		
		
//		String base64 = req.getParameter("image");
//		System.out.println(base64.length());
//		ProcessBase64 process = new ProcessBase64();
//		byte[] image = process.base64ToBytes(base64);
//		System.out.println(image.length);
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "PETBOX";
		String passwd = "123456";
		
		try {
			Class.forName(driver);
			try (
					Connection con = DriverManager.getConnection(url, userid, passwd);
					PreparedStatement ps = con.prepareStatement("UPDATE member set document_image = ? where member_id = ?");) {
				
				ps.setBytes(1, p.getImage());
				ps.setString(2, "MB00001");
				ps.executeUpdate();
			}} catch(Exception e) {
				e.printStackTrace();
			}
		
		//product
//		Product p = gson.fromJson(json, Product.class);

//		for(Product p : products) {
//			System.out.println(p.getName());
//			System.out.println(p.getPid());
//			System.out.println(p.getProduct_version());
//			System.out.println(p.getProduct_version().get(0).getVid());
//			for(ProductVersion pv : p.getProduct_version()) {
//				System.out.println(pv.getVid());
//				System.out.println(pv.getVersion());
//			}	
//		}
		
		
//		product score
//	List<Product_score> ps = gson.fromJson(json, new TypeToken<List<Product_score>>(){}.getType());
//	for(Product_score p : ps) {
//		System.out.println(p.getMember_id());
//		System.out.println(p.getProduct_id());
//		System.out.println(p.getScore());
//	}

//		System.out.println(member_id + " " + score + " "+ product_id);

	}

}
