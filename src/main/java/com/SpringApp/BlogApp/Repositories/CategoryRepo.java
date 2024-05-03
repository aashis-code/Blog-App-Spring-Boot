package com.SpringApp.BlogApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringApp.BlogApp.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
