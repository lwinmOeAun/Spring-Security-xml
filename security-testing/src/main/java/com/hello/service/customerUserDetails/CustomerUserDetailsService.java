package com.hello.service.customerUserDetails;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hello.service.customerDetails.CustomerService;
import com.hello.service.dao.Customer;


@Service
public class CustomerUserDetailsService implements UserDetailsService{
@Autowired
CustomerService customerService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer>result=customerService.findByEmail(username);
	return result.map(customer->User.withUsername(username)
			.accountExpired(isExpired(customer))
			.disabled(customer.isActivated())
			.accountLocked(customer.isLocked())
			.password(customer.getPassword())
			.credentialsExpired(isCreationalExpired(customer))
			.authorities("Customer")
			.build()).orElseThrow(()->new UsernameNotFoundException(username))
;		
	}
	private boolean isExpired(Customer custom) {
		if(null !=custom.getRetired()) {
		if(custom.getRetired().toLocalDate().isBefore(LocalDate.now())) {
			return true;
		}
		}
		return false;
	}
	private boolean isCreationalExpired(Customer custom) {
	if(null != custom.getValidpass()) {
		var validpass=custom.getValidpass().toLocalDate();
		if(validpass.isBefore(LocalDate.now())){
			return true;
		}
	}
		return false;
	}

}
