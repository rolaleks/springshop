package ru.geekbrains.controllers;

public class NotFoundException extends RuntimeException {



    public NotFoundException(String err) {
        super(err);
    }

    public NotFoundException() {


    }
}
