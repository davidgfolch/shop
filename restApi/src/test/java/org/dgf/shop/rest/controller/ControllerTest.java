package org.dgf.shop.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dgf.shop.rest.dto.ProductDto;
import org.dgf.shop.rest.model.Product;
import org.dgf.shop.rest.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@ComponentScan(basePackages = "org.dgf.controller")
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void create() throws Exception {
        Product product1 = ProductDto.builder().id(1L).name("Product1").price(10f).build();

        when(service.create(product1)).thenReturn(product1);

        String json = mapper.writeValueAsString(product1);
        mockMvc.perform(
                post("/product/create")
                        .content(json)
                        .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }

    @Test
    void find() throws Exception {
        when(service.find("")).thenReturn(new ArrayList<>());

        mockMvc.perform(
                    get("/product/find/{nameLikeFilter}","this product doesn't exist")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(new ArrayList<>())));
    }



}
