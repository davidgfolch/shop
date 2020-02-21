package org.dgf.shop.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order implements IOrder<Product> {

    private Long id;
    private LocalDate date;
    private List<Product> products;
    private String customerEmail;
    private Float price;
}
