package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.exceptions.ArticleNotFoundException;
import org.skypro.skyshop.model.searchable.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private String title;
    private String description;
    private final UUID id = UUID.randomUUID();
    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getID () {
        return id.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "Название статьи: " + title + "\n" + "Текст статьи: " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article) {
            Article other = (Article) obj;
            return this.title.equals(other.title) && this.description.equals(other.description);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return title.hashCode() + description.hashCode();
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    @JsonIgnore
    public String searchableName() {
        return title + " - " + description;
    }

    @Override
    @JsonIgnore
    public String typeContent() {
        return "ARTICLE";
    }


    public static class BucketArticle {
        private Article[] articles;
        private int size;

        public BucketArticle() {
            this.articles = new Article[5];
        }

        public Searchable addArticles(Article article) {
            if (size >= articles.length) {
                System.out.println("Нельзя добавить товар, корзина переполнена");
            }
            this.articles[size++] = article;
            return article;
        }

        public void printArticle() throws ArticleNotFoundException {
            for (int i = 0; i < size; i++) {
                Article article = articles[i];
                if (article != null) {
                    System.out.println(article.toString());
                } else if (article == null) {
                    System.out.println(article);
                }
            }
        }

    }
}