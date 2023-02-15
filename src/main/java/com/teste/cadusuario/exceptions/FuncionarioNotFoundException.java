package com.teste.cadusuario.exceptions;

public class FuncionarioNotFoundException extends RuntimeException {

    public FuncionarioNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public FuncionarioNotFoundException(String message){
        super(message);
    }
}
