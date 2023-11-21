package com.hello.service.dao;

import java.sql.Date;

import lombok.Data;
@Data
public class Customer {
	String name;
	String password;
	String phone;
	String email;
	private boolean activated;
	private boolean locked;
	private Date validpass;
	private Date retired;

}
