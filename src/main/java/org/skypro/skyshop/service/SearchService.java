package org.skypro.skyshop.service;

import org.skypro.skyshop.model.searchable.SearchResult;
import org.skypro.skyshop.model.searchable.Searchable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {

        this.storageService = storageService;
    }


    public List<SearchResult> search(String pattern) {
        Collection<Searchable> searchables = storageService.getAllSearchables();

        return searchables.stream()
                .filter(searchable -> calculateRelevance(searchable, pattern) > 0)
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

    private int calculateRelevance(Searchable searchable, String pattern) {
        String searchableName = searchable.getStringRepreseentation().toLowerCase();
        String term = pattern.toLowerCase();

        if (searchableName.contains(term)) {
            return 1;
        } else {
            return 0;
        }
    }


}
