package br.com.treinaweb.ediaristas.services;

import br.com.treinaweb.ediaristas.dtos.ViaCepResponse;
import br.com.treinaweb.ediaristas.exceptions.CepInvalidoException;
import br.com.treinaweb.ediaristas.exceptions.CepNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        var url = String.format("https://viacep.com.br/ws/%s/json/", cep);

        var clienteHttp = new RestTemplate();
        ResponseEntity<ViaCepResponse> response;

        try {
            response = clienteHttp.getForEntity(url, ViaCepResponse.class);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new CepInvalidoException("Cep inválido!");
        }

        if (response.getBody().getCep() == null) {

            throw new CepNaoEncontradoException("Cep não encontrado!");
        }

        return response.getBody();
    }
}
