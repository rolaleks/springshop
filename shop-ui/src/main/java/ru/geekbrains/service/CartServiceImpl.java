package ru.geekbrains.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.repl.ProductRepl;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {


    private static final long serialVersionUID = -5644453081317199880L;

    private ArrayList<OrderItem> orderItems;


    public CartServiceImpl() {
        this.orderItems = new ArrayList<>();
    }

    @Override
    public void addQty(ProductRepl product) {
        OrderItem item = getOrderItem(product);
        item.setQuantity(item.getQuantity() + 1);

        setOrderItem(item);
    }

    @Override
    public void setQty(ProductRepl product, int qty) {
        OrderItem item = getOrderItem(product);
        item.setQuantity(qty);
        if (item.getQuantity() <= 0) {
            removeOrderItem(item);
        } else {
            setOrderItem(item);
        }
    }

    @Override
    public OrderItem getOrderItem(ProductRepl product) {
        OrderItem item = new OrderItem(product, 0);
        int index = orderItems.indexOf(item);
        if (index != -1) {
            return orderItems.get(index);
        }

        return item;
    }

    @Override
    public void setOrderItem(OrderItem item) {
        int index = orderItems.indexOf(item);
        if (index != -1) {
            orderItems.set(index, item);
            return;
        }

        orderItems.add(item);
    }

    @Override
    public void removeOrderItem(OrderItem item) {
        orderItems.remove(item);
    }

    @Override
    public void removeQty(ProductRepl product) {
        OrderItem item = getOrderItem(product);
        if (item.getQuantity() > 0) {
            item.setQuantity(item.getQuantity() - 1);
            setOrderItem(item);
        } else {
            removeOrderItem(item);
        }
    }


    @Override
    public List<OrderItem> getItems() {
        return orderItems;
    }
}
