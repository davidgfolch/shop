package org.dgf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor()
@Data()
@Builder
public class Product implements IProduct {

    private Long id;
    private String name;
    private Float price;

}
