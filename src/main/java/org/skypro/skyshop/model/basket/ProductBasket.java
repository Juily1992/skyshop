package org.skypro.skyshop.model.basket;

import java.util.*;

public class ProductBasket {
    private final Map<UUID, Integer> basket;
    public ProductBasket() {
        this.basket = new HashMap<>();
    }
    public void addProduct(UUID id) {
        if (basket.containsKey(id)) {
            basket.put(id, basket.get(id) + 1);
        } else {
            basket.put(id, 1);
        }
    }

    public Map <UUID, Integer> getProductBasket(UUID id) {
        return Collections.unmodifiableMap(basket);
    }
}
