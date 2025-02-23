package es.in2.issuer.modules.oid4vci.infrastructure.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OID4VCIStatusController.class)
class OID4VCIStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void testGetStatus() throws Exception {
        mockMvc.perform(get("/oid4vci/v1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("OID4VCI is running"));
    }

}
