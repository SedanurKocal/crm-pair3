package com.tcellpair3.productservice.entities;

import com.tcellpair3.productservice.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="catalogs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalog extends BaseEntity {

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
    private List<Product> products;
}
