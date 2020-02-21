package org.dgf.shop.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dgf.shop.rest.model.Order;

import java.time.LocalDate;

public class OrderNewDto extends Order<Long> {

    @Override
    @JsonIgnore
    public Long getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore
    public LocalDate getDate() {
        return super.getDate();
    }

    @Override
    @JsonIgnore
    public Double getPrice() {
        return super.getPrice();
    }
}
