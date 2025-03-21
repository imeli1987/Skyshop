package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable{

    private final String name;
    private final String text;
    private final UUID id;

    public Article(UUID id, String name, String text) {
        this.name = name;
        this.text = text;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {return name + " " + text;}

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() { return name + ": " + text;}

    @Override
    public boolean equals( Object o ){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals( name, article.name );
    }

    @Override
    public int hashCode(){
        return Objects.hash( name );
    }
}
