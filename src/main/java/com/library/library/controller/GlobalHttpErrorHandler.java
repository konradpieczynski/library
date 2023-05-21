package com.library.library.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(CopyOfBookNotFoundException.class)
    public ResponseEntity<Object> handleCopyOfBookNotFoundException(CopyOfBookNotFoundException exception){
        return new ResponseEntity<>("CopyOfBook with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<Object> handleRentalNotFoundException(RentalNotFoundException exception){
        return new ResponseEntity<>("Rental with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TitleExistException.class)
    public ResponseEntity<Object> handleTitleExistException(TitleExistException exception){
        return new ResponseEntity<>("Title already exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleTitleNotFoundException(TitleNotFoundException exception){
        return new ResponseEntity<>("Title with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
