package com.teste.cadusuario.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "TB_FUNCIONARIO")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String Sobrenome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private Integer nis;
}
