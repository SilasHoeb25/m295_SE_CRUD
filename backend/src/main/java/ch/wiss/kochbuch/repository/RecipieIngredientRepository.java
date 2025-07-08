package ch.wiss.kochbuch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.kochbuch.model.Recipie;
import ch.wiss.kochbuch.model.RecipieIngredient;

public interface RecipieIngredientRepository extends JpaRepository<RecipieIngredient, Long> {
void deleteByRecipie(Recipie recipie);
}