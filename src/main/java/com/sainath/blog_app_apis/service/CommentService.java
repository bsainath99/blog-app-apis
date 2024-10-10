package com.sainath.blog_app_apis.service;

import com.sainath.blog_app_apis.payloads.CommentDTO;

public interface CommentService {
	CommentDTO createComment(CommentDTO commentDTO,Integer postId);
	void deleteComment(Integer commentId);
}
