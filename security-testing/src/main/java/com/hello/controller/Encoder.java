package com.hello.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.experimental.var;

public class Encoder {
	public static void main(String[]args) {
	BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	System.out.println(encoder.encode("Admin"));
	System.out.println(encoder.encode("Member"));
	System.out.println(encoder.encode("Customer"));
	
	}

}
