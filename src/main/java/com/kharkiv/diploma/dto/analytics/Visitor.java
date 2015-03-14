package com.kharkiv.diploma.dto.analytics;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.kharkiv.diploma.dto.BaseEntity;

@Entity
@DynamicInsert
@Table(name = "visitor")
public class Visitor extends BaseEntity {

	private static final long serialVersionUID = 6436177741210370112L;
	
	@Column
	private Long trackingId;
	
	@Column
	private Integer age;
	
	@OneToMany(mappedBy="visitor", fetch = FetchType.EAGER)
	private Set<Session> sessions;

	public Long getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(Long trackingId) {
		this.trackingId = trackingId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session) {
		session.setVisitor(this);
		sessions.add(session);
	}
	
}
