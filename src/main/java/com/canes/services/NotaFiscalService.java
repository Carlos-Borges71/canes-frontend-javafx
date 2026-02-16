package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;

import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Fornecedor;
import com.canes.model.dpo.NotaFiscalDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotaFiscalService extends BaseService {

    public NotaFiscalService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public Long salvarNotaFiscal(Integer notafiscal, String data, Long fornecedorId) throws Exception {

        // Monta objeto Nota (Java, não JSON manual)
        NotaFiscalDTO nota = new NotaFiscalDTO();
        nota.setNotaFiscal(notafiscal);
        nota.setData(data);

        // Só adiciona se não for nulo

        if (fornecedorId != null) {
            nota.setFornecedor(new Fornecedor(fornecedorId));
        }

        // converte o objeto em JSON
        String json = mapper.writeValueAsString(nota);

        // exibe no console
        System.out.println("JSON enviado:");
        System.out.println(json);

        // Usa o POST genérico do BaseService
        NotaFiscalDTO notaFiscalSalva = post(ApiConstantes.NOTASFISCAIS, nota, NotaFiscalDTO.class);

        return notaFiscalSalva.getId();
    }

    public List<NotaFiscalDTO> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.NOTASFISCAIS, new TypeReference<List<NotaFiscalDTO>>() {
        });
    }

    // atualizar todo nota
    public NotaFiscalDTO atualizarUsuario(NotaFiscalDTO nota)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.NOTASFISCAIS + "/" + nota.getId();

        return put(url, nota, NotaFiscalDTO.class);
    }

    // Atualizar parte dos nota
    public NotaFiscalDTO atualizarParcial(Long id, NotaFiscalDTO notaParcial)
            throws IOException, InterruptedException {

        String url = ApiConstantes.NOTASFISCAIS + "/" + id;

        return patch(url, notaParcial, NotaFiscalDTO.class);
    }

    // Deletar nota
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.NOTASFISCAIS + "/" + id);
    }

}
