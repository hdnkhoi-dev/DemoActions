package com.example.Ho_Nguyen_Dang_Khoi_DOCKER;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfileForm {
	@NotBlank
	@Size(max = 120)
	private String fullName;

	@NotBlank
	@Size(max = 30)
	private String studentId;

	public ProfileForm() {
	}

	public ProfileForm(String fullName, String studentId) {
		this.fullName = fullName;
		this.studentId = studentId;
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
}
