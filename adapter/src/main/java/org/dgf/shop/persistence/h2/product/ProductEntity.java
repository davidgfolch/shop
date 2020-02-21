package org.dgf.shop.persistence.h2.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dgf.shop.rest.model.IProduct;
import org.dgf.shop.rest.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "shop_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements IProduct {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private Float price;

    public ProductEntity(Long id) {
        this.id=id;
    }

    public ProductEntity copyFromModel(Product entity) {
        this.name=entity.getName();
        this.price=entity.getPrice();
        return this;
    }
}

