package com.company.erp.sneakerstorespringboot.service;

import com.company.erp.sneakerstorespringboot.model.dto.CategoryRequest;
import com.company.erp.sneakerstorespringboot.model.entity.Category;
import com.company.erp.sneakerstorespringboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                            "找不到分類, ID: " + id
                    )
                );
    }

    @Override
    @Transactional
    public Category create(CategoryRequest request) {

        String name = request.getName().trim();
        String slug = request.getSlug().trim().toLowerCase(Locale.ROOT);

        if(categoryRepository.existsByName(name)){
            throw new IllegalArgumentException("分類名稱已存在");
        }

        if(categoryRepository.existsBySlug(slug)){
            throw new IllegalArgumentException("Slug 已存在");
        }

        Category category = new Category();

        category.setName(name);
        category.setSlug(slug);
        category.setActive(request.isActive());

        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category update(Long id, CategoryRequest request) {

        Category category = findById(id);

        String name = request.getName().trim();
        String slug = request.getSlug().trim().toLowerCase(Locale.ROOT);

        boolean nameChanged = !category.getName().equalsIgnoreCase(name);

        boolean slugChanged = !category.getSlug().equalsIgnoreCase(slug);

        if(nameChanged && categoryRepository.existsByName(name)){
            throw new IllegalArgumentException("分類名稱已存在");
        }

        if(slugChanged && categoryRepository.existsBySlug(slug)){
            throw new IllegalArgumentException("Slug 已存在");
        }

        category.setName(name);
        category.setSlug(slug);
        category.setActive(request.isActive());

        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Category category = findById(id);
        categoryRepository.delete(category);

    }
}
