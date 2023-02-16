package com.teste.cadusuario.dtos;

import com.teste.cadusuario.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO implements Serializable {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private Long nis;

    public FuncionarioDTO(Funcionario funcionario){
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.email = funcionario.getEmail();
        this.nis = funcionario.getNis();
    }
}
