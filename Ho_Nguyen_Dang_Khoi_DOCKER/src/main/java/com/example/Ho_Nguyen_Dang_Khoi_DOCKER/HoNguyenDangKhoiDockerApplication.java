package com.example.Ho_Nguyen_Dang_Khoi_DOCKER;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HoNguyenDangKhoiDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoNguyenDangKhoiDockerApplication.class, args);
	}

	@Bean
	ProfileSeed profileSeed() {
		return new ProfileSeed("Hồ Nguyễn Đăng Khôi 1", "2280601558");
	}

	@Bean
	ApplicationRunner seedProfile(StudentProfileService profileService) {
		return args -> profileService.getOrCreateProfile();
	}
}

record ProfileSeed(String fullName, String studentId) {
}
