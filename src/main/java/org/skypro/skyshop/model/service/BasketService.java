package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService{
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService( ProductBasket productBasket, StorageService storageService ){
        this.productBasket = productBasket;
        this.storageService = storageService;
    }
    public void addProduct( UUID id) throws NoSuchFieldException{
        if ( !storageService.getProductById( id ).isPresent()){
            throw new NoSuchProductException("Товар не найден");
        }
        productBasket.addProduct( id );
    }

    public UserBasket getUserBasket(){
        Map<UUID, Integer> basketItems = productBasket.getProductBasket();
        List<BasketItem> items = basketItems.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    int count = entry.getValue();
                    Product product = storageService.getProductById( id )
                            .orElseThrow(()->
                                    new NoSuchProductException("Товар не найден"));
                    return new BasketItem(product, count);
                        })
                .collect(Collectors.toList());
        return new UserBasket(items);
    }
}
































