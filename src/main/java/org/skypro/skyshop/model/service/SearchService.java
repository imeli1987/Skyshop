package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService{
    private final StorageService storage;

    public SearchService( StorageService storage ){
        this.storage = storage;
    }

    public List<SearchResult> search( String query ){
        return storage.getAllSearch().stream()
                .filter(s -> s.getName().contains(query))
                .map(SearchResult::fromSearchable)
                .collect( Collectors.toCollection( ArrayList::new ) );
    }
}














