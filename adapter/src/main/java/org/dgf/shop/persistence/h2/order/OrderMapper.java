package org.dgf.shop.persistence.h2.order;

import org.dgf.shop.persistence.h2.product.ProductEntity;
import org.dgf.shop.persistence.h2.product.ProductMapper;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public static OrderEntity convert(Order<Long> bean) {
        return new OrderEntity(bean.getId(), bean.getDate(),
                bean.getProducts().stream().map(ProductEntity::new).collect(Collectors.toList()),
                bean.getCustomerEmail(), bean.getPrice());
    }

    public static Order<Product> convert(OrderEntity entity) {
        return new Order<>(entity.getId(), entity.getDate(),
                entity.getProducts().stream().map(ProductMapper::convert).collect(Collectors.toList()),
                entity.getCustomerEmail(), entity.getPrice());
    }

}
