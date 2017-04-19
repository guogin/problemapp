package com.myapp.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(insertable = true, updatable = false)
	private Timestamp createdAt;

	@Column(insertable = true, updatable = true)
	private Timestamp updatedAt;

	@Version
	private long version;

	@PrePersist
	protected void onPrePersist() {
		Timestamp now = now();

		if (createdAt == null) {
			this.createdAt = now;
		}
		if (updatedAt == null) {
			this.updatedAt = now;
		}
	}

	@PreUpdate
	protected void onPreUpdate() {
		this.updatedAt = now();
	}

	protected static Timestamp now() {
		return new Timestamp(new Date().getTime());
	}

	public Date getCreatedAt() {
		return new Date(this.createdAt.getTime());
	}

	public Date getLastUpdatedAt() {
		return new Date(this.updatedAt.getTime());
	}

	public Long getId() {
		return id;
	}
}
