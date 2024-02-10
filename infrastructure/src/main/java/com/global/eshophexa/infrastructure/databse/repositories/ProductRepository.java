package com.global.eshophexa.infrastructure.databse.repositories;

import com.global.eshophexa.infrastructure.databse.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
