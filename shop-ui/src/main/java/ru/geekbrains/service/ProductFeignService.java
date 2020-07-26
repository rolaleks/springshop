package ru.geekbrains.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service", url = "http://localhost:8080/product")
public interface ProductFeignService {

    @GetMapping(value = "/price/{productId}")
    String getPrice(@PathVariable("productId") Long productId);
}