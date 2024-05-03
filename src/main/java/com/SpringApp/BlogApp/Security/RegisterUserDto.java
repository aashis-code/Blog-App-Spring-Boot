package com.SpringApp.BlogApp.Security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterUserDto {

    private String email;
    
    private String password;
    
    private String name;
	
    private String about;
}
