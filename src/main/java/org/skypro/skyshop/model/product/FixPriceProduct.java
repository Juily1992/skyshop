package org.skypro.skyshop.model.product;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 10;

    public FixPriceProduct(String nameProduct, int id) {
        super(nameProduct, id);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "< " + getNameProduct() + " > : < Фиксированная цена: " + FIXED_PRICE + " >";
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

}