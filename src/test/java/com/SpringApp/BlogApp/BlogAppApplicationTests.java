package com.SpringApp.BlogApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SpringApp.BlogApp.Repositories.UserRepo;

@SpringBootTest
class BlogAppApplicationTests {
 
	@Autowired
	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
		String name = this.userRepo.getClass().getName();
		Package package1 = this.userRepo.getClass().getPackage();
//		System.out.println(name);
//		System.out.println(package1);
	}

}
