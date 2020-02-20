package org.dgf.port.out;

import org.dgf.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductCrudPort {
    Product create(Product entity);
    Optional<Product> update(Product entity);
    void delete(Long id);
    Optional<Product> find(Long id);
    List<Product> find(String nameLike);
}
