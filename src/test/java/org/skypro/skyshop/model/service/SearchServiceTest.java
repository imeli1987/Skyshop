package org.skypro.skyshop.model.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest{

    @Mock
    private StorageService mockStorageService;

    @InjectMocks
    private SearchService mockSearchService;

    @Test
    void testSearchWhenEmpty(){
        when( mockStorageService.getAllSearch() ).thenReturn( Collections.emptyList() );

        List<SearchResult> results = mockSearchService.search( "кофе" );

        assertTrue( results.isEmpty() );
    }

    @Test
    void testSearch(){
        Product testProduct1 = new DiscountedProduct( UUID.randomUUID(), "Чай", 300, 10 );
        Article testAarticle1 = new Article( UUID.randomUUID(), "Молоко", "2,5%" );
        Product testProduct2 = new SimpleProduct( UUID.randomUUID(), "Чай зелёный", 300 );
        Article testAarticle2 = new Article( UUID.randomUUID(), "Кофе растворимый", "Робуста" );

        when( mockStorageService.getAllSearch() ).thenReturn( List.of( testProduct1, testProduct2, testAarticle1, testAarticle2 ) );

        List<SearchResult> results = mockSearchService.search( "кофе" );
        List<SearchResult> results2 = mockSearchService.search( "Чай" );

        List<String> results3 = results2.stream().map( SearchResult::getName ).toList();

        assertTrue( results.isEmpty() );

        assertEquals( 2, results2.size() );

        assertTrue( results3.contains( "Чай" ) );
        assertTrue( results3.contains( "Чай зелёный" ) );

    }

}