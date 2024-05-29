package ManyToOne.java.ManyToOne.Femme;

import ManyToOne.java.ManyToOne.controller.FemmeController;
import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.model.Mari;
import ManyToOne.java.ManyToOne.service.FemmeService;
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
import org.springframework.beans.BeanUtils;
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
@WebMvcTest(value = FemmeController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})


public class FemmeControllerTest {


        /**
         * since @ControllerAdvice scans all controllers, so they all have a dependency to board controller
         * and here board controller is ErrorMessageController
         * and it has a dependency to ErrorMessageService so they will all have a dependency to ErrorMessageService
         */




        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private FemmeService femmeService;


        @MockBean
        private ErrorMessageService errorMessageService;

        private Femme femme;

        private Mari mari;

        @Autowired
        private ObjectMapper objectMapper;
        private final String uri = "/Femme";

        private ResponseEntity<Femme> femmeReturn;

        private String femmeInJSON;

        private Femme femme1;

        List<Femme> femmeList;
        private final int mariId = 3;

        private Mari mari3;

        private Femme femme5;

        private Femme updateFemme;



    @BeforeEach
    void setup(){
            mari=new Mari();
            mari.setId(2);
            mari.setNom("Diallo");
            mari.setPrenom("Ili");
            mari.setAge(28);
            mari.setNb_femmes(1);

            mari3 =new Mari();
            mari3.setId(6);
            mari3.setNom("Diallo");
            mari3.setPrenom("Ili");
            mari3.setAge(28);
            mari3.setNb_femmes(1);


            femme = new Femme();
            femme.setId(2);
            femme.setNom("Diallo");
            femme.setPrenom("Aissatou");
            femme.setAge(20);
            femme.setMari(mari3);

            femme5 = new Femme();
            femme5.setId(1);
            femme5.setNom("Diallo");
            femme5.setPrenom("Aissatou");
            femme5.setAge(20);
            femme5.setMari(mari3);

            femme1 = new Femme();
            femme1.setId(4);
            femme1.setNom("Diallo");
            femme1.setPrenom("Aissatou");
            femme1.setAge(20);
            femme1.setMari(mari);

            femmeList = new ArrayList<>();
            femmeList.add(femme);
            femmeList.add(femme1);
            femmeList.add(femme5);

            updateFemme = new Femme();
            updateFemme.setNom("updateName");
            updateFemme.setPrenom("updatePrenom");
            updateFemme.setAge(550);
            updateFemme.setMari(mari);






        try {
                        femmeInJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(femme);
                } catch (JsonProcessingException e) {
                        log.info(e.getMessage());
                }

                femmeReturn = ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(femme);
            }

        @DisplayName("Junit test for create femme and return femme")
        @Test
        public void testCreateFemme() throws Exception {

                ResponseEntity<Femme> test = ResponseEntity.status(HttpStatus.CREATED).body(femme);

                when(femmeService.createFemme(any(Femme.class),eq(mariId))).thenReturn(test);


                String uriUpdate = uri.concat("/{MariId}");

                RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uriUpdate, mariId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(femmeInJSON);


                ResultActions response = mockMvc.perform(requestBuilder);

                response

                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.id",
                                equalTo(femme.getId())
                        ))
                        .andExpect(jsonPath("$.nom",
                                equalTo(femme.getNom())
                        ))
                        .andExpect(jsonPath("$.prenom",
                                equalTo(femme.getPrenom())
                        ))

                        .andExpect(jsonPath("$.age",
                                equalTo(femme.getAge())
                        ))
                        .andExpect(jsonPath("$.mari.id",
                                equalTo(femme.getMari().getId())
                        ))
                        .andDo(print());

        }

        @DisplayName("Junit test for get all femme and return femme")
        @Test
        public void testGetAllFemme() throws Exception {

            when(femmeService.getAllFemme()).thenReturn(ResponseEntity.ok(femmeList));

                RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(femmeInJSON);


                ResultActions response = mockMvc.perform(requestBuilder);

                response

                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.[1].id",
                                equalTo(femmeList.get(1).getId())
                        ))
                        .andExpect(jsonPath("$.[1].nom",
                                equalTo(femmeList.get(1).getNom())
                        ))
                        .andExpect(jsonPath("$.[0].prenom",
                                equalTo(femmeList.get(0).getPrenom())
                        ))

                        .andExpect(jsonPath("$.[1].age",
                                equalTo(femmeList.get(1).getAge())
                        ))
                        .andExpect(jsonPath("$.[0].mari.id",
                                equalTo(femmeList.get(0).getMari().getId())
                        ))
                        .andDo(print());

        }

        @DisplayName("Junit test for get femme by mari id and return femme")
        @Test
        public void testGetFemmeByIdMariId() throws Exception {
                when(femmeService.getAllFemmeByIdMariId(mari3.getId())).thenReturn(ResponseEntity.ok(femmeList));

                String uriUpdate = uri.concat("/{MariId}");

                RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uriUpdate, mari3.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(femmeInJSON);


                ResultActions response = mockMvc.perform(requestBuilder);

                response

                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.[1].id",
                                equalTo(femmeList.get(1).getId())
                        ))
                        .andExpect(jsonPath("$.[1].nom",
                                equalTo(femmeList.get(1).getNom())
                        ))
                        .andExpect(jsonPath("$.[0].prenom",
                                equalTo(femmeList.get(0).getPrenom())
                        ))

                        .andExpect(jsonPath("$.[1].age",
                                equalTo(femmeList.get(1).getAge())
                        ))
                        .andExpect(jsonPath("$.[1].mari.id",
                                equalTo(femmeList.get(1).getMari().getId())
                        ))
                        .andDo(print());


        }


    @DisplayName("Junit test for update femme by id")
    @Test
    public void testUpdateFemme() throws Exception {
        femme.setNom(updateFemme.getNom());
        femme.setPrenom(updateFemme.getPrenom());
        femme.setAge(updateFemme.getAge());
        femme.setMari(updateFemme.getMari());

        when(femmeService.putFemme(updateFemme,femme.getId())).thenReturn(ResponseEntity.ok(femme));

        String updateUri = uri + "/{femmeId}";

        String femmeJson = objectMapper.writeValueAsString(updateFemme);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(updateUri, femme.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(femmeJson);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(femme.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(femme.getNom())
                ))
                .andExpect(jsonPath("$.prenom",
                        equalTo(femme.getPrenom())
                ))

                .andExpect(jsonPath("$.age",
                        equalTo(femme.getAge())
                ))
                .andExpect(jsonPath("$.mari.id",
                        equalTo(femme.getMari().getId())
                ))
                .andDo(print());



    }



    @DisplayName("Junit test for update2 femme by id")
    @Test
    public void testUpdate2Femme() throws Exception {

        BeanUtils.copyProperties(updateFemme, femme, "id");

        when(femmeService.putFemme(updateFemme,femme.getId())).thenReturn(ResponseEntity.ok(femme));

        String updateUri = uri + "/{femmeId}";

        String femmeJson = objectMapper.writeValueAsString(updateFemme);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(updateUri, femme.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(femmeJson);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(femme.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(femme.getNom())
                ))
                .andExpect(jsonPath("$.prenom",
                        equalTo(femme.getPrenom())
                ))

                .andExpect(jsonPath("$.age",
                        equalTo(femme.getAge())
                ))
                .andExpect(jsonPath("$.mari.id",
                        equalTo(femme.getMari().getId())
                ))
                .andDo(print());



    }







    @DisplayName("Junit test for delete femme by id")
    @Test
    public void testDeleteFemme() throws Exception {
        String text = "Femme deleted successfuly";
        when(femmeService.deleteFemme(femme.getId())).thenReturn(ResponseEntity.ok(text));

        String uriUpdate = uri.concat("/{FemmeId}");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uriUpdate, femme.getId());

        ResultActions response = mockMvc.perform(requestBuilder);
            response
                    .andExpect(status().isOk())
                    .andExpect(content().string(text))
                    .andDo(print());





    }





        }
