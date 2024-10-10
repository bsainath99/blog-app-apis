package com.sainath.blog_app_apis.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sainath.blog_app_apis.config.AppConstants;
import com.sainath.blog_app_apis.payloads.ApiResponse;
import com.sainath.blog_app_apis.payloads.PostDTO;
import com.sainath.blog_app_apis.payloads.PostResponse;
import com.sainath.blog_app_apis.service.FileService;
import com.sainath.blog_app_apis.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDTO createPost=postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED);
	}
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getCategoryByUser(@PathVariable Integer userId){
		List<PostDTO> posts= postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getCategoryByCategory(@PathVariable Integer categoryId){
		List<PostDTO> posts= postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
	}

//	@GetMapping("/allPosts")
//	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,@RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy){
//		PostResponse postResponse = postService.getAllPosts(pageNumber,pageSize,sortBy);
//		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
//	}
//	
	@GetMapping("/allPosts")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> postDTOs = postService.getAllPosts();
		return new ResponseEntity<List<PostDTO>>(postDTOs,HttpStatus.OK);
	}
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO postDto=postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDto,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{postId}")
	public ApiResponse deletePostById(@PathVariable Integer postId) {
		postService.deletePostById(postId);
		return new ApiResponse("Post is successfully deleted",true);
	}

	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO,@PathVariable Integer postId){
		PostDTO postDTO2= postService.updatePostById(postDTO, postId);
		return new ResponseEntity<PostDTO>(postDTO2,HttpStatus.OK);
	}
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords){
		List<PostDTO> result= postService.searchposts(keywords);
		return new ResponseEntity<List<PostDTO>>(result,HttpStatus.OK);
	}
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
		PostDTO postDTO= postService.getPostById(postId);
		String fileName= fileService.uploadImage(path, image);
		postDTO.setImageName(fileName);
		PostDTO updatePost = postService.updatePostById(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException{
		InputStream resource=fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
}
