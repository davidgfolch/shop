package org.dgf.shop.persistence.h2.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dgf.shop.persistence.h2.product.ProductEntity;
import org.dgf.shop.rest.model.IOrder;
import org.dgf.shop.rest.model.IProduct;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements IOrder<ProductEntity> {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    private List<ProductEntity> products = new ArrayList<>();
    private String customerEmail;
    private Double price;

}

