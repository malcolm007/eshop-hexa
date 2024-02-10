package com.global.eshophexa.infrastructure.databse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderLine extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;
    private BigDecimal totalPrice;
    private long quantity;
}
