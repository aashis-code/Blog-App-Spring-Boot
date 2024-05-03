package com.SpringApp.BlogApp.Security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponse {
	
	private int id;
	
	private String name;
	
	private String email;
	
	private String token;

    private long expiresIn;

}
