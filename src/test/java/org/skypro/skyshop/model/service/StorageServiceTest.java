package org.skypro.skyshop.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StorageServiceTest{

    private StorageService mockStorageService;
    private StorageService testStorageService;


    @BeforeEach
    void setUp(){
        mockStorageService = mock( StorageService.class );
        testStorageService = new StorageService();
    }

    @Test
    void testGetProductById(){
        UUID id = UUID.randomUUID();
        Product testProduct = new SimpleProduct( id, "Test Product", 100 );

        when( mockStorageService.getProductById( id ) ).thenReturn( Optional.of( testProduct ) );

        Optional<Product> result = mockStorageService.getProductById( id );

        assertTrue( result.isPresent() );
        assertEquals( testProduct, result.get() );
    }

    @Test
    void testGetProductNonExistentId(){
        UUID nonExistentId = UUID.randomUUID();

        Optional<Product> result = testStorageService.getProductById( nonExistentId );

        assertFalse( result.isPresent() );
    }

    @Test
    void testGetAllProducts(){
        Collection<Product> products = testStorageService.getAllProducts();

        assertEquals( 11, products.size() );
    }

    @Test
    void testGetAllArticles(){
        Collection<Article> articles = testStorageService.getAllArticles();

        assertEquals( 8, articles.size() );
    }

    @Test
    void TestGetAllSearch(){
        List<Searchable> products = testStorageService.getAllSearch();

        assertEquals( 19, products.size() );
    }
}