package com.marcisz.patryk.demotesting;

import com.marcisz.patryk.demotesting.boardgame.api.TestingAppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<TestingAppException> handleServerErrorException(HttpServerErrorException e){
        return new ResponseEntity<>(new TestingAppException(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TestingAppException.class)
    protected ResponseEntity<TestingAppException> handleServerApplicationException(TestingAppException e){
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<TestingAppException> handleConstraintException(ConstraintViolationException e){
        log.info(e.getMessage());
        return new ResponseEntity<>(new TestingAppException("Walidacja parametrów wejściowych nie powiodła się", 404), HttpStatus.BAD_REQUEST);
    }


}
