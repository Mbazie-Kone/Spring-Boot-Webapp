package it.mbaziekone.book_e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.mbaziekone.book_e_commerce.model.security.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Admin findByUsername(String username);

}
