package com.SpringApp.BlogApp.Services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringApp.BlogApp.Entity.User;
import com.SpringApp.BlogApp.ModelMapper.UserModelMapper;
import com.SpringApp.BlogApp.Payloads.UserDto;
import com.SpringApp.BlogApp.Repositories.UserRepo;
import com.SpringApp.BlogApp.Services.UserService;
import com.SpringApp.BlogApp.Exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserModelMapper userModelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = new User();
		user = userModelMapper.UserDtoTOUser(userDto);
		User savedUser = userRepo.save(user);
		return userModelMapper.UserToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User save = userRepo.save(user);
		return userModelMapper.UserToUserDto(save);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","id",userId));
		return userModelMapper.UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> findAll = userRepo.findAll();
		List<UserDto> collect = findAll.stream().map(user -> userModelMapper.UserToUserDto(user)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		userRepo.delete(user);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User userByEmail = userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Email", "address", 0));
		return userModelMapper.UserToUserDto(userByEmail);
	}

}
