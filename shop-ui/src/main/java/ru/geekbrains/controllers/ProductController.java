package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;
import ru.geekbrains.service.ProductFeignService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductServerInterface productService;
    private ProductFeignService productFeignService;

    @Autowired
    public ProductController(ProductServerInterface productService, ProductFeignService productFeignService) {
        this.productService = productService;
        this.productFeignService = productFeignService;
    }

    @GetMapping("/product/{productId}")
    public String product(Model model, @PathVariable("productId") Long productId)
    {


        Optional<ProductRepl> product = productService.findReplById(productId);

        model.addAttribute("product", product.get());
        model.addAttribute("price", productFeignService.getPrice(productId));
        return "product";
    }
}
