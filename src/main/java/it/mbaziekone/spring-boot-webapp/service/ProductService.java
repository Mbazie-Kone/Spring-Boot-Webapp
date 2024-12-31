package it.mbaziekone.book_e_commerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import it.mbaziekone.book_e_commerce.model.Product;

public interface ProductService {
	
	public List<Product> getAllCatalogs();
	
	public void saveProduct(Product product, MultipartFile image) throws IOException;
	
	public Product getProductById(Long id);
	
	public void deleteProduct(Long id);
	
	public Page<Product> getPaginatedProducts(int page, int size);
	
}