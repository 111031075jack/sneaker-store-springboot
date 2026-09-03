package com.company.erp.sneakerstorespringboot.controller;

import com.company.erp.sneakerstorespringboot.model.dto.CategoryRequest;
import com.company.erp.sneakerstorespringboot.model.entity.Category;
import com.company.erp.sneakerstorespringboot.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String list(Model model) {

        model.addAttribute("categories", categoryService.findAll());

        return "admin/category/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("categoryRequest", new CategoryRequest());
        return "admin/category/form";

    }

    @PostMapping
    public String create(
            @Valid
            @ModelAttribute("categoryRequest")
            CategoryRequest request,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            return "admin/category/form";
        }

        try {
            categoryService.create(request);
        } catch (IllegalArgumentException exception) {

            result.reject(
                    "category.create",
                    exception.getMessage()
            );

            return "admin/category/form";
        }

        redirectAttributes.addFlashAttribute(
                "success",
                "分類建立成功"
        );

        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {

        Category category = categoryService.findById(id);

        CategoryRequest categoryRequest = new CategoryRequest();

        categoryRequest.setName(category.getName());
        categoryRequest.setSlug(category.getSlug());
        categoryRequest.setActive(category.isActive());

        model.addAttribute("categoryRequest", categoryRequest);

        model.addAttribute("categoryId", id);

        return "admin/category/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid
                         @ModelAttribute("/categoryRequest")
                         CategoryRequest request,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes
                         ){
        if(result.hasErrors()) {

            model.addAttribute("categoryId", id);

            return "admin/category/form";
        }

        try{
            categoryService.update(id, request);
        } catch (IllegalArgumentException e) {

            result.reject("category.update",e.getMessage());

            model.addAttribute("categoryId", id);

            return "admin/category/form";

        }
        redirectAttributes.addFlashAttribute("success", "分類修改成功");

        return "redirect:/admin/categories";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes){

        try{
            categoryService.delete(id);

            redirectAttributes.addFlashAttribute("success", "分類刪除成功");

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute("error", e.getMessage());

        }
        return "redirect:/admin/categories";
    }


}
