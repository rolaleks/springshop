package ru.geekbrains.persist.service.interdafaces;

import ru.geekbrains.persist.enity.User;

import java.util.List;
import java.util.Optional;

public interface UserServerInterface {

    public List<User> findAll() ;

    public void save(User user);

    public Optional<User> findById(long id) ;
}
