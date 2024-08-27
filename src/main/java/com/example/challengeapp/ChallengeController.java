package com.example.challengeapp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Challenges API", description = "This API enables you to manage the challenges in the DB")
@RequestMapping("/api/v1/challenges")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Challenges", tags = {"in development"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(type = "array", implementation = Challenge.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    private ResponseEntity<List<Challenge>> getAllChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new Challenge object", tags = {"in development"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Challenge.class), mediaType = "application/json", examples = {
                            @ExampleObject(
                                    name = "Add challenge",
                                    summary = "Challenge added successfully",
                                    value = "{\"id\": 1, \"month\": \"January\", \"description\": \"Skiing\"}"
                            )
                    })
            }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Examples of creating a new challenge",
            required = true,
            content = {
                    @Content(schema = @Schema(implementation = Challenge.class), examples = {
                            @ExampleObject(
                                    name = "A new challenge should be created",
                                    summary = "201 Example",
                                    value = "{\"month\": \"January\", \"description\": \"Skiing\"}"
                            )
                    })
            })
    private ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if(isChallengeAdded) {
            return new ResponseEntity<>("Challenge added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge not added successfully", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping( "/{month}")
    private ResponseEntity<Challenge> getChallengeByMonth(@PathVariable String month) {

        Challenge challenge = challengeService.getChallengeByMonth(month);
        if(challenge!=null)
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updatedChallenge);
        if(isChallengeUpdated) {
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Challenge not updated successfully", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id){
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);
        if(isChallengeDeleted) {
            return new ResponseEntity<>("Challenge deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Challenge not deleted successfully", HttpStatus.NOT_FOUND);
        }
    }
}
