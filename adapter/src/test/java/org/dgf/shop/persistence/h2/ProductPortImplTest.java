package org.dgf.shop.persistence.h2;

import org.dgf.shop.persistence.h2.product.ProductPortImpl;
import org.dgf.shop.persistence.h2.product.ProductEntity;
import org.dgf.shop.persistence.h2.product.ProductMapper;
import org.dgf.shop.persistence.h2.product.ProductRepository;
import org.dgf.shop.rest.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { ProductPortImpl.class, ProductMapper.class })
@RunWith(SpringRunner.class)
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
        when(repo.save(entity)).thenReturn(entity);
        Optional<Product> result = port.update(model);
        assertFalse(result.isPresent());
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
        Optional<Product> entity = port.find(1L);
        assertFalse(entity.isPresent());
    }

    @Test
    public void findByName() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        List<Product> entity = port.find("non existent product");
        assertTrue(entity.isEmpty());
    }

    @Test
    public void findByNameNull() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        List<Product> entity = port.find((String)null);
        assertTrue(entity.isEmpty());
    }

}
