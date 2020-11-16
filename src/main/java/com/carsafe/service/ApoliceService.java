package com.carsafe.service;

import com.carsafe.entities.Apolice;
import com.carsafe.repositories.ApoliceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class ApoliceService {

    private final ApoliceRepository repository;

    public ApoliceService(ApoliceRepository repository) {
        this.repository = repository;
    }

    private Integer getRandomNumber() {
        Random random = new Random();
        Integer min = 10000;
        Integer max = 99999;

        return random.ints(min, max).findFirst().getAsInt();
    }

    public Integer gerarNumeroUnico() {
        Integer numero;

        do {
            numero = getRandomNumber();
        } while(repository.findByNumero(numero).size() > 0);

        return numero;
    }

    public String validarApolice(Apolice apolice) {
        StringBuilder msg = new StringBuilder();

        if (apolice.getNumero() == null) {
            msg.append("Erro ao gerar número da apólice.");
        }

        if (apolice.getPlaca() == null || apolice.getPlaca().isEmpty()) {
            msg.append("Informe a placa do veículo segurado.");
        }

        if (apolice.getValorSeguro() == null) {
            msg.append("Informe o valor do seguro");
        }

        String mensagemVigencia = validarVigencias(apolice.getInicioVigencia(), apolice.getFinalVigencia());

        if (mensagemVigencia != null) {
            msg.append(mensagemVigencia);
        }

        return msg.toString();
    }

    private String validarVigencias(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            return "Informe o início e o final da vigência.";
        }

        if (inicio.compareTo(fim) >= 0) {
            return "A data do início da vigência deve ser maior que o final da vigência.";
        }

        return null;
    }
}
