package com.teste.cadusuario.resources;

import com.teste.cadusuario.domain.Funcionario;
import com.teste.cadusuario.dtos.FuncionarioDTO;
import com.teste.cadusuario.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioResource {

    @Autowired
    private FuncionarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Integer id) {
        Funcionario funcionario = service.findById(id);
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);

        return ResponseEntity.ok().body(funcionarioDTO);
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> findAll(){
        List<Funcionario> listaFuncionarios = service.findAll();
        List<FuncionarioDTO> listaFuncionariosDTO = listaFuncionarios.stream().map(func -> new FuncionarioDTO(func)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaFuncionariosDTO);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody FuncionarioDTO novoFuncionarioDTO) {
        Funcionario funcionario = service.save(novoFuncionarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
