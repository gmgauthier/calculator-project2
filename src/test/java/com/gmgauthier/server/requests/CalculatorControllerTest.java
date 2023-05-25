package com.gmgauthier.server.requests;

import com.hackerrank.test.utility.OrderedTestRunner;
import com.hackerrank.test.utility.TestWatchman;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import static org.junit.Assert.assertEquals;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public TestWatcher watchman = TestWatchman.watchman;

    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void setUpClass() {
        TestWatchman.watchman.registerClass(CalculatorControllerTest.class);
    }

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(CalculatorControllerTest.class);
    }

    @Test
    public void testValidSums() throws Exception {
        String response = mockMvc.perform(
                MockMvcRequestBuilders.post("/sum")
                        .content("{\"values\":[2,2]}")
                        .contentType("application/json")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("{\"sum\":4}", response);
    }

    @Test(expected = NestedServletException.class)
    public void testTooManyOperands() throws Exception {
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sum")
                                .content("{\"values\":[2,2,3]}")
                                .contentType("application/json")
                )
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("", response);
    }

    @Test
    public void testNoContentBody() throws Exception {
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sum")
                )
                .andExpect(MockMvcResultMatchers.status().is(415)) //mocker does our status check
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("", response);
    }

    @Test
    public void testStringOperands() throws Exception {
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sum")
                                .content("{\"values\":[\"apple\",\"orange\",\"banana\"]}")
                                .contentType("application/json")
                )
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("", response);
    }

    @Test
    public void testValidDifferences() throws Exception {
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/difference")
                                .content("{\"values\":[4,2]}")
                                .contentType("application/json")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("{\"difference\":2}", response);
    }

    @Test
    public void testValidProducts() throws Exception {
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/product")
                                .content("{\"values\":[4,2]}")
                                .contentType("application/json")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("{\"product\":8}", response);
    }

    @Test
    public void testValidQuotients() throws Exception {
        String response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/quotient")
                                .content("{\"values\":[4,2]}")
                                .contentType("application/json")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("{\"quotient\":2}", response);
    }
}
