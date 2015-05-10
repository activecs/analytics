package com.kharkiv.diploma.service.impl;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Integer.MAX_VALUE;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.kharkiv.diploma.converter.Event2ProductConverter;
import com.kharkiv.diploma.dao.EventDao;
import com.kharkiv.diploma.dto.BaseEntity.SortOrder;
import com.kharkiv.diploma.dto.analytics.Event;
import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.service.ProductService;
import com.kharkiv.diploma.util.PaginationData;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private static final String BASKET_EVENT_CATEGORY = "basket";
	private static final String BASKET_EVENT_ADD_ACTTION = "add";
	private static final String SORT_LABEL = "label";

	@Value("${product.default.treshold:50}")
	private int defaultProductTreshold;
	@Inject
	private EventDao eventDao;
	@Inject
	private Event2ProductConverter eventToProductConverter;
	
	@Override
	public List<Product> getAllProducts(int maxAmount) {
		PaginationData paginationData = buildPaginationData(SORT_LABEL, SortOrder.ASC, maxAmount);
		Set<Product> uniqueProducts = newHashSet(getConvertedProducts(paginationData));
		return newArrayList(uniqueProducts); 
	}
	
	@Override
	public List<Product> getAllProducts() {
		return getAllProducts(MAX_VALUE);
	}
	
	@Override
	public Optional<Product> getProductBySKU(final String sku) {
		List<Product> uniqueProducts = getAllProducts();
		Optional<Product> product = Iterables.tryFind(uniqueProducts, new Predicate<Product>() {
			@Override
			public boolean apply(Product arg) {
				return Objects.equals(arg.getSKU(), sku);
			}
		});
		return product;
	}
		
	private List<Product> getConvertedProducts(PaginationData paginationData) {
		List<Event> addedProductToBasketEvents = eventDao.get(BASKET_EVENT_CATEGORY, BASKET_EVENT_ADD_ACTTION, paginationData);
		return eventToProductConverter.convert(addedProductToBasketEvents);
	}
	
	private PaginationData buildPaginationData(String sortBy, SortOrder sortOrder, int pageSize){
		PaginationData data = new PaginationData();
		data.setPageSize(pageSize);
		data.setSortBy(sortBy);
		data.setSortOrder(sortOrder);
		return data;
	}

}
