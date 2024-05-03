package com.SpringApp.BlogApp.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	
	private int id;
	
	@NotEmpty
	@Size(min=5,message = "Name size must be above 5 characters.")
	private String name;
	
	@Email
	@NotEmpty(message = "Email must not be empty.")
	private String email;
	
	@NotEmpty
	@Size(min=3,max = 10,message = "Password must be within 3 to 10 characters.")
	private String password;
	
	@NotEmpty(message = "About field must not be empty")
	@Size(min = 5, message = "Must be at least 5 characters long.")
	private String about;
	
}
