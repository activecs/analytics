package com.kharkiv.diploma.service;

import java.util.List;

import com.kharkiv.diploma.dto.widget.Order;
import com.kharkiv.diploma.dto.widget.Product;

public interface EventService {
	
	List<Product> getAllRecentlyAddedToBasketProducts();

	List<Product> getAllRecentlyAddedToBasketProducts(int maxAmount);

	List<Order> getAllLatestOrders();
	
	List<Order> getAllLatestOrders(int maxAmount);

	List<Product> getAllProducts();

	List<Product> getAllProducts(int maxAmount);
}
