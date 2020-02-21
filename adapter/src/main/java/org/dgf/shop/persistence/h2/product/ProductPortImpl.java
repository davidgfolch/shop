package org.dgf.shop.persistence.h2.product;

import lombok.RequiredArgsConstructor;
import org.dgf.shop.port.ProductPort;
import org.dgf.shop.rest.model.Product;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
@EnableJpaRepositories
@EnableAutoConfiguration
public class ProductPortImpl implements ProductPort {

    private final ProductRepository repo;

    @Override
    public Product create(@NonNull Product entity) {
        return ProductMapper.convert(repo.save(ProductMapper.convert(entity)));
    }

    @Override
    public Optional<Product> update(@NonNull Product entity) {
        return repo.findById(entity.getId())
                .map(e->e.copyFromModel(entity))
                .map(repo::save)
                .map(ProductMapper::convert);
    }

    @Override
    public void delete(@NonNull Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Product> find(Long id) {
        return repo.findById(id).map(ProductMapper::convert);
    }

    @Override
    public List<Product> find(String nameLike) {
        return (isEmpty(nameLike) ? repo.findAll() : repo.findByNameContaining(nameLike))
            .stream().map(ProductMapper::convert).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAll(List<Long> products) {
        return repo.findAllById(products).stream().map(ProductMapper::convert).collect(Collectors.toList());
    }
}
