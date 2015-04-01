package com.kharkiv.diploma.dto.widget;



public class BrowserUsage {
	
	private Browser browser;
	private Integer occurence;
	
	public BrowserUsage() {
	}
	
	public BrowserUsage(Browser browser, Integer occurence) {
		this.browser = browser;
		this.occurence = occurence;
	}
	
	public Browser getBrowser() {
		return browser;
	}
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	public Integer getOccurence() {
		return occurence;
	}
	public void setOccurence(Integer occurence) {
		this.occurence = occurence;
	}

	
	
}
