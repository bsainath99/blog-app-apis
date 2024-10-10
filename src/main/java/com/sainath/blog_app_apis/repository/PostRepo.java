package com.sainath.blog_app_apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sainath.blog_app_apis.entities.Category;
import com.sainath.blog_app_apis.entities.Post;
import com.sainath.blog_app_apis.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
