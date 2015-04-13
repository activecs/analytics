package com.kharkiv.diploma.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kharkiv.diploma.converter.Event2OrderConverter;
import com.kharkiv.diploma.converter.Event2ProductConverter;
import com.kharkiv.diploma.dao.EventDao;
import com.kharkiv.diploma.dto.BaseEntity.SortOrder;
import com.kharkiv.diploma.dto.analytics.Event;
import com.kharkiv.diploma.dto.widget.Order;
import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.service.EventService;
import com.kharkiv.diploma.util.PaginationData;

@Service
@Transactional
public class EventServiceImpl implements EventService {
	
	private static final String BASKET_EVENT_CATEGORY = "basket";
	private static final String BASKET_EVENT_ADD_ACTTION = "add";
	private static final String ORDER_EVENT_CATEGORY = "order";
	private static final String ORDER_EVENT_PLACED_ACTTION = "placed";
	private static final String SORT_BY_DATE = "date";

	@Value("${product.default.treshold:50}")
	private int defaultProductTreshold;
	@Inject
	private EventDao eventDao;
	@Inject
	private Event2ProductConverter eventToProductConverter;
	@Inject
	private Event2OrderConverter eventToOrderConverter;
	
	@Override
	public List<Product> getAllRecentlyAddedToBasketProducts(int maxAmount) {
		PaginationData paginationData = buildPaginationData(SORT_BY_DATE, SortOrder.ASC, maxAmount);
		return getConvertedProducts(paginationData);
	}

	@Override
	public List<Product> getAllRecentlyAddedToBasketProducts() {
		PaginationData paginationData = buildPaginationData(SORT_BY_DATE, SortOrder.ASC);
		return getConvertedProducts(paginationData);
	}
	
	private List<Product> getConvertedProducts(PaginationData paginationData) {
		List<Event> addedProductToBasketEvents = eventDao.get(BASKET_EVENT_CATEGORY, BASKET_EVENT_ADD_ACTTION, paginationData);
		return eventToProductConverter.convert(addedProductToBasketEvents);
	}
	
	private PaginationData buildPaginationData(String sortBy, SortOrder sortOrder){
		return buildPaginationData(sortBy, sortOrder, defaultProductTreshold);
	}
	
	@Override
	public List<Order> getAllLatestOrders() {
		PaginationData paginationData = buildPaginationData(SORT_BY_DATE, SortOrder.ASC);
		return getConvertedOrders(paginationData);
	}

	@Override
	public List<Order> getAllLatestOrders(int maxAmount) {
		PaginationData paginationData = buildPaginationData(SORT_BY_DATE, SortOrder.ASC, maxAmount);
		return getConvertedOrders(paginationData);
	}
	
	private PaginationData buildPaginationData(String sortBy, SortOrder sortOrder, int pageSize){
		PaginationData data = new PaginationData();
		data.setPageSize(pageSize);
		data.setSortBy(sortBy);
		data.setSortOrder(sortOrder);
		return data;
	}
	
	private List<Order> getConvertedOrders(PaginationData paginationData) {
		List<Event> addedProductToBasketEvents = eventDao.get(ORDER_EVENT_CATEGORY, ORDER_EVENT_PLACED_ACTTION, paginationData);
		return eventToOrderConverter.convert(addedProductToBasketEvents);
	}

}
