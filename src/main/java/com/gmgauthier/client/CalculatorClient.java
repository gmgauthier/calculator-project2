package com.gmgauthier.client;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CalculatorClient {

    private String DEFAULT_ROOT_URL = "http://localhost:8000";
    static HttpClient httpClient;

    public CalculatorClient() {
        httpClient = HttpClient.newHttpClient();
    }
    
    public CalculatorClient(String serverUrl) {
        this.DEFAULT_ROOT_URL = serverUrl;
        httpClient = HttpClient.newHttpClient();
    }

    public Integer getSum(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(DEFAULT_ROOT_URL + "/sum");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        return (Integer) new JSONObject(makeRequest(addUrl, postJson).body()).get("sum");
    }

    public Integer getDifference(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(DEFAULT_ROOT_URL + "/difference");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        return (Integer) new JSONObject(makeRequest(addUrl, postJson).body()).get("difference");
    }

    public Integer getProduct(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(DEFAULT_ROOT_URL + "/product");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        return (Integer) new JSONObject(makeRequest(addUrl, postJson).body()).get("product");
    }

    public Double getQuotient(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(DEFAULT_ROOT_URL + "/quotient");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        JSONObject response = new JSONObject(makeRequest(addUrl, postJson).body());
        String val = response.get("quotient").toString();
        return Double.valueOf(val);
    }

    private HttpResponse<String> makeRequest(URI uri, JSONObject jsonBody)
            throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Content-Type","application/json")
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(jsonBody)))
                .build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
