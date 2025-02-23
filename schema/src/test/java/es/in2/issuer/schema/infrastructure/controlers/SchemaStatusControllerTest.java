package es.in2.issuer.schema.infrastructure.controlers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SchemaStatusControllerTest.class)
@ContextConfiguration(classes = SchemaStatusControllerTest.SchemaTestConfig.class)
class SchemaStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetStatus() throws Exception {
        mockMvc.perform(get("/schema/v1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Schema is running"));
    }

    @Configuration
    @ComponentScan(basePackages = "es.in2.issuer.schema.infrastructure.controllers")
    static class SchemaTestConfig {
        // Add additional configuration here, if needed
    }

}

