package es.in2.issuer.authserver.infrastructure.controllers;

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

@WebMvcTest(AuthServerStatusControllerTest.class)
@ContextConfiguration(classes = AuthServerStatusControllerTest.AuthServerTestConfig.class)
class AuthServerStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetStatus() throws Exception {
        mockMvc.perform(get("/auth-server/v1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Auth Server is running"));
    }

    @Configuration
    @ComponentScan(basePackages = "es.in2.issuer.authserver.infrastructure.controllers")
    static class AuthServerTestConfig {
        // Add additional configuration here, if needed
    }

}

