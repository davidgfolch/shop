package org.dgf.shop.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

    private Long id;
    private LocalDateTime date;
    private List<Product> products;
    private Customer customer;
    private Float price;
}
