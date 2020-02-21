package org.dgf.shop.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dgf.shop.rest.dto.ProductDto;
import org.dgf.shop.rest.dto.ProductNewDto;
import org.dgf.shop.rest.model.Product;
import org.dgf.shop.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.dgf.shop.rest.controller.ProductController.PRODUCT_NOT_FOUND;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void create() throws Exception {
        final Product newProduct = ProductNewDto.builder().name("Product1").price(10f).build();
        when(service.create(newProduct)).thenReturn(newProduct);

        String json = mapper.writeValueAsString(newProduct);
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
    void update() throws Exception {
        final Product updateProduct = ProductDto.builder().id(1L).name("Product1").price(10f).build();
        when(service.update(updateProduct)).thenReturn(Optional.empty());

        String json = mapper.writeValueAsString(updateProduct);
        mockMvc.perform(
                post("/product/update")
                        .content(json)
                        .contentType(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string(PRODUCT_NOT_FOUND));
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
