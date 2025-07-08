package ch.wiss.kochbuch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {

//TABLE ---------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameIngredient", nullable = false)
    private String name;

//Relations
@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonBackReference
private List<RecipieIngredient> recipieIngredients;

//Constructor ------------------------------------------------------------------------
public Ingredient() {}

public Ingredient(String name) {
    this.name = name;
}

// Getter & Setter -----------------------------------------------------------------
public Long getId() { 
    return id; 
}

public void setId(Long id) { 
    this.id = id; 
}

public String getName() { 
    return name; 
}

public void setName(String name) {
    this.name = name; 
}

public List<RecipieIngredient> getRecipieIngredients() {
     return recipieIngredients; 
}

public void setRecipieIngredients(List<RecipieIngredient> recipieIngredients) {
    this.recipieIngredients = recipieIngredients;
}

}
