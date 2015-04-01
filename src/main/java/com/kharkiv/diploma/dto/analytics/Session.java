package com.kharkiv.diploma.dto.analytics;

import static com.kharkiv.diploma.util.QueryNamesConstants.SessionsQueries.DELETE_BY_ID;
import static com.kharkiv.diploma.util.QueryNamesConstants.SessionsQueries.GET_ALL;
import static com.kharkiv.diploma.util.QueryNamesConstants.SessionsQueries.GET_BY_ID;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries(value = { @NamedQuery(name = GET_ALL, query = "SELECT s FROM Session s"),
        @NamedQuery(name = GET_BY_ID, query = "SELECT s FROM Session s WHERE s.id = :id"),
        @NamedQuery(name = DELETE_BY_ID, query = "DELETE FROM Session s WHERE s.id = :id")})
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
	
	@Column(nullable = false)
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

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}
	
}
