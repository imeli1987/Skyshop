package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Product implements Searchable{

    private final String name;
    private final UUID id;

    public Product(UUID id, String name) {
        this.id = id;
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.name = name;
    }

    public boolean isSpecial(){
        return false;
    }
    public int getPrice() {
        return 0;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() { return name; }

    @JsonIgnore
    @Override
    public String getContentType() { return "PRODUCT"; }

    @Override
    public String getName() { return name; }

    @Override
    public boolean equals( Object o ){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals( name, product.name );
    }

    @Override
    public int hashCode(){
        return Objects.hashCode( name );
    }
}
