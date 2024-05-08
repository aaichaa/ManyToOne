package ManyToOne.java.ManyToOne.repository;

import ManyToOne.java.ManyToOne.model.Femme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FemmeRepository extends JpaRepository<Femme, Integer> {
    List<Femme> findAllByMariId(int mariId);
}
