package com.publisap.stockStats.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.http2.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ConnectionException.class)
	public String handleConnectionException(HttpServletRequest request, Exception ex){
		log.info("ConnectionException Occured:: URL="+request.getRequestURL());
		return "connection_error";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public String handleIOException(){
		log.error("IOException handler executed");
		return "internal-server-error";
	}
}