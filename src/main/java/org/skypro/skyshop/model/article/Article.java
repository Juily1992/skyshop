package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.exceptions.ArticleNotFoundException;
import org.skypro.skyshop.model.searchable.Searchable;

import java.util.UUID;


public class Article implements Searchable {
    private String title;
    private String description;
    private UUID id;

    public Article(String title, String description, UUID id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Название статьи: " + title + "\n" + "Текст статьи: " + description;
    }

    @Override
    public String getStringRepreseentation() {
        return "Название статьи: " + title + " " +
                "\n" + "Текст статьи: " + description;
    }

    @Override
    public String searchableName() {
        return title + " - " + description;
    }

    @Override
    public String typeContent() {
        return "ARTICLE";
    }

    @Override
    public UUID getId() {
        return id;
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
}