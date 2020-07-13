package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.persist.service.ProductService;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.OrderItem;
import ru.geekbrains.service.forms.ChangeQuantity;

@RequestMapping("/cart")
@Controller
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String cart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        return "cart";
    }

    @PostMapping
    public String setQty(ChangeQuantity changeQuantity) {
        ProductRepl productRepl = productService.findReplById(changeQuantity.getId()).orElseThrow(IllegalArgumentException::new);
        cartService.setQty(productRepl, changeQuantity.getQty());
        return "redirect:/cart";
    }

    @DeleteMapping
    public String deleteLineItem(OrderItem orderItem) {
        cartService.removeOrderItem(orderItem);
        return "redirect:/cart";
    }
}
