package com.SpringApp.BlogApp.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringApp.BlogApp.Entity.User;
import com.SpringApp.BlogApp.Payloads.UserDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserModelMapper {

	
	@Autowired
	private ModelMapper modelMapper;
	
	//Convert User Dto to User
	public User UserDtoTOUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	//Convert UserDto to User
	public UserDto UserToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
}
