package org.dgf.shop.rest.model;

import java.time.LocalDate;
import java.util.List;

public interface IOrder<T> {

    Long getId();
    LocalDate getDate();
    List<T> getProducts();
    String getCustomerEmail();
    Double getPrice();

    void setId(Long id);
    void setDate(LocalDate date);
    void setProducts(List<T> products);
    void setCustomerEmail(String customerEmail);
    void setPrice(Double price);
}
