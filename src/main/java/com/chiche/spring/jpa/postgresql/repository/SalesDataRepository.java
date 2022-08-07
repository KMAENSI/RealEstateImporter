package com.chiche.spring.jpa.postgresql.repository;

import com.chiche.spring.jpa.postgresql.model.Commune;
import com.chiche.spring.jpa.postgresql.model.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesDataRepository extends JpaRepository<SalesData, Long> {
}
