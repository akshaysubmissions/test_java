package com.oorja.oorjaTest.advice;


import com.oorja.oorjaTest.customException.EmptyInputException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
        return new  ResponseEntity<String>("Order ID should be greater than 0",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException){
        return new  ResponseEntity<String>("No Value in a DB Present",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        return new  ResponseEntity<String>("Invalid Value Provided. Please Check Again",HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handlehttpMethodNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        return new  ResponseEntity<String>("Please provide data with valid data type",HttpStatus.BAD_REQUEST);
    }


}
