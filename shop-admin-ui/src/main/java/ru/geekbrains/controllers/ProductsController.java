package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;
import ru.geekbrains.search.ProductSearch;

import javax.validation.Valid;
import java.math.BigDecimal;

@RequestMapping("/product")
@Controller
public class ProductsController {


    private ProductServerInterface productService;

    @Autowired
    public ProductsController(ProductServerInterface productService) {
        this.productService = productService;
    }

    @GetMapping
    public String products(Model model,
                           @RequestParam(name = "minCost", required = false) BigDecimal minCost,
                           @RequestParam(name = "maxCost", required = false) BigDecimal maxCost,
                           @RequestParam(name = "page", required = false) Integer page,
                           @RequestParam(name = "pageSize", required = false) Integer pageSize,
                           @RequestParam(name = "title", required = false) String title
    ) {
        ProductSearch search = new ProductSearch(minCost, maxCost, title);
        search.setPage(page);
        search.setPageSize(pageSize);

        Page<Product> productPage = productService.findAll(search);
        search.setTotalPages(productPage.getTotalPages());

        model.addAttribute("search", search);
        model.addAttribute("products", productPage.getContent());
        return "product/products";
    }

    @GetMapping("create")
    public String create(Model model) {

        model.addAttribute("product", new Product());
        return "product/form";
    }

    @GetMapping("update/{id}")
    public String update(Model model, @PathVariable String id) {

        model.addAttribute("product", productService.findById(Long.parseLong(id)));
        return "product/form";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable String id) {

        productService.delete(Long.valueOf(id));
        return "redirect:/product";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult bindingResult) {


        if (productService.hasSameProduct(product)) {
            bindingResult.rejectValue("title", "1", "Товар с такой стоимостью и названием уже существует");
        }

        if (bindingResult.hasErrors()) {
            return "product/form";
        }

        if (product.getId() == null) {
            productService.save(product);
        } else {
            productService.update(product);
        }
        return "redirect:/product";
    }
}
