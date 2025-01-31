package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Service
public class ProductBasket{
    private final Map<UUID, Integer> productBasket;

    public ProductBasket( Map<UUID, Integer> productBasket){
        this.productBasket = new HashMap<>();
    }

    public void addProduct( UUID id){
        productBasket.put(id, productBasket.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getProductBasket(){
        return Collections.unmodifiableMap(productBasket);
    }
}




























