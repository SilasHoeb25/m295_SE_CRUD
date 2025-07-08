package ch.wiss.kochbuch.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ch.wiss.kochbuch.model.Recipie;

public interface RecipieRepository extends JpaRepository<Recipie, Long> {
  List<Recipie> findByNameContaining(String name);
}