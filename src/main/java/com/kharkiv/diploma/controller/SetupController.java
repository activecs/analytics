package com.kharkiv.diploma.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.kharkiv.diploma.controller.form.SettingsForm;
import com.kharkiv.diploma.dto.widget.Product;
import com.kharkiv.diploma.dto.widget.Settings;
import com.kharkiv.diploma.service.ProductService;

@RestController
@RequestMapping("/settings")
public class SetupController extends EventAbstractController {
	
	private static final int FIRST = 0;
	private static final String SETTINGS_PROPERTY = "settings";
	
	@Inject
	private ProductService productService;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public List<Product> getProducts(){
		return productService.getAllProducts();
	}
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	public void setupAdminView(@RequestBody SettingsForm settingsForm, HttpSession session){
		Settings settings = new Settings();
		populateSettings(settingsForm, settings);
		session.setAttribute(SETTINGS_PROPERTY, settings);
	}

	private void populateSettings(SettingsForm settingsForm, Settings settings) {
		settings.setFrom(settingsForm.getFrom());
		settings.setTo(settingsForm.getTo());
		String productSKU = settingsForm.getProductSku();
		Optional<Product> foundProduct = productService.getProductBySKU(productSKU);
		Product product = foundProduct.or(productService.getAllProducts().get(FIRST));
		settings.setProduct(product);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public SettingsForm getCurrentSettings(HttpSession session){
		Settings settings = (Settings) session.getAttribute(SETTINGS_PROPERTY);
		SettingsForm settingsForm = new SettingsForm();
		populateSettings(settings, settingsForm);
		return settingsForm;
	}
	
	private void populateSettings(Settings settings, SettingsForm settingsForm) {
		settingsForm.setFrom(settings.getFrom());
		settingsForm.setTo(settings.getTo());
		settingsForm.setProductSku(settings.getProduct().getSKU());
	}
}
