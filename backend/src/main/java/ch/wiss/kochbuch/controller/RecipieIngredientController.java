package ch.wiss.kochbuch.controller;

import ch.wiss.kochbuch.model.RecipieIngredient;
import ch.wiss.kochbuch.repository.RecipieIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/recipie-ingredients")
public class RecipieIngredientController {

    @Autowired
    private RecipieIngredientRepository recipieIngredientRepository;

    @GetMapping
    public List<RecipieIngredient> getAll() {
        return recipieIngredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public RecipieIngredient getById(@PathVariable Long id) {
        return recipieIngredientRepository.findById(id).orElse(null);
    }

    @PostMapping
    public RecipieIngredient create(@RequestBody RecipieIngredient ri) {
        return recipieIngredientRepository.save(ri);
    }


    @PutMapping("/{id}")
    public RecipieIngredient update(@PathVariable Long id, @RequestBody RecipieIngredient updated) {
        return recipieIngredientRepository.findById(id)
                .map(ri -> {
                    ri.setAmount(updated.getAmount());
                    return recipieIngredientRepository.save(ri);
                }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipieIngredientRepository.deleteById(id);
    }
}