package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.model.Femme;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FemmeService {
    public ResponseEntity<Femme> createFemme(Femme femme, int MariId);
  public ResponseEntity<List<Femme>> getAllFemme();
   public ResponseEntity<List<Femme>> getAllFemmeByIdMariId(int MariId);
    public ResponseEntity<String>  deleteFemme(int femmeId);
    public ResponseEntity<Femme> putFemme(Femme femme, int id);
}
