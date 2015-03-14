package com.kharkiv.diploma.dto.analytics;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "session")
public class Session extends BaseEntity {

	private static final long serialVersionUID = -6753254388122538087L;
	
	public enum SessionStatus {
		OPEN, CLOSED
	};
	
	@ManyToOne
	@JoinColumn(name = "visitor_id")
	private Visitor visitor;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@Enumerated
	private SessionStatus status;
	
	@OneToMany(mappedBy="session", fetch = FetchType.EAGER)
	private List<PageView> pageViews;
	
	@OneToMany(mappedBy="session", fetch = FetchType.EAGER)
	private Set<Transaction> transactions;
	
	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar open;
	
	@Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar closed;
	
	@OneToOne
	private Navigation navigation;

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public SessionStatus getStatus() {
		return status;
	}

	public void setStatus(SessionStatus status) {
		this.status = status;
	}

	public List<PageView> getPageViews() {
		return pageViews;
	}

	public void setPageViews(List<PageView> pageViews) {
		this.pageViews = pageViews;
	}
	
	public void addPageView(PageView pageView) {
		pageView.setSession(this);
		pageViews.add(pageView);
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		transaction.setSession(this);
		transactions.add(transaction);
	}

	public Calendar getOpen() {
		return open;
	}

	public void setOpen(Calendar open) {
		this.open = open;
	}

	public Calendar getClosed() {
		return closed;
	}

	public void setClosed(Calendar closed) {
		this.closed = closed;
	}
	
}
