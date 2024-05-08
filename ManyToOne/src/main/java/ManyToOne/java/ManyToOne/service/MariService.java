package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.model.Mari;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MariService {
    public ResponseEntity<Mari> createMari(Mari mari);
    public ResponseEntity<List<Mari>> getAllMari();
   /* public Optional<Mari> getMari(int id);
    public void deleteMari (int id);
    public Mari putMari(Mari mari, int id); */
 //   public Optional<Mari> getMari(int id);





}
