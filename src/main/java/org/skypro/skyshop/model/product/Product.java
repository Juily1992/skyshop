package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.searchable.Searchable;

import java.util.Objects;
import java.util.UUID;


public abstract class Product implements Searchable {
    final String nameProduct;
    private UUID id;

    public Product(String nameProduct, UUID id) throws IllegalArgumentException {
        try {
            if (nameProduct == null || nameProduct.isBlank() || nameProduct.equalsIgnoreCase("null")) {
                throw new IllegalArgumentException("Продукт не может быть null или пуст!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        this.nameProduct = nameProduct;
        this.id = UUID.randomUUID();
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public abstract int getPrice();

    public abstract String toString();

    public abstract boolean isSpecial();

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public String searchableName() {
        return getNameProduct() + typeContent();
    }

    @Override
    @JsonIgnore
    public String typeContent() {
        return ". PRODUCT";
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
