package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.searchable.Searchable;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchServiceTest {
    @Spy
    private StorageService storageService;
    private UUID existingProductId;
    private UUID nonExistingProductId;
    private UUID nonMatchingProductId;
    private UUID existingArticleId;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        existingProductId = UUID.randomUUID();
        nonExistingProductId = UUID.randomUUID();
        nonMatchingProductId = UUID.randomUUID();
        existingArticleId = UUID.randomUUID();

        Product existingProduct = new SimpleProduct("Пазлы", existingProductId, 457);
        Product noMatchingProduct = new SimpleProduct("Гирлянда, Гирлянда", nonMatchingProductId, 72);

        when(storageService.getProductById(existingProductId)).thenReturn(Optional.of(existingProduct));
        when(storageService.getProductById(nonExistingProductId)).thenReturn(Optional.empty());
        when(storageService.getProductById(nonMatchingProductId)).thenReturn(Optional.of(noMatchingProduct));
        when(storageService.getProducts()).thenReturn(List.of(existingProduct, noMatchingProduct));
        when(storageService.getArticles()).thenReturn(List.of(new Article("федорино горе", "Сказка", existingArticleId)));
        when(storageService.getAllSearchables()).thenReturn(List.of(existingProduct, noMatchingProduct, new Article("федорино горе", "Сказка", existingArticleId)));
    }

    @Test
    void testGetProductById_ExistingProduct() {
        Optional<Product> result = storageService.getProductById(existingProductId);
        assertTrue(result.isPresent());
        assertEquals("Пазлы", result.get().getNameProduct());
    }

    @Test
    void testGetProductById_nonExistingProduct() {
        Optional<Product> result = storageService.getProductById(nonExistingProductId);
        assertFalse(result.isPresent());

    }

    @Test
    void testGetProductById_nonMatchingProduct() {
        Optional<Product> result = storageService.getProductById(nonMatchingProductId);
        assertTrue(result.isPresent());
        assertNotEquals("Пазлы", result.get().getNameProduct());
    }

    @Test
    void testGetProducts() {
        Collection<Product> products = storageService.getProducts();
        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
    }

    @Test
    void testGetArticles() {
        Collection<Article> articles = storageService.getArticles();
        assertFalse(articles.isEmpty());
        assertEquals(1, articles.size());
    }

    @Test
    void testGetAllSearchables() {
        Collection<Searchable> searchables = storageService.getAllSearchables();
        assertFalse(searchables.isEmpty());
        assertEquals(3, searchables.size());
    }


    @Test
    void testGetProductsById_VerifiedMockito() {
        storageService.getProductById(existingProductId);
        verify(storageService, times(1)).getProductById(existingProductId);
    }
}
