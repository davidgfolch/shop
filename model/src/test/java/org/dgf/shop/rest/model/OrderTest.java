package org.dgf.shop.rest.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderTest {

    private final List<Product> products = Collections.unmodifiableList(Arrays.asList(
            Product.builder().name("Product1").price(10.5f).build(),
            Product.builder().name("Product2").price(12f).build()
    ));

    @Test
    public void order() {
        Order<?> order = Order.builder().products(products.stream().map(Product::getId).collect(Collectors.toList())).build();
        assertNull(order.getDate());
        assertEquals(2,order.getProducts().size());
    }
}
