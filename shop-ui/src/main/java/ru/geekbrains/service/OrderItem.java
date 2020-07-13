package ru.geekbrains.service;

import ru.geekbrains.persist.repl.ProductRepl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 6727639057928801473L;

    private ProductRepl product;

    private Integer quantity;

    public OrderItem(ProductRepl product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItem() {
    }


    public ProductRepl getProduct() {
        return product;
    }

    public void setProduct(ProductRepl product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return product.getCost().multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return product.getId().equals(orderItem.product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId());
    }

}
