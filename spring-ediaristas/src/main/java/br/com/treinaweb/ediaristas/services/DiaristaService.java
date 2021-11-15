package br.com.treinaweb.ediaristas.services;

import br.com.treinaweb.ediaristas.dtos.DiaristasPagedResponse;
import br.com.treinaweb.ediaristas.models.Diarista;
import br.com.treinaweb.ediaristas.repositories.DiaristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DiaristaService {

    @Autowired
    private DiaristaRepository diaristaRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private FileService fileService;

    public List<Diarista> listar() {
        return diaristaRepository.findAll();
    }

    public Diarista buscarPorId(Long id) {
        return diaristaRepository.getById(id);
    }

    public DiaristasPagedResponse buscarDiaristasPorCep(String cep) {
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();

        var pageable = PageRequest.of(0, 6);
        var diaristas = diaristaRepository.findByCodigoIbge(codigoIbge, pageable);

        var excedenteDiaristas = diaristas.getTotalElements() > 6 ? diaristas.getTotalElements() - 6 : 0;

        return new DiaristasPagedResponse(diaristas.getContent(), excedenteDiaristas);
    }

    public void cadastrar(MultipartFile imagem, Diarista diarista) throws IOException {
        diarista.setFoto(fileService.salvar(imagem));

        defineCep(diarista);

        diaristaRepository.save(diarista);
    }

    public void editar(MultipartFile imagem, Long id, Diarista diarista) throws IOException {
        var diaristaAtual = buscarPorId(id);
        if (imagem.isEmpty()) {
            diarista.setFoto(diaristaAtual.getFoto());
        } else {
            diarista.setFoto(fileService.salvar(imagem));
        }

        defineCep(diarista);

        diaristaRepository.save(diarista);
    }

    public void excluir(Long id) {
        diaristaRepository.deleteById(id);
    }

    private void defineCep(Diarista diarista) {
        var cep = diarista.getCep();
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();
        diarista.setCodigoIbge(codigoIbge);
    }
}
