package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.ProdutoService;

public class ProdutoFactory {

    public static ProdutoService getProdutoService() {
        return new ProdutoService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
