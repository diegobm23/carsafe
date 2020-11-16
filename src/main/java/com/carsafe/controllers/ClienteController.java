package com.carsafe.controllers;

import com.carsafe.service.ClienteService;
import com.carsafe.dto.ErroDTO;
import com.carsafe.entities.Cliente;
import com.carsafe.repositories.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    private final ClienteRepository repository;
    private final ClienteService service;

    public ClienteController(ClienteRepository repository, ClienteService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/cliente")
    ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) {
        String mensagensErro = service.validarCliente(cliente);

        if (mensagensErro.isEmpty()) {
            Cliente c = repository.save(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(c);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO(400, "Cliente", mensagensErro));
        }
    }

    @GetMapping("/cliente")
    public List<Cliente> buscarTodos(@RequestParam Optional<String> nome) {
        if (nome.isPresent()) {
            return repository.findByNome(nome);
        } else {
            return repository.findAll();
        }
    }

    @DeleteMapping("/cliente")
    void deletar(@RequestParam String id) {
        repository.deleteById(id);
    }
}