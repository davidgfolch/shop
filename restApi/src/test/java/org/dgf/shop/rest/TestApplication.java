package org.dgf.shop.rest;

import org.dgf.shop.rest.controller.ProductController;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;

@WebMvcTest(controllers = ProductController.class)
@ComponentScan(basePackages = "org.dgf.shop.rest.controller")
public class TestApplication {
    @Test
    public void contextLoads() {}
}
