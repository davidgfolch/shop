package org.dgf.shop.service.product;

import lombok.RequiredArgsConstructor;
import org.dgf.shop.port.ProductPort;
import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(value=Transactional.TxType.REQUIRED)
public class ProductService implements ProductUseCase {

    private final ProductPort port;

    @Override
    public Product create(Product model) {
        return port.create(model);
    }

    @Override
    public Optional<Product> update(Product model) {
        return port.update(model);
    }

    @Override
    public void delete(Long id) {
        port.delete(id);
    }

    @Override
    public Optional<Product> find(Long id) {
        return port.find(id);
    }

    @Override
    public List<Product> find(String nameLike) {
        return port.find(nameLike);
    }

}
