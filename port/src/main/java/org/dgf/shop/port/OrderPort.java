package org.dgf.shop.port;

import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface OrderPort {
    Order<Product> create(Order<Long> entity);
    List<Order<Product>> find(LocalDateTime from, LocalDateTime to);
}
