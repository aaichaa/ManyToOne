package ManyToOne.java.ManyToOne;

import ManyToOne.java.ManyToOne.model.Mari;
import ManyToOne.java.ManyToOne.repository.MariRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class MariServiceImplTest {

    private Mari mari;

    @InjectMocks
    MariServiceImpl mariServiceImplementation;

    @Mock
    MariRepository mariRepository;

    private List<Mari> mariList;

    private Mari mari2;



    @BeforeEach
    void setup() {
        mari = new Mari();
        mari.setId(2);
        mari.setNom("Diallo");
        mari.setPrenom("Ili");
        mari.setAge(28);
        mari.setNb_femmes(1);

        mari2 = new Mari();
        mari2.setId(4);
        mari2.setNom("Balde");
        mari2.setPrenom("Bibi");
        mari2.setAge(80);
        mari2.setNb_femmes(3);


        mariList = new ArrayList<>();
        mariList.add(mari);
        mariList.add(mari2);




    }

    @DisplayName("Junit test for create mari and return mari")
    @Test
    void testCreateMari_thenReturnMari() {

        when(mariRepository.save(mari)).thenReturn(mari);

        ResponseEntity<Mari> creatMari = mariServiceImplementation.createMari(mari);

        assertThat(creatMari).isNotNull();
        assertThat(creatMari.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.info(creatMari.getBody());
        assertThat(creatMari.getBody())
                .isNotNull()
                .hasFieldOrPropertyWithValue("id",mari.getId());

        verify(mariRepository, times(1)).save(mari);

    }

    @DisplayName("Junit test for get list mari and return list mari")
    @Test
    void testGetAllFemme_thenReturnAllFemme() {
        when(mariRepository.findAll()).thenReturn(mariList);

        ResponseEntity<List<Mari>> getAllMari= mariServiceImplementation.getAllMari();

        log.info(getAllMari.getBody());

        assertEquals("Balde", getAllMari.getBody().get(1).getNom());
        assertEquals(2,getAllMari.getBody().size());

        verify(mariRepository, times(1)).findAll();


    }


}


