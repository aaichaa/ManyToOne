package ManyToOne.java.ManyToOne;

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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class FemmeServiceImplTest {


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


    @DisplayName("Junit test for create femme and return femme")
    @Test
    void testCreatefemmeByMariId_thenReturnFemme() {


        when(mariRepository.findById(mari.getId())).thenReturn(Optional.of(mari));

        when(femmeRepository.save(femme)).thenReturn(femme);

        ResponseEntity<Femme> createFemmes = femmeServiceImplementation.createFemme(femme,mari.getId());

        assertThat(createFemmes).isNotNull();
        assertThat(createFemmes.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.info(createFemmes.getBody());
        assertThat(createFemmes.getBody())
                .isNotNull()
                .hasFieldOrPropertyWithValue("id",mari.getId());

        verify(femmeRepository, times(1)).save(femme);


    }

    @DisplayName("Junit test for get list femme and return list femmme")
    @Test
    void testGetAllFemme_thenReturnAllFemme(){
        when(femmeRepository.findAll()).thenReturn(femmeListe);

        ResponseEntity<List<Femme>> getAllFemmes = femmeServiceImplementation.getAllFemme();

        log.info(getAllFemmes.getBody());

        assertEquals("Diallo", getAllFemmes.getBody().get(0).getNom());
        assertEquals(2,getAllFemmes.getBody().size());

        verify(femmeRepository, times(1)).findAll();


    }

    @DisplayName("Junit test for get femme by mari id and return list femme ")
    @Test
    void testGetAllFemmetByIdMari_thenReturnFemme(){

        when(mariRepository.findById(mari.getId())).thenReturn(Optional.of(mari));
        when(femmeRepository.findAllByMariId(mari.getId())).thenReturn(femmeListe);


        ResponseEntity<List<Femme>> getAllFemmeByIdMariId = femmeServiceImplementation.getAllFemmeByIdMariId(mari.getId());
        log.info(getAllFemmeByIdMariId.getBody());
        assertThat(getAllFemmeByIdMariId).isNotNull();
        assertThat(getAllFemmeByIdMariId.getStatusCode()).isEqualTo(HttpStatus.OK);




    }











    }
