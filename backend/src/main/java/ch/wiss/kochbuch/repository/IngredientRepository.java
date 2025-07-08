package ch.wiss.kochbuch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.kochbuch.model.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
}
