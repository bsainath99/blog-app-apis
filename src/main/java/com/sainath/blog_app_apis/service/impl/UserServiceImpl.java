package com.sainath.blog_app_apis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sainath.blog_app_apis.entities.User;
import com.sainath.blog_app_apis.exception.ResourceNotFoundException;
import com.sainath.blog_app_apis.payloads.UserDTO;
import com.sainath.blog_app_apis.repository.UserRepo;
import com.sainath.blog_app_apis.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
    @Autowired
	private ModelMapper mapper;
	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user=dtoToUser(userDto);
		User newUser=userRepo.save(user);
		return userToDto(newUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=userRepo.save(user);
		UserDTO updatedUserDto= userToDto(updatedUser);
		return updatedUserDto;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users= userRepo.findAll();
		List<UserDTO> userDtos= users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		userRepo.delete(user);
	}

	private User dtoToUser(UserDTO userDTO) {
		User user=mapper.map(userDTO, User.class);
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		return user;
	}
	public UserDTO userToDto(User user) {
		UserDTO userDto=mapper.map(user, UserDTO.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
