package com.sainath.blog_app_apis.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sainath.blog_app_apis.payloads.CommentDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	@ManyToOne
	private Category category;
	@ManyToOne
	private User user;
	@OneToMany(mappedBy="post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Comment> comments=new HashSet<>();
}
