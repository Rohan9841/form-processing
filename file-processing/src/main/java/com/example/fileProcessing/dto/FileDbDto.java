package com.example.fileProcessing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDbDto {
	Long id;
	String name;
	String uri;
	String type;
	int size;
	byte[] data;
	
	public FileDbDto(String name, String uri, String type, int size) {
		super();
		this.name = name;
		this.uri = uri;
		this.type = type;
		this.size = size;
	}

	public FileDbDto(Long id, String name, String uri, String type, int size) {
		super();
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.type = type;
		this.size = size;
	}

	public FileDbDto(String name, String type, int size, byte[] data) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
		this.data = data;
	}
	
	public FileDbDto(byte[] data) {
		this.data = data;
	}
	
	
	
}
