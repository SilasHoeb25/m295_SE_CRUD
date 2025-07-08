package ch.wiss.kochbuch.service;

import ch.wiss.kochbuch.model.Ingredient;
import ch.wiss.kochbuch.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient getOrCreateIngredientByName(String name) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findByName(name);

        return optionalIngredient.orElseGet(() -> {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(name);
            return ingredientRepository.save(newIngredient);
        });
    }
}