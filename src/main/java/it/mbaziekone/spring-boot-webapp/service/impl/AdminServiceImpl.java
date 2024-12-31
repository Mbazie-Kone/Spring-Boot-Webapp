package it.mbaziekone.book_e_commerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.mbaziekone.book_e_commerce.model.dto.AdminDto;
import it.mbaziekone.book_e_commerce.model.security.Admin;
import it.mbaziekone.book_e_commerce.repository.AdminRepository;
import it.mbaziekone.book_e_commerce.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveAdmin(AdminDto adminDto) {
		Admin admin	= new Admin();
		admin.setUsername(adminDto.getUsername());
		admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		
		// Set a default role
		admin.setRole("ADMIN");
		
		adminRepository.save(admin);
	}
	
	@Override
	public Admin findByUsername(String username) {
		
		return adminRepository.findByUsername(username);
	}

}