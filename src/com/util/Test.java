package com.util;

import java.util.UUID;

public class Test {
	public String apple = "hello";
	public Integer quantity = 10;
	
	public static void main(String[] args) {
		 String nonce = UUID.randomUUID().toString();
		 System.out.println(nonce);
	}

}
