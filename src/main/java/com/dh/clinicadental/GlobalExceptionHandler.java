package com.dh.clinicadental;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import org.apache.log4j.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> allError(Exception ex, WebRequest req){
        logger.error(ex.getMessage());
        return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarErrorBadRequest(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
