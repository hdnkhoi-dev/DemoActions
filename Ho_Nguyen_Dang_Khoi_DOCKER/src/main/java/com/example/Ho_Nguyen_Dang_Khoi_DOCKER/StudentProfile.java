package com.example.Ho_Nguyen_Dang_Khoi_DOCKER;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;

@Entity
public class StudentProfile {
	@Id
	private Long id;

	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false)
	private String studentId;

	@Column(nullable = false)
	private Instant updatedAt;

	protected StudentProfile() {
	}

	public StudentProfile(Long id, String fullName, String studentId) {
		this.id = id;
		this.fullName = fullName;
		this.studentId = studentId;
	}

	@PrePersist
	@PreUpdate
	void touch() {
		this.updatedAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
