package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import pojo.LaureatesList;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private final static String LAUREATES_JSON_URL = "http://api.nobelprize.org/v1/laureate.json";

    public static LaureatesList fetchLaureates() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(LAUREATES_JSON_URL))
                .build();

        String body = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString())
                .body();

        ObjectMapper mapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();

        return mapper.readValue(body, LaureatesList.class);
    }
}
