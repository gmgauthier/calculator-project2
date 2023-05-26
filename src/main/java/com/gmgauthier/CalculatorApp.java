package com.gmgauthier;

import com.gmgauthier.client.CalculatorClient;

import java.io.IOException;
import java.net.URISyntaxException;

public class CalculatorApp {

    public static void main(String[] args)
            throws URISyntaxException, IOException, InterruptedException {
        CalculatorClient cc = new CalculatorClient();

        System.out.println(cc.getSum(10,20));
        System.out.println(cc.getProduct(123, 444));
        System.out.println(cc.getDifference(846, 233));
        System.out.println(cc.getQuotient(8, 2));
    }

}
