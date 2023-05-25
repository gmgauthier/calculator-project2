package com.gmgauthier;

import com.gmgauthier.client.requests.CalculatorClientTests;
import com.gmgauthier.server.requests.CalculatorControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorClientTests.class,
        CalculatorControllerTest.class
})
@SpringBootTest
public class Alltests {
}
