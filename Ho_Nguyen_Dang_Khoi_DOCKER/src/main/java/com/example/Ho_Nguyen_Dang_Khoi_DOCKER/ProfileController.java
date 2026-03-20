package com.example.Ho_Nguyen_Dang_Khoi_DOCKER;

import jakarta.validation.Valid;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ProfileController {
	private final StudentProfileService profileService;

	public ProfileController(StudentProfileService profileService) {
		this.profileService = profileService;
	}

	@GetMapping("/")
	public String index(
		@RequestParam(name = "edit", required = false) String edit,
		@RequestParam(name = "saved", required = false) String saved,
		Model model
	) {
		StudentProfile profile = profileService.getOrCreateProfile();
		model.addAttribute("profile", profile);
		model.addAttribute("avatarLetter", avatarLetter(profile.getFullName()));
		model.addAttribute("form", new ProfileForm(profile.getFullName(), profile.getStudentId()));
		model.addAttribute("editMode", edit != null || saved != null);
		return "index";
	}

	@GetMapping("/person/{id}")
	public String person(@PathVariable("id") Long id) {
		return "redirect:/";
	}

	@PostMapping("/profile")
	public String update(@Valid @ModelAttribute("form") ProfileForm form, BindingResult bindingResult, Model model) {
		StudentProfile profile = profileService.getOrCreateProfile();
		if (bindingResult.hasErrors()) {
			model.addAttribute("profile", profile);
			model.addAttribute("avatarLetter", avatarLetter(profile.getFullName()));
			model.addAttribute("editMode", true);
			return "index";
		}

		profileService.updateProfile(form.getFullName(), form.getStudentId());
		return "redirect:/?saved";
	}

	private static String avatarLetter(String fullName) {
		if (fullName == null) {
			return "K";
		}
		String normalized = fullName.trim().replaceAll("\\s+", " ");
		if (normalized.isEmpty()) {
			return "K";
		}
		String[] parts = normalized.split(" ");
		String last = parts[parts.length - 1];
		if (last.isEmpty()) {
			return "K";
		}
		return last.substring(0, 1).toUpperCase(Locale.ROOT);
	}
}
