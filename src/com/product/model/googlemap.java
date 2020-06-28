
package com.product.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class googlemap {
 private static final String MY_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
   + "location=25.0418903,121.5234316&"
   + "radius=200&"
   + "types=food&"
   + "name=7-11&"
   + "language=zh-TW&"
   + "key=AIzaSyBPJRhviSK2E8-7hm5IIc1HLh0PbmPeTEA";
 
 public static void main(String[] args) throws IOException, JSONException {
  URL url = new URL(MY_URL);
  HttpURLConnection con = (HttpURLConnection) url.openConnection();
  con.setRequestMethod("GET");
  con.setUseCaches(false);
  
  int statusCode = con.getResponseCode();
  
  StringBuilder sb = new StringBuilder();
  InputStream is = con.getInputStream();
  InputStreamReader isr = new InputStreamReader(is);
  BufferedReader br = new BufferedReader(isr);
  
  String data;
  while ((data = br.readLine()) != null) {
   sb.append(data);
  }
  
  br.close();
  isr.close();
  is.close();
  
  System.out.println(sb.toString());
  
  // parse JSON
  JSONObject jObj = new JSONObject(sb.toString());
  JSONArray jArray = jObj.getJSONArray("results");
  for (int i = 0; i < jArray.length(); i++) {
   JSONObject rest = jArray.getJSONObject(i);
   String name = rest.getString("name");
   double rating = rest.getDouble("rating");
   String addr = rest.getString("vicinity");
   // 緯經度
   JSONObject geo = rest.getJSONObject("geometry");
   JSONObject location = geo.getJSONObject("location");
   double lat = location.getDouble("lat");
   double lng = location.getDouble("lng");
   
   JSONObject open = rest.getJSONObject("opening_hours");
   boolean open_now = open.getBoolean("open_now");
   
   
   System.out.println("Name = " + name);
   System.out.println("Rating = " + rating);
   System.out.println("Addr = " + addr);
   System.out.println(open_now ? "營業中" : "休息中");
   System.out.println("緯經度 = " + lat + ", " + lng);
   System.out.println("=================================");
  }
 }
 
}