package com.company.erp.sneakerstorespringboot.controller;

import com.company.erp.sneakerstorespringboot.model.dto.BrandRequest;
import com.company.erp.sneakerstorespringboot.model.entity.Brand;
import com.company.erp.sneakerstorespringboot.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public String list(Model model) {

        model.addAttribute("brands", brandService.findAll());

        return "admin/brands/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("brandRequest", new BrandRequest());

        return "admin/brands/form";
    }

    @PostMapping
    public String create(
                        @Valid
                        @ModelAttribute("brandRequest")BrandRequest brandRequest,
                         BindingResult result,
                         RedirectAttributes attributes
                        ){

        if(result.hasErrors()){
            return "admin/brands/form";
        }

        try{
            brandService.create(brandRequest);
        } catch (IllegalArgumentException e) {

            result.reject("brand.create", e.getMessage());

            return "admin/brands/form";
        }

        attributes.addFlashAttribute("success", "品牌建立成功");

        return "redirect:/admin/brands";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        Brand brand = brandService.findById(id);

        BrandRequest brandRequest = new BrandRequest();

        brandRequest.setName(brand.getName());
        brandRequest.setSlug(brand.getSlug());
        brandRequest.setActive(brand.isActive());

        model.addAttribute("brandRequest", brandRequest);
        model.addAttribute("brandId", id);

        return "admin/brands/form";

    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("brandRequest")
                         BrandRequest brandRequest,
                         BindingResult result,
                         Model model,
                         RedirectAttributes attributes){
        if(result.hasErrors()){

            model.addAttribute("brandId", id);

            return "admin/brands/form";
        }

        try{
            brandService.update(id, brandRequest);
        } catch (IllegalArgumentException e) {

            result.reject("brand.update", e.getMessage());
            model.addAttribute("brandId", id);

            return "admin/brands/form";
        }

        attributes.addFlashAttribute("success", "品牌修改成功");
        return "redirect:/admin/brands";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){

        try{
            brandService.delete(id);

            attributes.addFlashAttribute(
                    "success",
                    "品牌刪除成功"
            );
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/brands";
    }


}

