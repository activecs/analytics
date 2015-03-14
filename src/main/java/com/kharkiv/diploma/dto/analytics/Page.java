package com.kharkiv.diploma.dto.analytics;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "page")
public class Page extends BaseEntity {

	private static final long serialVersionUID = -7063108102276659263L;

	@Column(length = 80)
	private String path;
	
	@Column(length = 50)
	private String title;
	
	@OneToMany(mappedBy="page")
	private Set<PageView> pageViews;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<PageView> getPageViews() {
		return pageViews;
	}

	public void setPageViews(Set<PageView> pageViews) {
		this.pageViews = pageViews;
	}
	
}
