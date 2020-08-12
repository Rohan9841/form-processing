package com.example.fileProcessing.serviceImpls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.fileProcessing.dto.FileDbDto;
import com.example.fileProcessing.models.FileDB;
import com.example.fileProcessing.repositories.FileDBrepository;
import com.example.fileProcessing.services.FileDBservice;

@Service("fileService")
public class FileDBserviceImpl implements FileDBservice {

	@Autowired
	FileDBrepository fileDBrepository;
	
	@Override
	public FileDbDto save(MultipartFile file) throws Exception{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDb = new FileDB(fileName, file.getContentType(), file.getBytes());
		fileDb = fileDBrepository.save(fileDb);
		
		String fileDownloadUri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/files")
				.path("/"+String.valueOf(fileDb.getId()))
				.toUriString();
		
		return new FileDbDto(fileDb.getName(),fileDownloadUri,fileDb.getType(),fileDb.getData().length);
	}

//	@Override
//	public FileDbDto getFile(Long id) {
//		Optional<FileDB> optFileDb = fileDBrepository.findById(id);
//		FileDB fileDb = optFileDb.isPresent()?optFileDb.get():null;
//		return(fileDb!=null?new FileDbDto(fileDb.getName(),fileDb.getType(), fileDb.getData().length,fileDb.getData()):null);
//	}
	
	@Override
	public FileDbDto getFile(Long id) {
		Optional<FileDB> optFileDb = fileDBrepository.findById(id);
		FileDB fileDb = optFileDb.isPresent()?optFileDb.get():null;
		return(fileDb!=null?new FileDbDto(fileDb.getName(),fileDb.getType(), fileDb.getData().length,fileDb.getData()):null);
	}

	@Override
	public List<FileDbDto> getAllFiles() {
		List<FileDbDto> files = fileDBrepository
				.findAll()
				.stream()
				.map(dbFile -> {
					String fileDownloadUri = ServletUriComponentsBuilder
							.fromCurrentContextPath()
							.path("/files")
							.path("/"+String.valueOf(dbFile.getId()))
							.toUriString();
					
					return new FileDbDto(
							dbFile.getId(),
							dbFile.getName(),
							fileDownloadUri,
							dbFile.getType(),
							dbFile.getData().length
							);
				}).collect(Collectors.toList());
		return files;
	}

	@Override
	public FileDbDto findByName(String name) {
		Optional<FileDB> optFileDb = fileDBrepository.findByName(name);
		FileDB fileDb = optFileDb.isPresent()?optFileDb.get():null;
		return(fileDb!=null?new FileDbDto(fileDb.getName(),fileDb.getType(), fileDb.getData().length,fileDb.getData()):null);
		
	}
	
	

}
