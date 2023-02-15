package com.teste.cadusuario.services;

import com.teste.cadusuario.model.Funcionario;
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
    private FuncionarioRepository funcionarioRepository;

    public Funcionario findById(Integer id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        return funcionario.orElseThrow(() -> new FuncionarioNotFoundException("Não foi encontrado funcionário com id: " + id));
    }

    public Funcionario save(FuncionarioDTO funcionarioDTO){
        validaPorEmailENis(funcionarioDTO);
        Funcionario novoFuncionario = new Funcionario(funcionarioDTO);

        return funcionarioRepository.save(novoFuncionario);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario update(Integer id, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionarioExistente = findById(id);
        funcionarioDTO.setId(id);
        validaPorEmailENis(funcionarioDTO);
        funcionarioExistente = new Funcionario(funcionarioDTO);

        return funcionarioRepository.save(funcionarioExistente);
    }

    public void delete(Integer id) {
        funcionarioRepository.delete(findById(id));
    }

    private void validaPorEmailENis(FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(funcionarioDTO.getEmail());
        if(funcionario.isPresent() && funcionarioDTO.getId() != funcionario.get().getId()){
            throw new ConstraintViolationException("Email já cadastrado");
        }

        funcionario = funcionarioRepository.findByNis(funcionarioDTO.getNis());
        if(funcionario.isPresent() && funcionarioDTO.getId() != funcionario.get().getId()){
            throw new ConstraintViolationException("NIS duplicado");
        }
    }
}
