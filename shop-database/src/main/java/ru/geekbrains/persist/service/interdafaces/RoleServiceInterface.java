package ru.geekbrains.persist.service.interdafaces;

import ru.geekbrains.persist.enity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleServiceInterface {

    public List<Role> findAll() ;

    public void save(Role role);

    public Optional<Role> findById(long id) ;
}
