package com.global.eshophexa.infrastructure.databse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private String name;
    private String description;
    private LocalDate creationDate;
    private BigDecimal price;
    @ManyToOne
    private CategoryEntity category;
    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines;

}
