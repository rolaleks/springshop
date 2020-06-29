package ru.geekbrains.persist.service.interdafaces;

import ru.geekbrains.persist.enity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {

    public List<Category> findAll() ;

    public void save(Category category);

    public Optional<Category> findById(long id) ;
}
