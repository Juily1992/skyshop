package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.searchable.Searchable;

import java.util.Objects;


public abstract class Product implements Searchable {
    final String nameProduct;
    private int id;

    public Product(String nameProduct, int id) throws IllegalArgumentException {
        try {
            if (nameProduct == null || nameProduct.isBlank() || nameProduct.equalsIgnoreCase("null")) {
                throw new IllegalArgumentException("Продукт не может быть null или пуст!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        this.nameProduct = nameProduct;
        this.id = id;
    }
 public int getId () { return id; }
    public String getNameProduct() {
        return nameProduct;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getName() {

        return nameProduct;
    }

    @Override
    @JsonIgnore
    public String searchableName() {
        return nameProduct;
    }

    @Override
    @JsonIgnore
    public String typeContent() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameProduct);
    }

}
