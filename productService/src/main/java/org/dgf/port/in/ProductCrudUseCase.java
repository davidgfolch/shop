package org.dgf.port.in;

import org.dgf.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductCrudUseCase {

    Product create(Product product);
    Optional<Product> update(Product product);
    void delete(Long id);
    Optional<Product> find(Long id);
    List<Product> find(String nameLike);
}
