package ch.wiss.kochbuch.dto;

import java.util.List;

public class RecipieDTO {
    private Long id;
    private String name;
    private String instruction;
    private int timeToPrep;
    private List<IngredientAmountDTO> ingredients;

//Constructor ------------------------------------------------------------------------
    public RecipieDTO(Long id, String name, String instruction, int timeToPrep, List<IngredientAmountDTO> ingredients) {
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.timeToPrep = timeToPrep;
        this.ingredients = ingredients;
        
    }

// Getter & Setter -----------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public String getName() {
         return name;
    }

    public String getInstruction() {
        return instruction; 
    }

    public int getTimeToPrep() {
        return timeToPrep;
    }

    public List<IngredientAmountDTO> getIngredients() {
        return ingredients; 
    }

    public void setId(Long id) {
        this.id = id; 
    }

    public void setName(String name) {
        this.name = name; 
    }

    public void setInstruction(String instruction) {
         this.instruction = instruction;
    }

    public void setIngredients(List<IngredientAmountDTO> ingredients) {
        this.ingredients = ingredients; 
    }

    public void setTimeToPrep(int timeToPrep) {
        this.timeToPrep = timeToPrep;
    }
}
