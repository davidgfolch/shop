package org.dgf.shop.persistence.h2;

import org.dgf.shop.persistence.h2.product.ProductEntity;
import org.dgf.shop.persistence.h2.product.ProductPortImpl;
import org.dgf.shop.persistence.h2.product.ProductRepository;
import org.dgf.shop.rest.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { ProductPortImpl.class })
@RunWith(SpringRunner.class)
@Transactional
public class ProductPortImplTest {

    @Autowired
    private ProductPortImpl port;
    @MockBean
    private ProductRepository repo;

    private final ProductEntity entity = new ProductEntity(1L,"Product1",10F);
    private final Product model = Product.builder().id(1L).name("Product1").price(10F).build();

    @Test
    public void create() {
        when(repo.save(entity)).thenReturn(entity);
        Product result = port.create(model);
        assertEquals(model,result);
    }

    @Test
    public void update() {
        when(repo.findById(model.getId())).thenReturn(Optional.of(entity));
        when(repo.save(entity)).thenReturn(entity);
        Optional<Product> result = port.update(model);
        assertTrue(result.isPresent());
        assertEquals(model,result.get());
    }

    @Test
    public void delete() {
        doNothing().when(repo).delete(entity);
        port.delete(1L);
        assertTrue(true);
    }

    @Test
    public void findById() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        Optional<Product> model = port.find(1L);
        assertFalse(model.isPresent());
    }

    @Test
    public void findByName() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        List<Product> model = port.find("non existent product");
        assertTrue(model.isEmpty());
    }

    @Test
    public void findByNameNull() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        List<Product> model = port.find((String)null);
        assertTrue(model.isEmpty());
    }

    @Test
    public void findAll() {
        List<Long> ids=Collections.singletonList(1L);
        when(repo.findAllById(ids)).thenReturn(new ArrayList<>());
        List<Product> model = port.findAllById(ids);
        assertTrue(model.isEmpty());
    }


}
