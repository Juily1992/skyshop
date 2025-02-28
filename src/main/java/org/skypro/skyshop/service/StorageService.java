package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.searchable.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        populateData();
        System.out.println(productStorage.values());
    }

    public Collection<Article> getArticles() {
        return articleStorage.values();
    }

    public Collection<Product> getProducts() {
        return productStorage.values();
    }

    private void populateData() {
        testData();
    }

    private void addProduct(Product product) {
        UUID productId = UUID.randomUUID();
        productStorage.put(productId, product);
    }

    private void addArticle(Article article) {
        UUID articleId = UUID.randomUUID();
        articleStorage.put(articleId, article);
    }

    private void testData() {
        Collection<Product> products = new ArrayList<>();
        products.add(new SimpleProduct("Пазлы", UUID.randomUUID(), 457));
        products.add(new SimpleProduct("Гирлянда Гирлянда", UUID.randomUUID(), 987));
        products.add(new DiscountedProduct("Гирлянда, Гирлянда, Гирлянда", UUID.randomUUID(), 50, 3));
        products.add(new DiscountedProduct("Книга", UUID.randomUUID(), 6, 76));
        products.add(new FixPriceProduct("Мороженое", UUID.randomUUID()));
        products.add(new FixPriceProduct("Яблоки", UUID.randomUUID()));

        products.forEach(this::addProduct);

        Collection<Article> articles = new ArrayList<>();
        articles.add(new Article("федорино горе", "Сказка", UUID.randomUUID()));
        articles.add(new Article("Места обитания китов", "Рассматриваются наиболее известные места обитания ... ", UUID.randomUUID()));

        articles.forEach(this::addArticle);
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(productStorage.values());
        searchables.addAll(articleStorage.values());
        return searchables;
    }

}
