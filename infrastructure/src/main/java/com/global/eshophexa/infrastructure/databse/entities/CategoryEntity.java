package com.global.eshophexa.infrastructure.databse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
