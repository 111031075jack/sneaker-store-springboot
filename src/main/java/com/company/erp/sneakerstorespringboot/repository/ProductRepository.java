package com.company.erp.sneakerstorespringboot.repository;

import com.company.erp.sneakerstorespringboot.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findBySlug(String slug);

    boolean existsBySlug(String slug);
}
