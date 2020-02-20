package org.dgf.service;

import lombok.RequiredArgsConstructor;
import org.dgf.model.Product;
import org.dgf.port.in.ProductCrudUseCase;
import org.dgf.port.out.ProductCrudPort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService implements ProductCrudUseCase {

    private final ProductCrudPort productCrudPort;

    @Override
    public Product create(Product entity) {
        return productCrudPort.create(entity);
    }

    @Override
    public Optional<Product> update(Product entity) {
        return productCrudPort.update(entity);
    }

    @Override
    public void delete(Long id) {
        productCrudPort.delete(id);
    }

    @Override
    public Optional<Product> find(Long id) {
        return productCrudPort.find(id);
    }

    @Override
    public List<Product> find(String nameLike) {
        return productCrudPort.find(nameLike);
    }

}
