package com.teste.cadusuario.model;

import com.teste.cadusuario.dtos.FuncionarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity(name = "TB_FUNCIONARIO")
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Integer id;

    @Getter @Setter
    @NotNull(message = "nome obrigatório")
    private String nome;

    @Getter @Setter private String sobrenome;

    @Column(unique = true)
    @NotNull(message = "email obrigatório")
    @Getter @Setter private String email;

    @Column(unique = true)
    @NotNull
    @Getter @Setter private Integer nis;

    public Funcionario(FuncionarioDTO funcionarioDTO){
        this.id = funcionarioDTO.getId();
        this.nome = funcionarioDTO.getNome();
        this.sobrenome = funcionarioDTO.getSobrenome();
        this.email = funcionarioDTO.getEmail();
        this.nis = funcionarioDTO.getNis();
    }
}
