package org.dgf.shop.service;

import org.dgf.shop.port.OrderPort;
import org.dgf.shop.port.ProductPort;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.OrderException;
import org.dgf.shop.rest.model.Product;
import org.dgf.shop.service.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = OrderService.class)
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    private final LocalDateTime now = LocalDateTime.now();

    @Autowired
    public OrderService service;
    @MockBean
    private OrderPort port;
    @MockBean
    private ProductPort productPort;

    private final Order<Long> model = Order.<Long>builder().customerEmail("d@g.com").products(Collections.singletonList(1L)).build();
    private final Order<Long> modelNoProducts = Order.<Long>builder().customerEmail("d@g.com").products(new ArrayList<>()).build();
    private final Order<Long> modelNullProducts = Order.<Long>builder().customerEmail("d@g.com").products(null).build();

    @Test
    public void create() throws OrderException {
        when(port.create(model)).thenReturn(Order.<Product>builder().customerEmail("d@g.com").build());
        Order<Product> result = service.create(model);
        assertEquals(model.getCustomerEmail(),result.getCustomerEmail());
    }
    @Test(expected = RuntimeException.class)
    public void createTransaction() throws OrderException {
        when(productPort.findAllById(anyList())).thenThrow(new RuntimeException("Checking service @Transactional annotation"));
        when(port.create(model)).thenReturn(Order.<Product>builder().customerEmail("d@g.com").build());
        service.create(model);
    }

    @Test(expected = IllegalStateException.class)
    public void createNoProductsException() throws OrderException {
        service.create(modelNoProducts);
    }

    @Test(expected = IllegalStateException.class)
    public void createNullProductsException() throws OrderException {
        service.create(modelNullProducts);
    }

    @Test()
    public void find() {
        when(port.find(now, now)).thenReturn(new ArrayList<>());
        List<Order<Product>> res = service.find(now,now);
        assertTrue(res.isEmpty());
    }

}
