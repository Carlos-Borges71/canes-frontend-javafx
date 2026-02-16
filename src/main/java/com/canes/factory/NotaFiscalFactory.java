package com.canes.factory;

import com.canes.config.AppConfig;
import com.canes.services.NotaFiscalService;

public class NotaFiscalFactory {

    public static NotaFiscalService getNotaFiscal() {
        return new NotaFiscalService(AppConfig.getHttpClient(), AppConfig.getObjectMapper());
    }
}
