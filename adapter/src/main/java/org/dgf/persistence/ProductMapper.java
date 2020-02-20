package org.dgf.persistence;

import org.dgf.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity convert(Product bean) {
        return new ProductEntity(bean.getId(), bean.getName(), bean.getPrice());
    }

    public Product convert(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice());
    }

}
