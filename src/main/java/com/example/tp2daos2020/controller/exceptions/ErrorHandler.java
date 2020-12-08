package com.example.tp2daos2020.controller.exceptions;

import com.example.tp2daos2020.exceptions.Excepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandler {

   @ExceptionHandler(Excepcion.class)
   public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request, Excepcion e) {
       ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
       return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
   }
}