package com.global.eshophexa.infrastructure.databse.repositories;

import com.global.eshophexa.infrastructure.databse.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
