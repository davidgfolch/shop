package org.dgf.shop.service.order;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.dgf.shop.port.OrderPort;
import org.dgf.shop.port.ProductPort;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.summingDouble;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService implements OrderUseCase {

    public static final String ORDER_MUST_HAVE_PRODUCTS = "Order must have products";
    private final OrderPort port;
    private final ProductPort productPort;

    @Override
    public Order<Product> create(Order<Long> entity) {
        if (entity.getProducts().isEmpty()) throw new IllegalStateException(ORDER_MUST_HAVE_PRODUCTS);
        entity.setPrice(productPort.findAll(entity.getProducts()).stream().mapToDouble(Product::getPrice).sum());
        return port.create(entity);
    }

    @Override
    public List<Order<Product>> find(@NonNull LocalDateTime from, @NonNull LocalDateTime to) {
        return port.find(from,to);
    }

}
