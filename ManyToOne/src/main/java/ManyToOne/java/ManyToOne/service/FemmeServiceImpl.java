package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.exceptions.httpexceptions.NotAcceptableException;
import ManyToOne.java.ManyToOne.exceptions.httpexceptions.NotFoundException;
import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.model.Mari;
import ManyToOne.java.ManyToOne.repository.FemmeRepository;
import ManyToOne.java.ManyToOne.repository.MariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FemmeServiceImpl implements FemmeService{
    @Autowired
    public FemmeRepository femmeRepository;
    @Autowired
    public MariRepository mariRepository;
    @Override
    public ResponseEntity<Femme> createFemme(Femme femme, int mariId) {
        Optional<Mari> optionalMari = getId(mariId);;
       if (optionalMari.isPresent()){
           femme.setMari(optionalMari.get());
           Femme femmeSave = femmeRepository.save(femme);
           return ResponseEntity.ok(femmeSave);
       }  else {
           throw new NotFoundException("Le mari Id n'existe pas");
       }

    }
  @Override
    public ResponseEntity<List<Femme>> getAllFemme() {
      List<Femme> listFemme = femmeRepository.findAll();
      return ResponseEntity.ok(listFemme);
    }

    @Override
    public ResponseEntity<List<Femme>> getAllFemmeByIdMariId(int mariId) {
        Optional<Mari> optionalMari = getId(mariId);
        if (optionalMari.isPresent()) {
            List<Femme> femmeFind = femmeRepository.findAllByMariId(mariId);
            return ResponseEntity.ok(femmeFind);
        } else {
            throw new NotFoundException("Le mari Id n'existe pas");
        }
    }
    private Optional<Mari> getId(int mariId){
        Optional<Mari> optionalMari = mariRepository.findById(mariId);
        return optionalMari;
    }

  @Override
    public ResponseEntity<String> deleteFemme(int femmeId) {
       Optional<Femme> optionalFemme = getFemmeId(femmeId);
        if(optionalFemme.isPresent()) {
            femmeRepository.deleteById(femmeId);
           return ResponseEntity.ok("femme supprimée avec succès");
        }else {
                throw new NotFoundException("La femme Id n'existe pas");
            }


    }
    private Optional<Femme> getFemmeId(int femmeId){
        Optional<Femme> optionalFemme = femmeRepository.findById(femmeId);
        return optionalFemme;
    }



    @Override
    public ResponseEntity<Femme> putFemme(Femme newfemme, int femmeId) {
        Optional<Femme> optionalFemme = getFemmeId(femmeId);

        if (optionalFemme.isPresent()){
            Femme femme = optionalFemme.get();
            femme.setNom(newfemme.getNom());
            femme.setPrenom(newfemme.getPrenom());
            femme.setAge(newfemme.getAge());
            Femme femmePut = femmeRepository.save(femme);
            return ResponseEntity.ok(femmePut);

        }
        else {
            throw new NotFoundException("La femme Id n'existe pas");
        }
    }

}
