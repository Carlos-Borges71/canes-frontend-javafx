package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.EnderecoService;

public class EnderecoFactory {

    public static EnderecoService getEnderecoService() {
        return new EnderecoService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
