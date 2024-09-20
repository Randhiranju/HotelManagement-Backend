package com.randhir.HotelManagement.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	
	//Handling all exception together (problem : ambiguous error field)
	public ResponseEntity<ApiResponse> Handler(MethodArgumentNotValidException ex){
		ApiResponse res=new ApiResponse();
		res.setMessage("Some fields are Invalid");
		res.setStatus(HttpStatus.BAD_REQUEST);
		res.setSuccess(false);
		return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
	}*/
	
	
	//we want to send custom exception for all field
	// logic: we will create map of String(fieldName) String(error message)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> Handler(MethodArgumentNotValidException ex){
		Map<String,String> map=new HashMap<>();
		List<ObjectError> list=ex.getBindingResult().getAllErrors(); //return list of all error with default message
		for(ObjectError err:list) {
			String message=err.getDefaultMessage();
			String field= ((FieldError)err).getField(); //extract field from object error class
			map.put(field,message);
			
		}
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
}
