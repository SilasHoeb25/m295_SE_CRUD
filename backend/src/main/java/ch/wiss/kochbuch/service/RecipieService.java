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

    public List<Recipie> getAllRecipies() {
        return recipieRepository.findAll();
    }

    public Recipie getById(Long id) {
        return recipieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipie not found"));
    }


// DTO--> get by ID with ALL Ingredients ------------------------------------------------
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
/*    
@Transactional
    public Recipie updateRecipie(Long id, RecipieDTO dto) {
        Recipie existing = recipieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Recipie not found"));
    
        // Basisdaten aktualisieren
        existing.setName(dto.getName());
        existing.setInstruction(dto.getInstruction());
        existing.setTimeToPrep(dto.getTimeToPrep());
    
        // Vorherige Zutatenbeziehungen löschen (optional: nur die, die nicht mehr im DTO sind)
        recipieIngredientRepository.deleteByRecipie(existing);
    
        // Neue Zutatenbeziehungen anlegen
        List<RecipieIngredient> newIngredients = new ArrayList<>();
    
        for (IngredientAmountDTO ingDTO : dto.getIngredients()) {
            Ingredient ingredient = ingredientService.getOrCreateIngredientByName(ingDTO.getName());
            RecipieIngredient ri = new RecipieIngredient(existing, ingredient, ingDTO.getAmount());
            newIngredients.add(ri);
        }
    
        existing.setRecipieIngredients(newIngredients);
    
        return recipieRepository.save(existing);
    }
*/

/* 
@Transactional
public Recipie updateRecipie(Long id, RecipieDTO dto) {
    Recipie existing = recipieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Recipie not found"));

    // Rezept-Basisdaten aktualisieren
    existing.setName(dto.getName());
    existing.setInstruction(dto.getInstruction());
    existing.setTimeToPrep(dto.getTimeToPrep());

    // Bestehende Verknüpfungen holen
    List<RecipieIngredient> existingLinks = recipieIngredientRepository.findByRecipie(existing);

    // Neue Zutaten aus DTO vorbereiten
    List<RecipieIngredient> updatedLinks = new ArrayList<>();

    for (IngredientAmountDTO ingDTO : dto.getIngredients()) {
        Ingredient ingredient = ingredientService.getOrCreateIngredientByName(ingDTO.getName());

        // Prüfen ob diese Zutat schon existiert (für dieses Rezept)
        RecipieIngredient existingLink = existingLinks.stream()
            .filter(link -> link.getIngredient().getName().equalsIgnoreCase(ingredient.getName()))
            .findFirst()
            .orElse(null);

        if (existingLink != null) {
            // Menge aktualisieren (falls sie sich geändert hat)
            existingLink.setAmount(ingDTO.getAmount());
            updatedLinks.add(existingLink);
        } else {
            // Neue Verknüpfung anlegen
            RecipieIngredient newLink = new RecipieIngredient(existing, ingredient, ingDTO.getAmount());
            updatedLinks.add(newLink);
        }
    }

    // Verknüpfungen entfernen, die nicht mehr vorkommen
    for (RecipieIngredient oldLink : existingLinks) {
        boolean stillUsed = updatedLinks.stream()
            .anyMatch(link -> link.getIngredient().getName().equalsIgnoreCase(oldLink.getIngredient().getName()));
        if (!stillUsed) {
            recipieIngredientRepository.delete(oldLink);
        }
    }

    existing.setRecipieIngredients(updatedLinks);

    return recipieRepository.save(existing);
}
*/

@Transactional
public Recipie updateRecipie(Long id, RecipieDTO dto) {
    Recipie existing = recipieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Recipie not found"));

    // Basisdaten aktualisieren
    existing.setName(dto.getName());
    existing.setInstruction(dto.getInstruction());
    existing.setTimeToPrep(dto.getTimeToPrep());

    // Neue Zutatenliste vorbereiten
    List<RecipieIngredient> updatedLinks = new ArrayList<>();

    for (IngredientAmountDTO ingDTO : dto.getIngredients()) {
        Ingredient ingredient = ingredientService.getOrCreateIngredientByName(ingDTO.getName());

        RecipieIngredient newLink = new RecipieIngredient(existing, ingredient, ingDTO.getAmount());
        updatedLinks.add(newLink);
    }

    // Wichtig: zuerst alte Liste leeren, damit orphanRemoval greift
    existing.getRecipieIngredients().clear();

    // Dann neue Liste hinzufügen
    existing.getRecipieIngredients().addAll(updatedLinks);

    return recipieRepository.save(existing);
}


    
// POST RECIPIE WITH INGREDIENT AND AMOUNTS ----------------------------------------------------------
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

    public void deleteRecipie(Long id) {
        recipieRepository.deleteById(id);
    }
}