package it.mbaziekone.book_e_commerce.service;

import it.mbaziekone.book_e_commerce.model.dto.AdminDto;
import it.mbaziekone.book_e_commerce.model.security.Admin;

public interface AdminService {
	
	public void saveAdmin(AdminDto adminDto);
	
	public Admin findByUsername(String username);
}
