package ch.wiss.kochbuch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipie_ingredient")
public class RecipieIngredient {

//TABLE ---------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private String amount; // z.B. "200g", "1 TL", "3 Stück"

//Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipie_id", nullable = false)
    private Recipie recipie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;


//Constructor ---------------------------------------------------------------------------------
    public RecipieIngredient() {}

    public RecipieIngredient(Recipie recipie, Ingredient ingredient, String amount) {
        this.recipie = recipie;
        this.ingredient = ingredient;
        this.amount = amount;
    }

//Getter & Setter---------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipie getRecipie() {
        return recipie;
    }

    public void setRecipie(Recipie recipie) { 
        this.recipie = recipie; 
    }

    public Ingredient getIngredient() {
         return ingredient; 
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getAmount() {
         return amount; 
    }

    public void setAmount(String amount) {
        this.amount = amount; 
    }
}
