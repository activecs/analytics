package com.kharkiv.diploma.dto.analytics;

import static com.kharkiv.diploma.util.QueryNamesConstants.PageViewQueries.DELETE_BY_ID;
import static com.kharkiv.diploma.util.QueryNamesConstants.PageViewQueries.GET_ALL;
import static com.kharkiv.diploma.util.QueryNamesConstants.PageViewQueries.GET_BY_ID;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "page_view")
@NamedQueries(value = { @NamedQuery(name = GET_ALL, query = "SELECT pv FROM PageView pv"),
        @NamedQuery(name = GET_BY_ID, query = "SELECT pv FROM PageView pv WHERE pv.id = :id"),
        @NamedQuery(name = DELETE_BY_ID, query = "DELETE FROM PageView pv WHERE pv.id = :id")})
public class PageView extends BaseEntity {

	private static final long serialVersionUID = 7172539220232486646L;
	
	@ManyToOne
	@JoinColumn(name = "page_id")
	private Page page;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private Session session;
	
	@Column(name = "created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;
	
	@Column
	private String referer;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	
}
