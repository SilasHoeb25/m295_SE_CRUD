package ch.wiss.kochbuch.config;

import ch.wiss.kochbuch.dto.RecipieDTO;
import ch.wiss.kochbuch.dto.IngredientAmountDTO;
import ch.wiss.kochbuch.model.*;
import ch.wiss.kochbuch.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private RecipieRepository recipieRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipieIngredientRepository recipieIngredientRepository;

    @PostConstruct
    public void loadData() {
        if (recipieRepository.count() > 0) {
            System.out.println("Datenbank ist nicht leer, DataLoader überspringt Import.");
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("data/recipies.json").getInputStream();
            List<RecipieDTO> recipieDTOs = mapper.readValue(inputStream, new TypeReference<>() {});

            for (RecipieDTO dto : recipieDTOs) {
                Recipie recipie = new Recipie(dto.getName(), dto.getInstruction(), dto.getTimeToPrep());
                recipie = recipieRepository.save(recipie);

                List<RecipieIngredient> links = new ArrayList<>();

                for (IngredientAmountDTO ingDTO : dto.getIngredients()) {
                    Ingredient ingredient = ingredientRepository.findByName(ingDTO.getName())
                        .orElseGet(() -> ingredientRepository.save(new Ingredient(ingDTO.getName())));

                    RecipieIngredient link = new RecipieIngredient(recipie, ingredient, ingDTO.getAmount());
                    links.add(link);
                }

                recipie.setRecipieIngredients(links);
                recipieRepository.save(recipie);
            }

            System.out.println("Beispielrezepte erfolgreich geladen.");
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der JSON-Datei: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
