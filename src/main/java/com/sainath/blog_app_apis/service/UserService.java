package com.sainath.blog_app_apis.service;

import java.util.List;

import com.sainath.blog_app_apis.payloads.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user);

	UserDTO updateUser(UserDTO user,Integer userId);
	
	UserDTO getUserById(Integer userId);
	
	List<UserDTO> getAllUsers();
	
	void deleteUser(Integer userId);
}
