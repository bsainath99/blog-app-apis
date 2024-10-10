package com.sainath.blog_app_apis.service;

import java.util.List;

import com.sainath.blog_app_apis.entities.Post;
import com.sainath.blog_app_apis.payloads.PostDTO;
import com.sainath.blog_app_apis.payloads.PostResponse;

public interface PostService {

	PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
	PostDTO updatePostById(PostDTO postDTO,Integer postId);
	void deletePostById(Integer postId);
	//PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
	List<PostDTO> getAllPosts();
	PostDTO getPostById(Integer postId);
	List<PostDTO> getPostByCategory(Integer categoryId);
	List<PostDTO> getPostByUser(Integer userId);
	List<PostDTO> searchposts(String keyword);
}
