package it.mbaziekone.book_e_commerce.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.mbaziekone.book_e_commerce.model.Product;
import it.mbaziekone.book_e_commerce.service.ProductService;
import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// Show products
	@GetMapping("/products")
	public String viewProductsPage(
			@RequestParam(defaultValue = "0") int page, // Current page (default the first page)
			@RequestParam(defaultValue = "4") int size, // Number of elements per page (default 4)
			Model model) {
		
		Page<Product> productPage = productService.getPaginatedProducts(page, size);
		
		model.addAttribute("productPage", productPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		
		return "productsAdmin"; //This is the view that contains the products table layout
	}
	
	// Insert product
	@PostMapping("/saveProduct")
	public String addProduct(@Valid @ModelAttribute("product") Product product, @RequestParam("image") MultipartFile image, 
							 BindingResult result, Model model){
		if(result.hasErrors()) {
			model.addAttribute("product", product);
		}
		
		try {
			productService.saveProduct(product, image);
			model.addAttribute("message", "Product added successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("message", "Error saving the product!");
			
			return "productsAdmin";
		}
		
		return "redirect:/products";
	}
	
	// Update product
	@PostMapping("/updateProduct/{id}")
	public String updateProduct(@ModelAttribute("product") Product product, @PathVariable(value = "id") long id, 
								@RequestParam("image") MultipartFile image, Model model) {
		
		product = productService.getProductById(id);
		model.addAttribute("product", product);
		
		try {
			productService.saveProduct(product, image);
			model.addAttribute("message", "Product updated successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("message", "Error updating product!");
			
			return "products";
		}
		
		return "redirect:/products";
	}
	
	// Delete product
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value = "id") long id) {
		productService.deleteProduct(id);
		
		return "redirect:/products";
	}
}
