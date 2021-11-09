package org.axon.poc.controller;

import org.axon.poc.command.api.test.commands.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest()
public class ProductCommandControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommandGateway commandGateway;

    @Test
    @Disabled
    void testProductController() throws Exception {

        Mockito.when(commandGateway.sendAndWait(Mockito.any(CreateProductCommand.class))).thenReturn("test");

        mockMvc.perform(
                    post("/products/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"name\" : \"Pen\", \"price\" : 10.00, \"quantity\" : 2 }")
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("test"));

        Mockito.verify(commandGateway, Mockito.times(1))
                .sendAndWait(Mockito.any(CreateProductCommand.class));
    }
}
