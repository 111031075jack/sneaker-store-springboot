package com.company.erp.sneakerstorespringboot.controller;

import com.company.erp.sneakerstorespringboot.model.dto.CategoryRequest;
import com.company.erp.sneakerstorespringboot.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
