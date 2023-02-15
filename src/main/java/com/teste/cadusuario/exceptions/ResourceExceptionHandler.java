package com.teste.cadusuario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.util.Iterator;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(FuncionarioNotFoundException.class)
    public ResponseEntity<ExceptionError> funcionarioNotFoundException(FuncionarioNotFoundException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Funcionario não encontrado", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionError> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request){
        ExceptionError error = new ExceptionError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Duplicidade de informações", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<ExceptionError> constraintViolationExceptionJavax(javax.validation.ConstraintViolationException ex, HttpServletRequest request){
        ValidacaoError error = new ValidacaoError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Erro na validação dos campos", ex.getMessage(), request.getRequestURI());

        ex.getConstraintViolations().forEach(
                constraintViolation -> error.adicionarErrors(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessageTemplate())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
