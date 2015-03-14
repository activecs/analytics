package com.kharkiv.diploma.dto.analytics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "navigation")
public class Navigation extends BaseEntity {
	
	private static final long serialVersionUID = -237907840528138834L;

	@Column(length = 50)
	private String provider;
	
	@Column
	private String device;
	
	@Column
	private String browser;
	
	@Column
	private String platform;
	
	@Column
	private String language;
	
	@OneToOne
	private Session session;

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
