package com.kharkiv.diploma.dto.analytics;

import static com.kharkiv.diploma.util.QueryNamesConstants.EventQueries.DELETE_BY_ID;
import static com.kharkiv.diploma.util.QueryNamesConstants.EventQueries.GET_ALL;
import static com.kharkiv.diploma.util.QueryNamesConstants.EventQueries.GET_BY_CATEGORY_AND_ACTION;
import static com.kharkiv.diploma.util.QueryNamesConstants.EventQueries.GET_BY_ID;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "event")
@NamedQueries(value = { @NamedQuery(name = GET_ALL, query = "SELECT e FROM Event e"),
        @NamedQuery(name = GET_BY_ID, query = "SELECT e FROM Event e WHERE e.id = :id"),
        @NamedQuery(name = GET_BY_CATEGORY_AND_ACTION, query = "SELECT e FROM Event e WHERE e.category = :category AND e.action = :action"),
        @NamedQuery(name = DELETE_BY_ID, query = "DELETE FROM Event e WHERE e.id = :id")})
public class Event extends BaseEntity {
	
	private static final long serialVersionUID = 7356353462905122855L;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private Session session;
	@Column
	private String category;
	@Column
	private String action;
	@Column
	private String label;
	@Column 
	private String value;
	@Column
	@Lob
	private String description;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;
	
	@PrePersist
	protected void onCreate() {
	    if (date == null)
	    	date = Calendar.getInstance();
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
