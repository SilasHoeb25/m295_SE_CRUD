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
@Table(name = "recipie")
public class Recipie {

//TABLE ---------------------------------------------------------------------------------------
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nameRecipie", nullable = false)
	private String name;

    @Column(name = "instructionRecipie", columnDefinition = "TEXT")
    private String instruction;
    
    @Column(name = "timeToPrep", columnDefinition = "INT")
    private int timeToPrep;

    //Relations
    @OneToMany(mappedBy = "recipie", cascade = CascadeType.ALL, orphanRemoval = true) //Bei verwendung von JPA | CascadeType.ALL: Alle Zutaten werden mit dem Rezept gespeichert | orphanRemoval = true: damit gelöschte Zutaten auch aus der DB verschwinden
    @JsonBackReference
    private List<RecipieIngredient> recipieIngredients;

//CONSTRUCTOR ----------------------------------------------------------------------------------
    public Recipie() {}

    public Recipie(String name, String instruction, int time) {
        this.name = name;
        this.instruction = instruction;
        this.timeToPrep = time;
    }
    
// Getter & Setter -----------------------------------------------------------------------------
    public long getId() {
         return id; 
    }
    public void setId(long id) {
         this.id = id;
    }

    public String getName() {
         return name; 
    }
    
    public void setName(String name) {
         this.name = name;
    }

    public String getInstruction() {
         return instruction;
    }

    public void setInstruction(String instruction) {
         this.instruction = instruction;
    }

    public int getTimeToPrep() {
        return timeToPrep;
    }

    public void setTimeToPrep(int time) {
        this.timeToPrep = time;
    }

    public List<RecipieIngredient> getRecipieIngredients() {
    return recipieIngredients;
    }
    
    public void setRecipieIngredients(List<RecipieIngredient> recipieIngredients) {
        this.recipieIngredients = recipieIngredients;
    }
}
