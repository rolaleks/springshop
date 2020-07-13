package ru.geekbrains.service.forms;

import java.io.Serializable;

public class ChangeQuantity {

    private int id;

    private int qty;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
