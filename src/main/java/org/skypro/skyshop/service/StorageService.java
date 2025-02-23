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
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        populateData();
    }

    public Collection<Article> getArticles() {
        return articles.values();
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    private void populateData() {
        testData(); // Вызываем метод для заполнения тестовыми данными.
    }

    private void addProduct(Product product) {
        UUID productId = UUID.randomUUID();
        products.put(productId, product);
    }

    private void addArticle(Article article) {
        UUID articleId = UUID.randomUUID();
        articles.put(articleId, article);
    }
    private void testData() {
        Collection<Product> products = new ArrayList<>();
        products.add(new SimpleProduct("Арбуз", 4, 457));
        products.add(new SimpleProduct("Гирлянда Гирлянда", 10, 987));
        products.add(new DiscountedProduct("Гирлянда, Гирлянда, Гирлянда", 500, 50, 653));
        products.add(new DiscountedProduct("Книга", 400, 6, 76));
        products.add( new FixPriceProduct("Апельсины", 64));
        products.add(new FixPriceProduct("Яблоки", 376));

        products.forEach(this::addProduct);

        Collection <Article> articles = new ArrayList<>();
        articles.add(new Article("федорино горе", "Сказка"));
        articles.add(new Article("Места обитания китов", "Рассматриваются наиболее известные места обитания ... "));

        articles.forEach(this::addArticle);
    }
    public Collection<Searchable> getAllSearchables() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(products.values());
        searchables.addAll(articles.values());
        return searchables;
    }

}
