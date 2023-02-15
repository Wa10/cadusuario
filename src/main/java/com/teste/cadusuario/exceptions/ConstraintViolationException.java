package com.teste.cadusuario.exceptions;

public class ConstraintViolationException extends RuntimeException{

    public ConstraintViolationException(String message, Throwable cause){
        super(message, cause);
    }

    public ConstraintViolationException(String message){
        super(message);
    }
}
