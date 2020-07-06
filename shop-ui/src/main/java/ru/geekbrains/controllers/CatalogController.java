package ru.geekbrains.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;

import java.util.List;

@Controller
public class CatalogController {

    private ProductServerInterface productService;

    @Autowired
    public CatalogController(ProductServerInterface productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String catalog(Model model)
    {


        List<ProductRepl> products = productService.findAll();

        model.addAttribute("products", products);
        return "catalog";
    }
}
