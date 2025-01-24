package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product{

    final int price;

    public SimpleProduct( UUID id, String name, int price){
        super(id, name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() +
                ": " +
                price;
    }
}
