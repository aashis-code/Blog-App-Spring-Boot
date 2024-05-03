package com.SpringApp.BlogApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringApp.BlogApp.Entity.Category;
import com.SpringApp.BlogApp.Entity.Post;
import com.SpringApp.BlogApp.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	List<Post> findByCategory(Category category);
	
	List<Post> findByUser(User user);
	
	List<Post> findByPostTitleContaining(String title);
	
	
}
