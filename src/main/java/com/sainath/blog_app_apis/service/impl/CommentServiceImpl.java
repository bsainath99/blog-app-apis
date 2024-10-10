package com.sainath.blog_app_apis.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sainath.blog_app_apis.entities.Comment;
import com.sainath.blog_app_apis.entities.Post;
import com.sainath.blog_app_apis.exception.ResourceNotFoundException;
import com.sainath.blog_app_apis.payloads.CommentDTO;
import com.sainath.blog_app_apis.repository.CommentRepo;
import com.sainath.blog_app_apis.repository.PostRepo;
import com.sainath.blog_app_apis.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ","postId", postId));
		Comment comment=	mapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment savedComment=	commentRepo.save(comment);
		CommentDTO commentDTO2= mapper.map(savedComment, CommentDTO.class);
		return commentDTO2;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment ","commentId", commentId));
		commentRepo.delete(comment);

	}

}
