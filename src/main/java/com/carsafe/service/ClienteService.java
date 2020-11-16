package com.carsafe.service;

import com.carsafe.entities.Cliente;
import com.carsafe.repositories.ClienteRepository;
import com.carsafe.utils.ValidarCPF;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public String validarCliente(Cliente cliente) {
        StringBuilder msg = new StringBuilder();

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            msg.append("Informe o nome do cliente.");
        }

        if (cliente.getCidade() == null || cliente.getCidade().isEmpty()) {
            msg.append("Informe a cidade do cliente.");
        }

        if (cliente.getUf() == null || cliente.getUf().isEmpty())  {
            msg.append("Informe a UF do cliente.");
        }

        String mensagemCPF = validarCPF(cliente.getCpf());

        if (mensagemCPF != null) {
            msg.append(mensagemCPF);
        }

        return msg.toString();
    }

    private String validarCPF(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf == null || cpf.isEmpty())  {
            return "Informe o CPF do cliente.";
        }

        if (!ValidarCPF.isCPF(cpf)) {
            return "CPF informado não é válido.";
        }

        if (repository.findByCpf(cpf).size() > 0) {
            return "Já existe este CPF cadastrado.";
        }

        return null;
    }
}
