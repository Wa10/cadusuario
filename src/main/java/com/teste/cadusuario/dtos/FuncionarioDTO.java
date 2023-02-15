package com.teste.cadusuario.dtos;

import com.teste.cadusuario.domain.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO implements Serializable {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private Integer nis;

    public FuncionarioDTO(Funcionario funcionario){
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.email = funcionario.getEmail();
        this.nis = funcionario.getNis();
    }
}
