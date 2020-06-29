package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.enity.Category;
import ru.geekbrains.persist.service.interdafaces.CategoryServiceInterface;

@RequestMapping("/category")
@Controller
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private CategoryServiceInterface service;

    @Autowired
    public CategoryController(CategoryServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public String categoryList(Model model) {
        logger.info("Category list");

        model.addAttribute("categories", service.findAll());
        return "category/categories";
    }

    @GetMapping("new")
    public String createCategory(Model model) {
        logger.info("Create category form");

        model.addAttribute("category", new Category());

        return "category/form";
    }
    @GetMapping("update/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {

        model.addAttribute("category", service.findById(id).orElseThrow(() -> new NotFoundException("not found")));

        return "category/form";
    }


    @PostMapping
    public String saveCategory(Category category) {
        logger.info("Save category method");

        service.save(category);
        return "redirect:/category";
    }
}
