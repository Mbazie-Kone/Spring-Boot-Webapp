package it.mbaziekone.book_e_commerce.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.mbaziekone.book_e_commerce.model.Product;
import it.mbaziekone.book_e_commerce.model.ProductCategory;
import it.mbaziekone.book_e_commerce.repository.ProductRepository;

@RestController
@RequestMapping("/api/chart")
public class ChartRestController {
	
	private ProductRepository productRepository;
	
	public ChartRestController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// Product category chart
	@GetMapping("/donut-data")
	public ResponseEntity<Map<String, Object>> getDonutChartData() {
		List<Product> products = productRepository.findAll();
		
		// Calculate the data for the graph
		Map<ProductCategory, Long> categoryCounts = products.stream()
				.collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
		
		Map<String, Object> response = new HashMap<>();
		
		// Convert keys to readable strings
		response.put("labels", categoryCounts.keySet().stream()
				.map(ProductCategory::getCategoryName)
				.collect(Collectors.toList()));
		
		response.put("values", new ArrayList<>(categoryCounts.values()));
		
		return ResponseEntity.ok(response);
	}
	
	// Product availability chart
	@GetMapping("/availability-data")
	public ResponseEntity<Map<String, Object>> getAvailabilityChartData() {
		List<Product> products = productRepository.findAll();
		
		// Calculate the data for the graph
		long availableCount = products.stream().filter(Product::isActive).count();
		long unavailableCount = products.size() - availableCount;
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("labels", List.of("Available", "Unavailable"));
		response.put("values", List.of(availableCount, unavailableCount));
		
		return ResponseEntity.ok(response);
	}
	
 }
