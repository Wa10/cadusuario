package com.teste.cadusuario.services;

import com.teste.cadusuario.domain.Funcionario;
import com.teste.cadusuario.dtos.FuncionarioDTO;
import com.teste.cadusuario.exceptions.ConstraintViolationException;
import com.teste.cadusuario.exceptions.FuncionarioNotFoundException;
import com.teste.cadusuario.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario findById(Integer id){
        Optional<Funcionario> funcionario = repository.findById(id);

        return funcionario.orElseThrow(() -> new FuncionarioNotFoundException("Não foi encontrado funcionário com id: " + id));
    }

    public Funcionario save(FuncionarioDTO funcionarioDTO){
        validaPorEmailENis(funcionarioDTO);
        Funcionario novoFuncionario = new Funcionario(funcionarioDTO);

        return repository.save(novoFuncionario);
    }

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    private void validaPorEmailENis(FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> funcionario = repository.findByEmail(funcionarioDTO.getEmail());
        if(funcionario.isPresent()){
            throw new ConstraintViolationException("Email já cadastrado");
        }

        funcionario = repository.findByNis(funcionarioDTO.getNis());
        if(funcionario.isPresent()){
            throw new ConstraintViolationException("NIS duplicado");
        }
    }
}
