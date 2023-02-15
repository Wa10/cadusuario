package com.teste.cadusuario.repositories;

import com.teste.cadusuario.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByNis(Integer nis);
    Optional<Funcionario> findByEmail(String email);

}
