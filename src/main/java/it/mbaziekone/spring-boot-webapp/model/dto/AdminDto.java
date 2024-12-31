package it.mbaziekone.book_e_commerce.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminDto {
	
	@NotEmpty(message = "Username is required")
	private String username;
	
	@NotBlank(message = "The password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 caracters long")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
		message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
	)
	private String password;
	
	@NotBlank(message = "The confirm password cannot be empty")
	@Size(min = 8, message = "Confirm password must be at least 8 caracters long")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
		message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
	)
	private String confirmPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}