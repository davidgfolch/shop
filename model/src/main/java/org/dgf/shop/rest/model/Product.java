package org.dgf.shop.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor
@AllArgsConstructor()
@Data()
@Builder
public class Product implements IProduct {

    private Long id;
    @NotEmpty
    private String name;
    private Float price;

}
