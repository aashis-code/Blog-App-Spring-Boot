package com.SpringApp.BlogApp.Services;

import java.util.List;

import com.SpringApp.BlogApp.Payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUser();

	void deleteUser(Integer userId);
	
	UserDto getUserByEmail(String email);

}
