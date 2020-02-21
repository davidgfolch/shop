package org.dgf.shop.service.order;

import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface OrderUseCase {

    Order<Product> create(Order<Long> product);
    List<Order<Product>> find(LocalDate from, LocalDate to);
}
