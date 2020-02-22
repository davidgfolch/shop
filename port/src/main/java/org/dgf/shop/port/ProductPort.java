package org.dgf.shop.port;

import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductPort {
    Product create(Product model);
    Optional<Product> update(Product model);
    void delete(Long id);
    Optional<Product> find(Long id);
    List<Product> find(String nameLike);
    List<Product> findAllById(List<Long> ids);
}
