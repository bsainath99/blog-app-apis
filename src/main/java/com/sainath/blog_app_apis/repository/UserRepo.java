package com.sainath.blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sainath.blog_app_apis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
