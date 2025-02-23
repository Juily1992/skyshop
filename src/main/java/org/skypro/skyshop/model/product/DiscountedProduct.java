package org.skypro.skyshop.model.product;

public class DiscountedProduct extends Product {
    protected int basePrice;
    public int discount;

    public DiscountedProduct(String nameProduct, int id, int basePrice, int discount) {
        super(nameProduct, id);
        try {
            if (basePrice <= 0) {
                throw new IllegalArgumentException("Базовая цена не может быть меньше 0 >");
            } else if (discount < 0) {
                throw new IllegalArgumentException("Скидка не может быть отрицательной");
            } else if (discount > 100) {
                throw new IllegalArgumentException("Скидка не может быть больше 100");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        this.basePrice = basePrice;
        this.discount = discount;

    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getDiscount() {
        return discount;
    }


    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getPrice() {
        return getBasePrice() - ((getDiscount() * getBasePrice()) / 100);
    }

    @Override
    public String toString() {
        return "< " + getNameProduct() + " > : < " + getPrice() + " > ( < " + getDiscount() + "% >)";
    }
}