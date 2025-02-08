package org.skypro.skyshop.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest{

    @Mock
    private ProductBasket mockProductBasket;

    @Mock
    private StorageService mockStorageService;

    @InjectMocks
    private BasketService testBasketService;

    UUID testId;
    Product testProduct;

    @BeforeEach
    void setUp(){
        testId = UUID.randomUUID();
        testProduct = new SimpleProduct( testId, "test", 200 );
    }

    @Test
    void testAddNonExistentProduct() throws NoSuchFieldException{

        when( mockStorageService.getProductById( testId ) )
                .thenReturn( Optional.empty() );

        assertThrows( NoSuchProductException.class, () -> testBasketService.addProduct( testId ) );
    }

    @Test
    void testAddExistingProduct() throws NoSuchFieldException{

        when( mockStorageService.getProductById( testId ) )
                .thenReturn( Optional.of( testProduct ) );

        testBasketService.addProduct( testId );

        verify( mockProductBasket, times( 1 ) ).addProduct( testId );
    }

    @Test
    void testGetEmptyUserBasket(){

        when( mockProductBasket.getProductBasket() ).thenReturn( new HashMap<>() );

        UserBasket testUserBasket = testBasketService.getUserBasket();

        assertTrue( testUserBasket.getBasketItems().isEmpty() );
    }

    @Test
    void testGetUserBasketWithProducts(){

        Map<UUID, Integer> testBasketItems = new HashMap<>();
        testBasketItems.put( testId, 2 );

        when( mockProductBasket.getProductBasket() ).thenReturn( testBasketItems );
        when( mockStorageService.getProductById( testId ) ).thenReturn( Optional.of( testProduct ) );

        UserBasket testUserBasket = testBasketService.getUserBasket();
        BasketItem testItem = testUserBasket.getBasketItems().get( 0 );

        assertEquals( 1, testBasketItems.size() );
        assertEquals( testProduct, testItem.getProduct() );
        assertEquals( 2, testItem.getCount() );
    }
}