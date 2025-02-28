package org.skypro.skyshop.model.searchable;

import java.util.UUID;

public class SearchResult {
    private final String name;
    private final String id;
    private final String typeContent;

    public SearchResult(String id, String name, String contentType) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.typeContent = contentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return typeContent;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        String id = UUID.randomUUID().toString();
        String name = searchable.searchableName();
        String contentType = searchable.getClass().getSimpleName();

        return new SearchResult(id, name, contentType);
    }
}