package org.dgf.shop.service.order;

import lombok.RequiredArgsConstructor;
import org.dgf.shop.port.OrderPort;
import org.dgf.shop.port.ProductPort;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.OrderException;
import org.dgf.shop.rest.model.Product;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(value=Transactional.TxType.REQUIRED)
public class OrderService implements OrderUseCase {

    public static final String ORDER_MUST_HAVE_PRODUCTS = "Order must have products";
    private final OrderPort port;
    private final ProductPort productPort;

    @Override
    public Order<Product> create(@NonNull Order<Long> model) throws OrderException {
        if (model.getProducts()==null || model.getProducts().isEmpty()) throw new IllegalStateException(ORDER_MUST_HAVE_PRODUCTS);
        model.setPrice(productPort.findAllById(model.getProducts()).stream().mapToDouble(Product::getPrice).sum());
        return port.create(model);
    }

    @Override
    public List<Order<Product>> find(@NonNull LocalDateTime from, @NonNull LocalDateTime to) {
        return port.find(from,to);
    }

}
