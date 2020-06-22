package com.member.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashPassword {

//	public static void main(String[] args) {
//
//		HashPassword p = new HashPassword();
//		String password = "afhgfjf123";
//		String algorithm = "SHA-256";
//		byte[] salt = p.createSalt();
//		String hashValue = p.generateHash(password, algorithm, salt);
//		System.out.println("hash:�@" + hashValue);
//		System.out.println(hashValue.length());
//	}

//	public String generateHash(String password, String algorithm, byte[] salt) {
	public String generateHash(String password, String algorithm) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
			md.reset();
//			md.update(salt);
			byte[] hash = md.digest(password.getBytes());

			return bytesToStringHex(hash);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private final static char[] HEXARRAY = "0123456789ABCDEF".toCharArray();

	private String bytesToStringHex(byte[] hash) {
		char[] hexChars = new char[hash.length * 2];
		for (int i = 0; i < hash.length; i++) {
			int v = hash[i] & 0xFF;
			hexChars[i * 2] = HEXARRAY[v >>> 4];
			hexChars[i * 2 + 1] = HEXARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	public byte[] createSalt() {
		byte[] bytes = new byte[10];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}

}
