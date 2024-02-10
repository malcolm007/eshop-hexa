package com.global.eshophexa.infrastructure.databse.repositories;

import com.global.eshophexa.infrastructure.databse.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByEmail(String email);
}
