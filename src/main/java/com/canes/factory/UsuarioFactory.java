package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.UsuarioService;

public class UsuarioFactory {

    public static UsuarioService getUsuarioService() {
        return new UsuarioService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
