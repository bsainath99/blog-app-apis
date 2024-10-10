package com.sainath.blog_app_apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Integer id;
	@NotEmpty
	@Size(min=4,message = "Username must be minimum 4 characters")
	private String name;
	@Email(message = "Invalid Email Address")
	private String email;
	@NotEmpty
	@Size(min=3,max=9,message = "Password must be min of 3 and max of 10 characters")
	private String password;
	@NotNull
	private String about;
}
