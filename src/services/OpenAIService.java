package services;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenAIService {

    private String apiUrl;
    private String apiKey;
    
    public OpenAIService() {
        // Carregar config.properties
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            this.apiUrl = props.getProperty("openai.api.url");
            this.apiKey = props.getProperty("openai.api.key");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
    }
    
    // Método para enviar uma solicitação para a API OpenAI
    public String sendMessage(String message) {

        try {
            var client = HttpClient.newHttpClient();

            var jsonRequest = new JSONObject();
            jsonRequest.put("model", "gpt-4o");

            jsonRequest.put("messages", new JSONArray()
                .put(new JSONObject()
                    .put("role", "system")
                    .put("content", "Você é um assistente que só deve responder perguntas relacionadas a atendimento de suporte de computadores. Gere respostas cordiais, tratando o cliente pelo seu nome, e não responda perguntas que não sejam relacionadas a suporte de computadores."))
                .put(new JSONObject()
                    .put("role", "user")
                    .put("content", message))
            );

            // configurando a chamada para a OpenAI
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest.toString()))
                    .build();

            // fazendo a requisição
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // capturando a resposta
            var jsonResponse = new JSONObject(response.body());

            if (jsonResponse.has("choices")) {
                var content = jsonResponse
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");

                return content;
            } else {
                System.out.println("ERRO NA RESPOSTA: " + jsonResponse.toString(2));
            }

        } catch (Exception e) {
            System.out.println("\nERRO: " + e.getMessage());
        }

        return null;
    }
}
