package org.dgf.shop.port;

import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.OrderException;
import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface OrderPort {
    Order<Product> create(Order<Long> model) throws OrderException;
    List<Order<Product>> find(LocalDateTime from, LocalDateTime to);
}
