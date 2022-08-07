package com.chiche.spring.jpa.postgresql.repository;

import com.chiche.spring.jpa.postgresql.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommuneRepository extends JpaRepository<Commune, Long> {
    List<Commune> findByDescriptionContaining(String description);
}