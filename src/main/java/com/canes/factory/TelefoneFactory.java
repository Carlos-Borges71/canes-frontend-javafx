package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.TelefoneService;

public class TelefoneFactory {

    public static TelefoneService getTelefoneService() {
        return new TelefoneService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
