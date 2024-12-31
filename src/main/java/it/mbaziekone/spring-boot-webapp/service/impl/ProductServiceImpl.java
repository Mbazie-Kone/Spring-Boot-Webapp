package it.mbaziekone.book_e_commerce.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.mbaziekone.book_e_commerce.model.Product;
import it.mbaziekone.book_e_commerce.repository.ProductRepository;
import it.mbaziekone.book_e_commerce.service.ProductService;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	private static final String UPLOAD_DIR = "images/";

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllCatalogs() {
		return productRepository.findAll();
	}

	public void saveProduct(Product product, MultipartFile image) throws IOException {

		File uploadDir = new File(UPLOAD_DIR);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		String originalFileName = image.getOriginalFilename();
		String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
		String safeFileName = uniqueFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
		String filePath = UPLOAD_DIR + safeFileName;
		Path path = Paths.get(filePath);
		try {
			Files.write(path, image.getBytes());
			product.setImagePath(safeFileName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error when save file " + uniqueFileName, e);
		}

		productRepository.save(product);
	}

	public Product getProductById(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		return optional.orElseThrow(() -> new RuntimeException("Product not found for ID :: " + id));
	}

	@Transactional
	public void deleteProduct(Long id) {

		Product product = getProductById(id);
		String imagePath = product.getImagePath();
		if (imagePath != null && !imagePath.isEmpty()) {
			Path path = Paths.get(UPLOAD_DIR + new File(imagePath).getName());
			try {
				Files.deleteIfExists(path);
			} catch (IOException e) {
				throw new RuntimeException("Error deleting image file: " + imagePath, e);
			}
		}

		productRepository.deleteById(id);
	}

	@Override
	public Page<Product> getPaginatedProducts(int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		return productRepository.findAll(pageable);
	}
}
