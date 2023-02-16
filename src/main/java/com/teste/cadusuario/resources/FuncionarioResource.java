package com.teste.cadusuario.resources;

import com.teste.cadusuario.model.Funcionario;
import com.teste.cadusuario.dtos.FuncionarioDTO;
import com.teste.cadusuario.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/funcionarios")
public class FuncionarioResource {

    @Autowired
    private FuncionarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Integer id) {
        Funcionario funcionario = service.findById(id);

        return ResponseEntity.ok().body(new FuncionarioDTO(funcionario));
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> findAll(){
        List<Funcionario> listaFuncionarios = service.findAll();
        List<FuncionarioDTO> listaFuncionariosDTO = listaFuncionarios.stream().map(func -> new FuncionarioDTO(func)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaFuncionariosDTO);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO novoFuncionarioDTO) {
        Funcionario funcionario = service.save(novoFuncionarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Integer id, @Valid @RequestBody FuncionarioDTO funcionarioDTO){
        Funcionario funcionario = service.update(id, funcionarioDTO);

        return ResponseEntity.ok().body(new FuncionarioDTO(funcionario));
    }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity<FuncionarioDTO> delete(@PathVariable Integer id){
            service.delete(id);

            return ResponseEntity.noContent().build();
        }
}
