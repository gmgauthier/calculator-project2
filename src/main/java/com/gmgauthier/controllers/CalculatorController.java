package com.gmgauthier.controllers;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@RestController
public class CalculatorController {

    @RequestMapping(value = "/sum",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String sums(@RequestBody HashMap<String, List<Integer>> payload) {
        List<Integer> values = validateValues(payload);
        Integer sum = values.get(0) + values.get(1);

        HashMap<String, Object> results = new HashMap<>();
        results.put("sum", sum);
        JSONObject jsonResults = new JSONObject(results);

        return jsonResults.toString();
    }

    @RequestMapping(value = "/difference",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String differences(@RequestBody HashMap<String, List<Integer>> payload) {
        List<Integer> values = validateValues(payload);
        Integer remainder = values.get(0) - values.get(1);

        HashMap<String, Object> results = new HashMap<>();
        results.put("difference", remainder);
        JSONObject jsonResults = new JSONObject(results);

        return jsonResults.toString();
    }

    @RequestMapping(value = "/product",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String products(@RequestBody HashMap<String, List<Integer>> payload) {
        List<Integer> values = validateValues(payload);
        Integer product = values.get(0) * values.get(1);

        HashMap<String, Object> results = new HashMap<>();
        results.put("product", product);
        JSONObject jsonResults = new JSONObject(results);

        return jsonResults.toString();
    }

    @RequestMapping(value = "/quotient",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String quotients(@RequestBody HashMap<String, List<Integer>> payload) {
        List<Integer> values = validateValues(payload);
        Float quotient = values.get(0).floatValue() / values.get(1).floatValue();

        HashMap<String, Object> results = new HashMap<>();
        results.put("quotient", quotient);
        JSONObject jsonResults = new JSONObject(results);

        return jsonResults.toString();
    }


    private List<Integer> validateValues(HashMap<String, List<Integer>> payload) {
        List<Integer> values;

        // Make sure the payload is extractable
        try {
            values = payload.get("values");
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "operand values could not be extracted from payload");
        }

        if (values.size() != 2) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "incorrect number of operands");
        }

        return values;
    }
}
