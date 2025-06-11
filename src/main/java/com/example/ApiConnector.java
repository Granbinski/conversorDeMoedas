package com.example;

import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ApiConnector {

    public ExchangeRates getRates(String baseCurrency) {
        String apiKey = loadApiKey();

        if (apiKey == null) {
            System.err.println("Chave da API não encontrada. Verifique o arquivo config.properties.");
            System.exit(1);
        }

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return new Gson().fromJson(response.body(), ExchangeRates.class);
            } else {
                System.out.println("Erro ao buscar dados da API: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro na conexão com a API: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private String loadApiKey() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            props.load(input);
            return props.getProperty("API_KEY");
        } catch (IOException ex) {
            return null;
        }
    }
}