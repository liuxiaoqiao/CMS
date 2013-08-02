package com.foxconn.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class test {			
	public static void main(String[] args) {
	// TODO Auto-generated method stub

	Md5PasswordEncoder md5=new Md5PasswordEncoder();
	String md5Password=md5.encodePassword("admin", "ADMIN");	
	System.out.println(md5Password);
	}
}