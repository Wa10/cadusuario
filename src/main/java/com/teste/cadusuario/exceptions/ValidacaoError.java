package com.teste.cadusuario.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ValidacaoError extends ExceptionError{

    private List<CampoMessage> errors = new ArrayList<>();

    public ValidacaoError(Long timestamp, Integer status, String message, String error, String path) {
        super(timestamp, status, message, error, path);
    }

    public void adicionarErrors(String fieldName, String message){
        this.errors.add(new CampoMessage(fieldName, message));
    }
}
