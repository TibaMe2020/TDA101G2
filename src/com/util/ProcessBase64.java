package com.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class ProcessBase64 {
	public byte[] base64ToBytes(String base64) {
		try {
			base64 = base64.substring(base64.indexOf(",", 0) + 1);
			byte[] decodedString = Base64.getDecoder().decode(base64.getBytes("UTF-8"));
			return decodedString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}		
	}


}
