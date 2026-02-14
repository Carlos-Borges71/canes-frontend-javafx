package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.ClienteService;
import com.canes.services.PedidoProdutoService;

public class PedidoProdutoFactory {

    public static PedidoProdutoService getPedidoProdutoService() {
        return new PedidoProdutoService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
