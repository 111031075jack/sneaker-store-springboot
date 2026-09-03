package com.company.erp.sneakerstorespringboot.controller;

import com.company.erp.sneakerstorespringboot.model.entity.Brand;
import com.company.erp.sneakerstorespringboot.repository.BrandRepository;
import com.company.erp.sneakerstorespringboot.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    public List<Brand> showAll(Model model) {
        return brandService.findAll();
    }


}
