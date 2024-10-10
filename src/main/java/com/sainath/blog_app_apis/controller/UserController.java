package com.sainath.blog_app_apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sainath.blog_app_apis.payloads.ApiResponse;
import com.sainath.blog_app_apis.payloads.UserDTO;
import com.sainath.blog_app_apis.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
@Autowired
	private UserService userService;
	@PostMapping("/addUser")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
		UserDTO newUserDto=userService.createUser(userDto);
		return new ResponseEntity<UserDTO>(newUserDto,HttpStatus.CREATED);
	}
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto,@PathVariable Integer userId){
		UserDTO updatedUserDto= userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDTO>(updatedUserDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully!!!",true),HttpStatus.OK);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> userDtos=userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(userDtos,HttpStatus.OK);
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
		UserDTO userDto=userService.getUserById(userId);
		return new ResponseEntity<UserDTO>(userDto,HttpStatus.OK);
	}
}
