package org.dgf.shop.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dgf.shop.rest.model.Product;

public class ProductNewDto extends Product {

    @Override
    @JsonIgnore
    public Long getId() {
        return super.getId();
    }
}
