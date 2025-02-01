package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.exceptions.ShopError;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
public class ShopController{
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    public ShopController( StorageService storageService, SearchService searchService, BasketService basketService ) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductException( NoSuchProductException e){
        return ResponseEntity.status(404).body(new ShopError( "404", e.getMessage() ));
    }

    @GetMapping("/products")
    public Collection<Product> getAllProduct() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticle() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public List<SearchResult> getSearch( @RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProductsToBasket(@PathVariable("id") UUID id) throws NoSuchFieldException{
        if (id == null) {
            throw new NoSuchProductException("Нет такого продукта");
        }
        basketService.addProduct( id );
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket(){
        return basketService.getUserBasket();
    };
}















