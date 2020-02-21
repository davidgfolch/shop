package org.dgf.shop.rest;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;

@WebMvcTest()
@ComponentScan(basePackages = "org.dgf.controller")
public class TestApplication {
    @Test
    public void contextLoads() {}
}
