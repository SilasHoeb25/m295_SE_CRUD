package ch.wiss.kochbuch.dto;

import java.util.List;

public class RecipieCreateDTO {
    private String name;
    private String instruction;
    private int timeToPrep;
    private List<IngredientAmountDTO> ingredients;

    public RecipieCreateDTO() {}

    public RecipieCreateDTO(String name, String instruction, int timeToPrep, List<IngredientAmountDTO> ingredients) {
        this.name = name;
        this.instruction = instruction;
        this.timeToPrep = timeToPrep;
        this.ingredients = ingredients;
    }

    public String getName() { return name; }
    public String getInstruction() { return instruction; }
    public int getTimeToPrep() { return timeToPrep; }
    public List<IngredientAmountDTO> getIngredients() { return ingredients; }

    public void setName(String name) { this.name = name; }
    public void setInstruction(String instruction) { this.instruction = instruction; }
    public void setTimeToPrep(int timeToPrep) { this.timeToPrep = timeToPrep; }
    public void setIngredients(List<IngredientAmountDTO> ingredients) { this.ingredients = ingredients; }
}