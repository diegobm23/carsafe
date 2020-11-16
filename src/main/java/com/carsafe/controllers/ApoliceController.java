package com.carsafe.controllers;

import com.carsafe.dto.ApoliceStatusDTO;
import com.carsafe.dto.ErroDTO;
import com.carsafe.service.ApoliceService;
import com.carsafe.entities.Apolice;
import com.carsafe.repositories.ApoliceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApoliceController {

    private final ApoliceRepository repository;
    private final ApoliceService service;

    public ApoliceController(ApoliceRepository repository, ApoliceService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/apolice")
    ResponseEntity<?> criarApolice(@RequestBody Apolice apolice) {
        apolice.setNumero(service.gerarNumeroUnico());
        String mensagensErro = service.validarApolice(apolice);

        if (mensagensErro.isEmpty()) {
            Apolice c = repository.save(apolice);
            return ResponseEntity.status(HttpStatus.OK).body(c);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO(400, "Apolice", mensagensErro));
        }
    }

    @GetMapping("/apolice")
    public List<Apolice> buscarTodas(@RequestParam Optional<Integer> numero) {
        if (numero.isPresent()) {
            return repository.findByNumero(numero);
        } else {
            return repository.findAll();
        }
    }

    @GetMapping("/apolice/consultar")
    public List<ApoliceStatusDTO> consultarApolice(@RequestParam Integer numero) {
        return service.consultarApolices(numero);
    }

    @DeleteMapping("/apolice")
    void deletar(@RequestParam String id) {
        repository.deleteById(id);
    }
}
