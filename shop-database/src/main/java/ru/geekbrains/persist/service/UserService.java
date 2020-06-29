package ru.geekbrains.persist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.enity.User;
import ru.geekbrains.persist.repo.UserRepository;
import ru.geekbrains.persist.service.interdafaces.UserServerInterface;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServerInterface {

    private UserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void save(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

}
