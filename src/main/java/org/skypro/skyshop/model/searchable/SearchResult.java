package org.skypro.skyshop.model.searchable;

import java.util.UUID;

public class SearchResult {
    private final String name;
    private final String id;
    private final String typeContent;

    public SearchResult(String name, String id, String typeContent, Searchable searchable, int relevance, int getRelevance) {
        this.name = name;
        this.id = id;
        this.typeContent = typeContent;

    }

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.typeContent = contentType;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getTypeContent() {
        return typeContent;
    }


    public static SearchResult fromSearchable(Searchable searchable) {
        String id = UUID.randomUUID().toString(); // Генерация случайного ID для результата поиска
        String name = searchable.searchableName();
        String contentType = searchable.getClass().getSimpleName(); // Получаем имя класса Searchable

        return new SearchResult(id, name, contentType);
    }
}