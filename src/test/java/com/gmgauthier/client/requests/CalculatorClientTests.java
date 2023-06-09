package com.gmgauthier.client.requests;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.gmgauthier.client.CalculatorClient;
import com.hackerrank.test.utility.OrderedTestRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(OrderedTestRunner.class)
public class CalculatorClientTests {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8000);
    //public WireMockServer wms = new WireMockServer(9999);

    @Before
    public void reset() {
        WireMock.reset();
    }

    @Test
    public void testAddition() throws URISyntaxException, IOException, InterruptedException {
        stubFor(
                post(urlEqualTo("/sum"))
                        .withHeader("Content-Type", equalTo("application/json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"sum\": 4}")));
        CalculatorClient calc = new CalculatorClient();
        Integer resp = calc.getSum(2,2);//two plus two
        assertEquals(4, resp.intValue());
    }

    @Test
    public void testSubtraction() throws URISyntaxException, IOException, InterruptedException {
        stubFor(
                post(urlEqualTo("/difference"))
                        .withHeader("Content-Type", equalTo("application/json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"difference\": 4}")));
        CalculatorClient calc = new CalculatorClient();
        Integer resp = calc.getDifference(6,2); //six minus two
        assertEquals(4, resp.intValue());
    }

    @Test
    public void testMultiplication() throws URISyntaxException, IOException, InterruptedException {
        stubFor(
                post(urlEqualTo("/product"))
                        .withHeader("Content-Type", equalTo("application/json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"product\": 4}")));
        CalculatorClient calc = new CalculatorClient();
        Integer resp = calc.getProduct(2,2);//two times two
        assertEquals(4, resp.intValue());
    }

    @Test
    public void testWholeNumberDivision() throws URISyntaxException, IOException, InterruptedException {
        stubFor(
                post(urlEqualTo("/quotient"))
                        .withHeader("Content-Type", equalTo("application/json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"quotient\": 4}")));
        CalculatorClient calc = new CalculatorClient();
        Double resp = calc.getQuotient(8,2);//eight divided by two
        assertEquals(Double.valueOf(4), resp);
    }

    @Test
    public void testDecimalDivision() throws URISyntaxException, IOException, InterruptedException {
        stubFor(
                post(urlEqualTo("/quotient"))
                        .withHeader("Content-Type", equalTo("application/json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"quotient\": 3.8}")));
        CalculatorClient calc = new CalculatorClient();
        Double resp = calc.getQuotient(19,5);
        assertEquals(Double.valueOf(3.8), resp);
    }
}
