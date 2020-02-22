package org.dgf.shop.persistence.h2.product;

import lombok.experimental.UtilityClass;
import org.dgf.shop.rest.model.Product;

@UtilityClass
public class ProductMapper {

    public static ProductEntity convert(Product model) {
        return new ProductEntity(model.getId(), model.getName(), model.getPrice());
    }

    public static Product convert(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice());
    }

    public static ProductEntity copy(Product model, ProductEntity entity) {
        entity.setName(model.getName());
        entity.setPrice(model.getPrice());
        return entity;
    }


}
