package ch.wiss.kochbuch.controller;

import ch.wiss.kochbuch.dto.RecipieDTO;
import ch.wiss.kochbuch.model.Recipie;
import ch.wiss.kochbuch.repository.RecipieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ch.wiss.kochbuch.service.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/recipies")
public class RecipieController {

    private final RecipieService recipieService;
    

    @Autowired
    public RecipieController(RecipieService recipieService, RecipieRepository recipieRepository) {
        this.recipieService = recipieService;    
    }

    @GetMapping
    public List<Recipie> getAll() {
        return recipieService.getAllRecipies();
    }
    //TODO
    @GetMapping("/search")
    public List<Recipie> searchRecipies() {
        return recipieService.filterRecipies();
    }

    @GetMapping("/{id}")
    public RecipieDTO getRecipieWithIngredients(@PathVariable Long id) {
        return recipieService.getRecipieWithIngredients(id);
    }

    @PostMapping
    public Recipie create(@RequestBody RecipieDTO recipieDTO) {
        return recipieService.createRecipie(recipieDTO);
    }

    @PutMapping("/{id}")
    public Recipie update(@PathVariable Long id, @RequestBody RecipieDTO recipieDTO) {
        return recipieService.updateRecipie(id, recipieDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipieService.deleteRecipie(id);
    }
}