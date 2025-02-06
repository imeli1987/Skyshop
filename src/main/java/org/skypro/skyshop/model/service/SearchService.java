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
        return storage.getAllSearch().stream()                              // Весь список для поиска
                .filter(s -> s.getName().contains(query))       //  отфильтровываем по поисковому запросу
                .map(SearchResult::fromSearchable)                         //  и формируем результат
                .collect( Collectors.toCollection( ArrayList::new ) );      //  в список
    }
}
