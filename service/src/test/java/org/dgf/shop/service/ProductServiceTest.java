package org.dgf.shop.service;

import org.dgf.shop.rest.model.Product;
import org.dgf.shop.port.ProductPort;
import org.dgf.shop.service.product.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductService.class)
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    public ProductService service;
    @MockBean
    private ProductPort port;

    private final Product model = Product.builder().id(1L).name("Product1").price(10F).build();

    @Test
    public void create() {
        when(port.create(model)).thenReturn(model);
        Product result = service.create(model);
        assertEquals(model,result);
    }

    @Test
    public void update() {
        when(port.update(model)).thenReturn(Optional.of(model));
        Optional<Product> result = service.update(model);
        assertTrue(result.isPresent());
        assertEquals(model,result.get());
    }

    @Test()
    public void delete() {
        doNothing().when(port).delete(1L);
        service.delete(1L);
        assertTrue(true);
    }

    @Test()
    public void findById() {
        when(port.find(1L)).thenReturn(Optional.of(model));
        Optional<Product> res = service.find(1L);
        assertTrue(res.isPresent());
        assertEquals(res.get(), model);
    }

    @Test()
    public void findByName() {
        when(port.find("non existent")).thenReturn(new ArrayList<>());
        List<Product> res = service.find("non existent");
        assertTrue(res.isEmpty());
    }

}
