package com.example.fileProcessing.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.fileProcessing.dto.FileDbDto;

public interface FileDBservice {
	FileDbDto save(MultipartFile file) throws Exception;
	FileDbDto getFile(Long id);
	List<FileDbDto> getAllFiles();
	FileDbDto findByName(String name);
}
