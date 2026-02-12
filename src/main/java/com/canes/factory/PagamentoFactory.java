package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.PagamentoService;
import com.canes.services.ProdutoService;

public class PagamentoFactory {

    public static PagamentoService getPagamentoService() {
        return new PagamentoService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
