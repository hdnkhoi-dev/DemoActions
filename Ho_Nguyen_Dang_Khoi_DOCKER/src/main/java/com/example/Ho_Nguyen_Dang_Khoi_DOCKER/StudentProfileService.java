package com.example.Ho_Nguyen_Dang_Khoi_DOCKER;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentProfileService {
	private static final long PROFILE_ID = 1L;

	private final StudentProfileRepository repository;
	private final ProfileSeed profileSeed;

	public StudentProfileService(StudentProfileRepository repository, ProfileSeed profileSeed) {
		this.repository = repository;
		this.profileSeed = profileSeed;
	}

	@Transactional
	public StudentProfile getOrCreateProfile() {
		return repository.findById(PROFILE_ID).orElseGet(() -> repository.save(
			new StudentProfile(PROFILE_ID, normalize(profileSeed.fullName()), normalize(profileSeed.studentId()))
		));
	}

	@Transactional
	public StudentProfile updateProfile(String fullName, String studentId) {
		StudentProfile profile = getOrCreateProfile();
		profile.setFullName(normalize(fullName));
		profile.setStudentId(normalize(studentId));
		return repository.save(profile);
	}

	private static String normalize(String value) {
		if (value == null) {
			return "";
		}
		return value.trim().replaceAll("\\s+", " ");
	}
}
