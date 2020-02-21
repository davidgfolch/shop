package org.dgf.shop.persistence.h2.product;

import lombok.experimental.UtilityClass;
import org.dgf.shop.rest.model.Product;

@UtilityClass
public class ProductMapper {

    public static ProductEntity convert(Product bean) {
        return new ProductEntity(bean.getId(), bean.getName(), bean.getPrice());
    }

    public static Product convert(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice());
    }

}
