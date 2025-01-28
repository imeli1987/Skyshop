package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product{
    static int PRICE = 400;

    public FixPriceProduct( UUID id, String name) {
        super(id, name);
    }

    public int getPrice() {
        return PRICE;
    }

    public boolean isSpecial(){
        return true;
    }

    @Override
    public String toString() {
        return getName() +
                " с фиксированной ценой: " +
                getPrice();
    }
}
