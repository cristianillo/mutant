package com.meli.mutant.api.config;

import com.meli.mutant.api.exception.MatrixValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AspectExpection {
    private static final Logger logger = LoggerFactory.getLogger(AspectExpection.class);

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Object> handleException (ArrayIndexOutOfBoundsException exception){
        logger.error("The rows or columns of the matrix are not same length =>> ", exception);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MatrixValidationException.class)
    public ResponseEntity<Object> handleException (MatrixValidationException exception){
        logger.error("The request is invalid, it has wrong number of rows or columns length =>> ", exception);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
