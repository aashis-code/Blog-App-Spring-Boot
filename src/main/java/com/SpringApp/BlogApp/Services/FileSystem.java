package com.SpringApp.BlogApp.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileSystem {

	
	String uploadFile(String path, MultipartFile file) throws IOException;
	
	InputStream serveFile(String path, String fileName) throws FileNotFoundException;
}
