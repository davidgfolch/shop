package org.dgf.shop.persistence.h2;

import org.dgf.shop.persistence.h2.order.OrderEntity;
import org.dgf.shop.persistence.h2.order.OrderPortImpl;
import org.dgf.shop.persistence.h2.order.OrderRepository;
import org.dgf.shop.persistence.h2.product.ProductEntity;
import org.dgf.shop.persistence.h2.product.ProductRepository;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.OrderException;
import org.dgf.shop.rest.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { OrderPortImpl.class })
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class OrderPortImplTest {

    @Autowired
    private OrderPortImpl port;
    @MockBean
    private OrderRepository repo;
    @MockBean
    private ProductRepository productRepo;

    private final List<Long> prodIds = Collections.singletonList(1L);
    private final ProductEntity productEntity =new ProductEntity(1L,"Product",10F);
    private final OrderEntity entity = new OrderEntity(1L, LocalDateTime.now(),Collections.singletonList(productEntity),"d@g.com",10D);
    private final Order<Long> model = Order.<Long>builder().id(1L).customerEmail("d@g.com").products(prodIds).date(LocalDateTime.now()).price(10D).build();

    @Test
    public void create() throws OrderException {
        when(productRepo.findAllById(prodIds)).thenReturn(Collections.singletonList(productEntity));
        when(repo.save(any(OrderEntity.class))).thenReturn(entity);
        Order<Product> result = port.create(model);
        assertEquals(model.getId(),result.getId());
    }

    @Test
    public void createOrderException() throws OrderException {
        OrderEntity entityNoProductsFound = this.entity;
        entityNoProductsFound.setProducts(new ArrayList<>());
        when(productRepo.findAllById(prodIds)).thenReturn(Collections.singletonList(productEntity));
        when(repo.save(any(OrderEntity.class))).thenReturn(entityNoProductsFound);
        Order<Product> result = port.create(model);
        assertEquals(model.getId(),result.getId());
    }

    @Test()
    public void createCheckTransaction() throws OrderException {
        when(productRepo.findAllById(prodIds)).thenThrow(new RuntimeException("transaction test"));
        try {
            port.create(model);
        } catch (RuntimeException e) {
            assertTrue(Arrays.stream(e.getStackTrace()).anyMatch(
                    l->l.getClassName().contains(TransactionInterceptor.class.getSimpleName())
            ));
            assertTrue(Arrays.stream(e.getStackTrace()).anyMatch(
                    l->l.getClassName().contains(TransactionAspectSupport.class.getSimpleName())
            ));
        }
    }

    @Test
    public void find() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        LocalDateTime now=LocalDateTime.now();
        List<Order<Product>> model = port.find(now,now);
        assertTrue(model.isEmpty());
    }

}
