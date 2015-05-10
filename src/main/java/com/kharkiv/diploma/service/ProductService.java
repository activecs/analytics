package com.kharkiv.diploma.service;

import java.util.List;

import com.google.common.base.Optional;
import com.kharkiv.diploma.dto.widget.Product;

public interface ProductService {
	
	Optional<Product> getProductBySKU(String sku);

	List<Product> getAllProducts();

	List<Product> getAllProducts(int maxAmount);
}
