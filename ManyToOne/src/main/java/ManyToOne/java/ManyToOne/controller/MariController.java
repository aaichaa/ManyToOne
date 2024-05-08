package ManyToOne.java.ManyToOne.controller;

import ManyToOne.java.ManyToOne.model.Femme;
import ManyToOne.java.ManyToOne.model.Mari;
import ManyToOne.java.ManyToOne.service.MariService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Mari", description = "All resources referencing a mari controller.")
@RequestMapping("/Mari")
public class MariController {
    @Autowired
    public MariService mariService;



    @Operation(summary = "Create a new mari", responses = {
            @ApiResponse(responseCode = "201", description = "Mari is created successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Mari.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Mari project not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<Mari> createMari(@RequestBody Mari mari){
        return mariService.createMari(mari);
    }


    @Operation(summary = "Get all maris", responses = {
            @ApiResponse(responseCode = "200", description = "Mari successfully retrieved.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Mari not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mari>>  getAllMari(){
        return mariService.getAllMari();
    }
     /* @GetMapping("/{id}")
    public ResponseEntity getMari(@PathVariable int id){
        try{
            Optional<Mari> mari = mariService.getMari(id);
          return  ResponseEntity.ok(mari);


        } catch(EntityNotFoundException exception){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, exception.getMessage()));


        }
*/



}
