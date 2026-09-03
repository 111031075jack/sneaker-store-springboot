package com.company.erp.sneakerstorespringboot.service;

import com.company.erp.sneakerstorespringboot.model.dto.BrandRequest;
import com.company.erp.sneakerstorespringboot.model.entity.Brand;
import com.company.erp.sneakerstorespringboot.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(long id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("找不到品牌,ID: " + id)
                );
    }

    @Override
    @Transactional
    public Brand create(BrandRequest request) {

        String name = request.getName().strip();

        String slug = request.getSlug().strip().toLowerCase(Locale.ROOT);

        if(brandRepository.existsByName(name)) {
            throw new IllegalArgumentException("品牌名稱已存在");
        }

        if(brandRepository.existsBySlug(slug)) {
            throw new IllegalArgumentException("Slug 已存在");
        }

        Brand brand = new Brand();

        brand.setName(name);
        brand.setSlug(slug);
        brand.setActive(request.isActive());

        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public Brand update(Long id, BrandRequest request) {

        Brand brand = findById(id);

        String name = request.getName().strip();

        String slug = request.getSlug().strip().toLowerCase(Locale.ROOT);

        boolean nameChanged = !brand.getName().equalsIgnoreCase(name);

        boolean slugChanged = !brand.getSlug().equalsIgnoreCase(slug);

        if(nameChanged && brandRepository.existsByName(name)) {
            throw new IllegalArgumentException("品牌名稱已存在");
        }

        if(slugChanged && brandRepository.existsBySlug(slug)) {
            throw new IllegalArgumentException("Slug 已存在");
        }

        brand.setName(name);
        brand.setSlug(slug);
        brand.setActive(request.isActive());


        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Brand brand = findById(id);

        brandRepository.delete(brand);

    }
}
