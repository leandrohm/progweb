package org.usp.stockmarket.utils;

import java.security.*;

public class MD5 {
	public static String encode(String msg) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");

		md.update(msg.getBytes(), 0, msg.length());

		byte[] mdbytes = md.digest();
	 
		// conversao para hexa
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
		  sb.append(Integer.toString((mdbytes[i] & 0xff) 
					  + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
