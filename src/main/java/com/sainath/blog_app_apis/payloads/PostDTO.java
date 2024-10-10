package com.sainath.blog_app_apis.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	private Integer postId;
	private String title;
	private String content;
	private Date addedDate;
	private String imageName;
	private CategoryDTO category;
	private UserDTO user;
	private Set<CommentDTO> comments=new HashSet<>();
}
