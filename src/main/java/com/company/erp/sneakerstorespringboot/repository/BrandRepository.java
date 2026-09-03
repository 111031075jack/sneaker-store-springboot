package com.company.erp.sneakerstorespringboot.repository;


import com.company.erp.sneakerstorespringboot.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findBySlug(String slug);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

}
