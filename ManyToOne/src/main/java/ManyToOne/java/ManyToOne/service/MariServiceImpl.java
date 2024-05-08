package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.model.Mari;
import ManyToOne.java.ManyToOne.repository.MariRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MariServiceImpl implements MariService{
    @Autowired
    public MariRepository mariRepository;
    @Override
    public ResponseEntity<Mari> createMari(Mari mari) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mariRepository.save(mari));
    }

    @Override
    public ResponseEntity<List<Mari>> getAllMari() {
        return ResponseEntity.ok(mariRepository.findAll());
    }

   /* @Override
    public Optional<Mari> getMari(int id) {
        Optional<Mari> optionalMari = mariRepository.findById(id);
        return optionalMari.orElseThrow(
                () -> new EntityNotFoundException("Aucun mari n'existe avec ce id")
        );
    }*/

    /*@Override
    public Optional<Mari> getMari(int id) {
        return Optional.empty();
    }

    @Override
    public void deleteMari(int id) {

    }

    @Override
    public Mari putMari(Mari mari, int id) {
        return null;
    }*/
}
