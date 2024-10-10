package com.sainath.blog_app_apis.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sainath.blog_app_apis.entities.Category;
import com.sainath.blog_app_apis.entities.Post;
import com.sainath.blog_app_apis.entities.User;
import com.sainath.blog_app_apis.exception.ResourceNotFoundException;
import com.sainath.blog_app_apis.payloads.PostDTO;
import com.sainath.blog_app_apis.payloads.PostResponse;
import com.sainath.blog_app_apis.repository.CategoryRepo;
import com.sainath.blog_app_apis.repository.PostRepo;
import com.sainath.blog_app_apis.repository.UserRepo;
import com.sainath.blog_app_apis.service.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Override
	public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","userId", userId));
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","category", categoryId));		
		Post post=mapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=postRepo.save(post);
		return mapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePostById(PostDTO postDTO, Integer postId) {
		Post post=	postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","postId", postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		postRepo.save(post);
		return mapper.map(post,PostDTO.class);
	}

	@Override
	public void deletePostById(Integer postId) {
	Post post=	postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","postId", postId));
	postRepo.delete(post);

	}

//	@Override
//	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
//		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
//		Page<Post> pagePost=postRepo.findAll(p);
//		List<Post> allPosts=pagePost.getContent();
//		List<PostDTO> postDtos=allPosts.stream().map((post)->mapper.map(post, PostDTO.class)).collect(Collectors.toList());
//		PostResponse postResponse=new PostResponse();
//		postResponse.setContent(postDtos);
//		postResponse.setPageNumber(pagePost.getNumber());
//		postResponse.setPageSize(pagePost.getSize());
//		postResponse.setTotalElements(pagePost.getTotalElements());
//		postResponse.setTotalPages(pagePost.getTotalPages());
//		postResponse.setLastPage(pagePost.isLast());
//		return postResponse;
//	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","postId", postId));
		PostDTO postDTO= mapper.map(post, PostDTO.class);
		return postDTO;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","category", categoryId));
		List<Post> posts= postRepo.findByCategory(cat);
		List<PostDTO> postDTOs= posts.stream().map((post)->mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","userId", userId));
		List<Post> posts=postRepo.findByUser(user);
		List<PostDTO> postDTOs= posts.stream().map((post)->mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> searchposts(String keyword) {
		List<Post> posts= postRepo.findByTitleContaining(keyword);
		List<PostDTO> postDTOs= posts.stream().map((post)->mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> getAllPosts() {
		List<Post> posts= postRepo.findAll();
		List<PostDTO> postDTOs= posts.stream().map((post)->mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

}
