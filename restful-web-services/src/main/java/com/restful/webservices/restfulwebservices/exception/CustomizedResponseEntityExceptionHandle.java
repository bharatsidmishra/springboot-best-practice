package com.restful.webservices.restfulwebservices.exception;

import com.restful.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetils> handleAllException(Exception ex, WebRequest request) throws Exception {
        ErrorDetils errorDetils = new ErrorDetils(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<ErrorDetils>(errorDetils, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetils> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetils errorDetils = new ErrorDetils(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<ErrorDetils>(errorDetils, HttpStatus.NOT_FOUND);

    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetils errorDetils = new ErrorDetils(LocalDateTime.now(),"total Errors : "+ ex.getErrorCount() +" First Error : " +ex.getFieldError().getDefaultMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetils, HttpStatus.BAD_REQUEST);
    }
}
