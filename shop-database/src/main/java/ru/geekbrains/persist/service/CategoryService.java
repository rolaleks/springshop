package ru.geekbrains.persist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.enity.Category;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.service.interdafaces.CategoryServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface {

    private CategoryRepository repository;


    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void save(Category category) {
        repository.save(category);
    }

    @Transactional(readOnly = true)
    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }

}
