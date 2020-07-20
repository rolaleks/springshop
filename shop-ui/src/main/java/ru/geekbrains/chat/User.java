package ru.geekbrains.chat;

import java.security.Principal;

public class User implements java.security.Principal {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
