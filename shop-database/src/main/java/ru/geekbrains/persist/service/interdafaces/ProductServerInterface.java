package ru.geekbrains.persist.service.interdafaces;

import org.springframework.data.domain.Page;
import ru.geekbrains.persist.enity.Product;
import ru.geekbrains.persist.repl.ProductRepl;
import ru.geekbrains.search.ProductSearch;

import java.util.List;
import java.util.Optional;

public interface ProductServerInterface {

    public List<ProductRepl> findAll();

    public Page<Product> findAll(ProductSearch search);

    public void save(ProductRepl product);

    public Optional<Product> findById(long id);

    public Optional<ProductRepl> findReplById(long id);

    public void update(ProductRepl product);

    public void delete(Long id);

    public boolean hasSameProduct(ProductRepl product);

}
