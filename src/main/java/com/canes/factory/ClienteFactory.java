package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.ClienteService;

public class ClienteFactory {

    public static ClienteService getClienteService() {
        return new ClienteService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
