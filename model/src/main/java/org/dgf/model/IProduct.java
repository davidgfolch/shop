package org.dgf.model;

public interface IProduct {

    Long getId();
    String getName();
    Float getPrice();

    void setId(Long id);
    void setName(String name);
    void setPrice(Float price);

}
