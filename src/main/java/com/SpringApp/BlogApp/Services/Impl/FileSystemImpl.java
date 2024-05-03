package com.SpringApp.BlogApp.Services.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SpringApp.BlogApp.Services.FileSystem;

@Service
public class FileSystemImpl implements FileSystem{

	
	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		
	
		//Get file Name
		String originalFilename = file.getOriginalFilename();
		
		//Add path to make full path
		String randomId = UUID.randomUUID().toString();
		String pathName = randomId.concat(originalFilename.substring( originalFilename.lastIndexOf(".")));
		String fullPath = path  + pathName;
		
		//create folder to store if not created
		File file2 = new File(path);
		
		if(!file2.exists()) {
			file2.mkdir();
		}
		
		//file copy on the path
		
		Files.copy(file.getInputStream(),Paths.get(fullPath));
		
		return fullPath;
	}

	
	@Override
	public InputStream serveFile(String path, String fileName) throws FileNotFoundException {
		String fullPath = path+ File.separator + fileName;
		InputStream iStream = new FileInputStream(fullPath);
		return iStream;
	}
}
