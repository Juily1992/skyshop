package org.skypro.skyshop.model.basket;

import java.util.ArrayList;
import java.util.Objects;


public final class UserBasket {
    private final int total;
    private final ArrayList <BasketItem> basketItems;

        public UserBasket(ArrayList<BasketItem> basketItems) {
        this.basketItems = Objects.requireNonNull(basketItems);
        this.total = basketItems.stream()
                .map(item -> item.getProduct().getPrice() * item.getAmount())
                .reduce(0, Integer :: sum);
    }

    public int getTotal() {
        return total;
    }
    public ArrayList <BasketItem> getBasketItems() {
        return basketItems;
       }


}
