package org.dgf.persistence;

import lombok.RequiredArgsConstructor;
import org.dgf.model.Product;
import org.dgf.port.out.ProductCrudPort;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
//@AllArgsConstructor
@RequiredArgsConstructor
@EnableJpaRepositories
@EnableAutoConfiguration
public class ProductCrudPortImpl implements ProductCrudPort {

    private final ProductRepository repo;
    private final ProductMapper mapper;

    @Override
    public Product create(Product entity) {
        return mapper.convert(repo.save(mapper.convert(entity)));
    }

    @Override
    public Optional<Product> update(Product entity) {
        return repo.findById(entity.getId())
                .map(repo::save)
                .map(mapper::convert);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Product> find(Long id) {
        return repo.findById(id).map(mapper::convert);
    }

    @Override
    public List<Product> find(String nameLike) {
        List<ProductEntity> productEntities;
        if (nameLike==null) productEntities= repo.findAll();
        else productEntities = repo.findByNameContaining(nameLike);
        return productEntities.parallelStream().map(mapper::convert).collect(Collectors.toList());
    }
}
