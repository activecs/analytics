package com.kharkiv.diploma.dto.analytics;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "transaction")
public class Transaction extends BaseEntity {

	private static final long serialVersionUID = -3031410252192833209L;
	
	@Column
	private BigDecimal revenue;
	
	@Column
	private BigDecimal shipping;
	
	@Column
	private BigDecimal tax;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private Session session;
	
	@OneToMany(mappedBy="transaction", fetch = FetchType.EAGER)
	private Set<TransactionEntry> entries;

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getShipping() {
		return shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Set<TransactionEntry> getEntries() {
		return entries;
	}

	public void setEntries(Set<TransactionEntry> entries) {
		this.entries = entries;
	}
	
	public void addEntry(TransactionEntry entry){
		entry.setTransaction(this);
		entries.add(entry);
	}
	
}
