package com.SpringApp.BlogApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringApp.BlogApp.Entity.Comment;
import com.SpringApp.BlogApp.Entity.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
	List<Comment> findByPost(Post post);

}
