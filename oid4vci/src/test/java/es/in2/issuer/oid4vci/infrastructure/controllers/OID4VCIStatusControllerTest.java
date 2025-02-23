package es.in2.issuer.oid4vci.infrastructure.controllers;

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

@WebMvcTest(OID4VCIStatusControllerTest.class)
@ContextConfiguration(classes = OID4VCIStatusControllerTest.OID4VCITestConfig.class)
class OID4VCIStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetStatus() throws Exception {
        mockMvc.perform(get("/oid4vci/v1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("OID4VCI is running"));
    }

    @Configuration
    @ComponentScan(basePackages = "es.in2.issuer.oid4vci.infrastructure.controllers")
    static class OID4VCITestConfig {
        // Add additional configuration here, if needed
    }

}


