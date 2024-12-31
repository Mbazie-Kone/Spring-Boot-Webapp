package it.mbaziekone.book_e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.mbaziekone.book_e_commerce.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
