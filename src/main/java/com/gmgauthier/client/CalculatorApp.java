package com.gmgauthier.client;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CalculatorApp {

    private static final String ROOT_URL = "http://localhost:8000/";
    static HttpClient httpClient;

    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException {
        httpClient = HttpClient.newHttpClient();

        System.out.println(getSum(10,20));
        System.out.println(getProduct(123, 444));
        System.out.println(getDifference(846, 233));
        System.out.println(getQuotient(999, 4));

    }

    public static Integer getSum(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(ROOT_URL + "sum");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        return (Integer) new JSONObject(makeRequest(addUrl, postJson).body()).get("sum");
    }

    public static Integer getDifference(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(ROOT_URL + "difference");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        return (Integer) new JSONObject(makeRequest(addUrl, postJson).body()).get("difference");
    }

    public static Integer getProduct(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(ROOT_URL + "product");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        return (Integer) new JSONObject(makeRequest(addUrl, postJson).body()).get("product");
    }

    public static BigDecimal getQuotient(Integer operanda, Integer operandb)
            throws URISyntaxException, IOException, InterruptedException {
        URI addUrl = new URI(ROOT_URL + "quotient");
        JSONObject postJson = new JSONObject().put("values", new Integer[] {operanda, operandb});
        return (BigDecimal) new JSONObject(makeRequest(addUrl, postJson).body()).get("quotient");
    }

    private static HttpResponse<String> makeRequest(URI uri, JSONObject jsonBody)
            throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Content-Type","application/json")
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(jsonBody)))
                .build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

}
