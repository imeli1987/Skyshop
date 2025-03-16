package org.skypro.skyshop.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StorageServiceTest{

    private StorageService testStorageService;

    @BeforeEach
    void setUp(){
        testStorageService = new StorageService();
    }

    @Test
    void testAddProduct(){
        Collection<Product> emptyResult = testStorageService.getAllProducts();

        assertTrue( emptyResult.isEmpty() );

        UUID id = UUID.randomUUID();
        Product testProduct = new SimpleProduct( id, "Test Product", 100 );

        testStorageService.addProduct( testProduct );

        assertEquals( 1, emptyResult.size() );
        assertEquals( testProduct, emptyResult.toArray()[0]);
    }

    @Test
    void testGetProductById(){
        UUID id = UUID.randomUUID();
        Product testProduct = new SimpleProduct( id, "Test Product", 100 );

        testStorageService.addProduct( testProduct );
        Optional<Product> result = testStorageService.getProductById( id );

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

        assertTrue( products.isEmpty() );

        testStorageService.addProduct( new SimpleProduct( UUID.randomUUID(), "Test Product", 100 ) );
        testStorageService.addProduct( new DiscountedProduct( UUID.randomUUID(), "Test Product", 100, 10 ) );
        testStorageService.addProduct( new FixPriceProduct( UUID.randomUUID(), "Test Product") );

        assertEquals( 3, products.size() );
    }

    @Test
    void testGetAllArticles(){
        Collection<Article> articles = testStorageService.getAllArticles();

        assertTrue( articles.isEmpty() );

        testStorageService.addArticle( new Article( UUID.randomUUID(), "Test Product", "0000000000" ) );
        testStorageService.addArticle( new Article( UUID.randomUUID(), "Test Product", "333333333" ) );

        assertEquals( 2, articles.size() );
    }

    @Test
    void TestGetAllSearch(){
        List<Searchable> result = testStorageService.getAllSearch();

        assertTrue( result.isEmpty() );

        testStorageService.addArticle( new Article( UUID.randomUUID(), "Test Product", "0000000000" ) );
        testStorageService.addArticle( new Article( UUID.randomUUID(), "Test Product", "333333333" ) );
        testStorageService.addProduct( new SimpleProduct( UUID.randomUUID(), "Test Product", 100 ) );
        testStorageService.addProduct( new DiscountedProduct( UUID.randomUUID(), "Test Product", 100, 10 ) );
        testStorageService.addProduct( new FixPriceProduct( UUID.randomUUID(), "Test Product") );

        int result2 = testStorageService.getAllSearch().size();

        assertEquals( 5, result2 );
    }
}