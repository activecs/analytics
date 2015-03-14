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
@Table(name = "location")
public class Location extends BaseEntity {
	
	private static final long serialVersionUID = 7330053549690079817L;

	@Column
	private String country;
	
	@Column
	private String city;
	
	@OneToMany
	private Set<Session> sessions;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session){
		session.setLocation(this);
		sessions.add(session);
	}
	
}
