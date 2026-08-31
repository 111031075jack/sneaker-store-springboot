package com.company.erp.sneakerstorespringboot.service;

import com.company.erp.sneakerstorespringboot.model.dto.CategoryRequest;
import com.company.erp.sneakerstorespringboot.model.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    Category create(CategoryRequest request);

    Category update(Long id, CategoryRequest request);

    void delete(Long id);

}
