package org.dgf.shop.persistence.h2.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dgf.shop.persistence.h2.product.ProductEntity;
import org.dgf.shop.rest.model.IOrder;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime date;
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    private List<ProductEntity> products;
    private String customerEmail;
    private Double price;

}

