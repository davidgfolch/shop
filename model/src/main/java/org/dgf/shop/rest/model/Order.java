package org.dgf.shop.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order<T> implements IOrder<T> {

    private Long id;
    private LocalDate date;
    private List<T> products;
    @Email(message = "Email should be valid")
    private String customerEmail;
    private Double price;
}
