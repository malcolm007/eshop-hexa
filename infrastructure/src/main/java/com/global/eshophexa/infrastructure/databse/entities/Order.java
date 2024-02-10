package com.global.eshophexa.infrastructure.databse.entities;

import jakarta.persistence.*;
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
@Table(name = "T_order")
public class Order extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    @Column(unique = true)
    private String number;
    private LocalDate date;
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;
    @ManyToOne
    private PersonEntity client;
}
