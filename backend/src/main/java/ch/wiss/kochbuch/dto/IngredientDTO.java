package ch.wiss.kochbuch.dto;

public class IngredientDTO {
    private String ingredientName;
    private double amount;

    public IngredientDTO() {}

    public IngredientDTO(String ingredientName, double amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
