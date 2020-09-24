package com.lemon1234.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {

	public final static String SALT="shaniyexianggan"; // 加密的盐
	
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	
	public static void main(String[] args) {
		// 47c6d93c7d34ee237085fafa3617fc4d
		// 47c6d93c7d34ee237085fafa3617fc4d
		String password="123123";
		
		System.out.println("Md5加密："+CryptographyUtil.md5(password, SALT));
	}
}
