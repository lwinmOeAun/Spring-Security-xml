package com.hello.service.customerDetails;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hello.service.dao.Customer;

import lombok.experimental.var;

@Service
public class CustomerService {

	JdbcTemplate template;

	public CustomerService(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}

	public Optional<Customer> findByEmail(String email) {
		var customer = template.queryForObject("select * from CUSTOMER where email=?",
				new BeanPropertyRowMapper<>(Customer.class), email);
		return Optional.ofNullable(customer);
	}

}