package ru.geekbrains.persist.repl;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.persist.enity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductRepl productRepl);

    @InheritInverseConfiguration
    ProductRepl fromProduct(Product product);

    List<Product> toProductList(List<ProductRepl> productRepls);

    List<ProductRepl> fromProductList(List<Product> products);
}
