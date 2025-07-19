package ch.wiss.kochbuch.dto;

public class IngredientAmountDTO {
    private String name;
    private String amount;

//Constructor ------------------------------------------------------------------------
    public IngredientAmountDTO() {
    // Leerer Konstruktor für das initiale Laden von recipies.json
    }
    
    public IngredientAmountDTO(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }
// Getter & Setter -----------------------------------------------------------------
    public String getName() {
        return name; 
    }

    public String getAmount() {
         return amount; 
    }

    public void setName(String name) {
         this.name = name; 
    }

    public void setAmount(String amount) { 
        this.amount = amount; 
    }
}
