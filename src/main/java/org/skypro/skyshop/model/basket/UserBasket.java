package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket{
    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket( List<BasketItem> basketItems){
        this.basketItems = basketItems;
        this.total = getTotalCost();
    }

    public List<BasketItem> getBasketItems(){
        return basketItems;
    }

    public int getTotalCost(){
        return basketItems.stream()
                .mapToInt( s -> s.getProduct().getPrice() * s.getCount())
                .sum();
    }
}





























