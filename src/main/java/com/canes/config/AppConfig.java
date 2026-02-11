package com.canes.config;

import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppConfig {

    // Um único HttpClient para a aplicação inteira
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    // Um único ObjectMapper para conversão JSON
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static HttpClient getHttpClient() {
        return CLIENT;
    }

    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }
}
