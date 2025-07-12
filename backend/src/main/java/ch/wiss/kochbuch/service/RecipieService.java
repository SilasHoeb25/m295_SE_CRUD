package ch.wiss.kochbuch.service;

import ch.wiss.kochbuch.dto.RecipieDTO;
import ch.wiss.kochbuch.dto.IngredientAmountDTO;
import ch.wiss.kochbuch.model.Ingredient;
import ch.wiss.kochbuch.model.Recipie;
import ch.wiss.kochbuch.model.RecipieIngredient;
import ch.wiss.kochbuch.repository.RecipieIngredientRepository;
import ch.wiss.kochbuch.repository.RecipieRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipieService {

    private final RecipieRepository recipieRepository;
    private final IngredientService ingredientService;
    private final RecipieIngredientRepository recipieIngredientRepository;


    public RecipieService(RecipieRepository recipieRepository, IngredientService ingredientService, RecipieIngredientRepository recipieIngredientRepository) {
        this.recipieRepository = recipieRepository;
        this.ingredientService = ingredientService;
        this.recipieIngredientRepository = recipieIngredientRepository;
    }

// GET ALL Recipies WITH IngredientAmount -----------------------------------------------------
    public List<RecipieDTO> getAllRecipieDTOs() {
        List<Recipie> recipies = recipieRepository.findAllByOrderByNameAsc();
    
        return recipies.stream().map(recipie -> {
            List<IngredientAmountDTO> ingredientDTOs = recipie.getRecipieIngredients()
                .stream()
                .map(ri -> new IngredientAmountDTO(
                    ri.getIngredient().getName(),
                    ri.getAmount()
                ))
                .collect(Collectors.toList());
    
            return new RecipieDTO(
                recipie.getId(),
                recipie.getName(),
                recipie.getInstruction(),
                recipie.getTimeToPrep(),
                ingredientDTOs
            );
        }).collect(Collectors.toList());
    }
    



//GET by ID with ALL IngredientAmount ------------------------------------------------
    public RecipieDTO getRecipieWithIngredients(Long id) {
        Recipie recipie = recipieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipie not found"));

        List<IngredientAmountDTO> ingredients = recipie.getRecipieIngredients()
            .stream()
            .map(ri -> new IngredientAmountDTO(
                ri.getIngredient().getName(),
                ri.getAmount()))
            .collect(Collectors.toList());

        return new RecipieDTO(
            recipie.getId(),
            recipie.getName(),
            recipie.getInstruction(),
            recipie.getTimeToPrep(),
            ingredients
        );
    }

//UPDATE Recipie --------------------------------------------------------------
@Transactional //@Transactional Annotation macht die Datenbanktransaktion mit COMMIT und bei error ein ROLLBACK
public Recipie updateRecipie(Long id, RecipieDTO dto) {
    Recipie existing = recipieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Recipie not found"));

    existing.setName(dto.getName());
    existing.setInstruction(dto.getInstruction());
    existing.setTimeToPrep(dto.getTimeToPrep());

    List<RecipieIngredient> updatedLinks = new ArrayList<>();

    for (IngredientAmountDTO ingDTO : dto.getIngredients()) {
        Ingredient ingredient = ingredientService.getOrCreateIngredientByName(ingDTO.getName());

        RecipieIngredient newLink = new RecipieIngredient(existing, ingredient, ingDTO.getAmount());
        updatedLinks.add(newLink);
    }

    existing.getRecipieIngredients().clear();
    existing.getRecipieIngredients().addAll(updatedLinks);

    return recipieRepository.save(existing);
}


    
// POST Recipie WITH IngredientAmount ----------------------------------------------------------
public Recipie createRecipie(RecipieDTO recipieDTO) {
    Recipie recipie = new Recipie();
    recipie.setName(recipieDTO.getName());
    recipie.setInstruction(recipieDTO.getInstruction());
    recipie.setTimeToPrep(recipieDTO.getTimeToPrep());


    List<RecipieIngredient> recipieIngredients = new ArrayList<>();

    for (IngredientAmountDTO ingredientAmountDTO : recipieDTO.getIngredients()) {
        Ingredient ingredient = ingredientService.getOrCreateIngredientByName(ingredientAmountDTO.getName());
        RecipieIngredient recipieIngredient = new RecipieIngredient(recipie, ingredient, ingredientAmountDTO.getAmount());
        recipieIngredients.add(recipieIngredient);
    }

    recipie.setRecipieIngredients(recipieIngredients);

    return recipieRepository.save(recipie);
}

//DELETE RECIPIE with RecipieIngredient Link (--> Cascade.ALL im Controller)------------------
@Transactional
public void deleteRecipie(Long id) {
    Recipie recipie = recipieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Recipie not found with id: " + id));

    // Optional, aber sauber: referenzierte Zutaten entfernen
    recipie.getRecipieIngredients().clear();

    // Jetzt wird durch orphanRemoval alles mitgelöscht
    recipieRepository.delete(recipie);
}
}