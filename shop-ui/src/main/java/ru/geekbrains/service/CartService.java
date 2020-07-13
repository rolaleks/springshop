package ru.geekbrains.service;

import ru.geekbrains.persist.repl.ProductRepl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface CartService extends Serializable {

    void addQty(ProductRepl product);

    void setQty(ProductRepl product, int qty);

    void removeQty(ProductRepl product);

    OrderItem getOrderItem(ProductRepl product);

    void setOrderItem(OrderItem item);

    void removeOrderItem(OrderItem item);

    List<OrderItem> getItems();
}
