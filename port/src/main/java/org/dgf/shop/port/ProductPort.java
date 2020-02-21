package org.dgf.shop.port;

import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductPort {
    Product create(Product entity);
    Optional<Product> update(Product entity);
    void delete(Long id);
    Optional<Product> find(Long id);
    List<Product> find(String nameLike);
    List<Product> findAll(List<Long> products);
}