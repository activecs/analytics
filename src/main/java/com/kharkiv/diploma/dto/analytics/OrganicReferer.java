package com.kharkiv.diploma.dto.analytics;

import static com.kharkiv.diploma.util.QueryNamesConstants.OrganicRefererQueries.GET_ALL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "organic_referer")
@NamedQueries(value = { @NamedQuery(name = GET_ALL, query = "SELECT o FROM OrganicReferer o")})
public class OrganicReferer extends BaseEntity {
	
	private static final long serialVersionUID = 7356353462905122855L;
	
	@Column 
	private String value;
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}
