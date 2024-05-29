package ManyToOne.java.ManyToOne.controller;

import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.service.FemmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Femme", description = "All resources referencing a femme controller.")
@RequestMapping("/Femme")
public class FemmeController {
    @Autowired
    public FemmeService femmeService;


    @Operation(summary = "Create a new femme", responses = {
            @ApiResponse(responseCode = "201", description = "Femme is created successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Femme.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Femme project not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/{MariId}")
    public ResponseEntity<Femme> createFemme(@RequestBody Femme femme, @PathVariable int MariId){
        return femmeService.createFemme(femme, MariId);
    }

    @Operation(summary = "Get all femmes", responses = {
            @ApiResponse(responseCode = "200", description = "Femme successfully retrieved.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Femme not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Femme>> getAllFemme(){
        return femmeService.getAllFemme();
    }


    @Operation(summary = "Get femme by mari id", responses = {
            @ApiResponse(responseCode = "200", description = "Femme successfully retrieved.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Femme.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Femme not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{mariId}")
    public ResponseEntity<List<Femme>> getAllFemmeByMariId(@PathVariable int mariId){
        return femmeService.getAllFemmeByIdMariId(mariId);
    }



    @Operation(summary = "Put femme by id", responses = {
            @ApiResponse(responseCode = "200", description = "Femme is Updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Femme.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Femme not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{femmeId}")
    public ResponseEntity<Femme> putFemme( @RequestBody Femme newfemme, @PathVariable int femmeId){
        return femmeService.putFemme( newfemme, femmeId);
    }

    @Operation(summary = "Delete femme by id", responses = {
            @ApiResponse(responseCode = "200", description = "SortSerie is Updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Femme.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Femme not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{femmeId}")
    public ResponseEntity<String> deleteFemme(@PathVariable int femmeId){
        return femmeService.deleteFemme(femmeId);

    }


}
