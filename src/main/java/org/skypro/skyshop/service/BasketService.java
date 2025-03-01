package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Service
@SessionScope
public class BasketService {
    private final Map<UUID, Integer> basket;
    private final StorageService storageService;

    public BasketService(Map<UUID, Integer> basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isPresent() == false) throw new IllegalArgumentException("Такой продукт не найден!");
            basket.merge(id, 1, Integer::sum);
    }
 public UserBasket getUserBasket () {
     ArrayList <BasketItem> basketItems = new ArrayList<>();

     for (Map.Entry<UUID, Integer> entry : basket.entrySet()) {
         UUID id = entry.getKey();
         int quantity = entry.getValue();

         Product product = storageService.getProductById(id)
                 .orElseThrow(NoSuchElementException::new);
         basketItems.add(new BasketItem(product, quantity));
     }
     return new UserBasket(basketItems);
 }
}
