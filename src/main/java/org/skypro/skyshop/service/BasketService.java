package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductEsception;
import org.skypro.skyshop.model.exceptions.ProductNotFoundException;
import org.skypro.skyshop.model.exceptions.ShopError;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.stream.Collectors;

@Service
@SessionScope
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> product = storageService.getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productQuantities = productBasket.getProducts();

        ArrayList<BasketItem> basketItems = productQuantities.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    Integer quantity = entry.getValue();
                    Product product = storageService.getProducts().stream()
                            .filter(p -> p.getId().equals(id))
                            .findFirst()
                            .orElse(null);

                    if (product == null) {
                        throw new NoSuchProductEsception("Запрос не может быть пустым");
                     }
                    return new BasketItem(product, quantity);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));

        return new UserBasket(basketItems);
    }


}
