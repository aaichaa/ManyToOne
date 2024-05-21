package ManyToOne.java.ManyToOne.Femme;

import ManyToOne.java.ManyToOne.exceptions.httpexceptions.NotFoundException;
import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.model.Mari;
import ManyToOne.java.ManyToOne.repository.FemmeRepository;
import ManyToOne.java.ManyToOne.repository.MariRepository;
import ManyToOne.java.ManyToOne.service.FemmeServiceImpl;
import ManyToOne.java.ManyToOne.service.MariServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class FemmeExceptionTest {

    @InjectMocks
    MariServiceImpl mariServiceImplementation;

    @Mock
    MariRepository mariRepository;

    @InjectMocks
    FemmeServiceImpl femmeServiceImplementation;

    @Mock
    FemmeRepository femmeRepository;

    private Mari mari;
    private Femme femme;
    private Femme femme1;

    private Femme updateFemme;
    private List<Femme> femmeListe;

    @BeforeEach
    void setup() {
        mari = new Mari();
        mari.setId(2);
        mari.setNom("Diallo");
        mari.setPrenom("Ili");
        mari.setAge(28);
        mari.setNb_femmes(1);


        femme = new Femme();
        femme.setId(2);
        femme.setNom("Diallo");
        femme.setPrenom("Aissatou");
        femme.setAge(20);
        femme.setMari(mari);

        updateFemme = new Femme();
        updateFemme.setNom("updateName");
        updateFemme.setPrenom("updatePrenom");
        updateFemme.setAge(550);
        updateFemme.setMari(mari);

        femme1 = new Femme();
        femme1.setId(10);
        femme1.setNom("Balde");
        femme1.setPrenom("Mariama");
        femme1.setAge(30);
        femme1.setMari(mari);



        femmeListe = new ArrayList<>();
        femmeListe.add(femme);
        femmeListe.add(femme1);


    }


    @DisplayName("JUnit test for createFemme")
    @Test
    void testcreateFemme_thenThrowNotFoundException() {
        when(mariRepository.findById(mari.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            femmeServiceImplementation.createFemme(femme, mari.getId());
        });
        assertEquals("Le mari Id n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

    }


    @DisplayName("JUnit test for getAllFemmeByIdMariId")
    @Test
    void testgetAllFemmeByIdMariId_thenThrowNotFoundException() {
        when(mariRepository.findById(mari.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            femmeServiceImplementation.getAllFemmeByIdMariId(mari.getId());
        });
        assertEquals("Le mari Id n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

    }


    @DisplayName("JUnit test for putFemme")
    @Test
    void testputFemme_thenThrowNotFoundException() {
        when(femmeRepository.findById(femme.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            femmeServiceImplementation.putFemme(updateFemme,femme.getId());
        });
        assertEquals("La femme Id n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

    }


    @DisplayName("JUnit test for deleteFemme")
    @Test
    void testdeleteFemme_thenThrowNotFoundException() {
        when(femmeRepository.findById(femme.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            femmeServiceImplementation.deleteFemme(femme.getId());
        });
        assertEquals("La femme Id n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

    }







}
