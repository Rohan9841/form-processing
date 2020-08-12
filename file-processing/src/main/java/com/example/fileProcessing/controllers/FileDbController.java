package com.example.fileProcessing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileProcessing.dto.FileDbDto;
import com.example.fileProcessing.services.FileDBservice;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/files")
public class FileDbController {

	@Autowired
	FileDBservice fileDbService;
	
	@PostMapping("/upload")
	public FileDbDto uploadFile(@RequestParam("imageFile") MultipartFile file) {
		FileDbDto fileDbDto = null;
		try {
			fileDbDto = fileDbService.save(file);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return fileDbDto;
	}
	
	@GetMapping
	public List<FileDbDto> getAllFiles(){
		return fileDbService.getAllFiles();
	}
	
	@GetMapping("/{name}")
	public FileDbDto getFile(@PathVariable("name")String name) {
		return fileDbService.findByName(name);
	}
}
