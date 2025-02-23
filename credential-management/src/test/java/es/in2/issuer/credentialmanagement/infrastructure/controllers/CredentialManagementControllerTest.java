package es.in2.issuer.credentialmanagement.infrastructure.controllers;

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

@WebMvcTest(CredentialManagementStatusControllerTest.class)
@ContextConfiguration(classes = CredentialManagementStatusControllerTest.CredentialManagementTestConfig.class)
class CredentialManagementStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetStatus() throws Exception {
        mockMvc.perform(get("/credential-management/v1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Credential Management is running"));
    }

    @Configuration
    @ComponentScan(basePackages = "es.in2.issuer.credentialmanagement.infrastructure.controllers")
    static class CredentialManagementTestConfig {
        // Add additional configuration here, if needed
    }

}


