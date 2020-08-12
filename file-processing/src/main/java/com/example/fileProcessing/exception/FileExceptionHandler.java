package com.example.fileProcessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileExceptionHandler extends Exception{
	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException ex){
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Max size exceed for upload file");
	}
}
