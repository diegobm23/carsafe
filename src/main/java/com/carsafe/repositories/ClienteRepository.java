package com.carsafe.repositories;

import java.util.List;
import java.util.Optional;

import com.carsafe.entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    List<Cliente> findByNome(@Param("nome") Optional<String> nome);
    List<Cliente> findByCpf(@Param("cpf") String cpf);
}
