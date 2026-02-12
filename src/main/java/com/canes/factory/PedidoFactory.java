package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.PedidoService;

public class PedidoFactory {

    public static PedidoService getPedidoService() {
        return new PedidoService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
