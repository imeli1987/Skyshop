package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService{
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService(){
        this.storageProduct = new HashMap<>();
        this.storageArticle = new HashMap<>();
        addProduct();
        addArticle();
    }

    public Optional<Product> getProductById(UUID id) {
        if ( id == null){
            throw new NoSuchProductException( "Нет такого продукта" );
        }
        return Optional.ofNullable(storageProduct.get(id));
    }

    public Collection<Product> getAllProducts(){
        return storageProduct.values();
    }

    public Collection<Article> getAllArticles(){
        return storageArticle.values();
    }

    public List<Searchable> getAllSearch(){
        ArrayList<Searchable> list = new ArrayList<>();
        for (Map.Entry<UUID, Product> entry : storageProduct.entrySet() ){
            list.add(entry.getValue());
        }
        for( Map.Entry<UUID, Article> entry : storageArticle.entrySet() ){
            list.add(entry.getValue());
        }
        return list;
    }

    private void addProduct(){
        Product product = new DiscountedProduct(UUID.randomUUID(),"Арабика", 500, 10);
        Product product1 = new SimpleProduct(UUID.randomUUID(),"Кофе в зёрнах", 600);
        Product product2 = new FixPriceProduct(UUID.randomUUID(),"Чай зелёный");
        Product product3 = new DiscountedProduct(UUID.randomUUID(),"Кофе", 50, 20);
        Product product4 = new SimpleProduct(UUID.randomUUID(),"Кофе молотый", 250);
        Product product5 = new DiscountedProduct(UUID.randomUUID(),"Ананас", 800, 20);
        Product product6 = new SimpleProduct(UUID.randomUUID(),"Чай", 500);
        Product product7 = new SimpleProduct(UUID.randomUUID(),"Чай", 500);
        Product product8 = new DiscountedProduct(UUID.randomUUID(),"Чай", 300, 60);
        Product product9 = new DiscountedProduct(UUID.randomUUID(),"Кофе", 300, 50);
        Product product10 = new DiscountedProduct(UUID.randomUUID(),"Чай", 300, 40);

        storageProduct.put(product.getId(), product);
        storageProduct.put(product1.getId(), product1);
        storageProduct.put(product2.getId(), product2);
        storageProduct.put(product3.getId(), product3);
        storageProduct.put(product4.getId(), product4);
        storageProduct.put(product5.getId(), product5);
        storageProduct.put(product6.getId(), product6);
        storageProduct.put(product7.getId(), product7);
        storageProduct.put(product8.getId(), product8);
        storageProduct.put(product9.getId(), product9);
        storageProduct.put(product10.getId(), product10);
    }
    private void addArticle(){
        Article article1 = new Article(UUID.randomUUID(),"Сахар", "сладкий");
        Article article2 = new Article(UUID.randomUUID(),"Молоко", "2,5 %");
        Article article3 = new Article(UUID.randomUUID(),"Кофе растворимый", "Кофе Робуста");
        Article article4 = new Article(UUID.randomUUID(),"Кофе черный", "Кофе арабика, производится из зёрен кофе сорта \"Арабика\". 100% Кофе");
        Article article5 = new Article(UUID.randomUUID(),"Кофе робуста", "Робуста");
        Article article6 = new Article(UUID.randomUUID(),"Чай зелёный", "Чай зелёный. Чай в пакетиках");
        Article article7 = new Article(UUID.randomUUID(),"Чай чёрный", "Чай зелёный. Чай в пакетиках");
        Article article8 = new Article(UUID.randomUUID(),"Чай растворимый", "Чай черный");

        storageArticle.put(article1.getId(),article1);
        storageArticle.put(article2.getId(),article2);
        storageArticle.put(article3.getId(),article3);
        storageArticle.put(article4.getId(),article4);
        storageArticle.put(article5.getId(),article5);
        storageArticle.put(article6.getId(),article6);
        storageArticle.put(article7.getId(),article7);
        storageArticle.put(article8.getId(),article8);
    }

}


















