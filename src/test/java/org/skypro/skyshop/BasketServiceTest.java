package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductEsception;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {

    private BasketService basketService;
    private ProductBasket productBasket;
    private StorageService storageService;
    private UUID existingProductId;
    private UUID notExistingProductId;

    @BeforeEach
    void setUp() {
        productBasket = mock(ProductBasket.class);
        storageService = mock(StorageService.class);

        basketService = new BasketService(productBasket, storageService);

        existingProductId = UUID.randomUUID();
        notExistingProductId = UUID.randomUUID();

        when(storageService.getProductById(existingProductId)).thenReturn(Optional.of(new SimpleProduct("Пазлы", existingProductId, 457)));
        when(storageService.getProductById(notExistingProductId)).thenReturn(Optional.empty());
    }


    @Test
    void testAddNotExistingProductToBasket() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            basketService.addProductToBasket(notExistingProductId);
        });
        assertEquals("Продукт с ID " + notExistingProductId + " не найден", exception.getMessage());
    }

    @Test
    void testAddExistingProductToBasket() {
        basketService.addProductToBasket(existingProductId);
        verify(productBasket, times(1)).addProduct(existingProductId);
    }

    @Test
    void testEmptyUserBasket() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertTrue(userBasket.getBasketItems().isEmpty());
    }

    @Test
    void testUserBasketWithProducts() {
        Map<UUID, Integer> possiblebasket = new HashMap<>();
        possiblebasket.put(existingProductId, 1);
        when(productBasket.getProducts()).thenReturn(possiblebasket);

        UserBasket userBasket = basketService.getUserBasket();
        assertFalse(userBasket.getBasketItems().isEmpty());
        assertEquals(1, userBasket.getBasketItems().size());
        assertEquals("Пазлы", userBasket.getBasketItems().get(0).getProduct().getNameProduct());
    }

    @Test
    void testGetUserBasketWithNoSuchProductException() {
        UUID missigUUD = UUID.randomUUID();
        Map<UUID, Integer> possibleBasket = new HashMap<>();
        possibleBasket.put(missigUUD, 1);
        when(productBasket.getProducts()).thenReturn(possibleBasket);

        assertThrows(NoSuchProductEsception.class, () -> {
            basketService.getUserBasket();
        });
    }
}
