package org.dgf.shop.service.order;

import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface OrderUseCase {

    Order<Product> create(Order<Long> product);
    List<Order<Product>> find(LocalDateTime from, LocalDateTime to);
}
