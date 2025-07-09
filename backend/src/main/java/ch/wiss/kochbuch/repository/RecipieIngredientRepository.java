package ch.wiss.kochbuch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.kochbuch.model.Ingredient;
import ch.wiss.kochbuch.model.Recipie;
import ch.wiss.kochbuch.model.RecipieIngredient;
import jakarta.transaction.Transactional;

public interface RecipieIngredientRepository extends JpaRepository<RecipieIngredient, Long> {
    List<RecipieIngredient> findByRecipie(Recipie recipie);
    RecipieIngredient findByRecipieAndIngredient(Recipie recipie, Ingredient ingredient);
    
    @Transactional
    void deleteByRecipie(Recipie recipie);
}