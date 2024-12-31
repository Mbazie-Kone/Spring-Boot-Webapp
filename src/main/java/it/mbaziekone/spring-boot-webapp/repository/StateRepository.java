package it.mbaziekone.book_e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import it.mbaziekone.book_e_commerce.model.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	
	public List<State> findByCountryCode(@Param("code") String code);

}
