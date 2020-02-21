package org.dgf.shop.rest.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dgf.shop.rest.model.IProduct;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements IProduct {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Float price;

}

