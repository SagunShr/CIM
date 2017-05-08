package com.cim.adcampaign.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cim.adcampaign.model.ErrorResponseDTO;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<ErrorResponseDTO>> exceptionHandler(BindException ex)
	{
		List<ErrorResponseDTO> resonseDtos = new ArrayList<ErrorResponseDTO>();
		
		for(ObjectError error : ex.getBindingResult().getGlobalErrors()){
			ErrorResponseDTO dto = new ErrorResponseDTO();
			dto.setErrorCode(error.getCode());
			dto.setErrorMsg(error.getDefaultMessage());
			dto.setResponseStatus(HttpStatus.BAD_REQUEST);
			
			resonseDtos.add(dto);
		}
		return new ResponseEntity<List<ErrorResponseDTO>>(resonseDtos, HttpStatus.OK);
	}
	
}
