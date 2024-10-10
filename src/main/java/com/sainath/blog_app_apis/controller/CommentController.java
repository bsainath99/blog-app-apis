package com.sainath.blog_app_apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sainath.blog_app_apis.payloads.ApiResponse;
import com.sainath.blog_app_apis.payloads.CommentDTO;
import com.sainath.blog_app_apis.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/comments")
public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,@PathVariable Integer postId){
		CommentDTO commentDto= commentService.createComment(commentDTO, postId);
		return new ResponseEntity<CommentDTO>(commentDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/delete/{commentId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable Integer commentId){
		commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully!!!",true),HttpStatus.OK);
	}
}
