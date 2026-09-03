package com.company.erp.sneakerstorespringboot.service;

import com.company.erp.sneakerstorespringboot.model.dto.BrandRequest;
import com.company.erp.sneakerstorespringboot.model.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    Brand findById(long id);

    Brand create(BrandRequest request);

    Brand update(Long id,BrandRequest request);

    void delete(Long id);

}
