package org.dgf.shop.persistence.h2.order;

import lombok.experimental.UtilityClass;
import org.dgf.shop.persistence.h2.product.ProductEntity;
import org.dgf.shop.persistence.h2.product.ProductMapper;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;

import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {

    public static OrderEntity convert(Order<Long> model) {
        return new OrderEntity(model.getId(), model.getDate(),
                model.getProducts().stream().map(ProductEntity::new).collect(Collectors.toList()),
                model.getCustomerEmail(), model.getPrice());
    }

    public static Order<Product> convert(OrderEntity entity) {
        return new Order<>(entity.getId(), entity.getDate(),
                entity.getProducts().stream().map(ProductMapper::convert).collect(Collectors.toList()),
                entity.getCustomerEmail(), entity.getPrice());
    }

}
