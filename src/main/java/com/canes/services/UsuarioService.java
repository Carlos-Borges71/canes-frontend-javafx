package com.canes.services;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.util.List;
import com.canes.config.ApiConstantes;
import com.canes.infra.http.BaseService;
import com.canes.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsuarioService extends BaseService {

    public UsuarioService(HttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    // Salvar usuario
    public Long salvarUsuario(Usuario usuario) throws IOException, InterruptedException, ConnectException {

        // Usando método generico
        Usuario usuarioSalvo = post(ApiConstantes.USUARIOS, usuario, Usuario.class);

        return usuarioSalvo.getId();
    }

    public List<Usuario> buscarTodos() throws IOException, InterruptedException, ConnectException {

        return getList(ApiConstantes.USUARIOS, new TypeReference<List<Usuario>>() {
        });
    }

    // atualizar todo usuario
    public Usuario atualizarUsuario(Usuario usuario)
            throws IOException, InterruptedException, ConnectException {

        String url = ApiConstantes.USUARIOS + "/" + usuario.getId();

        return put(url, usuario, Usuario.class);
    }

    // Atualizar parte dos usuario
    public Usuario atualizarParcial(Long id, Usuario usuarioParcial)
            throws IOException, InterruptedException {

        String url = ApiConstantes.USUARIOS + "/" + id;

        return patch(url, usuarioParcial, Usuario.class);
    }

    // Deletar Usuário
    public void deletar(Long id)
            throws IOException, InterruptedException, ConnectException {

        delete(ApiConstantes.USUARIOS + "/" + id);
    }

}
