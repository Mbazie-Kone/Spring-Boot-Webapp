package it.mbaziekone.book_e_commerce.service.impl;
	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.mbaziekone.book_e_commerce.model.security.Admin;
import it.mbaziekone.book_e_commerce.service.AdminService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private AdminService adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminService.findByUsername(username);
		if(admin == null) {
			LOGGER.error("User not found with username: "+ username);
			throw new UsernameNotFoundException("User not found");
		}
		
		return User.builder()
				.username(admin.getUsername())
				.password(admin.getPassword())
				.roles(admin.getRole())
				.build();
	}

}
