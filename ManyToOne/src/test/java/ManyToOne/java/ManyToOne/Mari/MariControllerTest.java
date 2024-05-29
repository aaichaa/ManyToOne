package ManyToOne.java.ManyToOne.Mari;




import ManyToOne.java.ManyToOne.controller.FemmeController;
import ManyToOne.java.ManyToOne.controller.MariController;
import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.model.Mari;
import ManyToOne.java.ManyToOne.service.FemmeService;
import ManyToOne.java.ManyToOne.service.MariService;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ManyToOne.java.ManyToOne.exceptions.ErrorMessageService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Diallo iliassou
 * @version 0.0.1
 * @since 0.0.1
 */

@Log4j2
@WebMvcTest(value = MariController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})


public class MariControllerTest {


    /**
     * since @ControllerAdvice scans all controllers, so they all have a dependency to board controller
     * and here board controller is ErrorMessageController
     * and it has a dependency to ErrorMessageService so they will all have a dependency to ErrorMessageService
     */




    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MariService mariService;


    @MockBean
    private ErrorMessageService errorMessageService;

    private Mari mari;


    @Autowired
    private ObjectMapper objectMapper;
    private final String uri = "/Mari";

    private ResponseEntity<Mari> mariReturn;

    private String mariInJSON;

    private Mari mari1;

    List<Mari> mariList;




    private final int mariId = 3;
    @BeforeEach
    void setup(){


        mari=new Mari();
        mari.setId(2);
        mari.setNom("Diallo");
        mari.setPrenom("Ili");
        mari.setAge(28);
        mari.setNb_femmes(1);

        mari1 =new Mari();
        mari1.setId(2);
        mari1.setNom("Diallo");
        mari1.setPrenom("Ili");
        mari1.setAge(28);
        mari1.setNb_femmes(1);

        mariList = new ArrayList<>();
        mariList.add(mari);
        mariList.add(mari1);




        try {
            mariInJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mari);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
        }

        mariReturn = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mari);
    }

    @DisplayName("Junit test for create mari and return mari")
    @Test
    public void testCreateMari() throws Exception {

        ResponseEntity<Mari> test = ResponseEntity.status(HttpStatus.CREATED).body(mari);

        when(mariService.createMari(any(Mari.class))).thenReturn(test);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mariInJSON);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(mari.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(mari.getNom())
                ))
                .andExpect(jsonPath("$.prenom",
                        equalTo(mari.getPrenom())
                ))

                .andExpect(jsonPath("$.age",
                        equalTo(mari.getAge())
                ))
                .andExpect(jsonPath("$.nb_femmes",
                        equalTo(mari.getNb_femmes())
                ))
                .andDo(print());


    }
    @DisplayName("Junit test for get all mari and return mari")
    @Test
    public void testGetAllMari() throws Exception {
        when(mariService.getAllMari()).thenReturn(ResponseEntity.ok(mariList));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mariInJSON);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id",
                        equalTo(mariList.get(0).getId())
                ))
                .andExpect(jsonPath("$.[1].nom",
                        equalTo(mariList.get(1).getNom())
                ))
                .andExpect(jsonPath("$.[0].prenom",
                        equalTo(mariList.get(0).getPrenom())
                ))

                .andExpect(jsonPath("$.[1].age",
                        equalTo(mariList.get(1).getAge())
                ))
                .andExpect(jsonPath("$.[0].nb_femmes",
                        equalTo(mariList.get(0).getNb_femmes())
                ))
                .andDo(print());
    }























}
