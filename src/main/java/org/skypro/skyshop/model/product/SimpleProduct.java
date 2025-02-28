package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.exceptions.ProductNotFoundException;

import java.util.UUID;

public class SimpleProduct extends Product {
    final int price;

    public SimpleProduct(String nameProduct, UUID id, int price) {
        super(nameProduct, id);
        try {
            if (price <= 0) {
                throw new IllegalArgumentException("Цена не может быть меньше 0 или = 0");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        this.price = price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return "< " + getNameProduct() + " > : < " + price + " >";
    }

    @Override
    public int getPrice() {
        return price;
    }
}