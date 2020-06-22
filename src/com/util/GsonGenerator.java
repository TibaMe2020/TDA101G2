package com.util;

import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class GsonGenerator {
	
	public Gson getGson() {
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
		return gson;
	}

}
