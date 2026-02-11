package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.FornecedorService;

public class FornecedorFactory {

    public static FornecedorService getFornecedorService() {
        return new FornecedorService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
