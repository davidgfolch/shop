package org.dgf.shop.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dgf.shop.rest.dto.OrderNewDto;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;
import org.dgf.shop.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void create() throws Exception {
        final Order<Long> newOrder = OrderNewDto.<Long>builder().customerEmail("d@g.com")
                .products(Collections.singletonList(1L)).build();
        Order<Product> res = OrderNewDto.<Product>builder().build();
        when(service.create(newOrder)).thenReturn(res);
        mockMvc.perform(
                post("/order/create")
                        .content(mapper.writeValueAsString(newOrder))
                        .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(res)));
    }

    @Test
    void find() throws Exception {
        LocalDateTime from=LocalDateTime.now();
        LocalDateTime to=LocalDateTime.now();
        when(service.find(from,to)).thenReturn(new ArrayList<>());
        DateTimeFormatter formatterIsoDT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        mockMvc.perform(
                    get("/order/find")
                            .param("from",formatterIsoDT.format(from))
                            .param("to",formatterIsoDT.format(to))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(new ArrayList<>())));
    }

}
