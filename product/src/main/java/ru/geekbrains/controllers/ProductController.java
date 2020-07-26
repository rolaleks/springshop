package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.service.interdafaces.ProductServerInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductServerInterface productService;

    @Autowired
    public ProductController(ProductServerInterface productService) {
        this.productService = productService;
    }

    @GetMapping("/price/{productId}")
    @ResponseBody
    public String getPrice(@PathVariable("productId") Long pictureId,
                               HttpServletResponse response) throws IOException {

        Optional<ProductRepl> picture = productService.findReplById(pictureId);

        return picture.orElseThrow().getCost().toString();
    }
}
