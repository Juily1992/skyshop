package org.skypro.skyshop.service;

import org.skypro.skyshop.model.searchable.SearchResult;
import org.skypro.skyshop.model.searchable.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {

        this.storageService = storageService;
    }

    public List<SearchResult> search(String searchTerm) {
        Collection<Searchable> searchables = storageService.getAllSearchables();

        return searchables.stream()
                .map(SearchResult::fromSearchable)
                .filter(searchResult -> searchResult.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    private int calculateRelevance(Searchable searchable, String searchTerm) {
        String searchableName = searchable.searchableName().toLowerCase();
        String term = searchTerm.toLowerCase();

        if (searchableName.contains(term)) {
            return 1;
        } else {
            return 0;
        }
    }


}
