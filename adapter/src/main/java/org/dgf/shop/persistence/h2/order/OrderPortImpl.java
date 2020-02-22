package org.dgf.shop.persistence.h2.order;

import lombok.RequiredArgsConstructor;
import org.dgf.shop.persistence.h2.product.ProductRepository;
import org.dgf.shop.port.OrderPort;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.OrderException;
import org.dgf.shop.rest.model.Product;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@EnableAutoConfiguration
@EnableJpaRepositories
@Transactional(rollbackOn = RuntimeException.class, value= Transactional.TxType.MANDATORY)
public class OrderPortImpl implements OrderPort {

    private final OrderRepository repo;
    private final ProductRepository productRepo;

    @Override
    public Order<Product> create(@NonNull Order<Long> model) throws OrderException {
        OrderEntity entity = OrderMapper.convert(model);
        entity.setProducts(productRepo.findAllById(model.getProducts()));
        if (entity.getProducts().size()!=model.getProducts().size())
            throw new OrderException("Some products don't exists");
        entity.setDate(LocalDateTime.now());
        return OrderMapper.convert(repo.save(entity));
    }

    @Override
    public List<Order<Product>> find(@NonNull LocalDateTime from, @NonNull LocalDateTime to) {
        return repo.findByDateBetween(from,to).stream().map(OrderMapper::convert).collect(Collectors.toList());
    }

}
